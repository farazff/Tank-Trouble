package Game;

public class Tank
{
    private int locX,locY,Health,power;
    private int degree;
    private String image;

    public Tank()
    {
        locX=200;
        locY=200;
        Health=100;
        power=50;
        degree = 45;
        image = "Images/Tanks/red0.png";
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

    public int getDegree()
    {
        return degree;
    }

    public void increaseDegree()
    {
        degree+=10;
        if(degree>=360)
            degree=0;
    }

    public void decreaseDegree()
    {
        degree-=10;
        if(degree<=0)
        {
            degree=359;
        }
    }

    public String getImage()
    {
        return image;
    }
}
