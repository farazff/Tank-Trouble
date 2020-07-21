package Game;

public class Tank
{
    private int locX,locY,Health,power;
    private Direction direction;
    private String image;

    public Tank()
    {
        locX=100;
        locY=100;
        Health=100;
        power=50;
        direction = Direction.NORTH;
        image = "Images/Tanks/red";
    }

    public int getLocX()
    {
        return locX;
    }

    public void setLocX(int locX)
    {
        this.locX = locX;
    }

    public void addLocX(int adder)
    {
        locX += adder;
    }

    public int getLocY()
    {
        return locY;
    }

    public void setLocY(int locY)
    {
        this.locY = locY;
    }

    public void addLocY(int adder)
    {
        locY += adder;
    }

    public int getHealth()
    {
        return Health;
    }

    public int getPower()
    {
        return power;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public String getImage()
    {
        return image;
    }
}
