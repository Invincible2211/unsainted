package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class CardAlreadyUnlockedException extends CustomException {
    public CardAlreadyUnlockedException (){ super(ExceptionMessagesKeys.CARD_ALREADY_UNLOCKED_EXCEPTION); }
}
