package Game;

public class Prize
{
    private int x;
    private int y;
    private String type;
    private String imgLoc;
    private boolean active;

    public Prize(int rand , int x ,int y)
    {
        this.x = x;
        this.y = y;
        active = true;
        if(rand==1)
        {
            type = "Protect";
        }
        if(rand==2)
        {
            type = "Laser";
        }
        if(rand==3)
        {
            type = "Health";
        }
        if(rand==4)
        {
            type = "power2";
        }
        if(rand==5)
        {
            type = "Power3";
        }
    }

    public void deActive()
    {
        active = false;
    }

    public boolean isActive()
    {
        return active;
    }

    public String getType()
    {
        return type;
    }

    public String getImgLoc()
    {
        return imgLoc;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
