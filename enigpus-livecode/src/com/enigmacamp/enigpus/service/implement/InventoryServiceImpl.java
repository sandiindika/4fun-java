package com.enigmacamp.enigpus.service.implement;

import com.enigmacamp.enigpus.book.type.Magazine;
import com.enigmacamp.enigpus.book.type.Novel;
import com.enigmacamp.enigpus.handler.InputValidator;
import com.enigmacamp.enigpus.service.InventoryService;
import com.enigmacamp.enigpus.utility.BookUtil;
import com.enigmacamp.enigpus.utility.ViewUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class InventoryServiceImpl implements InventoryService {
    Scanner scanner = new Scanner(System.in);

    static int novelCount = 1;
    static int magazineCount = 1;

    public static void getLastPoint() {
        novelCount = BookUtil.getLastPointNovel(novelCount);
        magazineCount = BookUtil.getLastPointMagazine(magazineCount);
    }

    @Override
    public void getAllBook() {
        ViewUtil.divider(true);
        out.println("|               All Books              |");
        ViewUtil.divider(false);

        int bookCount = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("novel.txt"))) {
            String novel;
            while ((novel = br.readLine()) != null) {
                String[] novelItems = novel.split(", ");
                out.println("\n" + bookCount + "\tNovel Code: " + novelItems[0]);
                out.println("\tNovel Title: " + novelItems[1]);
                out.println("\tNovel Publisher: " + novelItems[2]);
                out.println("\tYear of Publication: " + novelItems[3]);
                out.println("\tAuthor: " + novelItems[4]);

                novelCount = Integer.parseInt(novelItems[0].split("-")[2]);
                novelCount++;
                bookCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("magazine.txt"))) {
            String magazine;
            while ((magazine = br.readLine()) != null) {
                String[] magazineItems = magazine.split(", ");
                out.println("\n" + bookCount + "\tMagazine Code: " + magazineItems[0]);
                out.println("\tMagazine Title: " + magazineItems[1]);
                out.println("\tMagazine Publication Period: " + magazineItems[2]);
                out.println("\tYear of Publication: " + magazineItems[3]);

                magazineCount = Integer.parseInt(magazineItems[0].split("-")[2]);
                magazineCount++;
                bookCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bookCount - 1 == 0) {
            ViewUtil.divider(true);
            out.println("No books in inventory.");
            ViewUtil.divider(false);
        }
    }

    @Override
    public void deleteBookByCode() {
        ViewUtil.divider(true);
        out.println("|          Delete Book by Code         |");
        ViewUtil.divider(false);

        List<String> newNovels = new ArrayList<>();
        List<String> newMagazine = new ArrayList<>();

        int bookCount = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("novel.txt"))) {
            String novel;
            while ((novel = br.readLine()) != null) {
                newNovels.add(novel);
                String[] novelItems = novel.split(", ");
                out.println("\n" + bookCount + "\tNovel Code: " + novelItems[0]);
                out.println("\tNovel Title: " + novelItems[1]);
                out.println("\tNovel Publisher: " + novelItems[2]);
                out.println("\tYear of Publication: " + novelItems[3]);
                out.println("\tAuthor: " + novelItems[4]);

                bookCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("magazine.txt"))) {
            String magazine;
            while ((magazine = br.readLine()) != null) {
                newMagazine.add(magazine);
                String[] magazineItems = magazine.split(", ");
                out.println("\n" + bookCount + "\tMagazine Code: " + magazineItems[0]);
                out.println("\tMagazine Title: " + magazineItems[1]);
                out.println("\tMagazine Publication Period: " + magazineItems[2]);
                out.println("\tYear of Publication: " + magazineItems[3]);

                bookCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bookCount - 1 == 0) {
            ViewUtil.divider(true);
            out.println("No books in inventory.");
            ViewUtil.divider(false);
        } else {
            ViewUtil.divider(true);
            String bookCode = InputValidator.isValidString(scanner, "Input Code");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("novel.txt"))) {
                bw.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < newNovels.toArray().length; i++) {
                String[] novel = newNovels.get(i).split(", ");
                if (!novel[0].equalsIgnoreCase(bookCode)) {
                    BookUtil.writeFile(newNovels.get(i), "novel.txt", true);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("magazine.txt"))) {
                bw.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < newMagazine.toArray().length; i++) {
                String[] magazine = newMagazine.get(i).split(", ");
                if (!magazine[0].equalsIgnoreCase(bookCode)) {
                    BookUtil.writeFile(newMagazine.get(i), "magazine.txt", true);
                }
            }

            ViewUtil.divider(true);
            out.println("Book has been delete.");
            ViewUtil.divider(false);
        }
    }

    @Override
    public void searchBookByCode() {
        boolean bookFound = false;

        ViewUtil.divider(true);
        String bookCode = InputValidator.isValidString(scanner, "Input Code");
        ViewUtil.divider(false);

        try (BufferedReader br = new BufferedReader(new FileReader("novel.txt"))) {
            String novel;
            while ((novel = br.readLine()) != null) {
                String[] novelItems = novel.split(", ");
                if (novelItems[0].equalsIgnoreCase(bookCode)) {
                    out.println("Novel Code: " + novelItems[0]);
                    out.println("Novel Title: " + novelItems[1]);
                    out.println("Novel Publisher: " + novelItems[2]);
                    out.println("Year of Publication: " + novelItems[3]);
                    out.println("Author: " + novelItems[4]);
                    bookFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("magazine.txt"))) {
            String magazine;
            while ((magazine = br.readLine()) != null) {
                String[] magazineItems = magazine.split(", ");
                if (magazineItems[0].equalsIgnoreCase(bookCode)) {
                    out.println("Magazine Code: " + magazineItems[0]);
                    out.println("Magazine Title: " + magazineItems[1]);
                    out.println("Magazine Publication Period: " + magazineItems[2]);
                    out.println("Year of Publication: " + magazineItems[3]);
                    bookFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!bookFound) {
            ViewUtil.divider(true);
            out.println("Book not found.");
            ViewUtil.divider(false);
        }
    }

    @Override
    public void searchBookByTitle() {
        boolean bookFound = false;
        int bookCount = 1;

        ViewUtil.divider(true);
        String bookCode = InputValidator.isValidString(scanner, "Input Code");
        ViewUtil.divider(false);

        bookCode = bookCode.toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader("novel.txt"))) {
            String novel;
            while ((novel = br.readLine()) != null) {
                String[] novelItems = novel.split(", ");
                if (novelItems[1].toLowerCase().contains(bookCode)) {
                    out.println("\n" + bookCount + "\tNovel Code: " + novelItems[0]);
                    out.println("\tNovel Title: " + novelItems[1]);
                    out.println("\tNovel Publisher: " + novelItems[2]);
                    out.println("\tYear of Publication: " + novelItems[3]);
                    out.println("\tAuthor: " + novelItems[4]);
                    bookFound = true;
                    bookCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("magazine.txt"))) {
            String magazine;
            while ((magazine = br.readLine()) != null) {
                String[] magazineItems = magazine.split(", ");
                if (magazineItems[1].toLowerCase().contains(bookCode)) {
                    out.println("\n" + bookCount + "\tMagazine Code: " + magazineItems[0]);
                    out.println("\tMagazine Title: " + magazineItems[1]);
                    out.println("\tMagazine Publication Period: " + magazineItems[2]);
                    out.println("\tYear of Publication: " + magazineItems[3]);
                    bookFound = true;
                    bookCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!bookFound) {
            ViewUtil.divider(true);
            out.println("Book not found.");
            ViewUtil.divider(false);
        }
    }

    @Override
    public void addBook() {
        int selected;

        do {
            ViewUtil.divider(true);
            out.println("|               Add Book               |");
            ViewUtil.divider(false);

            out.println("1. Novel");
            out.println("2. Magazine");
            out.println("3. Cancel");

            String option = InputValidator.isValidString(scanner, "Enter book options (1-3)");
            if (InputValidator.isValidNumber(option)) {
                selected = Integer.parseInt(option);
                break;
            }
        } while (true);

        switch (selected) {
            case 1:
                ViewUtil.divider(true);
                out.println("|                 Novel                |");
                ViewUtil.divider(false);

                String titleNovel, publisherNovel, authorNovel, cekYearNovel;
                int publicationYearNovel;

                do {
                    titleNovel = InputValidator.isValidString(scanner, "Input book title");
                    if (InputValidator.stringValidator(titleNovel)) break;
                    ViewUtil.divider(true);
                    out.println("Title name doesn't match the criteria. Please try again!");
                    ViewUtil.divider(false);
                } while (true);

                do {
                    publisherNovel = InputValidator.isValidString(scanner, "Input book publisher name");
                    if (InputValidator.stringValidator(publisherNovel)) break;
                    ViewUtil.divider(true);
                    out.println("Publisher name doesn't match the criteria. Please try again!");
                    ViewUtil.divider(false);
                } while (true);

                do {
                    authorNovel = InputValidator.isValidString(scanner, "Input book author name");
                    if (InputValidator.stringValidator(authorNovel)) break;
                    ViewUtil.divider(true);
                    out.println("Author name doesn't match the criteria. Please try again!");
                    ViewUtil.divider(false);
                } while (true);

                do {
                    cekYearNovel = InputValidator.isValidString(scanner, "Input book publication year");
                    if (InputValidator.yearValidator(cekYearNovel)){
                        publicationYearNovel = Integer.parseInt(cekYearNovel);
                        break;
                    }
                } while (true);

                String novelCode = BookUtil.generateNovelCode(publicationYearNovel, novelCount);

                Novel novel = new Novel(novelCode, titleNovel, publisherNovel, publicationYearNovel, authorNovel);
                String bookNovel = novel.getCode() + ", "
                        + novel.getTitle() + ", "
                        + novel.getPublisher() + ", "
                        + novel.getPublicationYear() + ", "
                        + novel.getAuthor();

                BookUtil.writeFile(bookNovel, "novel.txt", true);

                novelCount++;

                ViewUtil.divider(true);
                out.println("Novel has been added to the inventory.");
                ViewUtil.divider(false);

                break;
            case 2:
                ViewUtil.divider(true);
                out.println("|                Magazine              |");
                ViewUtil.divider(false);

                String titleMagazine, publicationPeriodMagazine, cekYearMagazine;

                int publicationYearMagazine;

                do {
                    titleMagazine = InputValidator.isValidString(scanner, "Input book title");
                    if (InputValidator.stringValidator(titleMagazine)) break;
                    ViewUtil.divider(true);
                    out.println("Title name doesn't match the criteria. Please try again!");
                    ViewUtil.divider(false);
                } while (true);

                int selectedPeriod;
                do {
                    do {
                        ViewUtil.divider(true);
                        out.println("Publication period");
                        ViewUtil.divider(false);

                        out.println("1. Weekly");
                        out.println("2. Monthly");

                        String period = InputValidator.isValidString(scanner, "Enter period options (1-2)");
                        if (InputValidator.isValidNumber(period)) {
                            selectedPeriod = Integer.parseInt(period);
                            break;
                        }
                    } while (true);

                    if (selectedPeriod == 1) {
                        publicationPeriodMagazine = "Weekly";
                        break;
                    } else if (selectedPeriod == 2) {
                        publicationPeriodMagazine = "Monthly";
                        break;
                    } else {
                        ViewUtil.divider(true);
                        out.println("Invalid option. Try again!");
                        ViewUtil.divider(false);
                    }
                } while (true);

                do {
                    cekYearMagazine = InputValidator.isValidString(scanner, "Input book publication year");
                    if (InputValidator.yearValidator(cekYearMagazine)){
                        publicationYearMagazine = Integer.parseInt(cekYearMagazine);
                        break;
                    }
                } while (true);

                String magazineCode = BookUtil.generateMagazineCode(publicationYearMagazine, magazineCount);

                Magazine magazine = new Magazine(magazineCode, titleMagazine, publicationPeriodMagazine, publicationYearMagazine);
                String bookMagazine = magazine.getCode() + ", "
                        + magazine.getTitle() + ", "
                        + magazine.getPublicationPeriod() + ", "
                        + magazine.getPublicationYear();

                BookUtil.writeFile(bookMagazine, "magazine.txt", true);

                magazineCount++;

                ViewUtil.divider(true);
                out.println("Magazine has been added to the inventory.");
                ViewUtil.divider(false);

                break;
            case 3:
                break;
            default:
                ViewUtil.divider(true);
                out.println("Invalid option.");
                ViewUtil.divider(false);
                addBook();
        }
    }
}
