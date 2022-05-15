package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.exceptions.CustomException;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;

public class NotValidRngRollException extends CustomException {
    public NotValidRngRollException () { super(ExceptionMessagesKeys.NOT_VALID_RNG); }
}
