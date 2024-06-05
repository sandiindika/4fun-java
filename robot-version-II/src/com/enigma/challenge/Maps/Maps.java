package com.enigma.challenge.Maps;
import com.enigma.challenge.Robot.Robot;

public class Maps {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void showRobot(Robot robot) {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        String[] coordinate = robot.getCoordinate();

        for (int i = 0; i < this.width; i++) {
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                if (i == width - halfWidth - Integer.parseInt(coordinate[2]) - 1 && j == halfHeight + Integer.parseInt(coordinate[1])) {
                    switch (coordinate[0]) {
                        case "e" -> System.out.print("| > ");
                        case "s" -> System.out.print("| v ");
                        case "w" -> System.out.print("| < ");
                        default -> System.out.print("| ^ ");
                    }
                } else if (i == halfWidth && j == halfHeight) {
                    System.out.print("| 0 ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
        }
    }
}
