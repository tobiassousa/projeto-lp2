package com.example;

import java.util.Scanner;

public class ZerinhoOuUm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();

        if (a != b && a != c) {
            System.out.println("A");
        } else if (b != a && b != c) {
            System.out.println("B");
        } else if (c != a && c != b) {
            System.out.println("C");
        } else {
            System.out.println("*");
        }
    }
}
