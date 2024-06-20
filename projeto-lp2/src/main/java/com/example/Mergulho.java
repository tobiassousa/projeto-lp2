package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class Mergulho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }
        scanner.close();

        Arrays.sort(scores);
        double sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum += scores[i];
        }
        double average = sum / (n - 2);
        System.out.println(average);
    }
}
