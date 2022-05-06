package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.defaultvalues.ColorKeys;
import de.prog2.dungeontop.model.defaultvalues.DefaultValuesFallback;
import de.prog2.dungeontop.model.defaultvalues.PropertiesFilePaths;
import de.prog2.dungeontop.model.exceptions.costumexceptions.InvalidKeyException;

import java.util.Scanner;

public class IOUtils
{

    private final static String ANSI_DEFAULT_MESSAGE_COLOR;
    private final static String ANSI_WARNING_MESSAGE_COLOR;
    private final static String ANSI_ERROR_MESSAGE_COLOR;

    private final static PropertiesFileUtils FILE_UTILS = new PropertiesFileUtils(PropertiesFilePaths.CONSOLE_COLORS_PROPERTIES);

    static {
        ANSI_DEFAULT_MESSAGE_COLOR = loadDefaultMessageColor();
        ANSI_WARNING_MESSAGE_COLOR = loadWarningMessageColors();
        ANSI_ERROR_MESSAGE_COLOR = loadErrorMessageColors();
    }


    public static void printMessage(String message)
    {
        System.out.println(ANSI_DEFAULT_MESSAGE_COLOR + message);
    }

    public static void printWarning(String message)
    {
        System.out.println(ANSI_WARNING_MESSAGE_COLOR + message + ANSI_DEFAULT_MESSAGE_COLOR);
    }

    public static final String ANSI_RED = "\u001B[31m";

    public static void printError(String message)
    {
        System.out.println(ANSI_ERROR_MESSAGE_COLOR + message + ANSI_DEFAULT_MESSAGE_COLOR);
    }

    public static void print (String text)
    {
        System.out.println(ANSI_DEFAULT_MESSAGE_COLOR + text);
    }

    public static String getNextInput ()
    {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.next();
    }

    public static void printStringArray (String[] sr)
    {
        StringBuilder sb = new StringBuilder();
        int z = 0;
        for (int i = 0; i < sr.length; i++)
        {
            sb.append(sr[i]);
            z += sr[i].length();
            if (z > 60)
            {
                z = 0;
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static String loadDefaultMessageColor() {
        try {
            return FILE_UTILS.getFileValue(ColorKeys.COLOR_DEFAULT);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return DefaultValuesFallback.DEFAULT_MESSAGE_COLOR;
        }
    }

    private static String loadWarningMessageColors(){
        try {
            return FILE_UTILS.getFileValue(ColorKeys.COLOR_WARNING);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return DefaultValuesFallback.WARNING_MESSAGE_COLOR;
        }
    }

    private static String loadErrorMessageColors(){
        try {
            return FILE_UTILS.getFileValue(ColorKeys.COLOR_ERROR);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return DefaultValuesFallback.ERROR_MESSAGE_COLOR;
        }
    }

}
