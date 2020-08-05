package Game;

/**
 *  this class saves walls
 */

public class Wall
{
    private int x,y,length;
    private String type;
    private boolean destructible;
    private boolean isOK;
    private int health;

    /**
     * the constructor of thw all
     * @param x the x of the left top of the wall
     * @param y the y of the left top of the wall
     * @param length the length of the wall
     * @param type the type of the wall
     * @param destructible bool of the walls destruction
     * @param health the health of the wall
     */
    public Wall(int x,int y,int length,String type,boolean destructible,int health)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.type = type;
        this.destructible = destructible;
        isOK = true;
        this.health = health;
    }

    /**
     * get x of the wall
     * @return x field
     */
    public int getX()
    {
        return x;
    }

    /**
     * get y of the wall
     * @return y field
     */
    public int getY()
    {
        return y;
    }

    /**
     * get the length of the wall
     * @return length field
     */
    public int getLength()
    {
        return length;
    }

    /**
     * get the center  of the wall
     * @return the center X of the wall
     */
    public int getCenterX ()
    {
        if (getType ().equals ("H"))
        {
            return getX () + (getLength () / 2);
        } else {
            return getX () + (getThick () / 2);
        }
    }

    /**
     * get the center Y of the wall
     * @return the center Y of the wall
     */
    public int getCenterY ()
    {
        if (getType ().equals ("H"))
        {
            return getY () + (getThick () / 2);
        } else {
            return getY () + (getLength () / 2);
        }
    }


    public int getThick ()
    {
        return 10;
    }

    /**
     * get th type of the wall
     * @return type filed
     */
    public String getType()
    {
        return type;
    }

    /**
     * check if the wall is destructive
     * @return destructive field
     */
    public boolean isDestructible()
    {
        return destructible;
    }

    public boolean isOK()
    {
        return isOK;
    }

    /**
     * get the health of the wall
     * @return health of the wall
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * devrease the health of the wall
     * @param count the count that the all looses
     */
    public void decreaseHealth(int count)
    {
        health -= count;
        if(health<=0)
            isOK = false;
    }
}
