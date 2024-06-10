package com.enigmacamp.enigpus.utility;

import java.io.*;

public class BookUtil {

    public static String generateNovelCode(int year, int count) {
        if (count >= 10000 && count <= 99999) return year + "-A-" + count;
        else if (count >= 1000) return year + "-A-0" + count;
        else if (count >= 100) return year + "-A-00" + count;
        else if (count >= 10) return year + "-A-000" + count;
        else if (count >= 1) return year + "-A-0000" + count;
        return "";
    }

    public static String generateMagazineCode(int year, int count) {
        if (count >= 10000 && count <= 99999) return year + "-B-" + count;
        else if (count >= 1000) return year + "-B-0" + count;
        else if (count >= 100) return year + "-B-00" + count;
        else if (count >= 10) return year + "-B-000" + count;
        else if (count >= 1) return year + "-B-0000" + count;
        return "";
    }

    public static void writeFile(String book, String fileName, boolean dontReplace) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, dontReplace))) {
            bw.write(book);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLastPointNovel(int novelCount) {
        try (BufferedReader br = new BufferedReader(new FileReader("novel.txt"))) {
            String novel;
            while ((novel = br.readLine()) != null) {
                String[] novelItems = novel.split(", ");

                novelCount = Integer.parseInt(novelItems[0].split("-")[2]);
                novelCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return novelCount;
    }

    public static int getLastPointMagazine(int magazineCount) {
        try (BufferedReader br = new BufferedReader(new FileReader("magazine.txt"))) {
            String magazine;
            while ((magazine = br.readLine()) != null) {
                String[] magazineItems = magazine.split(", ");

                magazineCount = Integer.parseInt(magazineItems[0].split("-")[2]);
                magazineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return magazineCount;
    }
}
