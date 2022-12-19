package dev.adam;

import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    BufferedReader fr;

    public Day11(BufferedReader fr) {
        this.fr = fr;
    }

    static Day11 day;

    public static void main(String[] args) {
        String inputFile = "inputs/day11.txt";
        day = new Day11(Util.getFile(inputFile));
        System.out.println(day.solve1());
        day = new Day11(Util.getFile(inputFile));
        System.out.println(day.solve2());
    }

    static class Monkey {

        ArrayList<Long> items = new ArrayList<>();

        enum Operation {
            add,
            multiply
        }

        Operation op;
        int opAmt;
        int test;
        int throwToIfTrue;
        int throwToIfFalse;
        Long numOfInspections = 0l;

        /*
        Starting items: 83, 97, 95, 67
        Operation: new = old * 19
        Test: divisible by 17
        If true: throw to monkey 2
        If false: throw to monkey 7
        */

        public static Monkey createMonkeyFromLines(String[] lines) {
            Monkey mon = new Monkey();
            String[] data = lines[1].substring(18).split(", ");
            for (String str : data) {
                mon.items.add(Long.parseLong(str));
            }

            data = lines[2].substring(23).split(" ");
            String op = data[0];
            if (op.equals("*")) {
                mon.op = Operation.multiply;
            } else {
                mon.op = Operation.add;
            }
            try {
                mon.opAmt = Integer.parseInt(data[1]);
            } catch (NumberFormatException e) {
                mon.opAmt = -1;
            }

            mon.test = Integer.parseInt(lines[3].substring(21));

            mon.throwToIfTrue = Integer.parseInt(lines[4].substring(29));
            mon.throwToIfFalse = Integer.parseInt(lines[5].substring(30));


            return mon;
        }

        void takeTurn(Monkey[] monkeys, int divisor) {
            while (!items.isEmpty()) {
                inspectFrontItem(divisor);
                throwFrontItem(monkeys);
            }
        }

        void receiveItem(long value) {
            items.add(value);
        }

        void throwFrontItem(Monkey[] monkeys) {
            if (items.get(0) % test == 0) {
                monkeys[throwToIfTrue].receiveItem(items.get(0));
            } else {
                monkeys[throwToIfFalse].receiveItem(items.get(0));
            }
            items.remove(0);
        }

        void inspectFrontItem(int divisor) {
            numOfInspections++;
            long value = items.get(0);
            if (op == Operation.add) {
                if (opAmt == -1) {
                    value *= 2;
                } else
                    value += opAmt;
            } else if (op == Operation.multiply) {
                if (opAmt == -1) {
                    value *= value;
                } else
                    value *= opAmt;
            }
            value = value % (19 * 7 * 17 * 11 * 13 * 3 * 5 * 2);
            value = value / divisor;
            items.set(0, value);
        }
    }

    Monkey[] monkeys;

    public long solve1() {
        try {
            String input = Files.readString(Path.of("inputs/day11.txt"));

            String[] lines = input.split("\n");
            monkeys = new Monkey[8];
            for (int i = 0; i < lines.length; i += 7) {
                monkeys[i / 7] = Monkey.createMonkeyFromLines(Arrays.copyOfRange(lines, i, i + 6));
            }

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < monkeys.length; j++) {
                    monkeys[j].takeTurn(monkeys, 3);
                }
            }
            Monkey[] biggestMons = new Monkey[2];
            for (int i = 0; i < monkeys.length; i++) {
                if (biggestMons[0] == null || monkeys[i].numOfInspections > biggestMons[0].numOfInspections) {
                    biggestMons[1] = biggestMons[0];
                    biggestMons[0] = monkeys[i];
                } else if (biggestMons[1] == null || monkeys[i].numOfInspections > biggestMons[1].numOfInspections) {
                    biggestMons[1] = monkeys[i];
                }
            }
            return biggestMons[0].numOfInspections * biggestMons[1].numOfInspections;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long solve2() {
        try {
            String input = Files.readString(Path.of("inputs/day11.txt"));

            String[] lines = input.split("\n");
            monkeys = new Monkey[8];
            for (int i = 0; i < lines.length; i += 7) {
                monkeys[i / 7] = Monkey.createMonkeyFromLines(Arrays.copyOfRange(lines, i, i + 6));
            }

            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < monkeys.length; j++) {
                    monkeys[j].takeTurn(monkeys, 1);
                }
            }
            Monkey[] biggestMons = new Monkey[2];
            for (int i = 0; i < monkeys.length; i++) {
                if (biggestMons[0] == null || monkeys[i].numOfInspections > biggestMons[0].numOfInspections) {
                    biggestMons[1] = biggestMons[0];
                    biggestMons[0] = monkeys[i];
                } else if (biggestMons[1] == null || monkeys[i].numOfInspections > biggestMons[1].numOfInspections) {
                    biggestMons[1] = monkeys[i];
                }
            }
            System.out.println(biggestMons[0].numOfInspections + "," + biggestMons[1].numOfInspections);
            return biggestMons[0].numOfInspections * biggestMons[1].numOfInspections;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}