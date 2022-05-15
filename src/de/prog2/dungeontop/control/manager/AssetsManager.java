package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.AssetsFileReader;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.util.HashMap;

public class AssetsManager
{

    private final static HashMap<Integer, File> ASSETS = new HashMap<>();

    public static File getAssetById (int id)
    {
        GlobalLogger.log();
        if (ASSETS.containsKey(id)){
            GlobalLogger.log();
            return ASSETS.get(id);
        } else {
            File asset = AssetsFileReader.getInstance().getAssetFile(id);
            ASSETS.put(id, asset);
            GlobalLogger.log();
            return  asset;
        }
    }

    public static int getIdFromAsset (File asset)
    {
        GlobalLogger.log();
        for (Integer key :
                ASSETS.keySet()) {
            if (ASSETS.get(key) == asset) {
                GlobalLogger.log();
                return key;
            }
        }
        GlobalLogger.warning(ExceptionMessagesKeys.NO_ID_FOUND_EXCEPTION);
        return -1;
    }

}
