package Game;

public class Bullet implements Runnable

{
    private double x;
    private double y;
    private String m;
    private Direction direction;
    private final String imageAddress = "./Images/bulletDark1_outline.png";
    private static final int STEP = 8;

    public Bullet (int x, int y, double degree)
    {
        findQuarterAndM (degree);
        this.x = x;
        this.y = y;
    }

    private void findQuarterAndM (double degree)
    {
        degree = Math.abs (degree);
        degree %= 360;
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
                            y += STEP;
                            x -= ((1/newM)*STEP);
                        }
                        else if (newM >= 1)
                        {
                            x -= STEP;
                            y += newM * STEP;
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
                            y -= STEP;
                            x -= ((1/newM)*STEP);
                        }
                        else if (newM >= 1)
                        {
                            x -= STEP;
                            y -= newM * STEP;
                        } else if (newM == 0)
                        {
                            x -= STEP;
                        }
                    }
                }
            }


//            x = Math.max(x, 0);
//            x = Math.min(x, GameFrame.GAME_WIDTH);
//            y = Math.max(y, 0);
//            y = Math.min(y, GameFrame.GAME_HEIGHT);
        } catch (NumberFormatException e)
        {
            System.out.println (e.getMessage ());
        }
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

    public String getImageAddress () {
        return imageAddress;
    }
}
