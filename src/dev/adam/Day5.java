package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class Day5
{
    public static void main(String[] args)
    {
        D5Part1 p1 = new D5Part1(Util.getFile("day5.txt"));
        System.out.println(p1.solve());
        D5Part2 p2 = new D5Part2(Util.getFile("day5.txt"));
        System.out.println(p2.solve());
    }

}


class D5Part1
{

    BufferedReader fr;
    Stack<Character>[] stacks;

    public D5Part1(BufferedReader fr)
    {
        this.fr = fr;
    }

    public void readFile()
    {
        String line;
        try
        {
            while ((line = fr.readLine()) != null)
            {
                if (!line.contains("["))
                {
                    fr.readLine();
                    break;
                }

                int numOfStacks = ((line.length() - 1) / 4) + 1;
                for (int i = 0; i < numOfStacks; i++)
                {
                    int charPos = i * 4 +1;
                    System.out.println(line.substring(charPos, charPos + 1));
                    if(line.substring(charPos, charPos + 1).equals(" ")){
                       continue;
                    }
                    stacks[i].add(line.charAt(charPos));
                }
            }
            // reverse stacks
            for (int i = 0; i < stacks.length; i++)
            {
                Stack<Character> stack = stacks[i];
                Stack<Character> newStack = new Stack<>();
                for (int j = 0; j < stack.size(); j++)
                {
                    
                }
            }

            // read instrictions
        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
    }

    public int solve()
    {
        readFile();
        return 0;
    }
}

class D5Part2
{

    BufferedReader fr;

    public D5Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        return 0;
    }
}
