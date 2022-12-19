package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day4
{
    public static void main(String[] args)
    {
        D4Part1 p1 = new D4Part1(Util.getFile("inputs/day4.txt"));
        System.out.println(p1.solve());
        D4Part2 p2 = new D4Part2(Util.getFile("inputs/day4.txt"));
        System.out.println(p2.solve());
    }

}

class Range
{
    int start;
    int end;

    public Range(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
}

class D4Part1
{

    BufferedReader fr;

    public D4Part1(BufferedReader fr)
    {
        this.fr = fr;
    }


    public int solve()
    {
        int overlaps = 0;
        String line;
        try
        {
            while ((line = fr.readLine()) != null)
            {
                String[] parts = line.split(",");
                // I don't like how obtuse this is, but I don't care
                String[][] ranges = new String[][]{parts[0].split("-"), parts[1].split("-")};
                Range elf1 = new Range(Integer.parseInt(ranges[0][0]), Integer.parseInt(ranges[0][1]));
                Range elf2 = new Range(Integer.parseInt(ranges[1][0]), Integer.parseInt(ranges[1][1]));

                if (elf1.start >= elf2.start && elf1.end <= elf2.end || elf1.start <= elf2.start && elf1.end >= elf2.end)
                {
                    overlaps++;
                }
            }
        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
        return overlaps;
    }
}

class D4Part2
{

    BufferedReader fr;

    public D4Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int overlaps = 0;
        String line;
        try
        {
            while ((line = fr.readLine()) != null)
            {
                String[] parts = line.split(",");
                // I don't like how obtuse this is, but I don't care
                String[][] ranges = new String[][]{parts[0].split("-"), parts[1].split("-")};
                Range elf1 = new Range(Integer.parseInt(ranges[0][0]), Integer.parseInt(ranges[0][1]));
                Range elf2 = new Range(Integer.parseInt(ranges[1][0]), Integer.parseInt(ranges[1][1]));

                if (elf1.start <= elf2.end && elf1.end >= elf2.start)
                {
                    overlaps++;
                }
            }
        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
        return overlaps;
    }
}
