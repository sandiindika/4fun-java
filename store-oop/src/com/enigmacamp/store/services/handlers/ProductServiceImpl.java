package com.enigmacamp.store.services.handlers;

import com.enigmacamp.store.products.Product;
import com.enigmacamp.store.services.ProductService;
import com.enigmacamp.store.services.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.System.*;

public class ProductServiceImpl implements ProductService {
    LocalDate date = LocalDate.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.ENGLISH);
    String formattedDate = date.format(dateFormatter);

    private final List<Product> products = new ArrayList<>();
    static int productCount = 0;
    static int productId = 1;

    @Override
    public void createProduct() {
        out.println("\n" + "-".repeat(38));
        out.println("Add Product");
        out.println("-".repeat(38));

        String name, brand, cekPrice;
        int price;

        do {
            name = Validator.inputStr("Enter the name of the product");
            if (Validator.nameValidator(name)) break;
            out.println("\n" + "-".repeat(38));
            out.println("Product name doesn't match the criteria...!");
            out.println("-".repeat(38));
        } while (true);

        do {
            brand = Validator.inputStr("Enter the brand of the product");
            if (Validator.brandValidator(brand)) break;
            out.println("\n" + "-".repeat(38));
            out.println("Brand name doesn't match the criteria...!");
            out.println("-".repeat(38));
        } while (true);

        do {
            cekPrice = Validator.inputStr("Enter the price of the product");
            if (Validator.priceValidator(cekPrice)) {
                price = Integer.parseInt(cekPrice);
                break;
            }
        } while (true);

        out.println("-".repeat(38));
        Product product = new Product(name, brand, price, formattedDate);
        products.add(product);
        productCount++;
        productId++;

        out.println("\nProduct was successfully created!");
    }

    @Override
    public void viewProducts() {
        if (productCount == 0) {
            out.println("-".repeat(38));
            out.println("\nProduct list is empty!");
            return;
        }

        out.println("\n" + "-".repeat(38));
        out.println("View All Products");
        out.println("-".repeat(38));

        loopProduct();

        out.println("\n" + "-".repeat(38));
        out.printf("Total Products: %d\n", productCount);
        out.println("-".repeat(38));
    }

    @Override
    public void deleteProduct() {
        if (cekProduct()) {
            return;
        }

        out.println("\n" + "-".repeat(38));
        out.println("Select Product to Delete");
        out.println("-".repeat(38));

        loopProduct();

        out.println("\n" + "-".repeat(38));
        String removeProduct = Validator.inputStr("\nEnter the product ID");

        if (products.removeIf(product -> removeProduct.equalsIgnoreCase(product.getId()))) {
            out.println("\n" + "-".repeat(38));
            out.println("\nProduct was successfully deleted!");
            productCount--;
        } else {
            out.println("\n" + "-".repeat(38));
            out.println("\nProduct not found, product was not successfully deleted!");
        }
    }

    @Override
    public void updateProduct() {
        if (cekProduct()) {
            return;
        }

        out.println("\n" + "-".repeat(38));
        out.println("Select Product to Change");
        out.println("-".repeat(38));

        loopProduct();

        out.println("\n" + "-".repeat(38));
        String updateProduct = Validator.inputStr("\nEnter the product ID");

        for (Product product : products) {
            if (updateProduct.equalsIgnoreCase(product.getId())) {
                int selected;

                do {
                    out.println("\nSelect Change");
                    out.println("1. Product Name");
                    out.println("2. Product Brand");
                    out.println("3. Product Price");
                    out.println("-".repeat(38));

                    String option = Validator.inputStr("Enter menu options (1-3)");
                    if (Validator.isInt(option)) {
                        selected = Integer.parseInt(option);
                        break;
                    }
                } while (true);

                switch (selected) {
                    case 1:
                        String name;
                        do {
                            name = Validator.inputStr("Enter the name of the product");
                            if (Validator.nameValidator(name)) break;
                            out.println("\n" + "-".repeat(38));
                            out.println("Product name doesn't match the criteria...!");
                            out.println("-".repeat(38));
                        } while (true);
                        product.setName(name);

                        out.println("-".repeat(38));
                        out.println("\nProduct was successfully updated!");
                        break;
                    case 2:
                        String brand;
                        do {
                            brand = Validator.inputStr("Enter the brand of the product");
                            if (Validator.brandValidator(brand)) break;
                            out.println("\n" + "-".repeat(38));
                            out.println("Brand name doesn't match the criteria...!");
                            out.println("-".repeat(38));
                        } while (true);
                        product.setBrand(brand);

                        out.println("-".repeat(38));
                        out.println("\nProduct was successfully updated!");
                        break;
                    case 3:
                        String cekPrice;
                        int price;
                        do {
                            cekPrice = Validator.inputStr("Enter the price of the product");
                            if (Validator.priceValidator(cekPrice)) {
                                price = Integer.parseInt(cekPrice);
                                break;
                            }
                        } while (true);
                        product.setPrice(price);

                        out.println("-".repeat(38));
                        out.println("\nProduct was successfully updated!");
                        break;
                    default:
                        out.println("-".repeat(38));
                        out.println("\nOption not found!");
                        updateProduct();
                }
            } else {
                out.println("\n" + "-".repeat(38));
                out.println("\nProduct not found!");
            }
        }
    }

    @Override
    public void searchProduct() {
        if (cekProduct()) {
            return;
        }

        out.println("\n" + "-".repeat(38));
        out.println("Search Product");
        out.println("-".repeat(38));
        String searchProduct = Validator.inputStr("Enter the product Name/Brand");

        int counter = 0;

        for (Product product : products) {
            if (searchProduct.equalsIgnoreCase(product.getName()) || searchProduct.equalsIgnoreCase(product.getBrand())) {
                out.printf("\nID: %s", product.getId());
                out.printf("\nName: %s", product.getName());
                out.printf("\nBrand: %s", product.getBrand());
                out.printf("\nPrice: %s", product.getPrice());
                out.printf("\nDate Created: %s\n", product.getCreateDate());
                counter++;
            }
        }

        if (counter == 0) out.println("\nProduct not found!");
    }

    public void loopProduct() {
        for (Product product : products) {
            out.printf("\nID: %s", product.getId());
            out.printf("\nName: %s", product.getName());
            out.printf("\nBrand: %s", product.getBrand());
            out.printf("\nPrice: %s", product.getPrice());
            out.printf("\nDate Created: %s", product.getCreateDate());
        }
    }

    public static int getProductId() {
        return productId;
    }

    private boolean cekProduct() {
        if (productCount == 0) {
            out.println("-".repeat(38));
            out.println("\nProduct list is empty!");
            return true;
        } else return false;
    }
}
