package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.model.exceptions.customexceptions.InvalidKeyException;
import de.prog2.dungeontop.resources.FilePaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationFileReader {

    private final static ConfigurationFileReader instance = new ConfigurationFileReader();

    private final Properties properties = new Properties();

    private ConfigurationFileReader (){
        try {
            properties.load(new FileInputStream(new File(FilePaths.CONFIGURATION_FILE_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConfigurationValue(String key) throws InvalidKeyException {
        String result = properties.getProperty(key);
        if (result == null) throw new InvalidKeyException(key);
        return result;
    }

    public static ConfigurationFileReader getInstance() {
        return instance;
    }

}
