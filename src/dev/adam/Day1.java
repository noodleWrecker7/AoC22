package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day1
{
    public static void main(String[] args)
    {

        String john = "";
        john.compareTo("");
        D1Part1 p1 = new D1Part1(Util.getFile("day1.txt"));
        System.out.println(p1.solve());
        D1Part2 p2 = new D1Part2(Util.getFile("day1.txt"));
        System.out.println(p2.solve());
    }

}

class D1Part1
{

    BufferedReader fr;

    public D1Part1(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int maxElfTotal = 0;
        try
        {

            int currentElfTotal = 0;
            String line;
            while ((line = fr.readLine()) != null)
            {
                try
                {
                    currentElfTotal += Integer.parseInt(line);
                } catch (NumberFormatException e)
                {
                    if (currentElfTotal > maxElfTotal)
                    {
                        maxElfTotal = currentElfTotal;
                    }
                    currentElfTotal = 0;
                }
            }
        } catch (IOException e)
        {
            System.out.println("a");
        }
        return maxElfTotal;
    }
}

class D1Part2
{

    BufferedReader fr;

    public D1Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int[] maxElvesTotal = new int[3];
        try
        {

            int currentElfTotal = 0;
            String line;
            while ((line = fr.readLine()) != null)
            {
                try
                {
                    currentElfTotal += Integer.parseInt(line);
                } catch (NumberFormatException e)
                {
                    for (int i = 2; i >= 0; i--)
                    {
                        if (currentElfTotal > maxElvesTotal[i])
                        {
                            //shift down
                            for (int j = 0; j < i; j++)
                            {
                                maxElvesTotal[j] = maxElvesTotal[j + 1];
                            }
                            maxElvesTotal[i] = currentElfTotal;
                            break;
                        }
                    }
                    currentElfTotal = 0;
                }
            }
        } catch (IOException e)
        {
            System.out.println("a");
        }
        int sum = 0;
        for (int i = 0; i < 3; i++)
        {
            sum += maxElvesTotal[i];
        }
        return sum;
    }
}