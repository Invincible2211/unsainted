package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.view.CardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class CardViewController
{
    public static Node init(Card card)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/cardView.fxml"));
            CardView controller = loader.getController();
            fillCardViewWithData(card, controller);
            return cardView;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static void fillCardViewWithData(Card card, CardView controller)
    {
        controller.getRankLabel().setText(String.format(StringValues.RANK, card.getRank(), card.getMaxRank()));
        controller.getSummonCostLabel().setText(String.format(StringValues.SUMMON_COST, card.getSummonCost()));
        controller.setCard(card);
    }
}
