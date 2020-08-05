package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * this class represents a single prize
 */

public class Prize
{
    private int x;
    private int y;
    private String type;
    private BufferedImage img;
    private boolean active;

    /**
     * comstructor of the Prize class
     * @param rand type of the prize
     * @param x the x coordinate of the prize
     * @param y the y coordinate of the prize
     */
    public Prize(int rand , int x ,int y)
    {
        this.x = x;
        this.y = y;
        active = true;
        if(rand==1)
        {
            type = "Protect";
            try
            {
                img = ImageIO.read(new File("Images/Prizes/Protect.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if(rand==2)
        {
            type = "Laser";
            try
            {
                img = ImageIO.read(new File("Images/Prizes/Laser.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if(rand==3)
        {
            type = "Health";
            try
            {
                img = ImageIO.read(new File("Images/Prizes/Health.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if(rand==4)
        {
            type = "Power2";
            try
            {
                img = ImageIO.read(new File("Images/Prizes/Power2.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if(rand==5)
        {
            type = "Power3";
            try
            {
                img = ImageIO.read(new File("Images/Prizes/Power3.png"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(10000);
                    deActive();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * de active the prize
     */
    public void deActive()
    {
        active = false;
    }

    /**
     * get active
     * @return active field
     */
    public boolean isActive()
    {
        return active;
    }

    /**
     * get the type of the prize
     * @return type field
     */
    public String getType()
    {
        return type;
    }

    /**
     * image of the prize
     * @return ht img field
     */
    public BufferedImage getImg()
    {
        return img;
    }

    /**
     * get the x coordinate of the prize
     * @return x field
     */
    public int getX()
    {
        return x;
    }

    /**
     * get the T coordinate of the prize
     * @return y field
     */
    public int getY()
    {
        return y;
    }
}
