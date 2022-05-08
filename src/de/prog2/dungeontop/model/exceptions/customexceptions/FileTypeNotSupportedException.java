package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.defaultvalues.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class FileTypeNotSupportedException extends CustomException
{

    public FileTypeNotSupportedException() {
        super(ExceptionMessagesKeys.FILE_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

}
