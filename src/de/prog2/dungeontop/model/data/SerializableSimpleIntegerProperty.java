package de.prog2.dungeontop.model.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class SerializableSimpleIntegerProperty extends SimpleIntegerProperty implements Externalizable {

    private int value;
    public SerializableSimpleIntegerProperty(int value) {
        super(value);
        this.value = value;
        addListener((observable, oldValue, newValue) -> {
            this.setNewValue((Integer) newValue);
        });
    }

    public SerializableSimpleIntegerProperty() {}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        changeValue(in.readInt());
        addListener((observable, oldValue, newValue) -> {
            this.setNewValue((Integer) newValue);
        });
    }

    private void changeValue(int value){
        this.value = value;
        super.setValue(value);
    }

    public void setNewValue(int value) {
        this.value = value;
    }

}
