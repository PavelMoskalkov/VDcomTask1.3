package org.example.task;

import java.util.List;
import java.util.Set;

import static org.example.task.InputStringParser.parseQuestion;
import static org.example.task.InputStringParser.parseString;
import static org.example.task.WeightedGraph.pathMapToPath;

public class InputHandler {
    public static void processInput(String input, Set<String> vertices, WeightedGraph<String> graph) {
        if (input.contains("?")) {
            handleQuestionInput(input, graph);
        } else {
            handleRegularInput(input, vertices, graph);
        }
    }

    public static void handleQuestionInput(String input, WeightedGraph<String> graph) {
        ConversionInfo infoQuestion = parseQuestion(input);
        try {
            WeightedGraph.DijkstraResult dijkstraResult = graph.dijkstra(infoQuestion.getToUnit());
            List<WeightedEdge> path;
            path = pathMapToPath(graph.indexOf(infoQuestion.getToUnit()), graph.indexOf(infoQuestion.getFromUnit()), dijkstraResult.pathMap);
            graph.printWeightedPath(infoQuestion, path);
        } catch (Exception e) {
            System.out.println("Conversion not possible.");
        }
    }

    public static void handleRegularInput(String input, Set<String> vertices, WeightedGraph<String> graph) {
        ConversionInfo info = parseString(input);

        addVertexIfNotExists(vertices, graph, info.getFromUnit());
        addVertexIfNotExists(vertices, graph, info.getToUnit());

        vertices.add(info.getFromUnit());
        vertices.add(info.getToUnit());

        graph.addEdge(info.getFromUnit(), info.getToUnit(), info.getCoefficient());
    }

    public static void addVertexIfNotExists(Set<String> vertices, WeightedGraph<String> graph, String vertex) {
        if (!vertices.contains(vertex)) {
            graph.addVertex(vertex);
        }
    }

    public static boolean validateInputString(String input) {
        if (input.isEmpty()) {
            return false;
        }

        String pattern = "^\\d+(\\.\\d+)?\\s+\\w+\\s+=\\s+(\\d+(\\.\\d+)?|\\?)\\s+\\w+$";

        return input.matches(pattern);
    }
}
