package de.prog2.dungeontop.model.world;

public class Cell
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private Coordinate coordinate;
    private HellComponent hellComponent;
    private boolean isVisible = false;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Cell(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    public Cell(Coordinate coordinate, HellComponent hellComponent)
    {
        this(coordinate);
        this.hellComponent = hellComponent;
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public Coordinate getCoordinate()
    {
        return coordinate;
    }
    public boolean isVisible()
    {
        return isVisible;
    }
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }

    public HellComponent getHellComponent()
    {
        return hellComponent;
    }

    public void setHellComponent(HellComponent hellComponent)
    {
        this.hellComponent = hellComponent;
    }
}
