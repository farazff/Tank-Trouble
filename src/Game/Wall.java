package Game;

public class Wall
{
    private int x,y,length;
    private String type;
    private boolean destructible;
    private boolean isOK;
    private int health;

    public Wall(int x,int y,int length,String type,boolean destructible)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.type = type;
        this.destructible = destructible;
        isOK = true;
        health = 100;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getLength()
    {
        return length;
    }

    public int getThick ()
    {
        return 10;
    }

    public String getType()
    {
        return type;
    }

    public boolean isDestructible()
    {
        return destructible;
    }

    public boolean isOK()
    {
        return isOK;
    }

    public int getHealth()
    {
        return health;
    }

    public void decreaseHealth(int count)
    {
        health -= count;
        if(health<=0)
            isOK = false;
    }
}
