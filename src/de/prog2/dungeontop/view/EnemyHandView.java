package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.view.HandViewAbstract;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class EnemyHandView extends HandViewAbstract {

    private int currentHandCardsSize = 0;

    double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT;

    public EnemyHandView() {
        PlayerManager.getInstance().getPlayer().getHandCards().addListener(new ListChangeListener<Card>() {
            @Override
            public void onChanged(Change<? extends Card> c) {
                updateHand();
            }
        });
    }

    private void updateHand()
    {
        removeAll();
        addCards(currentHandCardsSize);
    }

    public void removeAll ()
    {
        super.getChildren().clear();
    }

    public void addCard()
    {
        //TODO Jesse hier handrueckenkarte einfuegen anstatt von imageview iv
        //Node cardView = CardViewController.getEnemtyCardView(1);
        //this.getChildren().add(iv);
    }

    public void addCards (int amount)
    {
        for (int i = 0; i < amount; i++) {
         addCard();
        }
    }

    public void removeCard()
    {
        super.getChildren().remove(super.getChildren().stream().findAny());
    }

    public int getCurrentHandCardsSize() {
        return super.getChildren().size();
    }
}
