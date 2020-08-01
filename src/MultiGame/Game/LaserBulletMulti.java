package MultiGame.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LaserBulletMulti extends BulletMulti implements Serializable
{

    public LaserBulletMulti (int x, int y, double degree, long startTime, ArrayList<WallMulti> walls,
                        ArrayList<TankMulti> tanks, int canonPower) {
        super (x, y, degree, startTime, walls, tanks, canonPower);
        super.setImageLoc ("./Images/Bullet/bulletRed1_outline.png");
    }


    @Override
    protected void checkCoincidence () {
        Iterator<WallMulti> walls = this.getWalls ().iterator ();
        while (walls.hasNext ())
        {
            WallMulti wall = walls.next ();


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
        Iterator<TankMulti> tanks = this.getTanks ().iterator ();
        while (tanks.hasNext ())
        {
            TankMulti tank = tanks.next ();

            if ((getCenterY () <= tank.getLocY () + tank.getHeight () + getTankAccuracy ()) &&
                    getCenterY () >= tank.getLocY () - 3) {
                if ((getCenterX () <= tank.getLocX () + tank.getHeight () + getTankAccuracy ()) &&
                        getCenterX () >= tank.getLocX () - getTankAccuracy ()) {
                    tank.looseStamina (tank.getStamina ());
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
