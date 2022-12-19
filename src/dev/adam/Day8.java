package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;

public class Day8
{

    BufferedReader fr;

    public Day8(BufferedReader fr)
    {
        this.fr = fr;
    }

    static Day8 day;

    public static final int WIDTH = 99, HEIGHT = 99;

    public static void main(String[] args)
    {
        day = new Day8(Util.getFile("inputs/day8.txt"));
        System.out.println(day.solve1());
        day = new Day8(Util.getFile("inputs/day8.txt"));
        System.out.println(day.solve2());
    }

    Tree[][] grid = new Tree[WIDTH][HEIGHT];

    public void setupGrid()
    {
        try
        {
            String line;
            int y = 0;
            while ((line = fr.readLine()) != null)
            {
                String[] values = line.split("");
                for (int i = 0; i < values.length; i++)
                {
                    grid[i][y] = new Tree(Integer.parseInt(values[i]));
                }
                y++;
            }
        } catch (IOException ignored)
        {
        }
    }

    int totalVisible = 0;

    class Tree
    {
        public Tree(int _height)
        {
            this.height = _height;
        }

        int height;
        boolean visible = false;

        public void setVisible()
        {
            if (!visible)
            {
                totalVisible++;
            }
            visible = true;
        }
    }

    public void checkFromTop()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            int heightToBeat = -1;
            for (int y = 0; y < HEIGHT; y++)
            {
                if (grid[x][y].height > heightToBeat)
                {
                    grid[x][y].setVisible();
                    heightToBeat = grid[x][y].height;
                }
            }
        }
    }

    public void checkFromRight()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            int heightToBeat = -1;
            for (int x = WIDTH - 1; x >= 0; x--)
            {
                if (grid[x][y].height > heightToBeat)
                {
                    grid[x][y].setVisible();
                    heightToBeat = grid[x][y].height;
                }
            }
        }
    }

    public void checkFromLeft()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            int heightToBeat = -1;
            for (int x = 0; x < WIDTH; x++)
            {
                if (grid[x][y].height > heightToBeat)
                {
                    grid[x][y].setVisible();
                    heightToBeat = grid[x][y].height;
                }
            }
        }
    }


    public void checkFromBottom()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            int heightToBeat = -1;
            for (int y = HEIGHT - 1; y >= 0; y--)
            {
                if (grid[x][y].height > heightToBeat)
                {
                    grid[x][y].setVisible();
                    heightToBeat = grid[x][y].height;
                }
            }
        }
    }

    public int solve1()
    {
        setupGrid();
        checkFromLeft();
        checkFromTop();
        checkFromBottom();
        checkFromRight();

        return totalVisible;
    }

    public int calculateScenicScore(int x, int y)
    {
        int height = grid[x][y].height;
        int[] totals = new int[4];
        int checkX = x, checkY = y;
        int lookDir = 0;
        while (lookDir < 4)
        {
            switch (lookDir)
            {
                case 0:
                    checkX++;
                    break;
                case 1:
                    checkX--;
                    break;
                case 2:
                    checkY++;
                    break;
                case 3:
                    checkY--;
                    break;
            }
            if (checkX < 0 || checkY < 0 || checkX >= WIDTH || checkY >= HEIGHT)
            {
                lookDir++;
                checkY = y;
                checkX = x;
                continue;
            }
            totals[lookDir]++;
            if (grid[checkX][checkY].height < height)
            {
                continue;
            }
            checkY = y;
            checkX = x;
            lookDir++;
        }
        return totals[0] * totals[1] * totals[2] * totals[3];
    }

    public int solve2()
    {
        setupGrid();
        int max = 0;
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                int score = calculateScenicScore(x, y);
                if (score > max)
                {
                    max = score;
                }
            }
        }
        return max;
    }

}