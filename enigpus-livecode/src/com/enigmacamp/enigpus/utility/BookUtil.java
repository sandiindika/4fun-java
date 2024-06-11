package com.enigmacamp.enigpus.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookUtil {
    public static void writeToFile(List<String> books, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String book : books) {
                writer.write(book);
                writer.newLine();
            }
            System.out.println("| Books have been written to " + fileName);
            ViewUtil.divider(false);
        } catch (IOException e) {
            ViewUtil.divider(true);
            System.out.println("| An error occurred while writing to file");
            ViewUtil.divider(false);
        }
    }

    public static List<String> readFile(String fileName) throws IOException {
        List<String> books = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String book;
            while ((book = br.readLine()) != null) {
                books.add(book);
            }
        }
        return books;
    }

    public static int getLastPointItem(String fileName) {
        int lastPoint = 0;
        try {
            List<String> books = readFile(fileName);

            for (String book : books) {
                String[] details = book.split(", ");
                lastPoint = Integer.parseInt(details[0].split("-")[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastPoint;
    }

    public static String generateNovelCode(int year, int count) {
        return String.format("%d-A-%05d", year, count);
    }

    public static String generateMagazineCode(int year, int count) {
        return String.format("%d-B-%05d", year, count);
    }
}
