package com.enigma.features;

import com.enigma.Menu;
import com.enigma.helper.Helper;
import com.enigma.persons.Student;
import com.enigma.services.StudentService;

public class StudentServiceImpl implements StudentService {
    private Student[] students = new Student[5];
    int studentCount = 0;

    @Override
    public void addStudent() {
        if (studentCount >= students.length) {
            System.out.println("\nJumlah Mahasiswa penuh, tidak dapat menambah Mahasiswa...!\n");
            return;
        }

        System.out.println("\n" + "-".repeat(38));
        System.out.println("Add Mahasiswa");
        System.out.println("-".repeat(38));

        String name, age, major;

        do {
            name = Helper.inputString("Nama (3-20 Karakter)");
            if (Helper.isName(name)) {
                break;
            }
            System.out.println("Nama tidak memenuhi kriteria...!\n");
        } while (true);

        do {
            age = Helper.inputString("Umur (min 17 Tahun)");
            if (Helper.isAge(age)) {
                break;
            }
            System.out.println("Umur tidak memenuhi kriteria...!\n");
        } while (true);

        do {
            major = Helper.inputString("Jurusan (maks 10 Karakter)");
            if (Helper.isMajor(major)) {
                break;
            }
            System.out.println("Karakter melebihi batas...!\n");
        } while (true);
        System.out.println("-".repeat(38));

        Student student = new Student(name, age, major);
        students[studentCount++] = student;
        System.out.println("\nSukses Menambah Mahasiswa");
        System.out.println();
    }

    @Override
    public void viewStudent() {
        if (studentCount == 0) {
            System.out.println("\nTidak ditemukan adanya Mahasiswa...!\n");
            return;
        }

        System.out.println("\n" + "-".repeat(38));
        System.out.println("View All Mahasiswa");
        System.out.println("-".repeat(38));

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.printf("%d.\n", (i + 1));
                System.out.println(students[i]);
                System.out.println();
            }
        }
    }

    @Override
    public void deleteStudent() {
        if (studentCount == 0) {
            System.out.println("\nTidak ditemukan adanya Mahasiswa...!\n");
            return;
        }

        String select;

        System.out.println("\n" + "-".repeat(38));
        System.out.println("Delete Mahasiswa");
        System.out.println("-".repeat(38));

        int idStudent;
        do {
            select = Helper.inputString("Hapus Mahasiswa berdasarkan nomor");
            if (Helper.isInteger(select)) {
                idStudent = Integer.parseInt(select);
                break;
            }
        } while (true);

        if (idStudent >= 1 && idStudent <= studentCount) {
            for (int i = idStudent - 1; i < studentCount - 1; i++) {
                students[i] = students[i + 1];
            }

            students[studentCount - 1] = null;
            studentCount--;

            System.out.println("Sukses Menghapus Mahasiswa");
            System.out.println("-".repeat(38) + "\n");
        } else {
            System.out.println("ID Mahasiswa tidak ditemukan...!");
            System.out.println("-".repeat(38) + "\n");
        }
    }
}
