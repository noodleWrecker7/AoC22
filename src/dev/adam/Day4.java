package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day4
{
    public static void main(String[] args)
    {
        D4Part1 p1 = new D4Part1(Util.getFile("day2.txt"));
        System.out.println(p1.solve());
        D4Part2 p2 = new D4Part2(Util.getFile("day2.txt"));
        System.out.println(p2.solve());
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
        return score;
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
        return score;
    }
}
