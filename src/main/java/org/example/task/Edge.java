package org.example.task;

public class Edge {
    private final int u;
    private final int v;

    private final double weight;

    public Edge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public Edge reversed() {
        return new Edge(v, u, 1 / weight);
    }

    @Override
    public String toString() {
        return u + " -> " + v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public double getWeight() {
        return weight;
    }
}
