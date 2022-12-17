package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {

    BufferedReader fr;

    public Day10(BufferedReader fr) {
        this.fr = fr;
    }

    static Day10 day;

    public static void main(String[] args) {
        String inputFile = "day10.txt";
        day = new Day10(Util.getFile(inputFile));
        System.out.println(day.solve1());
        day = new Day10(Util.getFile(inputFile));
        System.out.println(day.solve2());
    }

    int cycle = 0;
    int value = 1;
    int total = 0;

    public void incrCycle() {
        cycle++;
        if (cycle > 220) {
            return;
        }
        if (cycle % 40 == 20) {
            total += value * cycle;
        }
    }

    String output = "";

    public void incrCycle2() {
        System.out.println(value + "," + cycle);
        if (value == cycle % 40 || value - 1 == cycle % 40 || value + 1 == cycle % 40) {
            output += "#";
            System.out.println("outed");
        } else {
            output += ".";
        }
        cycle++;


        if (cycle % 40 == 0) {
            output += "\n";
        }
    }

    public int solve1() {
        try {
            String line;
            while ((line = fr.readLine()) != null) {
                String[] input = line.split(" ");
                if (input[0].equals("noop")) {
                    incrCycle();
                } else if (input[0].equals("addx")) {
                    int x = Integer.parseInt(input[1]);
                    incrCycle();
                    incrCycle();
                    value += x;
                }
            }
            return total;
        } catch (Exception e) {

        }
        return 0;
    }

    public int solve2() {
        try {
            cycle = 0;
            value = 1;
            String line;
            while ((line = fr.readLine()) != null) {
                String[] input = line.split(" ");
                if (input[0].equals("noop")) {
                    incrCycle2();
                } else if (input[0].equals("addx")) {
                    int x = Integer.parseInt(input[1]);
                    incrCycle2();
                    incrCycle2();
                    value += x;
                }
            }
            System.out.println(output);
            return total;
        } catch (Exception e) {

        }
        return 0;
    }

}