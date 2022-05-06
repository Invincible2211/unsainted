package de.prog2.dungeontop.utils;

import com.sun.tools.javac.Main;
import de.prog2.dungeontop.model.exceptions.costumexceptions.FileTypeNotSupportedException;
import de.prog2.dungeontop.model.exceptions.costumexceptions.InvalidKeyException;
import de.prog2.dungeontop.model.defaultvalues.StringValues;

import java.io.IOException;
import java.util.Properties;


public class PropertiesFileUtils {

    private final Properties PROPERTIES = new Properties();

    public PropertiesFileUtils (String path){
        try
        {
            this.checkInputFileType(path);
            this.PROPERTIES.load(Main.class.getClassLoader().getResourceAsStream(path));
        } catch (FileTypeNotSupportedException | IOException exception)
        {
            IOUtils.printError(exception.getMessage());
        }
    }

    public String getFileValue(String key) throws InvalidKeyException
    {
        String result = PROPERTIES.getProperty(key);
        if (result == null) throw new InvalidKeyException(key);
        return result;
    }

    public boolean containsKey(String key)
    {
        return PROPERTIES.containsKey(key);
    }

    private void checkInputFileType(String path) throws FileTypeNotSupportedException
    {
        String[] fileNameSplited = path.split("\\.");
        if (!fileNameSplited[fileNameSplited.length-1].equals(StringValues.FILE_TYPE)) throw new FileTypeNotSupportedException();
    }

}
