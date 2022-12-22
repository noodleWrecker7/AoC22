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
                    startX = j;
                    startY = i;
                }

                if (height == 'E') {
                    height = 'z';
                    endX = j;
                    endY = i;
                }
                map[j][i] = height;
            }
        }
    }

    public long solve1() {
        try {
            String input = Files.readString(Path.of("inputs/day11.txt"));
            genMap(input);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long solve2() {
        try {
            String input = Files.readString(Path.of("inputs/day11.txt"));

            String[] lines = input.split("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}