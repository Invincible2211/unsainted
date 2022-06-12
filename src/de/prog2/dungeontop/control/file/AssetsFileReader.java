package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.util.HashMap;

public class AssetsFileReader
{

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static AssetsFileReader instance = new AssetsFileReader();

    private final HashMap<Integer,String> assetPaths = new HashMap<>();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ruft die {@link #init()}-Methode auf, um sich die Assets schon einmal rauszusuchen.
     */
    private AssetsFileReader()
    {
        init();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Die init() laedt den Ordner der Assets und ruft anschließend fuer diesen
     * die {@link #getAssetPaths(File)} Methode auf.
     */
    private void init()
    {
        GlobalLogger.log(LoggerStringValues.INIT_ASSET_FILE_READER);
        File assetFolder = new File(FilePaths.ASSET_FOLDER);
        this.getAssetPaths(assetFolder);
        GlobalLogger.log(LoggerStringValues.INIT_FILE_READER_FINISHED);
    }

    /**
     * Die Methode speichert die Pfade aller assets aus dem Asset-Ordner zusammen mit der jeweiligen ID in einer
     * Hashmap ab. Die ID des Assets ergibt sich aus den beiden Zahlen am Anfang des Asset-Dateinamens.
     * @param folder der Asset Ordner
     */
    private void getAssetPaths(File folder)
    {
        for (File asset:
        folder.listFiles())
        {
            if (asset.isDirectory())
            {
                this.getAssetPaths(asset);
            } else
            {
                GlobalLogger.log(String.format(LoggerStringValues.ASSET_FILE_NAME, asset.getName()));
                assetPaths.put(Integer.parseInt(asset.getName().substring(0,2)), asset.getAbsolutePath());
            }
        }
    }

    /**
     * Diese Methode gibt für eine ID das jeweilige Asset zurueck.
     * @param assetId die ID des Assets (00 <= ID <= 99)
     * @return das Asset als File-Object
     */
    public File getAssetFile(int assetId)
    {
        return new File(assetPaths.get(assetId));
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/


    public static AssetsFileReader getInstance()
    {
        return instance;
    }

}
