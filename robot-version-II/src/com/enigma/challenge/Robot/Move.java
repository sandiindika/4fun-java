package com.enigma.challenge.Robot;

public class Move {
    public void turnRight(Robot robot) {
        String[] coordinate = robot.getCoordinate();

        if (coordinate[0].equalsIgnoreCase("e")) {
            coordinate[0] = "s";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("s")) {
            coordinate[0] = "w";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("w")) {
            coordinate[0] = "n";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("n")) {
            coordinate[0] = "e";
            robot.setCoordinate(coordinate);
        }
    }

    public void turnLeft(Robot robot) {
        String[] coordinate = robot.getCoordinate();

        if (coordinate[0].equalsIgnoreCase("e")) {
            coordinate[0] = "n";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("n")) {
            coordinate[0] = "w";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("w")) {
            coordinate[0] = "s";
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("s")) {
            coordinate[0] = "e";
            robot.setCoordinate(coordinate);
        }
    }

    public void stepForward(Robot robot, int width, int height) {
        String[] coordinate = robot.getCoordinate();
        int corX = Integer.parseInt(coordinate[1]);
        int corY = Integer.parseInt(coordinate[2]);

        if (coordinate[0].equalsIgnoreCase("e")) {
            if (corX + 1 <= height / 2) {
                corX += 1;
            }
            coordinate[1] = String.valueOf(corX);
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("w")) {
            if (corX - 1 >= -(height / 2)) {
                corX -= 1;
            }
            coordinate[1] = String.valueOf(corX);
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("n")) {
            if (corY + 1 <= width / 2) {
                corY += 1;
            }
            coordinate[2] = String.valueOf(corY);
            robot.setCoordinate(coordinate);
        } else if (coordinate[0].equalsIgnoreCase("s")) {
            if (corY - 1 >= -(width / 2)) {
                corY -= 1;
            }
            coordinate[2] = String.valueOf(corY);
            robot.setCoordinate(coordinate);
        }
    }
}
