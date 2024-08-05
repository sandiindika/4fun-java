package com.enigma.basic;

public class Cryptography {

    public static String encrypt(String text, int s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char charAt = text.charAt(i);

            if (Character.isUpperCase(charAt)) {
                char ch = (char) (((int)charAt + s - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(charAt)) {
                char ch = (char) (((int)charAt + s - 97) % 26 + 97);
                result.append(ch);
            } else {
                result.append(charAt);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int s) {
        return encrypt(text, -s);
    }

    public static void main(String[] args) {
        String text = "ATTACKATONCE";
        int s = 4;

        System.out.println("Original Text: " + text);
        System.out.println("Shift: " + s);
        System.out.println("Encrypted Text: " + encrypt(text, s));
        System.out.println("Decrypted Text: " + decrypt(encrypt(text, s), s));
    }
}
