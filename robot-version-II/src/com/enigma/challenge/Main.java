package com.enigma.challenge;

import com.enigma.challenge.Maps.Maps;
import com.enigma.challenge.Helper.Helper;
import com.enigma.challenge.Robot.Move;
import com.enigma.challenge.Robot.Robot;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        Maps maps = new Maps();
        Move go = new Move();

        int width = Helper.inputInteger("Panjang Maps");
        int height = Helper.inputInteger("Tinggi Maps");

        maps.setWidth(width);
        maps.setHeight(height);

        do {
            String coor = Helper.inputString("Inisiasi arah dan koordinat Robot");
            String[] coordinate = coor.split(" ");

            if (coordinate.length != 3) {
                System.out.println("Koordinat tidak valid...!\n");
            } else if (!Helper.isDirection(coordinate[0])) {
                System.out.println("Arah angin tidak ditemukan...!\n");
            } else if (Helper.isXY(coordinate[1]) || Helper.isXY(coordinate[2])) {
                System.out.println("Koordinat X atau Y tidak valid...!\n");
            } else if (Integer.parseInt(coordinate[1]) > maps.getWidth() / 2 || Integer.parseInt(coordinate[2]) > maps.getHeight() / 2) {
                System.out.println("Robot tidak dapat dispawn diluar maps...!\n");
            } else if (Integer.parseInt(coordinate[1]) < -(maps.getWidth() / 2) || Integer.parseInt(coordinate[2]) < -(maps.getHeight() / 2)) {
                System.out.println("Robot tidak dapat dispawn diluar maps...!\n");
            } else {
                robot.setCoordinate(coordinate);
                break;
            }
        } while (true);

        maps.showRobot(robot);

        System.out.println();
        do {
            String move = Helper.inputString("\nGerakan Robot");
            String[] moving = move.split("");

            for (String dir : moving) {
                if (dir.equalsIgnoreCase("a")) {
                    go.stepForward(robot, width, height);
                } else if (dir.equalsIgnoreCase("r")) {
                    go.turnRight(robot);
                } else if (dir.equalsIgnoreCase("l")) {
                    go.turnLeft(robot);
                }
            }

            maps.showRobot(robot);

            System.out.println();

        } while (Helper.doAgain("Pindah lagi? (Y/N)"));
    }
}

