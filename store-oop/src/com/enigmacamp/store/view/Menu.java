package com.enigmacamp.store.view;

import com.enigmacamp.store.services.ProductService;
import com.enigmacamp.store.services.handlers.ProductServiceImpl;
import com.enigmacamp.store.services.validator.Validator;

import java.util.Scanner;

import static java.lang.System.*;

public class Menu {
    Scanner scanner = new Scanner(in);
    ProductService service = new ProductServiceImpl();

    public void start() {
        int selected;

        do {
            out.println("\n" + "-".repeat(38));
            out.println("Main Menu");
            out.println("-".repeat(38));

            out.println("1. Add Product");
            out.println("2. Change Product");
            out.println("3. Delete Product");
            out.println("4. View All Products");
            out.println("5. Search Products By");
            out.println("6. Exit");

            String option = Validator.inputStr("Enter menu options (1-6)");

            if (Validator.isInt(option)) {
                selected = Integer.parseInt(option);
                break;
            }
        } while (true);

        switch (selected) {
            case 1:
                service.createProduct();
                start();
                break;
            case 2:
                service.updateProduct();
                start();
                break;
            case 3:
                service.deleteProduct();
                start();
                break;
            case 4:
                service.viewProducts();
                start();
                break;
            case 5:
                service.searchProduct();
                start();
                break;
            case 6:
                break;
            default:
                out.println("-".repeat(38));
                out.println("\nOption not found!");
                start();
        }
        scanner.close();
    }
}
