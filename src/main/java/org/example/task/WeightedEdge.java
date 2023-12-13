package org.example.task;

public class WeightedEdge extends Edge {

    private final double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v, weight);
        this.weight = weight;
    }

    @Override
    public WeightedEdge reversed() {
        return new WeightedEdge(getV(), getU(), 1 / weight);
    }

    @Override
    public String toString() {
        return getU() + " " + weight + "> " + getV();
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
