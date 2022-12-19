package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day6
{

    BufferedReader fr;

    public Day6(BufferedReader fr)
    {
        this.fr = fr;
    }

    static Day6 day;

    public static void main(String[] args)
    {
        day = new Day6(Util.getFile("inputs/day6.txt"));
        System.out.println(day.solve1(4));
        day = new Day6(Util.getFile("inputs/day6.txt"));
        System.out.println(day.solve2());
    }

    public int solve1(int codeLength)
    {
        try
        {
            String input = fr.readLine();
            int i = 0;
            while (true)
            {
                String code = input.substring(i, i + codeLength);
                boolean valid = true;
                for (int j = 0; j < codeLength - 1; j++)
                {
                    if (code.substring(j + 1).contains(code.substring(j, j + 1)))
                    {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                {
                    return i + codeLength;
                }
                i++;
            }
        } catch (IOException ignored)
        {

        }

        return 0;
    }

    public int solve2()
    {
        return solve1(14);
    }

}