package com.enigmacamp.enigpus.handler;

import com.enigmacamp.enigpus.utility.ViewUtil;

import java.util.Scanner;

import static java.lang.System.out;

public class InputValidator {
    public static String isValidString(Scanner scanner, String string) {
        while (true) {
            out.printf("%s: ", string);
            String input = scanner.nextLine();

            if (input.isBlank() || input.isEmpty()) {
                continue;
            }
            return input;
        }
    }

    public static boolean isValidNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            ViewUtil.divider(true);
            out.println("Please enter a valid number");
            ViewUtil.divider(false);
            return false;
        }
    }

    public static boolean stringValidator(String title) {
        title = title.replaceAll("[^a-zA-Z0-9]", "");
        return title.split("").length >= 3 && title.length() <= 50;
    }


    public static boolean yearValidator(String input) {
        try {
            int year = Integer.parseInt(input);
            if (year < 1000 || year > 2024) {
                ViewUtil.divider(true);
                out.println("Please enter a valid year");
                ViewUtil.divider(false);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            ViewUtil.divider(true);
            out.println("Please enter a valid year");
            ViewUtil.divider(false);
            return false;
        }
    }
}
