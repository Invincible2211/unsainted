package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.HBox;

public abstract class HandViewAbstract extends HBox
{
    public HandViewAbstract()
    {
        PlayerManager.getInstance().getPlayer().getHandCards().addListener((ListChangeListener<Card>) c -> updateHand());
    }

    private void updateHand()
    {
        removeAll();
        for (Card card : PlayerManager.getInstance().getPlayer().getHandCards())
        {
            addCard(card);
        }
    }

    public void removeAll ()
    {
        getChildren().clear();
    }

    protected abstract void addCard(Card card);
}
