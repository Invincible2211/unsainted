package de.prog2.dungeontop.model.world;

public class Field
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private Coordinate coordinate;
    private FieldAction action = new DefaultFieldAction();
    private boolean isVisible = false;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Field(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    public Field(Coordinate coordinate, FieldAction action)
    {
        this(coordinate);
        this.action = action;
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public Coordinate getCoordinate()
    {
        return coordinate;
    }
    public FieldAction getAction()
    {
        return this.action;
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
