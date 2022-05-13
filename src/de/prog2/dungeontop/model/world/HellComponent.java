package de.prog2.dungeontop.model.world;

public abstract class HellComponent
{
    Rotation rotation = Rotation.UP;
    private boolean isVisible = false;
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public boolean isVisible()
    {
        return isVisible;
    }
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
}
