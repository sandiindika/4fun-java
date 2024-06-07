package com.enigmacamp.store.services.validator;

import java.util.Scanner;

import static java.lang.System.*;

public class Validator {
    public static String inputStr(String string) {
        Scanner scanner = new Scanner(in);

        while (true) {
            out.printf("%s: ", string);
            String input = scanner.nextLine();

            if (input.isEmpty() || input.isBlank()) {
                continue;
            }
            return input;
        }
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            out.println("-".repeat(38));
            out.println("\nPlease enter a valid number");
            return false;
        }
    }

    static String stringCleaning(String string) {
        string = string.replaceAll("[^a-zA-Z]", "");
        return string.replaceAll("\\s{2,}", " ");
    }

    public static boolean nameValidator(String string) {
        string = stringCleaning(string);
        return string.split("").length >= 3 && string.split("").length <= 50;
    }

    public static boolean brandValidator(String string) {
        string = stringCleaning(string);
        return string.split("").length >= 3 && string.split("").length <= 30;
    }

    public static boolean priceValidator(String input) {
        try {
            int price = Integer.parseInt(input);
            if (price <= 0) {
                out.println("\n" + "-".repeat(38));
                out.println("Price must be greater than 0");
                out.println("-".repeat(38));
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            out.println("\n" + "-".repeat(38));
            out.println("Please enter a valid number");
            out.println("-".repeat(38));
            return false;
        }
    }
}
