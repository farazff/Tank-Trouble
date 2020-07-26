package Game;


import java.util.ArrayList;
import java.util.Iterator;

public class SignalBullet extends Bullet
{

    private Object data;
    private Tank owner;
    public SignalBullet (int x, int y, double degree, long startTime,
                         ArrayList<Wall> walls, ArrayList<Tank> tanks, Tank owner , int canonPower) {
        super (x, y, degree, startTime, walls, tanks , canonPower);
        this.owner = owner;
        data = null;
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
                        data = wall;
                        setExpired ();
                        return;
                    }
                }
                else if ((getCenterX () == wall.getX () + wall.getLength ()) &&
                        getCenterX () == wall.getX ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterY () >= wall.getY () - getWallAccuracy ())
                    {
                        data = wall;
                        setExpired ();
                        return;
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
                        data = wall;
                        setExpired ();
                        return;
                    }
                }
                else if ((getCenterY () == wall.getY () + wall.getLength ()) &&
                        getCenterY () == wall.getY ())
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick () + getWallAccuracy ()) &&
                            getCenterX () >= wall.getX () - getWallAccuracy ())
                    {
                        data = wall;
                        setExpired ();
                        return;

                    }
                }
            }
        }
        Iterator<Tank> tanks = this.getTanks ().iterator ();
        while (tanks.hasNext ())
        {
            Tank tank = tanks.next ();
            if (tank == owner || tank instanceof IntelligentTank)
                continue;
            if ((getCenterY () <= tank.getLocY () + tank.getHeight () + getTankAccuracy ()) &&
                    getCenterY () >= tank.getLocY () - 3) {
                if ((getCenterX () <= tank.getLocX () + tank.getHeight () + getTankAccuracy ()) &&
                        getCenterX () >= tank.getLocX () - getTankAccuracy ()) {
                    data = tank;
                    setExpired ();
                    return;
                }
            }
        }

    }

    public Object receiveData ()
    {
        return data;
    }

    @Override
    public boolean hasExpired () {
        return System.currentTimeMillis () - getStartTime () >= 1200 ||
                getHandExpired ();
    }
}