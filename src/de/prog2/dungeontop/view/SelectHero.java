package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class SelectHero
{
    private Hero hero;
    private Hero hero1 = new Hero("Warrior", 12, 2, "Sturdy");
    private Hero hero2 = new Hero("Mage", 8, 4, "Intelligent");
    private Hero hero3 = new Hero("Rogue", 10, 3, "Sneaky");

    @FXML
    private VBox heroText;

    @FXML
    private Text heroClass;
    @FXML
    private Text heroHealth;
    @FXML
    private Text heroAttack;
    @FXML
    private Text heroTalent;


    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Hero-Button gedrueckt wird.
     * Hero's Werte(Angriffe, usw.) wird jetzt gezeigt.
     */
    @FXML
    private void onHero1ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(hero1);
        hero = hero1;
        heroText.setVisible(true);
    }

    @FXML
    private void onHero2ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(hero2);
        hero = hero2;
        heroText.setVisible(true);
    }

    @FXML
    private void onHero3ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(hero3);
        hero = hero3;
        heroText.setVisible(true);
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Confirm-Button gedrueckt wird.
     * Held-Auswahl wird bestätigt und der Spieler geht zur nächsten Szene (Hell) über
     */
    @FXML
    private void onConfirmButtonClicked()
    {
        // TODO: Possibility to choose new game or load game
        HellView view = new HellView();
        Scene scene = view.initHellView(GameManager.getInstance().getGameWorld().getCurrentHell());
        DungeonTop.getStage().setScene(scene);
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Return-Button gedrueckt wird.
     * Der Spieler kehrt zum Hauptmenü zurück.
     */
    @FXML
    private void onReturnButtonClicked() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML));
        Scene scene = new Scene(root);
        DungeonTop.getStage().setScene(scene);
    }

    public void selectHeroFillText(Hero hero)
    {
        getHeroClass().setText("Class: " + hero.getName());
        getHeroHealth().setText("Health: " + hero.getHp());
        getHeroAttack().setText("Attack: " + hero.getAttackDamage());
        getHeroTalent().setText("Talent: " + hero.getTalent());
    }

    //Get- und Setters
    public Text getHeroClass()
    {
        return heroClass;
    }

    public Text getHeroHealth()
    {
        return heroHealth;
    }

    public Text getHeroAttack()
    {
        return heroAttack;
    }
    public Text getHeroTalent()
    {
        return heroTalent;
    }
}