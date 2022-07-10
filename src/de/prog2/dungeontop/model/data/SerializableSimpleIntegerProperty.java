package de.prog2.dungeontop.model.data;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;

public class SerializableSimpleIntegerProperty extends SimpleIntegerProperty implements Externalizable {

    int value;
    public SerializableSimpleIntegerProperty ()
    {
        super();
    }
    public SerializableSimpleIntegerProperty(int value) {
        super(value);
        this.value = value;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setValue(in.readInt());
    }

    public void setValue(int value) {
        this.value = value;
        super.setValue(value);
    }

}