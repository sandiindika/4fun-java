package com.enigma.helper;

import java.util.Scanner;

public class Helper {
    public static boolean isName(String string) {
        string = string.replaceAll("[^a-zA-Z]", "");
        string = string.replaceAll("\\s{2,}", " ");
        return string.split("").length >= 3 && string.split("").length <= 20;
    }

    public static boolean isMajor(String string) {
        return string.split("").length <= 10;
    }

    public static boolean isAge(String string) {
        try {
            int age = Integer.parseInt(string);
            return age >= 17;
        } catch (NumberFormatException e) {
            System.out.println("\nInput harus Angka...!");
            return false;
        }
    }

    public static String inputString(String string) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("%s : ", string);
            String input = scanner.nextLine();

            if (input.isEmpty() || input.isBlank()) {
                continue;
            }
            return input;
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("\nInput harus Angka...!\n");
            return false;
        }
    }
}
