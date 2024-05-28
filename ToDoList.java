import java.io.*;
import java.util.*;

public class ToDoList {

    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadTasks();

        boolean running = true;
        while (running) {
            System.out.println("\n==== To-Do List Manager ====");
            System.out.println("1. Tambahkan tugas");
            System.out.println("2. Hapus tugas");
            System.out.println("3. Lihat tugas");
            System.out.println("4. Keluar");

            System.out.print("\nMasukkan pilihanmu: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addTask(scanner);
                    running = showMenu(scanner);
                    break;
                case 2:
                    removeTasks(scanner);
                    running = showMenu(scanner);
                    break;
                case 3:
                    viewTasks();
                    running = showMenu(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("\nPilihan tidak tersedia. Coba lagi!");
            }
        }

        saveTasks();
        System.out.println("\nTerima kasih!");

        scanner.close();
    }

    private static boolean showMenu(Scanner scanner) {
        System.out.print("\nKembali ke menu? (Y/N): ");
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

    private static void addTask(Scanner scanner) {
        System.out.print("\nMasukkan tugas: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Tugas berhasil ditambahkan.");
    }

    private static void removeTasks(Scanner scanner) {
        if (tasks.size() == 0) {
            System.out.println("\nTidak ada tugas yang dapat dihapus.");
        } else {
            System.out.println("\nDaftar tugas:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }

            System.out.print("\nMasukkan nomor tugas yang ingin dihapus: ");
            int taskNumber = Integer.parseInt(scanner.nextLine());

            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                tasks.remove(taskNumber - 1);
                System.out.println("\nTugas berhasil dihapus.");
            } else {
                System.out.println("\nTugas tidak ditemukan.");
            }
        }
    }

    private static void viewTasks() {
        if (tasks.size() == 0) {
            System.out.println("\nTidak ada tugas yang tersedia.");
        } else {
            System.out.println("\nDaftar tugas:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println("\nJumlah tugas: " + tasks.size());
        }
    }

    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String task;
            while ((task = reader.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("\nTerjadi kesalahan saat memuat tugas.");
            e.printStackTrace();
        }
    }

    private static void saveTasks() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String task : tasks) {
                writer.write(task + System.lineSeparator());
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("\nTerjadi kesalahan saat menyimpan tugas.");
            e.printStackTrace();
        }
    }
}