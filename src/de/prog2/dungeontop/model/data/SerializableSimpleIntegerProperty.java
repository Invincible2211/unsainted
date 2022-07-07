package de.prog2.dungeontop.model.data;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class SerializableSimpleIntegerProperty extends SimpleIntegerProperty implements Serializable {

    public SerializableSimpleIntegerProperty(int value) {
        super(value);
    }
}
