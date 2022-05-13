package de.prog2.dungeontop.model.world;

import java.util.UUID;

public abstract class HellComponent
{
    Rotation rotation = Rotation.UP;
    private boolean isVisible = false;
    private UUID assetId;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public HellComponent(UUID assetId)
    {
        this.assetId = assetId;
    }
    public HellComponent(UUID assetId, Rotation rotation)
    {
        this.assetId = assetId;
        this.rotation = rotation;
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public Rotation getRotation()
    {
        return rotation;
    }
    public boolean isVisible()
    {
        return isVisible;
    }
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
}
