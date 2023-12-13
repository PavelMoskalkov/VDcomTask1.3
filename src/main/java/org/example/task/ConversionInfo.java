package org.example.task;

public class ConversionInfo {
    private final String fromUnit;
    private final String toUnit;
    private final double coefficient;

    public ConversionInfo(String fromUnit, String toUnit, double coefficient) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.coefficient = coefficient;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public double getCoefficient() {
        return coefficient;
    }

}
