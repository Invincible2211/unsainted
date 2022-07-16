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

public class EnemyHandView extends PlayerHandView
{
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getEnemyCardView(handCardScale));
    }
}
