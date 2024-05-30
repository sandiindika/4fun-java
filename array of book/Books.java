import java.io.*;
import java.util.*;

public class Books {

    private static final String FILE_NAME = "books.txt";
    private static final int MAX_BOOKS = 50;
    private static String[] books = new String[MAX_BOOKS];
    private static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadBooks();

        boolean running = true;
        while (running) {
            int selected;

            do {
                System.out.println("\n==== Daftar Buku Gosling Class ====");
                System.out.println("|1.| Tambahkan Buku               |");
                System.out.println("|2.| Hapus Buku                   |");
                System.out.println("|3.| Lihat Buku                   |");
                System.out.println("|4.| Ubah Nama Buku               |");
                System.out.println("|5.| Keluar                       |");
                System.out.println("===================================");

                System.out.print("\nMasukkan pilihanmu: ");
                String choice = scanner.nextLine();

                if (isChoice(choice)) {
                    selected = Integer.parseInt(choice);
                    break;
                }
            } while (true);

            System.out.println("\n===================================");

            switch (selected) {
                case 1:
                    running = addBook(scanner);
                    break;
                case 2:
                    running = removeBooks(scanner);
                    break;
                case 3:
                    running = viewBooks(scanner);
                    break;
                case 4:
                    running = updateBook(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("\nPilihan tidak tersedia. Coba lagi!");
            }
        }

        saveBooks();
        System.out.println("\nTerima kasih!");

        scanner.close();
    }

    public static boolean isChoice(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("\nPilihan tidak tersedia. Coba lagi!");
            return false;
        }
    }

    private static boolean showMenu(Scanner scanner) {
        System.out.print("\nKembali ke menu utama? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            return true;
        } else if (choice.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("\nPilihan tidak tersedia. Coba lagi!");
            return showMenu(scanner);
        }
    }

    private static boolean addBook(Scanner scanner) {
        int totalBook;

        do {
            System.out.print("\nMasukkan buku yang ingin ditambahkan: ");
            String choice = scanner.nextLine();

            if (isChoice(choice)) {
                totalBook = Integer.parseInt(choice);
                break;
            }
        } while (true);

        boolean success = true;

        for (int i = 0; i < totalBook; i++) {
            if (bookCount < MAX_BOOKS) {
                System.out.printf("\nMasukkan judul buku ke-%d: ", i + 1);
                String book = scanner.nextLine();
                books[bookCount] = book;
                bookCount++;
            } else if (bookCount == MAX_BOOKS) {
                System.out.println("\nTidak ada buku yang dapat ditambahkan. Silahkan hapus buku terlebih dahulu.");
                success = false;
            }
        }

        if (success) {
            System.out.println("Buku berhasil ditambahkan.");
        }

        System.out.println("\nDaftar buku:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }

        boolean running = showMenu(scanner);
        if (!running) {
            running = addBook(scanner);
        }

        return true;
    }

    private static boolean updateBook(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("\nTidak ada buku yang tersedia.");
        } else {
            System.out.println("\nDaftar buku:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println((i + 1) + ". " + books[i]);
            }
            int bookNumber;

            do {
                System.out.print("\nMasukkan nomor buku yang ingin diubah: ");
                String choice = scanner.nextLine();

                if (isChoice(choice)) {
                    bookNumber = Integer.parseInt(choice);
                    break;
                }
            } while (true);

            if (bookNumber >= 1 && bookNumber <= bookCount) {
                System.out.print("\nMasukkan judul buku baru: ");
                String newBook = scanner.nextLine();
                books[bookNumber - 1] = newBook;
                System.out.println("\nBuku berhasil diubah.");

                System.out.println("\nDaftar buku:");
                for (int i = 0; i < bookCount; i++) {
                    System.out.println((i + 1) + ". " + books[i]);
                }
            } else {
                System.out.println("\nBuku tidak ditemukan.");
            }
        }

        boolean running = showMenu(scanner);
        if (!running) {
            running = updateBook(scanner);
        }

        return true;
    }

    private static boolean removeBooks(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("\nTidak ada buku yang dapat dihapus.");
        } else {
            System.out.println("\nDaftar buku:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println((i + 1) + ". " + books[i]);
            }

            int bookNumber;

            do {
                System.out.print("\nMasukkan nomor buku yang ingin dihapus: ");
                String choice = scanner.nextLine();

                if (isChoice(choice)) {
                    bookNumber = Integer.parseInt(choice);
                    break;
                }
            } while (true);

            if (bookNumber >= 1 && bookNumber <= bookCount) {
                for (int i = bookNumber - 1; i < bookCount - 1; i++) {
                    books[i] = books[i + 1];
                }

                books[bookCount - 1] = null;
                bookCount--;

                System.out.println("\nDaftar buku:");
                for (int i = 0; i < bookCount; i++) {
                    System.out.println((i + 1) + ". " + books[i]);
                }

                System.out.println("\nBuku berhasil dihapus.");
            } else {
                System.out.println("\nBuku tidak ditemukan.");
            }
        }

        boolean running = showMenu(scanner);
        if (!running) {
            running = removeBooks(scanner);
        }

        return true;
    }

    private static boolean viewBooks(Scanner scanner) {
        if (bookCount == 0) {
            System.out.println("\nTidak ada buku yang tersedia.");
        } else {
            System.out.println("\nDaftar buku:");
            for (int i = 0; i < bookCount; i++) {
                System.out.println((i + 1) + ". " + books[i]);
            }
            System.out.println("\nJumlah buku: " + bookCount);
        }

        boolean running = showMenu(scanner);
        if (!running) {
            running = viewBooks(scanner);
        }

        return true;
    }

    private static void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String book;
            while ((book = reader.readLine()) != null && bookCount < MAX_BOOKS) {
                books[bookCount] = book;
                bookCount++;
            }
        } catch (IOException e) {
            System.out.println("\nTerjadi kesalahan saat memuat buku.");
            e.printStackTrace();
        }
    }

    private static void saveBooks() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (int i = 0; i < bookCount; i++) {
                writer.write(books[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("\nTerjadi kesalahan saat menyimpan buku.");
            e.printStackTrace();
        }
    }
}