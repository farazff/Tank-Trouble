package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class represents Bullet in game
 */
public class Bullet implements Runnable
{
    private int code;  // the code of the owner of the bulet
    private double x;
    private static final int WALL_ACCURACY = 4;
    private static final int TANK_ACCURACY = -10;
    private long startTime;
    private double y;
    private int height;
    private int width;
    private String m;
    private double degree; //the degree of the bullet
    private Direction direction;
    private BufferedImage image;
    private static final int STEP = 12;
    private int canonPower;
    private ArrayList<Wall> walls;
    private ArrayList<Tank> tanks;
    private boolean expired;
    private int[] kills;

    /**
     * get the code of the bullet
     * @return the code field
     */
    public int getCode()
    {
        return code;
    }

    /**
     * creating new bullet
     * @param x the x coordinate of the bullet
     * @param y the y coordinate of the bullet
     * @param degree the degree of the bullet
     * @param startTime starting time of the bullet
     * @param walls list of all walls
     * @param tanks the list of all tanks
     * @param canonPower the power of every bullet
     * @param code the code of the bullet
     * @param kills kills array
     */
    public Bullet (int x, int y, double degree, long startTime, ArrayList<Wall> walls,
                   ArrayList<Tank> tanks , int canonPower,int code,int[] kills)
    {
        this.kills = kills;
        this.code = code;
        this.degree = degree;
        expired = false;
        findQuarterAndM (degree);
        this.x = x;
        this.y = y;
        this.canonPower = canonPower;
        this.startTime = startTime;
        this.walls = walls;
        this.tanks = tanks;
        try {
            image = ImageIO.read (new File ("./Images/Bullet/bulletDark1_outline.png"));
        } catch (IOException e) {
            e.printStackTrace ();
        }

        width = image.getWidth ();
        height = image.getHeight ();
    }

    /**
     * set image of the bullet
     * @param image image of te tank
     */
    public void setImage (BufferedImage image) {
        this.image = image;
    }

    /**
     * find the quarter of the bullet
     * @param degree the degree of the bullet
     */
    private void findQuarterAndM (double degree)
    {
        degree = Math.abs (degree);

        if (degree >= 0 && degree < 90)
            this.direction = Direction.SOUTHEAST;
        else if (degree >= 90 && degree < 180)
            this.direction = Direction.SOUTHWEST;
        else if (degree >= 180 && degree < 270)
            this.direction = Direction.NORTHWEST;
        else if (degree >= 270)
            this.direction = Direction.NORTHEAST;

        if (degree == 0)
            this.m = "0";
        else if (degree == 90)
            this.m = "infinity";
        else if (degree == 180)
            this.m = "0";
        else if (degree == 270)
            this.m = "infinity";
        else
            this.m = "" + Math.abs (Math.tan (Math.toRadians (degree)));
    }

    /**
     * get the x center of the bullet
     * @return the x center of the bullet
     */
    public int getCenterX ()  {

        return (int)x + width/2;
    }

    /**
     * get the y center of the bullet
     * @return the y center of the bullet
     */
    public int getCenterY () {
        return (int)y + height/2;
    }

