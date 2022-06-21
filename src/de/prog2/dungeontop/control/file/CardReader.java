package de.prog2.dungeontop.control.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CardReader {

    private final List<Card> cards = new ArrayList<>();

    private final static CardReader instance = new CardReader();

    private CardReader(){
        try {
            loadCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCards() throws FileNotFoundException {
        GlobalLogger.log(LoggerStringValues.CARDS_LOADING);
        JsonParser jsonParser = new JsonParser();
        File folder = new File(FilePaths.CARDS_FOLDER);
        for (File f:
             folder.listFiles()) {
            JsonElement jsonFile = jsonParser.parse(new FileReader(f));
            //MinionCard card = new MinionCard(0,0,0,0); //TODO Json -> card
            //cards.add(card);
            GlobalLogger.log(LoggerStringValues.LOAD_CARD);
        }
        GlobalLogger.log(LoggerStringValues.CARDS_LOADED);
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public static CardReader getInstance() {
        return instance;
    }

}
