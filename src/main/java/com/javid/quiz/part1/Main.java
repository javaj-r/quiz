package com.javid.quiz.part1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var sample = "This is a test I designed to test a candidate.";
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Please enter sample input or just press Enter for default sample\n");
            System.out.print("input: ");

            var input = scanner.nextLine();
            if (!input.isEmpty()) {
                sample = input;
            }

            var output = new WordCounter().getWordMap(sample).toString();
            System.out.println("output: " + output);
        }
    }

}
