package Game;

public class Tank
{
    private int locX,locY,Health,power;
    private Direction direction;
    private String tank;

    public Tank()
    {
        locX=100;
        locY=100;
        Health=100;
        power=50;
        direction = Direction.NORTH;
        tank = "C:\\Users\\ffara\\Desktop\\shapes\\PNG\\Retina\\tank_red.png";
    }

    public int getLocX()
    {
        return locX;
    }

    public int getLocY()
    {
        return locY;
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
}
