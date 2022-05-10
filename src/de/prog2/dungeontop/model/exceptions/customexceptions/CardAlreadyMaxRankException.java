package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class CardAlreadyMaxRankException extends CustomException {
    public CardAlreadyMaxRankException(){ super(ExceptionMessagesKeys.CARD_ALREADY_MAX_RANK_EXCEPTION); }
}
