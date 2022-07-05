package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.AssetsFileReader;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class AssetsManager
{

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static AssetsManager instance = new AssetsManager();

    private final static HashMap<Integer, File> ASSETS = new HashMap<>();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ist private, da der AssetManager ein Singelton ist.
     */
    private AssetsManager(){

    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode wird benutzt, um auf die Assets zuzugreifen.
     * @param id die ID des Assets
     * @return ein File-Object, welches das Asset beinhaltet
     */
    public static File getAssetById (int id)
    {
        GlobalLogger.log(String.format(LoggerStringValues.STARTED_GET_ASSET_BY_ID, id));
        //Wurde ein Asset schon einmal geladen, ist dieses fuer weniger Festplattenzugriffe im Arbeitsspeicher geladen.
        if (ASSETS.containsKey(id)){
            GlobalLogger.log(String.format(LoggerStringValues.ASSET_FOUND, id));
            return ASSETS.get(id);
        //Assets die noch nicht geladen wurden, werden fuer weitere Zugriffe in den Arbeitsspeicher geladen.
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

    /**
     * Handling requests for Images with the specified parameters.
     *
     * @param id AssetId of the requested image
     * @param requestedWidth the image's bounding box width
     * @param requestedHeight the image's bounding box height
     * @param preserveRatio indicates whether to preserve the aspect ratio of the original image when
     *                      scaling to fit the image within the specified bounding box
     * @param smooth indicates whether to use a better quality filtering algorithm or a faster one when
     *               scaling this image to fit within the specified bounding box
     * @return Image object
     */
    public static Image getImageByAssetId (int id, double requestedWidth, double requestedHeight,
                                           boolean preserveRatio, boolean smooth)
    {
        Image image = null;
        try
        {
            image = new Image (
                    new FileInputStream(AssetsManager.getAssetById(id)),
                    requestedWidth, requestedHeight, preserveRatio, smooth
            );
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            GlobalLogger.warning(LoggerStringValues.FILE_NOT_FOUND);
        }
        return image;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static AssetsManager getInstance() {
        return instance;
    }

}
