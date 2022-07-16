package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Deprecated
public class ConfigurationFileReader
{

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static ConfigurationFileReader instance = new ConfigurationFileReader();

    private final Properties properties = new Properties();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor laedt die .properties Datei, welche verwendet werden soll.
     */
    private ConfigurationFileReader ()
    {
        GlobalLogger.log(LoggerStringValues.LOAD_CONFIG);
        try
        {
            properties.load(new FileInputStream(FilePaths.CONFIGURATION_FILE_PATH));
            GlobalLogger.log(LoggerStringValues.CONFIG_LOADED);
        } catch (IOException e)
        {
            GlobalLogger.warning(LoggerStringValues.CONFIG_LOAD_ERROR);
            throw new RuntimeException(e);
        }
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode gibt den Wert eines Eintrags in der Property-Datei fuer einen bestimmten Key zurueck.
     * @param key ein String, der als identifier fuer den value dient
     * @return ein String, der den in der .properties-Datei gespeicherten Wert enthaelt,
     * existiert kein Eintrag ist das Ergebnis der Abfrage null
     */
   public String getConfigurationValue(String key)
   {
       String result = properties.getProperty(key);
       if (result == null)
       {
           GlobalLogger.warning(LoggerStringValues.CONFIG_KEY_DOES_NOT_EXISTS);
       }
       return result;
   }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static ConfigurationFileReader getInstance()
    {
        return instance;
    }

}
