package de.prog2.dungeontop.model.game;

public class DungeonMaster extends Player
{
    private int ego_points;
    private Deck deck;

    public DungeonMaster()
    {

    }



    //Set- and Getters
    public int getEgo_points()
    {
        return ego_points;
    }
    public void setEgo_points(int ego_points)
    {
        this.ego_points = ego_points;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
}
