package org.example.task;


public class InputStringParser {
    public static ConversionInfo parseQuestion(String input) {
        String[] parts = input.split("\\s+");
        double coefficient = Double.parseDouble(parts[0]);

        String fromUnit = parts[1].toLowerCase();
        String toUnit = parts[4].toLowerCase();

        return new ConversionInfo(fromUnit, toUnit, coefficient);
    }

    public static ConversionInfo parseString(String input) {
        String[] parts = input.split("\\s+");

        double value1 = Double.parseDouble(parts[0]);
        double value2 = Double.parseDouble(parts[3]);

        String unit1 = parts[1].toLowerCase();
        String unit2 = parts[4].toLowerCase();

        String fromUnit;
        String toUnit;
        double coefficient;

        if (value1 > value2) {
            fromUnit = unit1;
            toUnit = unit2;
            coefficient = value1 / value2;
        } else {
            fromUnit = unit2;
            toUnit = unit1;
            coefficient = value2 / value1;
        }

        return new ConversionInfo(fromUnit, toUnit, coefficient);
    }

}
