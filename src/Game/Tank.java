package Game;

import GUI.Music;
import GameData.User;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * this class have all of the info of a tank and updates it in every frame
 */

public class Tank implements Runnable
{
    private int locX,locY,stamina;
    private int degree;
    private String imageAddress;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean shot;
    private boolean hasProtection;
    private String bulletType;
    private boolean fireDestroyed;
    private boolean destroyed;
    private boolean mousePress;
    private int height;
    private int width;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private boolean canShot;
    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls;
    private ArrayList<Tank> tanks;

    private  BufferedImage tankImage;
    private static BufferedImage fireImage;
    private static BufferedImage fireDestroyImage;
    private User user;
    private Prize prizeOwn;
    private Prizes prizes;

    /**
     * get the prize that the tank own
     * @return the prizeOwn field
     */
    public Prize getPrizeOwn()
    {
        return prizeOwn;
    }

    private int canonPower;
    private Maps maps;
    private int[] kills;
    private int code;

    /**
     * get the code of the tank
     * @return the code Field
     */
    public int getCode()
    {
        return code;
    }


    static
    {
        try
        {
            fireImage = ImageIO.read (new File ("./Images/Bullet/shotLarge.png"));
            fireDestroyImage = ImageIO.read (new File ("./Images/Explosion/explosion3.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * constructor of the tank class
     * @param bullets list of all bullets
     * @param walls list of all walls
     * @param tanks list of all tanks
     * @param prizes list of all prizes
     * @param tankStamina stamina of the tank
     * @param canonPower power of the canon
     * @param maps map of the game
     * @param imageAddress image address of the tank
     * @param user user who signed in
     * @param code the code of the tank
     * @param kills array of the kills of every tank
     */
    public Tank (ArrayList<Bullet> bullets, ArrayList<Wall> walls, ArrayList<Tank> tanks, Prizes prizes,
                int tankStamina,int canonPower , Maps maps,String imageAddress, User user,int code,int[] kills)
    {
        this.kills = kills;
        this.code = code;
        this.imageAddress = imageAddress;
        this.maps = maps;
        this.canonPower = canonPower;
        this.prizes = prizes;
        this.bullets = bullets;
        this.walls = walls;
        this.tanks = tanks;
        destroyed = false;
        fireDestroyed = false;
        hasProtection = false;
        canShot = true;
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        shot = false;
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        keyHandler = new KeyHandler ();
        prizeOwn = null;
        bulletType = "Normal";
        this.user = user;

        do
        {
            locX = new Random().nextInt(((16 * 720) / 9) - 200) + 100;
            locY = new Random().nextInt(720 - 200) + 100;

        }while(!canMoveForward() || !maps.canPut(locX,locY) || !isEmpty(0,0,1));


        stamina = tankStamina;

        degree = 45;

        try {
            tankImage = ImageIO.read (new File (getImageAddress ()));
            height = tankImage.getHeight ();
            width = tankImage.getWidth ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    /**
     * get the user
     * @return the user
     */
    public User getUser ()
    {
        return user;
    }

    /**
     * set the tank protection prize
     * @param hasProtection boolean hasProtection
     */
    public void setProtection (boolean hasProtection)
    {
        this.hasProtection = hasProtection;
    }

    /**
     * set the bullet type laser or simple
     * @param bulletType the type of the bullet
     */
    public void setBulletType (String bulletType) {
        this.bulletType = bulletType;
    }

    /**
     * get bullet type
     * @return bulletType filed
     */
    public String getBulletType () {
        return bulletType;
    }

    /**
     * get tanks image height
     * @return height field
     */
    public int getHeight () {
        return height;
    }

    /**
     * get tanks image width
     * @return width field
     */
    public int getWidth () {
        return width;
    }

    /**
     * loose stamina
     * @param damage damage
     */
    public void looseStamina (int damage) {
        if (!hasProtection)
            stamina -= damage;
        if (stamina <= 0) {
            fireDestroyed = true;
            keyUP = false;
            keyDOWN = false;
            keyLEFT = false;
            keyRIGHT = false;
            new Thread (new Runnable () {
                @Override
                public void run () {
                    try {
                        Thread.sleep (200);
                        destroyed = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
            }).start ();
        }

    }

    /**
     * get keyHandler
     * @return keyHandler field
     */
    public KeyHandler getKeyHandler () {
        return keyHandler;
    }


    /**
     * get centerX
     * @return centerX filed
     */
    public int getCenterX () {
        return locX + width / 2 - 2;
    }

    /**
     * get centerY
     * @return centerY filed
     */
    public int getCenterY () {
        return locY + height / 2 - 2;
    }

    /**
     *
     * @return get CanonStartX
     */
    public int getCanonStartX () {

        return getCenterX () +
                ((int) (Math.sqrt (968) * Math.cos (Math.toRadians (degree))));
    }

    /**
     *
     * @return get CanonStartY
     */
    public int getCanonStartY () {
        return getCenterY () +
                ((int) (Math.sqrt (968) * Math.sin (Math.toRadians (degree))));
    }


    /**
     *
     * @return get ImageAddress
     */
    public String getImageAddress () {
        return imageAddress;
    }

    /**
     * get locx
     * @return locX field
     */
    public int getLocX () {
        return locX;
    }

    /**
     * set locX
     * @param locX locX new value
     */
    public void setLocX (int locX) {
        this.locX = locX;
    }

    /**
     * add to loc X
     * @param adder the value to add
     */
    public void addLocX (int adder) {
        locX += adder;
    }

    /**
     * get locY
     * @return locY field
     */
    public int getLocY () {
        return locY;
    }

    /**
     * set locY
     * @param locY locY new value
     */
    public void setLocY (int locY) {
        this.locY = locY;
    }

    /**
     * add to loc Y
     * @param adder the value to add
     */
    public void addLocY (int adder) {
        locY += adder;
    }

    /**
     *
     * @return get Stamina
     */
    public int getStamina () {
        return stamina;
    }

    /**
     * get tanks
     * @return the tanks field
     */
    public ArrayList<Tank> getTanks () {
        return tanks;
    }

    /**
     * get bullets
     * @return bullets method
     */
    public ArrayList<Bullet> getBullets () {
        return bullets;
    }

    /**
     * get walls
     * @return walls filed
     */
    public ArrayList<Wall> getWalls () {
        return walls;
    }

    /**
     * get degree
     * @return degree field
     */
    public int getDegree () {
        return degree;
    }

    /**
     * increase the degree of the tank
     */
    public void increaseDegree () {
        degree += 10;
        if (degree >= 360)
            degree = 0;
    }

    /**
     * decrease the degree of the tank
     */
    public void decreaseDegree () {
        degree -= 10;
        if (degree <= 0) {
            degree = 359;
        }
    }

    /**
     * check if the tank can move forward
     * @return true if it can and false otherwise
     */
    public boolean canMoveForward()
    {
        boolean ans = true;

        Iterator<Wall> walls = this.walls.iterator();

        while(walls.hasNext ())
        {
            Wall wall = walls.next();

            if(wall.getType ().equals ("H"))
            {
                if(locX>=wall.getX()-15 && locX<=wall.getX()+wall.getLength()+15)
                {
                    if(wall.getY()-locY<=50 && wall.getY()-locY>=0 && degree>=0 && degree<=150)
                    {
                        ans=false;
                    }
                    if(locY-wall.getY()<=8 && locY-wall.getY()>=0 && degree>=180 && degree<=360)
                    {
                        ans=false;
                    }
                }
            }

            if(wall.getType ().equals ("V"))
            {
                if(locY>=wall.getY()-15 &&locY<=wall.getY()+wall.getLength()+15)
                {
                    if(wall.getX()-locX<=50 && wall.getX()-locX>=0 &&
                            ((degree>=0 && degree<=90)||(degree>=270 && degree<=360)) )
                    {
                        ans=false;
                    }
                    if(locX-wall.getX()<=8 && locX-wall.getX()>=0 &&
                            degree>=90 && degree<=270 )
                    {
                        ans=false;
                    }
                }
            }
        }


        return ans;
    }

    /**
     * check if the tank can move backward
     * @return true if it can and false otherwise
     */
    public boolean canMoveBackward()
    {
        boolean ans = true;

        Iterator<Wall> walls = this.walls.iterator();

        while(walls.hasNext ())
        {
            Wall wall = walls.next();

            if(wall.getType ().equals ("H"))
            {
                if(locX>=wall.getX()-15 && locX<=wall.getX()+wall.getLength()+15)
                {
                    //tank upper than wall
                    if(wall.getY()-locY<=60 && wall.getY()-locY>=0 && degree>=270 && degree<=360)
                    {
                        ans=false;
                    }
                    //tank lower than wall
                    if(locY-wall.getY()<=8 && locY-wall.getY()>=0 && degree>=0 && degree<=180)
                    {
                        ans=false;
                    }
                }
            }

            if(wall.getType ().equals ("V"))
            {
                if(locY>=wall.getY()-15 &&locY<=wall.getY()+wall.getLength()+15)
                {
                    //tank at the left side of the wall
                    if(wall.getX()-locX<=60 && wall.getX()-locX>=0 &&
                            (degree>=90 && degree<=270) )
                    {
                        ans=false;
                    }
                    //tank at the right side of the wall
                    if(locX-wall.getX()<=8 && locX-wall.getX()>=0 &&
                            ((degree>=0 && degree<=90)||(degree>=270 && degree<=360)) )
                    {
                        ans=false;
                    }
                }
            }
        }

        return ans;
    }

    /**
     * check if the place is empty
     * @param forX the movement of tank in x
     * @param forY the movement of the tank in y
     * @param dir the direction of the tank 1 means forward and -1 otherwise
     * @return true if the new place is empty and false otherwise
     */
    public boolean isEmpty(int forX , int forY , int dir)
    {
        boolean ans = true;
        if(dir==-1)
        {
            forX *= (-1);
            forY *= (-1);
        }

        for(int i=0;i<tanks.size();i++)
        {
            if(tanks.get(i)!=this)
            {
                int thisX = this.getLocX() + forX;
                int thisY = this.getLocY() + forY;
                int otherX = tanks.get(i).getLocX();
                int otherY = tanks.get(i).getLocY();
                if(   ( (thisX-otherX)*(thisX-otherX) + (thisY-otherY)*(thisY-otherY) ) <= (56)*(56)  )
                {
                    ans = false;
                    break;
                }
            }
        }

        return ans;
    }

    /**
     * check if the tank can move
     * @param forX the distance of x
     * @param forY the distance of y
     * @return true it the tank can move
     */
    public boolean canMove(int forX , int forY)
    {


        int tankX = locX + width/2;
        int tankY = locY + height/2;


        for(Wall wall : walls)
        {
            if(wall.getType().equals("H"))
            {

                int disStart = (wall.getX()-tankX)*(wall.getX()-tankX) + (wall.getY()-tankY)*(wall.getY()-tankY);
                if(disStart<=700)
                {
                    int dis2 = (wall.getX()-(tankX+forX))*(wall.getX()-(tankX+forX)) + (wall.getY()-(tankY+forY))*(wall.getY()-(tankY+forY));
                    if(dis2<disStart)
                        return false;
                }

                int disEnd = ((wall.getX()+wall.getLength())-tankX)*((wall.getX()+wall.getLength())-tankX) + (wall.getY()-tankY)*(wall.getY()-tankY);
                if(disEnd<=1700)
                {
                    int dis2 = ((wall.getX()+wall.getLength())-(tankX+forX))*((wall.getX()+wall.getLength())-(tankX+forX)) + (wall.getY()-(tankY+forY))*(wall.getY()-(tankY+forY));
                    if(dis2<disEnd)
                        return false;
                }

                if(tankX >= wall.getX() && tankX <= (wall.getX() + wall.getLength()))
                {
                    //tank upper than wall
                    if( tankY+20 < wall.getY() && wall.getY()-(tankY+40)<=20 )
                    {
                        if( wall.getY() <= (tankY+20 + forY) )
                        {
                            return false;
                        }
                    }

                    //tank lower than wall
                    if( (tankY-35) > wall.getY() && (tankY-35)-wall.getY()<=25 )
                    {
                        if( wall.getY() >= (tankY - 35 + forY) )
                        {
                            return false;
                        }
                    }
                }
            }

            if(wall.getType().equals("V"))
            {

                int disStart = (wall.getX()-tankX)*(wall.getX()-tankX) + (wall.getY()-tankY)*(wall.getY()-tankY);
                if(disStart<=700)
                {
                    int dis2 = (wall.getX()-(tankX+forX))*(wall.getX()-(tankX+forX)) + (wall.getY()-(tankY+forY))*(wall.getY()-(tankY+forY));
                    if(dis2<disStart)
                        return false;
                }

                int disEnd = (wall.getX()-tankX)*(wall.getX()-tankX) + ((wall.getY()+wall.getLength())-tankY)*((wall.getY()+wall.getLength())-tankY);
                if(disEnd<=3600)
                {
                    int dis2 = (wall.getX()-(tankX+forX))*(wall.getX()-(tankX+forX)) + ((wall.getY()+wall.getLength())-(tankY+forY))*((wall.getY()+wall.getLength())-(tankY+forY));
                    if(dis2<disEnd)
                        return false;
                }


                if(tankY >= wall.getY() && tankY <= (wall.getY() + wall.getLength()))
                {
                    //tank at the left side of the wall
                    if( tankX+20 < wall.getX() && wall.getX()-(tankX+40)<=20 )
                    {
                        if( wall.getX() <= (tankX+20 + forX) )
                        {
                            return false;
                        }
                    }

                    //tank lower than wall
                    if( (tankX-35) > wall.getX() && (tankX-35)-wall.getX()<=25 )
                    {
                        if( wall.getX() >= (tankX - 35 + forX) )
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * update the tank in every frame
     */
    public void update()
    {
        if(mousePress)
        {
            this.setLocY( mouseY - 30 / 2 );
            this.setLocX( mouseX - 30 / 2 );
        }

        int forX = (int) (6 * Math.cos (Math.toRadians (this.getDegree ())));
        int forY = (int) (6 * Math.sin (Math.toRadians (this.getDegree ())));

        if(keyUP && canMove(forX,forY) && isEmpty(forX,forY,1))
        {
            this.addLocX(forX);
            this.addLocY(forY);
        }
        if(keyDOWN && canMove(-1*forX , -1*forY) && isEmpty(forX,forY,-1))
        {
            this.addLocX(-1*forX);
            this.addLocY(-1*forY);
        }

        checkPrize();

        if(keyRIGHT && !keyLEFT)
            this.increaseDegree();

        if(!keyRIGHT  && keyLEFT)
            this.decreaseDegree();

        this.setLocX(Math.max(this.getLocX(), 0));
        this.setLocX(Math.min(this.getLocX(), GameFrame.GAME_WIDTH - 30));
        this.setLocY(Math.max(this.getLocY(), 0));
        this.setLocY(Math.min(this.getLocY(), GameFrame.GAME_HEIGHT - 30));
    }

    /**
     * check if the tank get any prize
     */
    public void checkPrize()
    {
        for(Prize prize : prizes.getPrizes())
        {
            if(prize.isActive())
            {
                if (((locX - prize.getX()) * (locX - prize.getX()) + (locY - prize.getY()) * (locY - prize.getY())) <= 35 * 35) {
                    if (prizeOwn == null)
                    {
                        Music music = new Music();
                        music.setFilePath("Files/Sounds/GetPrize.au",false);
                        music.execute();

                        prize.deActive();
                        prizeOwn = prize;
                        if (prize.getType().equals("Health")) {
                            stamina += (stamina / 10);
                        }
                        if (prize.getType().equals("Power2")) {
                            canonPower *= 2;
                        }
                        if (prize.getType().equals("Power3")) {
                            canonPower *= 3;
                        }
                        if (prize.getType ().equals ("Laser")) {
                            setBulletType ("Laser");
                        }

                        if (prize.getType ().equals ("Protect")) {
                            setProtection (true);
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(10000);
                                    prizeOwn = null;
                                    if (prize.getType().equals("Power2")) {
                                        canonPower /= 2;
                                    }
                                    if (prize.getType().equals("Power3")) {
                                        canonPower /= 3;
                                    }
                                    if (prize.getType ().equals ("Laser")) {
                                        setBulletType ("Normal");
                                    }
                                    Thread.sleep (5000);
                                    if (prize.getType ().equals ("Protect")) {
                                        setProtection (false);
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }
            }
        }
    }


    @Override
    public void run()
    {
        this.update();
    }

    /**
     * check if FireDestroyed
     * @return fireDestroyed field
     */
    public boolean isFireDestroyed () {
        return fireDestroyed;
    }

    /**
     *
     * @return is Destroyed
     */
    public boolean isDestroyed () {
        return destroyed;
    }

    /**
     *
     * @return is Shot
     */
    public boolean isShot () {
        return shot;
    }

    /**
     * get canShot
     * @return chanShot filed
     */
    public boolean isCanShot () {
        return canShot;
    }

    /**
     * set degree of the tank
     * @param degree the degree field
     */
    public void setDegree (int degree) {
        this.degree = degree;
    }

    /**
     *
     * @param canShot canShot field
     */
    public void setCanShot (boolean canShot) {
        this.canShot = canShot;
    }

    /**
     * set shot
     * @param shot shot
     */
    public void setShot (boolean shot) {
        this.shot = shot;
    }

    /**
     *
     * @return get TankImage
     */
    public  BufferedImage getTankImage () {
        return tankImage;
    }

    /**
     *
     * @return get FireDestroyImage
     */
    public static BufferedImage getFireDestroyImage () {
        return fireDestroyImage;
    }

    /**
     * get the fire image
     * @return fireImage field
     */
    public static BufferedImage getFireImage () {
        return fireImage;
    }

    /**
     * this class handles components
     */
    protected class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed (KeyEvent e) {
            if (!(destroyed || fireDestroyed)) {
                switch (e.getKeyCode ()) {
                    case KeyEvent.VK_UP:
                        keyUP = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        keyDOWN = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        keyLEFT = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        keyRIGHT = true;
                        break;
                }
            }
        }

        @Override
        public void keyReleased (KeyEvent e) {
            if (!(destroyed || fireDestroyed)) {
                switch (e.getKeyCode ()) {
                    case KeyEvent.VK_UP:
                        keyUP = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        keyDOWN = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        keyLEFT = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        keyRIGHT = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (canShot)
                        {
                            Music music = new Music ();
                            music.setFilePath ("Files/Sounds/Bullet.au", false);
                            music.execute ();
                            if (getBulletType ().equals ("Laser"))
                            {
                                bullets.add (new LaserBullet (getCanonStartX (), getCanonStartY (),
                                        getDegree (), System.currentTimeMillis (), walls, tanks,canonPower,1,kills));
                                setBulletType ("Normal");
                            }
                            else
                            {
                                bullets.add (new Bullet (getCanonStartX (), getCanonStartY (),
                                        getDegree (), System.currentTimeMillis (), walls, tanks,canonPower,1,kills));
                            }

                            canShot = false;
                            shot = true;
                            new Thread (new Runnable () {
                                @Override
                                public void run () {
                                    try {
                                        Thread.sleep (100);
                                        shot = false;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace ();
                                    }
                                }
                            }).start ();
                            new Thread (new Runnable () {
                                @Override
                                public void run () {
                                    try {
                                        Thread.sleep (500);
                                        canShot = true;
                                    } catch (InterruptedException e) {
                                        e.printStackTrace ();
                                    }
                                }
                            }).start ();
                        }
                }
            }

        }
    }

    /**
     *
     * @return get CanonPower
     */
    public int getCanonPower() {
        return canonPower;
    }
}




