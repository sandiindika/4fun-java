package com.enigmacamp.enigpus.service.implement;

import com.enigmacamp.enigpus.book.type.Novel;
import com.enigmacamp.enigpus.book.type.Magazine;
import com.enigmacamp.enigpus.handler.InputValidator;
import com.enigmacamp.enigpus.service.InventoryService;
import com.enigmacamp.enigpus.utility.BookUtil;
import com.enigmacamp.enigpus.utility.ViewUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

public class InventoryServiceImpl implements InventoryService {
    int novelCount = BookUtil.getLastPointItem("novel.txt");
    int magazineCount = BookUtil.getLastPointItem("magazine.txt");

    static InventoryService service = new InventoryServiceImpl();

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

            String option = InputValidator.isValidString("Enter book options (1-3)");
            if (InputValidator.isValidNumber(option)) {
                selected = Integer.parseInt(option);
                break;
            }
        } while (true);

        switch (selected) {
            case 1:
                addNovel();
                break;
            case 2:
                addMagazine();
                break;
            case 3:
                break;
            default:
                ViewUtil.divider(true);
                out.println("| Invalid selection. Please try again!");
                ViewUtil.divider(false);
                addBook();
        }
    }

    private void addNovel() {
        ViewUtil.divider(true);
        out.println("|                 Novel                |");
        ViewUtil.divider(false);

        String title = getInput("Input book title");
        String publisher = getInput("Input book publisher name");
        String author = getInput("Input book author name");
        int publicationYear = getYearInput("Input book publication year");
        ViewUtil.divider(true);

        novelCount++;
        String novelCode = BookUtil.generateNovelCode(publicationYear, novelCount);

        Novel novel = new Novel(novelCode, title, publisher, publicationYear, author);

        try {
            List<String> novels = BookUtil.readFile("novel.txt");
            novels.add(novel.getCode()+ ", " +novel.getTitle()+ ", " +novel.getPublisher()+ ", " +novel.getPublicationYear()+ ", " +novel.getAuthor());
            BookUtil.writeToFile(novels, "novel.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewUtil.divider(true);
        out.println("| Novel has been added to the inventory.");
        ViewUtil.divider(false);
    }

    private void addMagazine() {
        ViewUtil.divider(true);
        out.println("|                Magazine              |");
        ViewUtil.divider(false);

        String title = getInput("Input book title");
        String publicationPeriod = getPublicationPeriod();
        int publicationYear = getYearInput("Input book publication year");
        ViewUtil.divider(true);

        magazineCount++;
        String magazineCode = BookUtil.generateMagazineCode(publicationYear, magazineCount);

        Magazine magazine = new Magazine(magazineCode, title, publicationPeriod, publicationYear);

        try {
            List<String> magazines = BookUtil.readFile("magazine.txt");
            magazines.add(magazine.getCode()+ ", " +magazine.getTitle()+ ", " +magazine.getPublicationPeriod()+ ", " +magazine.getPublicationYear());
            BookUtil.writeToFile(magazines, "magazine.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewUtil.divider(true);
        out.println("| Magazine has been added to the inventory.");
        ViewUtil.divider(false);
    }

    private String getInput(String prompt) {
        String input;
        do {
            input = InputValidator.isValidString(prompt);
            if (InputValidator.stringValidator(input)) break;
            ViewUtil.divider(true);
            out.println("| Input doesn't match the criteria. Please try again!");
            ViewUtil.divider(false);
        } while (true);
        return input;
    }

    private int getYearInput(String prompt) {
        String input;
        int year;
        do {
            input = InputValidator.isValidString(prompt);
            if (InputValidator.yearValidator(input)) {
                year = Integer.parseInt(input);
                break;
            }
        } while (true);
        return year;
    }

    private String getPublicationPeriod() {
        int selectedPeriod;
        String period = "";
        do {
            ViewUtil.divider(true);
            out.println("Publication period");
            ViewUtil.divider(false);

            out.println("1. Weekly");
            out.println("2. Monthly");

            String option = InputValidator.isValidString("Enter period options (1-2)");
            if (InputValidator.isValidNumber(option)) {
                selectedPeriod = Integer.parseInt(option);
                if (selectedPeriod == 1) {
                    period = "Weekly";
                    break;
                } else if (selectedPeriod == 2) {
                    period = "Monthly";
                    break;
                } else {
                    ViewUtil.divider(true);
                    out.println("| Invalid option. Try again!");
                    ViewUtil.divider(false);
                }
            }
        } while (true);
        return period;
    }

    @Override
    public void searchBookByTitle() {
        searchBook("Input title", true);
    }

    @Override
    public void searchBookByCode() {
        searchBook("Input code", false);
    }

    private void searchBook(String prompt, boolean isTitleSearch) {
        try {
            List<String> novels = BookUtil.readFile("novel.txt");
            List<String> magazines = BookUtil.readFile("magazine.txt");
            int bookCount = 0;

            if (novels.isEmpty() && magazines.isEmpty()) {
                ViewUtil.divider(true);
                out.println("| No book found.");
                ViewUtil.divider(false);
                return;
            }

            ViewUtil.divider(true);
            String searchString = InputValidator.isValidString(prompt).toLowerCase();
            ViewUtil.divider(false);

            bookCount = searchInList(novels, searchString, isTitleSearch, bookCount, true);
            bookCount = searchInList(magazines, searchString, isTitleSearch, bookCount, false);

            if (bookCount == 0) {
                ViewUtil.divider(true);
                out.println("| Book not found.");
                ViewUtil.divider(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int searchInList(List<String> books, String searchString, boolean isTitleSearch, int bookCount, boolean isNovel) {
        for (String book : books) {
            String[] bookDetail = book.split(", ");
            boolean isMatch = isTitleSearch
                    ? bookDetail[1].toLowerCase().contains(searchString)
                    : bookDetail[0].equalsIgnoreCase(searchString);

            if (isMatch) {
                bookCount++;
                out.println("\n" + bookCount + "\tBook Code\t\t\t: " + bookDetail[0]);
                out.println(isNovel
                        ? "\tNovel Title\t\t\t: " + bookDetail[1] + "\n\tNovel Publisher\t\t: " + bookDetail[2] + "\n\tPublication Year\t: " + bookDetail[3] + "\n\tAuthor\t\t\t\t: " + bookDetail[4]
                        : "\tMagazine Title\t\t: " + bookDetail[1] + "\n\tPublication Period\t: " + bookDetail[2] + "\n\tPublication Year\t: " + bookDetail[3]);
            }
        }
        return bookCount;
    }

    @Override
    public void deleteBookByCode() {
        service.getAllBook();

        try {
            List<String> novels = BookUtil.readFile("novel.txt");
            List<String> magazines = BookUtil.readFile("magazine.txt");

            if (novels.size() == 0 && magazines.size() == 0) {
                ViewUtil.divider(true);
                out.println("| No book found.");
                ViewUtil.divider(false);
                return;
            }

            ViewUtil.divider(true);
            String bookCode = InputValidator.isValidString("Input book code to delete").toLowerCase();
            ViewUtil.divider(false);

            if (deleteBook(novels, bookCode)) {
                ViewUtil.divider(true);
                out.println("| Book with code " + bookCode + " has been deleted.");
                BookUtil.writeToFile(novels, "novel.txt");
            } else if (deleteBook(magazines, bookCode)) {
                ViewUtil.divider(true);
                out.println("| Book with code " + bookCode + " has been deleted.");
                BookUtil.writeToFile(magazines, "magazine.txt");
            } else {
                ViewUtil.divider(true);
                out.println("| Book not found.");
                ViewUtil.divider(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteBook(List<String> books, String bookCode) {
        Iterator<String> iterator = books.iterator();
        while (iterator.hasNext()) {
            String book = iterator.next();
            String[] bookDetail = book.split(", ");
            if (bookDetail[0].equalsIgnoreCase(bookCode)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void getAllBook() {
        try {
            List<String> novels = BookUtil.readFile("novel.txt");
            List<String> magazines = BookUtil.readFile("magazine.txt");
            int bookCount = 0;

            if (novels.isEmpty() && magazines.isEmpty()) {
                ViewUtil.divider(true);
                out.println("| No book found.");
                ViewUtil.divider(false);
                return;
            }

            ViewUtil.divider(true);
            out.println("|               All Book               |");
            ViewUtil.divider(false);

            bookCount = printBooks(novels, bookCount, "Novel");
            printBooks(magazines, bookCount, "Magazine");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int printBooks(List<String> books, int bookCount, String type) {
        for (String book : books) {
            String[] bookDetail = book.split(", ");
            bookCount++;

            out.println("\n" + bookCount + "\tBook Code\t\t\t: " + bookDetail[0]);
            if (type.equals("Novel")) {
                out.println("\tNovel Title\t\t\t: " + bookDetail[1]);
                out.println("\tNovel Publisher\t\t: " + bookDetail[2]);
                out.println("\tPublication Year\t: " + bookDetail[3]);
                out.println("\tAuthor\t\t\t\t: " + bookDetail[4]);
            } else {
                out.println("\tMagazine Title\t\t: " + bookDetail[1]);
                out.println("\tPublication Period\t: " + bookDetail[2]);
                out.println("\tPublication Year\t: " + bookDetail[3]);
            }
        }
        return bookCount;
    }

    public static void searchBook() {
        int selected = 3;
        do {
            ViewUtil.divider(true);
            out.println("|              Search Book             |");
            ViewUtil.divider(false);

            out.println("1. By Code");
            out.println("2. By Title");
            out.println("3. Cancel");

            String option = InputValidator.isValidString("Enter search option (1-3)");
            if (InputValidator.isValidNumber(option)) {
                selected = Integer.parseInt(option);

                if (selected == 1) {
                    service.searchBookByCode();
                    break;
                } else if (selected == 2) {
                    service.searchBookByTitle();
                    break;
                } else if (selected == 3) {
                    break;
                } else {
                    ViewUtil.divider(true);
                    out.println("| Invalid selection. Please try again!");
                    ViewUtil.divider(false);
                }
            }
        } while (true);
    }
}
