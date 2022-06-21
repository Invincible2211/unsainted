package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.exceptions.CustomException;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;

public class NotAValidRoomtypeException extends CustomException {
    public NotAValidRoomtypeException () { super(ExceptionMessagesKeys.NO_VALID_ROOMTYPE); }
}
