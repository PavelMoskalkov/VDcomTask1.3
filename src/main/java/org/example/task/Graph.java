package org.example.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Graph<V, E extends Edge> {
    private final ArrayList<V> vertices = new ArrayList<>();
    protected ArrayList<ArrayList<E>> edges = new ArrayList<>();

    public Graph() {
    }

    public Graph(List<V> vertices) {
        this.vertices.addAll(vertices);
        for (V vertex : vertices) {
            edges.add(new ArrayList<>());
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public void addVertex(V vertex) {
        vertices.add(vertex);
        edges.add(new ArrayList<>());
    }

    public V vertexAt(int index) {
        return vertices.get(index);
    }

    public int indexOf(V vertex) {
        return vertices.indexOf(vertex);
    }

    public List<V> neighborsOf(int index) {
        return edges.get(index).stream().map(edge -> vertexAt(edge.getV())).collect(Collectors.toList());
    }

    public List<E> edgesOf(int index) {
        return edges.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(neighborsOf(i).toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
