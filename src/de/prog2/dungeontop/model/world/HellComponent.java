package de.prog2.dungeontop.model.world;

import java.util.UUID;

/**
 * Represents a quadratic cell in a grid, where all the rooms are deployed as their corners and walls
 */
public abstract class HellComponent
{
    Rotation rotation = Rotation.UP;
    private boolean isVisible = false;
    private final UUID assetId;
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

    public UUID getAssetId()
    {
        return assetId;
    }
}
