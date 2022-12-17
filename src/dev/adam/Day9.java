package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {

    BufferedReader fr;

    public Day9(BufferedReader fr) {
        this.fr = fr;
    }

    static Day9 day;

    public static void main(String[] args) {
        String inputFile = "day9.txt";
        day = new Day9(Util.getFile(inputFile));
        System.out.println(day.solve1());
        day = new Day9(Util.getFile(inputFile));
        System.out.println(day.solve2());
    }

    int headX = 0, headY = 0;
    int tailX = 0, tailY = 0;
    ArrayList<String> visited = new ArrayList<>();

    public void addToVisited(int x, int y) {
        visited.add(x + "," + y);
    }

    public int solve1() {
        try {
            visit();
            String line;
            while ((line = fr.readLine()) != null) {
                String dir = line.split(" ")[0];
                int dist = Integer.parseInt(line.split(" ")[1]);
                for (int i = 0; i < dist; i++) {
                    switch (dir) {
                        case "D":
                            headY--;
                            break;
                        case "U":
                            headY++;
                            break;
                        case "L":
                            headX--;
                            break;
                        case "R":
                            headX++;
                            break;
                    }
                    moveTail();
                }
            }
            return visited.size();
        } catch (IOException ignored) {
        }
        return 0;
    }

    public void moveTail() {
        int deltaX = headX - tailX;
        int deltaY = headY - tailY;

        if (deltaX > 2 || deltaY > 2) {
            System.out.println("big gaps");
        }

        if (deltaY == 0 && deltaX == 0) {
            visit();
            return;
        }
        if (deltaY == 0 && Math.abs(deltaX) > 1) {
            tailX += (deltaX / Math.abs(deltaX));
            visit();
            return;
        }
        if (deltaX == 0 && Math.abs(deltaY) > 1) {
            tailY += (deltaY / Math.abs(deltaY));
            visit();
            return;
        }
        if ((Math.abs(deltaY) >= 1 || Math.abs(deltaX) >= 1) && !(Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1)) {
            tailX += (deltaX / Math.abs(deltaX));
            tailY += (deltaY / Math.abs(deltaY));
            visit();
        }


    }

    int[] tailsX = new int[9];
    int[] tailsY = new int[9];

    public void moveTail(int leaderX, int leaderY, int tailIndex) {
        int deltaX = leaderX - tailsX[tailIndex];
        int deltaY = leaderY - tailsY[tailIndex];

        if (tailIndex == 9) {
            System.out.println(tailsX[tailIndex] + "," + tailsY[tailIndex]);
        }

        if (deltaX > 2 || deltaY > 2) {
            System.out.println("big gaps");
        }

        if (deltaY == 0 && deltaX == 0) {
            visit(tailIndex);
            return;
        }
        if (deltaY == 0 && Math.abs(deltaX) > 1) {
            tailsX[tailIndex] += (deltaX / Math.abs(deltaX));
            visit(tailIndex);
            return;
        }
        if (deltaX == 0 && Math.abs(deltaY) > 1) {
            tailsY[tailIndex] += (deltaY / Math.abs(deltaY));
            visit(tailIndex);
            return;
        }
        if ((Math.abs(deltaY) >= 1 || Math.abs(deltaX) >= 1) && !(Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1)) {
            tailsX[tailIndex] += (deltaX / Math.abs(deltaX));
            tailsY[tailIndex] += (deltaY / Math.abs(deltaY));
            visit(tailIndex);
            return;
        }
    }

    public int solve2() {
        try {
            visit();
            String line;
            while ((line = fr.readLine()) != null) {
                String dir = line.split(" ")[0];
                int dist = Integer.parseInt(line.split(" ")[1]);
                for (int i = 0; i < dist; i++) {
                    switch (dir) {
                        case "D":
                            headY--;
                            break;
                        case "U":
                            headY++;
                            break;
                        case "L":
                            headX--;
                            break;
                        case "R":
                            headX++;
                            break;
                    }
                    moveTail(headX, headY, 0);
                    for (int j = 1; j < 9; j++) {
                        moveTail(tailsX[j - 1], tailsY[j - 1], j);
                    }
                }
            }
            return visited.size();
        } catch (IOException ignored) {
        }
        return 0;
    }


    public void visit() {
        String location = tailX + "," + tailY;
        if (!visited.contains(location)) {
            visited.add(location);
        }
    }

    public void visit(int index) {
        if (index != 8) return;
        String location = tailsX[8] + "," + tailsY[8];
        if (!visited.contains(location)) {
            visited.add(location);
        }
    }
}