package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.util.HashMap;

public class AssetsFileReader {

    private final static AssetsFileReader instance = new AssetsFileReader();

    private final HashMap<Integer,String> assetPaths = new HashMap<>();

    private AssetsFileReader(){
        init();
    }

    private void init(){
        GlobalLogger.log(LoggerStringValues.INIT_ASSET_FILE_READER);
        File assetFolder = new File(FilePaths.ASSET_FOLDER);
        this.getAssetPaths(assetFolder);
        GlobalLogger.log(LoggerStringValues.INIT_FILE_READER_FINISHED);
    }

    private void getAssetPaths(File folder){
        for (File asset:
        folder.listFiles()) {
            if (asset.isDirectory()){
                this.getAssetPaths(asset);
            } else {
                GlobalLogger.log(String.format(LoggerStringValues.ASSET_FILE_NAME, asset.getName()));
                assetPaths.put(Integer.parseInt(asset.getName().substring(0,2)), asset.getAbsolutePath());
            }
        }
    }

    public File getAssetFile(int assetId){
        return new File(assetPaths.get(assetId));
    }


    public static AssetsFileReader getInstance() {
        return instance;
    }

}
