package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.resources.ApplicationConstants;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalLogger {

    private static final Logger LOGGER = Logger.getGlobal();

    public static void log(String message){
        log(message, LoggerLevel.DEBUG);
    }

    public static void log(String message, LoggerLevel loggerLevel){
        if (ApplicationConstants.LOGGER_LEVEL == LoggerLevel.DEBUG){
            LOGGER.log(Level.INFO, message);
        } else if (!(ApplicationConstants.LOGGER_LEVEL == LoggerLevel.NONE)){
            if (loggerLevel == LoggerLevel.DEFAULT){
                LOGGER.log(Level.INFO, message);
            }
        }
    }

    public static void warning(String message){
        LOGGER.warning(message);
    }

    public static void turnOff () {
        LOGGER.setLevel(Level.OFF);
    }

    public static enum LoggerLevel{
        NONE,DEFAULT,DEBUG;
    }

}
