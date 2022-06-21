package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.exceptions.CustomException;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;

public class SpotAlreadyOccupiedException extends CustomException {
    public SpotAlreadyOccupiedException () { super(ExceptionMessagesKeys.SPOT_ALREADY_OCCUPIED); }
}
