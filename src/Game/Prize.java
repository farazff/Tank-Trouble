package Game;

public class Prize
{
    private String type;
    private int locX;
    private int locY;

    public Prize(String type,int x,int y)
    {
        this.type = type;
        this.locX = x;
        this.locY = y;
    }

    public String getType()
    {
        return type;
    }

    public int getLocX()
    {
        return locX;
    }

    public int getLocY()
    {
        return locY;
    }
}
