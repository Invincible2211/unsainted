package de.prog2.dungeontop.model.world.arena;

public class Arena
{
    ArenaComponent[][] default_board;
    public Arena(int h, int b)
    {
           default_board = new ArenaComponent[h][b];
    }

    //Set- and Getters
    public ArenaComponent[][] getDefault_board()
    {
        return default_board;
    }
    public void setDefault_board(ArenaComponent[][] default_board)
    {
        this.default_board = default_board;
    }

}
