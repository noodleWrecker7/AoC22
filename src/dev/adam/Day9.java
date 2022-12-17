package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9
{

    BufferedReader fr;

    public Day9(BufferedReader fr)
    {
        this.fr = fr;
    }

    static Day9 day;

    public static void main(String[] args)
    {
        String inputFile = "day9.txt";
        day = new Day9(Util.getFile(inputFile));
        System.out.println(day.solve1());
        day = new Day9(Util.getFile(inputFile));
        System.out.println(day.solve2());
    }

    int headX = 0, headY = 0;
    ArrayList<String> visited = new ArrayList<>();

    public void addToVisited(int x, int y)
    {
        visited.add(x + "," + y);
    }

    public int solve1()
    {
        try
        {
            String line;
            while ((line = fr.readLine()) != null)
            {
                String dir = line.split(" ")[0];
                int dist = Integer.parseInt(line.split(" ")[1]);
                for (int i = 0; i < dist; i++)
                {
                    
                }
            }
        } catch (IOException ignored)
        {
        }
        return 0;
    }

    public int solve2()
    {
        return 0;
    }

}