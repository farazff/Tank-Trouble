package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class represents laser
 */
public class LaserBullet extends Bullet
{

    /**
     * creates laser
     * @param x x
     * @param y y
     * @param degree degree
     * @param startTime startTime
     * @param walls walls
     * @param tanks tanks
     * @param canonPower canonPower
     * @param code code
     * @param kills kills
     */
    public LaserBullet (int x, int y, double degree, long startTime, ArrayList<Wall> walls,
                        ArrayList<Tank> tanks, int canonPower,int code,int[] kills) {
        super (x, y, degree, startTime, walls, tanks, canonPower,code,kills);
        try {
            super.setImage (ImageIO.read (new File ("./Images/Bullet/bulletRed1_outline.png")));
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


    @Override
    protected void checkCoincidence () {
        Iterator<Wall> walls = this.getWalls ().iterator ();
        while (walls.hasNext ())
        {
            Wall wall = walls.next ();


            if (wall.getType ().equals ("H"))
            {
                if ((getCenterX () < wall.getX () + wall.getLength () + getWallAccuracy ()) &&
                        getCenterX () > wall.getX () - getWallAccuracy ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterY () >= wall.getY () - getWallAccuracy ())
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (wall.getHealth ());
                            walls.remove ();
                        }
                        else
                            mirrorBack ("X_AXIS");
                    }
                }
                else if ((getCenterX () == wall.getX () + wall.getLength ()) &&
                        getCenterX () == wall.getX ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterY () >= wall.getY () - getWallAccuracy ())
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (wall.getHealth ());
                            walls.remove ();
                        }
                        else
                            mirrorBack ("Y_AXIS");
                    }
                }
            }
            else
            {
                if ((getCenterY () < wall.getY () + wall.getLength () + getWallAccuracy ()) &&
                        getCenterY () > wall.getY () - 3)
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterX () >= wall.getX () - getWallAccuracy ())
                    {
                        if (wall.isDestructible ())
                        {

                            wall.decreaseHealth (wall.getHealth ());
                            walls.remove ();
                        }
                        else
                            mirrorBack ("Y_AXIS");
                    }
                }
                else if ((getCenterY () == wall.getY () + wall.getLength ()) &&
                        getCenterY () == wall.getY ())
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterX () >= wall.getX () - getWallAccuracy ())
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (wall.getHealth ());
                            walls.remove ();
                        }
                        else
                            mirrorBack ("X_AXIS");
                    }
                }
            }
        }
        Iterator<Tank> tanks = this.getTanks ().iterator ();
        while (tanks.hasNext ())
        {
            Tank tank = tanks.next ();

            if ((getCenterY () <= tank.getLocY () + tank.getHeight () + getTankAccuracy ()) &&
                    getCenterY () >= tank.getLocY () - 3) {
                if ((getCenterX () <= tank.getLocX () + tank.getHeight () + getTankAccuracy ()) &&
                        getCenterX () >= tank.getLocX () - getTankAccuracy ())
                {
                    tank.looseStamina (tank.getStamina ());
                    if(tank.getStamina()<=0 && tank.getCode()!=this.getCode())
                    {
                        kills[this.getCode()-1]++;
                    }
                    setExpired();
                    return;
                }
            }
        }
    }

    @Override
    public boolean hasExpired ()
    {
        return System.currentTimeMillis () - getStartTime () >= 2500 ||
                getHandExpired ();
    }
}
