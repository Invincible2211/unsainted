package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.defaultvalues.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class CardNotFoundException extends CustomException {
    public CardNotFoundException(){ super(ExceptionMessagesKeys.CARD_NOT_FOUND_EXCEPTION); }
}
