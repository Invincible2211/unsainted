package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.view.HandViewAbstract;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class EnemyHandView extends HandViewAbstract {

    private int currentHandCardsSize = 0;

    double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT;

    public void removeAll ()
    {
        super.getChildren().clear();
    }

    public void addCard()
    {
        ImageView iv = new ImageView();
        iv.maxWidth(ArenaViewConstants.HAND_PLAYER_X / super.getChildren().size());
        iv.maxHeight(ArenaViewConstants.CARD_HEIGHT);
        iv.setImage(AssetsManager.getImageByAssetId(220));
        this.getChildren().add(iv);
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
