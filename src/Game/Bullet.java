package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Bullet implements Runnable

{
    private double x;
    private long startTime;
    private double y;
    private int height;
    private int width;
    private String m;
    private double degree;
    private Direction direction;
    private final String imageAddress = "./Images/Bullet/bulletDark1_outline.png";
    private static final int STEP = 10;
    private int canonPower;
    private ArrayList<Wall> walls;

    public Bullet (int x, int y, double degree, long startTime, ArrayList<Wall> walls)
    {
        this.degree = degree;
        findQuarterAndM (degree);
        this.x = x;
        this.y = y;
        this.startTime = startTime;
        this.walls = walls;
        try {
            BufferedImage image = ImageIO.read (new File (getImageAddress ()));
            width = image.getWidth ();
            height = image.getHeight ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

    }

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

    public int getCenterX ()  {

        return (int)x + width/2;
    }

    public int getCenterY () {
        return (int)y + height/2;
    }

    private void checkCoincidence ()
    {
        Iterator<Wall> walls = this.walls.iterator ();
        while (walls.hasNext ())
        {
            Wall wall = walls.next ();
            // TODO : add destroy walls

            if (wall.getType ().equals ("H"))
            {
                if ((getCenterX () < wall.getX () + wall.getLength ()) &&
                    getCenterX () > wall.getX ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick ()) &&
                        getCenterY () >= wall.getY ())
                    {
                        mirrorBack ("X_AXIS");
                    }
                }
                else if ((getCenterX () == wall.getX () + wall.getLength ()) &&
                        getCenterX () == wall.getX ())
                {
                    if ((getCenterY () <= wall.getY () + wall.getThick ()) &&
                            getCenterY () >= wall.getY ())
                    {
                        mirrorBack ("Y_AXIS");
                    }
                }
            }
            else
            {
                if ((getCenterY () < wall.getY () + wall.getLength ()) &&
                    getCenterY () > wall.getY ())
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick ()) &&
                        getCenterX () >= wall.getX ())
                    {
                        mirrorBack ("Y_AXIS");
                    }
                }
                else if ((getCenterY () == wall.getY () + wall.getLength ()) &&
                        getCenterY () == wall.getY ())
                {
                    if ((getCenterX () <= wall.getX () + wall.getThick ()) &&
                            getCenterX () >= wall.getX ())
                    {
                        mirrorBack ("X_AXIS");
                    }
                }
            }
        }
    }

    private void mirrorBack (String axis)
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

    public boolean hasExpired ()
    {
        return System.currentTimeMillis () - startTime >= 3500;
    }

    @Override
    public void run () {
        update ();
    }

    public int getX () {
        return ((int)x);
    }

    public int getY () {
        return ((int)y);
    }

    public double getDegree () {
        return degree;
    }

    public String getImageAddress () {
        return imageAddress;
    }
}
