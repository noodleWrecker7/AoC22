package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

public class Day5
{
    public static void main(String[] args)
    {
        D5Part1 p1 = new D5Part1(Util.getFile("inputs/day5.txt"));
        System.out.println(p1.solve());
        D5Part2 p2 = new D5Part2(Util.getFile("inputs/day5.txt"));
        System.out.println(p2.solve());
    }

    static void readFiles(Stack<Character>[] stacks, BufferedReader fr)
    {
        String line;
        for (int i = 0; i < stacks.length; i++)
        {
            stacks[i] = new Stack<>();
        }
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
                    int charPos = i * 4 + 1;
                    if (line.substring(charPos, charPos + 1).equals(" "))
                    {
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
                while (!stack.isEmpty())
                {
                    newStack.push(stack.pop());
                }
                stacks[i] = newStack;
            }


        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
    }
}


class D5Part1
{

    BufferedReader fr;
    Stack<Character>[] stacks = new Stack[9];

    public D5Part1(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        Day5.readFiles(stacks, fr);

        // read instrictions
        String line;
        try
        {
            while ((line = fr.readLine()) != null)
            {
                String[] instruction = line.split(" ");
                int amt = Integer.parseInt(instruction[1]);
                int from = Integer.parseInt(instruction[3]);
                int to = Integer.parseInt(instruction[5]);

//                System.out.println("Move " + amt + " from " + from + " to " + to);

                for (int i = 0; i < amt; i++)
                {
                    try
                    {
                        stacks[to - 1].push(stacks[from - 1].pop());
                    } catch (EmptyStackException e)
                    {
                    }
                }
            }

            for (int i = 0; i < stacks.length; i++)
            {
                try
                {
                    System.out.print(stacks[i].peek());
                } catch (EmptyStackException e)
                {
                }
            }
        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
        return 0;
    }
}

class D5Part2
{

    BufferedReader fr;
    Stack<Character>[] stacks = new Stack[9];

    public D5Part2(BufferedReader fr)
    {
        this.fr = fr;
    }

    public int solve()
    {
        Day5.readFiles(stacks, fr);

        // read instrictions
        String line;
        try
        {
            while ((line = fr.readLine()) != null)
            {
                String[] instruction = line.split(" ");
                int amt = Integer.parseInt(instruction[1]);
                int from = Integer.parseInt(instruction[3]);
                int to = Integer.parseInt(instruction[5]);

//                System.out.println("Move " + amt + " from " + from + " to " + to);

                Stack<Character> tempStack = new Stack<>();
                for (int i = 0; i < amt; i++)
                {
                    try
                    {
                        tempStack.push(stacks[from - 1].pop());
                    } catch (EmptyStackException e)
                    {
                    }
                }
                while (!tempStack.isEmpty())
                {
                    stacks[to - 1].push(tempStack.pop());
                }
            }

            for (int i = 0; i < stacks.length; i++)
            {
                try
                {
                    System.out.print(stacks[i].peek());
                } catch (EmptyStackException e)
                {
                }
            }
        } catch (IOException e)
        {
            System.out.println("Uh Oh!");
        }
        return 0;
    }
}
