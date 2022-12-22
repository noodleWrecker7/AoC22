package dev.adam;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Day12 {

    static Day12 day;
    BufferedReader fr;
    char[][] map = new char[70][41];
    int startX, startY, endX, endY;
    HashMap<String, Integer> weightToNode;
    HashMap<String, String> previousNode;
    ArrayList<String> visited;
    ArrayList<String> queue;

    public Day12(BufferedReader fr) {
        this.fr = fr;
    }

    public static void main(String[] args) {
        String inputFile = "inputs/day12.txt";
        day = new Day12(Util.getFile(inputFile));
        System.out.println(day.solve1());
        day = new Day12(Util.getFile(inputFile));
        System.out.println(day.solve2());
    }

    public void genMap(String input) {
        String[] lines = input.split("\n");
        for (int j = 0; j < lines.length; j++) {
            String line = lines[j];
            String[] split = line.split("");
            for (int i = 0; i < split.length; i++) {
                char height = split[i].charAt(0);
                if (height == 'S') {
                    height = 'a';
                    startX = i;
                    startY = j;
                }

                if (height == 'E') {
                    height = 'z';
                    endX = i;
                    endY = j;
                }
                map[i][j] = height;
            }
        }
    }

    public long solve1() {
        try {
            visited = new ArrayList<>();
            queue = new ArrayList<>();
            previousNode = new HashMap<>();
            weightToNode = new HashMap<>();
            String input = Files.readString(Path.of("inputs/day12.txt"));
            genMap(input);

            return findPathLength(startX, startY, endX, endY, true);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertToQueue(String coords) {
        if (visited.contains(coords) || queue.contains(coords)) {
            return;
        }
        for (int i = 0; i < queue.size(); i++) {
            if (weightToNode.get(coords) < weightToNode.get(queue.get(i))) {
                queue.add(i, coords);
                return;
            }
        }
        queue.add(coords);
    }

    public int findPathLength(int fromX, int fromY, int toX, int toY, boolean asc) {
        System.out.println("searching from: " + fromX + "," + fromY + "  to: " + toX + "," + toY);
        queue.add(fromX + "," + fromY);
        weightToNode.put(fromX + "," + fromY, 0);


        while (!queue.isEmpty()) {
            String checking = queue.remove(0);
            visited.add(checking);
            System.out.println("checking: " + checking);
            if ((checking.equals(toX + "," + toY) && asc) ||
                    (!asc && map[Integer.parseInt(checking.split(",")[0])][Integer.parseInt(checking.split(",")[1])] == 'a')) {
                System.out.println("found end");
                return weightToNode.get(checking);
            }
            int checkX = Integer.parseInt(checking.split(",")[0]);
            int checkY = Integer.parseInt(checking.split(",")[1]);


            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 || Math.abs(x) == 1 && Math.abs(y) == 1) {
                        continue;
                    }
                    try {
                        // if neighbours valid step add to queue
                        int gap = map[checkX + x][checkY + y] - map[checkX][checkY];
                        if ((gap <= 1 && asc) || (gap >= -1 && !asc)) {
                            // if this route is quicker than current route so far
                            String nextNode = (checkX + x) + "," + (checkY + y);
                            if (visited.contains(nextNode)) {
                                continue;
                            }
                            if (weightToNode.get(nextNode) == null || weightToNode.get(nextNode) > weightToNode.get(checking) + 1) {
                                previousNode.put(nextNode, checking);
                                weightToNode.put(nextNode, weightToNode.get(checking) + 1);
                                insertToQueue(nextNode);
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("index oob");
                    }
                }
            }

        }
        return 0;
    }

    public long solve2() {
        try {
            visited = new ArrayList<>();
            queue = new ArrayList<>();
            previousNode = new HashMap<>();
            weightToNode = new HashMap<>();
            String input = Files.readString(Path.of("inputs/day12.txt"));
            genMap(input);

            return findPathLength(endX, endY, -1, -1, false);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}