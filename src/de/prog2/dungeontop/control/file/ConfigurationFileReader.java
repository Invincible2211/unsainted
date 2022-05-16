package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationFileReader {


    private final static ConfigurationFileReader instance = new ConfigurationFileReader();

    private final Properties properties = new Properties();

    private ConfigurationFileReader (){
        GlobalLogger.log(LoggerStringValues.LOAD_CONFIG);
        try {
            properties.load(new FileInputStream(new File(FilePaths.CONFIGURATION_FILE_PATH)));
            GlobalLogger.log(LoggerStringValues.CONFIG_LOADED);
        } catch (IOException e) {
            GlobalLogger.warning(LoggerStringValues.CONFIG_LOAD_ERROR);
            throw new RuntimeException(e);
        }
    }

   public String getConfigurationValue(String key)
   {
       String result = properties.getProperty(key);
       if (result == null)
       {
           // TODO: Fynn2 mach sachen
           GlobalLogger.warning(LoggerStringValues.CONFIG_KEY_DOES_NOT_EXISTS);
       }
       return result;
   }

    public static ConfigurationFileReader getInstance() {
        return instance;
    }

}
