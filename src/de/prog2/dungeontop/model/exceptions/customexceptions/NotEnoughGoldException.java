package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.model.defaultvalues.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class NotEnoughGoldException  extends CustomException {
    public NotEnoughGoldException(){ super(ExceptionMessagesKeys.NOT_ENOUGH_GOLD_EXCEPTION); }
}
