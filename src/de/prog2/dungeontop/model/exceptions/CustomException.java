package de.prog2.dungeontop.model.exceptions;


import de.prog2.dungeontop.resources.PropertiesFilePaths;
import de.prog2.dungeontop.utils.PropertiesFileUtils;

public class CustomException extends Exception{

    private final static PropertiesFileUtils PROPERTIES_FILE_UTILS = new PropertiesFileUtils(PropertiesFilePaths.EXCEPTION_MESSAGES_PROPERTIES);

    public CustomException (String exceptionMessageKey, String... messages){
        String exceptionMessage = null;
        try {
            exceptionMessage = PROPERTIES_FILE_UTILS.getFileValue(exceptionMessageKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String appendedMessages = null;
        if (messages != null){
            StringBuilder builder = new StringBuilder();
            for (String message : messages) {
                builder.append(message).append(" ");
            }
            appendedMessages = builder.toString();
        }
        if (exceptionMessage != null || appendedMessages != null){
            System.err.println(exceptionMessage+appendedMessages);
        }
    }

}
