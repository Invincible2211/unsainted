package de.prog2.dungeontop.control.manager;

import com.google.gson.GsonBuilder;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

public class AssetsManager {

    private final static HashMap<Integer,Object> ASSETS = new HashMap<>();

    private static String inPath = "";
    private static String outPath = "";

    static {
        loadAssets();
    }

    private static void loadAssets(){

    }

    public static File getAsset(int id){
        File dir = new File(inPath);
        String[] pathNames = dir.list();
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept (File dir, String name)
            {
                return true;
            }
        };
        File[] files = dir.listFiles(filter);
        Scanner sc = null;
        StringBuilder sb = new StringBuilder();
        for (File file :
                files) {
               if (getIdFromJsonFile(file, file.getClass()) == id){
                   return file;
               }
        }

        sc.close();
        GlobalLogger.warning(ExceptionMessagesKeys.CAN_NOT_FIND_FILE_EXCEPTION);
        return null;

    }

    /**
     * returns the ID of a JsonFile by trying to make a JSON from it.
     * @param jsonFile
     * @param type
     * @return id
     */
    private static int getIdFromJsonFile (File jsonFile, Type type) {
        Scanner sc = null;
        StringBuilder sb = new StringBuilder();
        try {
            sc = new Scanner(jsonFile);
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
           return Integer.getInteger(gsonBuilder.create().fromJson(sb.toString(), type)); //hiervon ein getID wenn es eine JSON ware.

        } catch (FileNotFoundException e) {
            GlobalLogger.warning(ExceptionMessagesKeys.CAN_NOT_LOAD_FILE);
            e.printStackTrace();
        }
        GlobalLogger.warning(ExceptionMessagesKeys.NO_ID_FOUND_EXCEPTION);
        return 0;
    }

    public static void saveAsset()
    {

    }

}