    /**
     * check if the bullet reflects from wall or it destroys any tank
     */
    protected void checkCoincidence ()
    {
        Iterator<Wall> walls = this.walls.iterator ();
        while (walls.hasNext ())
        {
            Wall wall = walls.next ();


            if (wall.getType ().equals ("H"))
            {
                if ((getCenterX () < wall.getX () + wall.getLength () + WALL_ACCURACY) &&
                    getCenterX () > wall.getX () - WALL_ACCURACY)
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick () + WALL_ACCURACY) &&
                        getCenterY () >= wall.getY () - WALL_ACCURACY)
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (canonPower);
                            setExpired ();
                            if (!wall.isOK ())
                                walls.remove ();
                        }
                        else
                            mirrorBack ("X_AXIS");
                    }
                }
                else if ((getCenterX () == wall.getX () + wall.getLength ()) &&
                        getCenterX () == wall.getX ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick () + WALL_ACCURACY) &&
                            getCenterY () >= wall.getY () - WALL_ACCURACY)
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (canonPower);
                            setExpired ();
                            if (!wall.isOK ())
                                walls.remove ();
                        }
                        else
                            mirrorBack ("Y_AXIS");
                    }
                }
            }
            else
            {
                if ((getCenterY () < wall.getY () + wall.getLength () + WALL_ACCURACY) &&
                    getCenterY () > wall.getY () - 3)
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick () + WALL_ACCURACY) &&
                        getCenterX () >= wall.getX () - WALL_ACCURACY)
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (canonPower);
                            setExpired ();
                            if (!wall.isOK ())
                                walls.remove ();
                        }
                        else
                            mirrorBack ("Y_AXIS");
                    }
                }
                else if ((getCenterY () == wall.getY () + wall.getLength ()) &&
                        getCenterY () == wall.getY ())
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick () + WALL_ACCURACY) &&
                            getCenterX () >= wall.getX () - WALL_ACCURACY)
                    {
                        if (wall.isDestructible ())
                        {
                            wall.decreaseHealth (canonPower);
                            setExpired ();
                            if (!wall.isOK ())
                                walls.remove ();
                        }
                        else
                            mirrorBack ("X_AXIS");
                    }
                }
            }
        }
        Iterator<Tank> tanks = this.tanks.iterator ();
        while (tanks.hasNext ())
        {
            Tank tank = tanks.next ();

            if ((getCenterY () <= tank.getLocY () + tank.getHeight () + TANK_ACCURACY) &&
                    getCenterY () >= tank.getLocY () - 3) {
                if ((getCenterX () <= tank.getLocX () + tank.getHeight () + TANK_ACCURACY) &&
                        getCenterX () >= tank.getLocX () - TANK_ACCURACY) {
                    tank.looseStamina (canonPower);
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

    /**
     * get the canon power
     * @return canonPower Filed
     */
    public int getCanonPower () {
        return canonPower;
    }

    /**
     * get the list of all tanks
     * @return tanks field
     */
    public ArrayList<Tank> getTanks () {
        return tanks;
    }

    /**
     * get the list of all walls
     * @return thw walls field
     */
    public ArrayList<Wall> getWalls () {
        return walls;
    }

    /**
     *
     * @return get WallAccuracy
     */
    public static int getWallAccuracy () {
        return WALL_ACCURACY;
    }

    /**
     *
     * @return get TankAccuracy
     */
    public static int getTankAccuracy () {
        return TANK_ACCURACY;
    }

    /**
     * set expired true means the bullet is not active any more
     */
    protected void setExpired ()
    {
        expired = true;
    }

    /**
     * reflecting the bullet when it hits any wall
     * @param axis the axis of the bullet
     */
    protected void mirrorBack (String axis)
    {
        if (axis == null)
            return;
        if (axis.equals ("Y_AXIS"))
        {
            degree = 180 - degree;
            if (degree < 0)
                degree = 360 + degree;
            findQuarterAndM (degree);

        }
        else if (axis.equals ("X_AXIS"))
        {
            degree = 360 - degree;
            findQuarterAndM (degree);
        }
    }

    /**
     * updating the bullet by checking if it hits any wall or tank
     */
    private void update ()
    {
        try {
            if (direction.getY_AXIS ().equals ("+"))  // that means UP
            {
                if (m.equals ("infinity"))
                {
                    y += STEP;
                } else
                {
                    double newM = Math.abs (Double.parseDouble (m));

                    if (direction.getX_AXIS ().equals ("+"))
                    {
                        if (newM >= 1)
                        {
                            y += STEP;
                            x += ((1/newM)*STEP);
                        }
                        else if (newM != 0 && newM < 1)
                        {
                            x += STEP;
                            y += newM * STEP;
                        } else if (newM == 0)
                        {
                            x += STEP;
                        }
                    } else if (direction.getX_AXIS ().equals ("-"))
                    {
                        if (newM != 0 && newM < 1)
                        {
                            y += newM * STEP;
                            x -= STEP;
                        }
                        else if (newM >= 1)
                        {
                            x -= ((1/newM)*STEP);
                            y += STEP;
                        } else if (newM == 0)
                        {
                            x -= STEP;
                        }
                    }
                }
            }
            else if (direction.getY_AXIS ().equals ("-"))
            {
                if (m.equals ("infinity"))
                {
                    y -= STEP;
                } else
                {
                    double newM = Math.abs (Double.parseDouble (m));

                    if (direction.getX_AXIS ().equals ("+"))
                    {
                        if (newM >= 1)
                        {
                            y -= STEP;
                            x += ((1/newM)*STEP);
                        }
                        else if (newM != 0 && newM < 1)
                        {

                            x += STEP;
                            y -= newM * STEP;
                        } else if (newM == 0)
                        {
                            x += STEP;
                        }
                    } else if (direction.getX_AXIS ().equals ("-"))
                    {
                        if (newM != 0 && newM < 1)
                        {
                            x -= STEP;
                            y -= newM * STEP;

                        }
                        else if (newM >= 1)
                        {
                            y -= STEP;
                            x -= ((1/newM)*STEP);
                        } else if (newM == 0)
                        {
                            x -= STEP;
                        }
                    }
                }
            }
            checkCoincidence ();
        } catch (NumberFormatException e)
        {
            System.out.println (e.getMessage ());
        }
    }

    /**
     * give Expired info about the bullet
     * @return true if it is expired and false otherwise
     */
    public boolean hasExpired ()
    {
        return System.currentTimeMillis () - startTime >= 3500 ||
                expired;
    }

    /**
     * get the star time of the tank
     * @return the startTime filed
     */
    public long getStartTime () {
        return startTime;
    }

    /**
     *
     * @return is bullet expired in hits
     */
    public boolean getHandExpired ()
    {
        return expired;
    }

    @Override
    public void run () {
        update ();
    }

    /**
     * get the x coordinate of the bullet
     * @return the X filed
     */
    public int getX () {
        return ((int)x);
    }

    /**
     * get the Y coordinate of the bullet
     * @return the Y filed
     */
    public int getY () {
        return ((int)y);
    }
    /**
     * get the degree of the bullet
     * @return the degree filed
     */
    public double getDegree () {
        return degree;
    }
    /**
     * get the image of the bullet
     * @return the image filed
     */
    public BufferedImage getImage () {
        return image;
    }
}
