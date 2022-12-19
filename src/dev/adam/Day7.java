package dev.adam;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Day7
{

    BufferedReader fr;

    public Day7(BufferedReader fr)
    {
        this.fr = fr;
    }

    static Day7 day;

    public static void main(String[] args)
    {
        day = new Day7(Util.getFile("inputs/day7.txt"));
        System.out.println(day.solve1());
        day = new Day7(Util.getFile("inputs/day7.txt"));
        System.out.println(day.solve2());
    }

    HashMap<String, Integer> paths;


    public void setupTree()
    {
        paths = new HashMap<>();
        String line;
        String currentPath = "";
        try
        {
            while ((line = fr.readLine()) != null)
            {
                if (line.charAt(0) == '$')
                {
                    String command = line.substring(2, 4);
                    if (command.equals("ls"))
                    {

                    } else if (command.equals("cd"))
                    {
                        String path = line.substring(5);
                        if (path.equals(".."))
                        {
                            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
                        } else if (path.equals("/"))
                        {
                            currentPath = "/";
                        } else
                        {
                            currentPath += "/" + path;
                        }
                        if (!paths.containsKey(currentPath))
                        {
                            paths.put(currentPath, 0);
                        }
                    }
                } else
                {
                    String[] data = line.split(" ");
                    if (!data[0].equals("dir"))
                    {
                        int size = Integer.parseInt(data[0]);
                        recursiveAddSize(size, currentPath);
                    }

                }
            }
        } catch (IOException ignored)
        {
        }
    }

    public int solve1()
    {
        setupTree();

        int total = 0;
        for (String key : paths.keySet())
        {
            int size = paths.get(key);
            if (size <= 100000)
            {
                total += size;
            }
        }
        return total;
    }

    public void recursiveAddSize(int size, String path)
    {
        paths.put(path, paths.get(path) + size);
        if (path.equals("/"))
        {
            return;
        }
        path = path.substring(0, path.lastIndexOf("/"));
        recursiveAddSize(size, path);
    }

    public int solve2()
    {
        setupTree();
        int totalSpace = 70000000;
        int requiredSpace = 30000000;
        int maxSize = totalSpace - requiredSpace;
        int amountToLose = paths.get("/") - maxSize;

        int smallestSoFar = 0;
        for (int value: paths.values())
        {
            if(value >= amountToLose && (value < smallestSoFar || smallestSoFar == 0)) {
                smallestSoFar = value;
            }
        }

        return smallestSoFar;
    }

}