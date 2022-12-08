package dev.adam;

import java.io.BufferedReader;
import java.io.FileReader;

public class Util
{
    public static BufferedReader getFile(String file)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader;
        } catch (Exception e) {
            System.exit(2);
        }
        return null;
    }
}
