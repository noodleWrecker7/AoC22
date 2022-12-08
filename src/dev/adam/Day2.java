package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day2
{
    public static void main(String[] args)
    {
        D2Part1 p1 = new D2Part1(Util.getFile("day2.txt"));
        System.out.println(p1.solve());
        D2Part2 p2 = new D2Part2(Util.getFile("day2.txt"));
        System.out.println(p2.solve());
    }

}

class D2Part1
{

    BufferedReader fr;

    public D2Part1(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int score = 0;

        try
        {
            String line;
            while ((line = fr.readLine()) != null)
            {
                char opp = line.charAt(0);
                switch (line.charAt(2))
                {
                    case 'X':
                        score += 1;
                        if (opp == 'A') score += 3;
                        if (opp == 'B') score += 0;
                        if (opp == 'C') score += 6;
                        break;
                    case 'Y':
                        score += 2;
                        if (opp == 'A') score += 6;
                        if (opp == 'B') score += 3;
                        if (opp == 'C') score += 0;
                        break;
                    case 'Z':
                        score += 3;
                        if (opp == 'A') score += 0;
                        if (opp == 'B') score += 6;
                        if (opp == 'C') score += 3;
                        break;

                }
            }
        } catch (IOException e)
        {

        }
        return score;
    }
}

class D2Part2
{

    BufferedReader fr;

    public D2Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        int score = 0;

        try
        {
            String line;
            while ((line = fr.readLine()) != null)
            {
                char opp = line.charAt(0);
                switch (line.charAt(2))
                {
                    case 'X':
                        score += 0;
                        if (opp == 'A') score += 3;
                        if (opp == 'B') score += 1;
                        if (opp == 'C') score += 2;
                        break;
                    case 'Y':
                        score += 3;
                        if (opp == 'A') score += 1;
                        if (opp == 'B') score += 2;
                        if (opp == 'C') score += 3;
                        break;
                    case 'Z':
                        score += 6;
                        if (opp == 'A') score += 2;
                        if (opp == 'B') score += 3;
                        if (opp == 'C') score += 1;
                        break;

                }
            }
        } catch (IOException e)
        {

        }
        return score;
    }
}
