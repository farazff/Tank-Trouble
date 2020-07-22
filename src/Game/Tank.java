package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        image = "Images/Tanks/red315.png";
    }


    public int getCenterX() throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(image));
        int width = sourceImage.getWidth();
        return locX + width/2;
    }

    public int getCenterY() throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(image));
        int height = sourceImage.getHeight();
        return locY + height/2;
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
