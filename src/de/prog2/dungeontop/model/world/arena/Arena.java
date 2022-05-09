package de.prog2.dungeontop.model.world.arena;

public class Arena
{
    private String[] player_deck;
    public static char[][] default_board;
    public Arena(int h, int b)
    {
           default_board = new char[h][b];
    }

    //Set- and Getters
    public String[] getPlayer_deck()
    {
        return player_deck;
    }

    public void setPlayer_deck(String[] player_deck)
    {
        this.player_deck = player_deck;
    }
}
