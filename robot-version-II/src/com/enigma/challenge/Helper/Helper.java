package com.enigma.challenge.Helper;

import java.util.Scanner;

public class Helper {
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input harus Angka...!\n");
            return false;
        }
    }

    public static boolean isXY(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isDirection(String direction) {
        return direction.equalsIgnoreCase("e") || direction.equalsIgnoreCase("w")
                || direction.equalsIgnoreCase("n") || direction.equalsIgnoreCase("s");
    }

    public static boolean isOdd(int number) {
        return number % 2 == 1;
    }

    public static int inputInteger(String string) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.printf("%s: ", string);
            String input = scanner.nextLine();

            if (isInteger(input)) {
                if (Integer.parseInt(input) > 2 && isOdd(Integer.parseInt(input))) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("Input harus lebih besar dari 2 dan ganjil...!\n");
                }
            }
        } while (true);
    }

    public static String inputString(String string) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("%s: ", string);
            String input = scanner.nextLine();

            if (input.isBlank() || input.isEmpty()) {
                System.out.println("Koordinat tidak valid...!\n");
                continue;
            }

            input = input.toLowerCase();
            return input;
        }
    }

    public static boolean doAgain(String string) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("\n%s: ", string);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Pilihan tidak valid...!");
            }
        }
    }
}
