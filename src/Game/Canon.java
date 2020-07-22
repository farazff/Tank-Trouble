package Game;

public class Canon implements Runnable

{
    private double x;
    private double y;
    private String m;
    private Direction direction;
    private final String imageAddress = "./Images/bulletDark1_outline.png";
    private static final int STEP = 8;

    public Canon (int x, int y, String m, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.m = m;
        this.direction = direction;
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
}
