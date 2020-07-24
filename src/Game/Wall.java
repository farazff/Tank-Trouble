package Game;

public class Wall
{
    private int x,y,length;
    private String type;

    public Wall(int x,int y,int length,String type)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.type = type;
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
        return 5;
    }

    public String getType()
    {
        return type;
    }
}
