package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class NotEnoughSoulsException extends CustomException {
    public NotEnoughSoulsException(){ super(ExceptionMessagesKeys.NOT_ENOUGH_SOULS_EXCEPTION); }
}
