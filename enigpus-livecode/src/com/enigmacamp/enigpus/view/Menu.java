package com.enigmacamp.enigpus.view;

import com.enigmacamp.enigpus.handler.InputValidator;
import com.enigmacamp.enigpus.service.InventoryService;
import com.enigmacamp.enigpus.service.implement.InventoryServiceImpl;
import com.enigmacamp.enigpus.utility.ViewUtil;

import java.util.Scanner;

import static java.lang.System.out;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    InventoryService service = new InventoryServiceImpl();

    public void start() {
        InventoryServiceImpl.getLastPoint();

        int selected;

        do {
            ViewUtil.divider(true);
            out.println("|               Main Menu              |");
            ViewUtil.divider(false);

            out.println("1. Add Book");
            out.println("2. Search Book");
            out.println("3. Delete Book");
            out.println("4. View All Book");
            out.println("5. Exit");

            String option = InputValidator.isValidString(scanner, "Enter menu options (1-6)");
            if (InputValidator.isValidNumber(option)) {
                selected = Integer.parseInt(option);
                break;
            }
        } while (true);

        switch (selected) {
            case 1:
                service.addBook();
                start();
                break;
            case 2:
                int selectedOption = 3;
                do {
                    ViewUtil.divider(true);
                    out.println("|             Search Option            |");
                    ViewUtil.divider(false);

                    out.println("1. Search by Title");
                    out.println("2. Search by Code");
                    out.println("3. Cancel");

                    String searchOption = InputValidator.isValidString(scanner, "Enter search options (1-3)");
                    if (InputValidator.isValidNumber(searchOption)) {
                        selectedOption = Integer.parseInt(searchOption);
                    }

                    if (selectedOption == 1) {
                        service.searchBookByTitle();
                        break;
                    } else if (selectedOption == 2) {
                        service.searchBookByCode();
                        break;
                    } else if (selectedOption == 3) {
                        break;
                    } else {
                        ViewUtil.divider(true);
                        out.println("Invalid option.");
                        ViewUtil.divider(false);
                    }
                } while (true);

                start();
                break;
            case 3:
                service.deleteBookByCode();
                start();
                break;
            case 4:
                service.getAllBook();
                start();
                break;
            case 5:
                break;
            default:
                ViewUtil.divider(true);
                out.println("Invalid option");
                ViewUtil.divider(false);
                start();
        }
        scanner.close();
    }
}
