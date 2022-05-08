package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.defaultvalues.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class InvalidKeyException extends CustomException
{
    public InvalidKeyException(String key) {
        super(ExceptionMessagesKeys.INVALID_KEY_EXCEPTION, key);
    }
}
