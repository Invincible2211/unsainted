package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.AssetsFileReader;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class AssetsManager
{

    private final static AssetsManager instance = new AssetsManager();

    private final static HashMap<Integer, File> ASSETS = new HashMap<>();

    private AssetsManager(){

    }

    public static File getAssetById (int id)
    {
        GlobalLogger.log(String.format(LoggerStringValues.STARTED_GET_ASSET_BY_ID, id));
        if (ASSETS.containsKey(id)){
            GlobalLogger.log(String.format(LoggerStringValues.ASSET_FOUND, id));
            return ASSETS.get(id);
        } else {
            File asset = AssetsFileReader.getInstance().getAssetFile(id);
            ASSETS.put(id, asset);
            GlobalLogger.log(String.format(LoggerStringValues.ASSET_NOT_FOUND, id));
            return asset;
        }
    }

    /**
     * Handling requests for Images
     *
     * @param id AssetId of the requested image
     * @return Image object
     */
    public static Image getImageByAssetId (int id)
    {
        Image image = null;
        try
        {
            image = new Image (new FileInputStream(AssetsManager.getAssetById(id)));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            GlobalLogger.warning(LoggerStringValues.FILE_NOT_FOUND);
        }
        return image;
    }

    public static int getIdFromAsset (File asset)
    {
        GlobalLogger.log(String.format(LoggerStringValues.STARTED_GET_ID_BY_ASSET, asset.getName()));
        for (Integer key :
                ASSETS.keySet()) {
            if (ASSETS.get(key) == asset) {
                GlobalLogger.log(String.format(LoggerStringValues.ASSET_ID_FOUND, asset.getName()));
                return key;
            }
        }
        GlobalLogger.warning(ExceptionMessagesKeys.NO_ID_FOUND_EXCEPTION);
        return -1;
    }

    public static AssetsManager getInstance() {
        return instance;
    }

}
