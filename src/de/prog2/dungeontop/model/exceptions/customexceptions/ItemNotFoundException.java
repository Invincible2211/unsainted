package de.prog2.dungeontop.model.exceptions.customexceptions;

import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.model.exceptions.CustomException;

public class ItemNotFoundException extends CustomException {
    public ItemNotFoundException (){ super(ExceptionMessagesKeys.ITEM_NOT_FOUND_EXCEPTION); }
}
