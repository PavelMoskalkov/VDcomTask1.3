package org.example.task;

import java.util.*;

public class WeightedGraph<V> extends Graph<V, WeightedEdge> {
    public WeightedGraph() {
    }

    public WeightedGraph(List<V> vertices) {
        super(vertices);
    }

    public void addEdge(WeightedEdge edge) {
        edges.get(edge.getU()).add(edge);
        edges.get(edge.getV()).add(edge.reversed());
    }

    public void addEdge(int u, int v, double weight) {
        addEdge(new WeightedEdge(u, v, weight));
    }

    public void addEdge(V first, V second, double weight) {
        addEdge(indexOf(first), indexOf(second), weight);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(edgesOf(i).stream()
                    .map(we -> "(" + vertexAt(we.getV()) + ", " + we.getWeight() +
                            ")").toArray()));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public static double totalWeight(List<WeightedEdge> path) {
        return path.stream().mapToDouble(WeightedEdge::getWeight).reduce(1, (a, b) -> a * b);
    }

    public static final class DijkstraNode implements Comparable<DijkstraNode> {
        public final int vertex;
        public final double distance;

        public DijkstraNode(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(DijkstraNode other) {
            Double mine = distance;
            Double theirs = other.distance;

            return mine.compareTo(theirs);
        }
    }

    public static final class DijkstraResult {
        public final double[] distances;
        public final Map<Integer, WeightedEdge> pathMap;

        public DijkstraResult(double[] distances, Map<Integer, WeightedEdge>
                pathMap) {
            this.distances = distances;
            this.pathMap = pathMap;
        }
    }

    public static List<WeightedEdge> pathMapToPath(
            int start,
            int end,
            Map<Integer, WeightedEdge> pathMap) throws Exception {

        if (pathMap.size() == 0) {
            return List.of();
        }

        LinkedList<WeightedEdge> path = new LinkedList<>();
        WeightedEdge edge = pathMap.get(end);
        path.add(edge);
        if (edge == null) {
            throw new Exception();
        }
        while (edge.getU() != start) {
            edge = pathMap.get(edge.getU());
            path.add(edge);
        }
        Collections.reverse(path);

        return path;
    }

    public DijkstraResult dijkstra(V root) {
        int first = indexOf(root);

        double[] distances = new double[getVertexCount()];
        distances[first] = 0;
        boolean[] visited = new boolean[getVertexCount()];
        visited[first] = true;

        HashMap<Integer, WeightedEdge> pathMap = new HashMap<>();
        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>();
        pq.offer(new DijkstraNode(first, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            double distU = distances[u];

            for (WeightedEdge we : edgesOf(u)) {

                double distV = distances[we.getV()];
                double pathWeight = we.getWeight() + distU;

                if (!visited[we.getV()] || (distV > pathWeight)) {
                    visited[we.getV()] = true;
                    distances[we.getV()] = pathWeight;

                    pathMap.put(we.getV(), we);
                    pq.offer(new DijkstraNode(we.getV(), pathWeight));
                }
            }
        }
        return new DijkstraResult(distances, pathMap);
    }

    public void printWeightedPath(ConversionInfo infoQuestion, List<WeightedEdge> path) {
        System.out.println(infoQuestion.getCoefficient() + " " + infoQuestion.getFromUnit() + " = " + totalWeight(path) * infoQuestion.getCoefficient() + " " + infoQuestion.getToUnit());
    }
}