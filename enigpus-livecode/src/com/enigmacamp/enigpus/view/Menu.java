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
                int selected;

        do {
            ViewUtil.divider(true);
            out.println("|               Main Menu              |");
            ViewUtil.divider(false);

            out.println("1. Add Book");
            out.println("2. Search Book");
            out.println("3. Delete Book");
            out.println("4. View All Book");
            out.println("5. Edit Book");
            out.println("6. Exit");

            String option = InputValidator.isValidString("Enter menu options (1-6)");
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
                InventoryServiceImpl.searchBook();
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
                out.println("Edit Book");
                start();
                break;
            case 6:
                break;
            default:
                ViewUtil.divider(true);
                out.println("| Invalid selection. Please try again!");
                ViewUtil.divider(false);
                start();
        }
        scanner.close();
    }
}
