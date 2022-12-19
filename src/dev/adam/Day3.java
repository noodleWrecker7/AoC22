package dev.adam;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3
{
    public static void main(String[] args)
    {
        D3Part1 p1 = new D3Part1(Util.getFile("inputs/day3.txt"));
        System.out.println(p1.solve());
        D3Part2 p2 = new D3Part2(Util.getFile("inputs/day3.txt"));
        System.out.println(p2.solve());
    }

    static int charToInt(char input)
    {
        int value = input;
        if (value >= 97)
        {
            value -= 96;
        } else
        {
            value = value - 38;
        }
        return value;
    }

}

class D3Part1
{

    BufferedReader fr;

    public D3Part1(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int totalPriority = 0;
        int[] counts = new int[52];

        try
        {
            String line;
            while ((line = fr.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    int item = Day3.charToInt(line.charAt(i));
                    if (i < line.length() / 2)
                    {
                        counts[item - 1]++;
                    } else if (counts[item - 1] > 0)
                    {
                        totalPriority += item;
                        counts = new int[52];
                        break;
                    }

                }
            }
        } catch (IOException e)
        {

        }
        return totalPriority;
    }


}

class D3Part2
{

    BufferedReader fr;

    public D3Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int totalPriority = 0;
        int[] counts = new int[52];

        try
        {
            String line1;
            ArrayList<Integer> sharedIn1n2;
            while ((line1 = fr.readLine()) != null)
            {
                String line2 = fr.readLine();
                String line3 = fr.readLine();

                sharedIn1n2 = new ArrayList<>();

                for (int i = 0; i < line1.length(); i++)
                {
                    int item = Day3.charToInt(line1.charAt(i));
                    counts[item - 1]++;
                }
                for (int i = 0; i < line2.length(); i++)
                {
                    int item = Day3.charToInt(line2.charAt(i));
                    if (counts[item - 1] > 0)
                    {
                        sharedIn1n2.add(item);
                        counts[item - 1] = -1;
                    }
                }
                for (int i = 0; i < sharedIn1n2.size(); i++)
                {
                    char item = intToChar(sharedIn1n2.get(i));
                    if (line3.contains(Character.toString(item)))
                    {
                        totalPriority += sharedIn1n2.get(i);
                        break;
                    }
                }

                counts = new int[52];

            }
        } catch (IOException e)
        {

        }
        return totalPriority;
    }

    char intToChar(int i)
    {
        if (i > 26)
        {
            return (char) (i + 38);
        } else
        {
            return (char) (i + 96);
        }

    }
}
