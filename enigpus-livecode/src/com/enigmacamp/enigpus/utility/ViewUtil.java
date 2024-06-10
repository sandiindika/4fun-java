package com.enigmacamp.enigpus.utility;

import static java.lang.System.out;

public class ViewUtil {
    public static void divider(boolean newLine) {
        if (newLine) {
            out.println("\n" + "-".repeat(40));
        } else {
            out.println("-".repeat(40));
        }
    }
}
