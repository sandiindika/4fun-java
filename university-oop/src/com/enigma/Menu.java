package com.enigma;

import com.enigma.features.StudentServiceImpl;
import com.enigma.helper.Helper;
import com.enigma.services.StudentService;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    StudentService service = new StudentServiceImpl();

    public void start() {
        int selected;

        do {
            System.out.println("-".repeat(38));
            System.out.println("Main Menu");
            System.out.println("-".repeat(38));
            System.out.println("1. Add Mahasiswa");
            System.out.println("2. Delete Mahasiswa");
            System.out.println("3. View All Mahasiswa");
            System.out.println("4. Exit");
            String option = Helper.inputString("Masukan menu yang dipilih");

            if (Helper.isInteger(option)) {
                selected = Integer.parseInt(option);
                break;
            }
        } while (true);

        switch (selected) {
            case 1:
                service.addStudent();
                start();
                break;
            case 2:
                service.deleteStudent();
                start();
                break;
            case 3:
                service.viewStudent();
                start();
                break;
            case 4:
                break;
            default:
                System.out.println("\nPilihan tidak tersedia...!\n");
                start();
        }
        scanner.close();
    }
}
