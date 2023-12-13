package org.example;

import org.example.task.WeightedGraph;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.example.task.InputHandler.processInput;
import static org.example.task.InputHandler.validateInputString;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите равенство величин в формате a V = b W:");
        Scanner scanner = new Scanner(System.in);
        String input;

        Set<String> vertices = new HashSet<>();
        WeightedGraph<String> graph = new WeightedGraph<>();

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();

            if (!validateInputString(input)) {
                System.out.println("Некорректный ввод данных");
                continue;
            }

            processInput(input, vertices, graph);
        }
    }
}