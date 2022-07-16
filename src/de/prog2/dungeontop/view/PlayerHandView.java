package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.view.cardViews.CardView;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandView extends HandViewAbstract
{
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getCardView(card, handCardScale));
    }
}
