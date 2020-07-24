package Game;

import GUI.Music;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Tank implements Runnable
{
    private int locX,locY,stamina;
    private int degree;
    private String imageAddress;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean shot;
    private boolean mousePress;
    private int height;
    private int width;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private boolean canShot;
    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls;
    private ArrayList<Tank> tanks;

    public Tank(ArrayList<Bullet> bullets, ArrayList<Wall> walls, ArrayList<Tank> tanks)
    {
        this.bullets = bullets;
        this.walls = walls;
        this.tanks = tanks;
        canShot = true;
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        shot = false;
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        locX= new Random ().nextInt (((16 * 720) / 9) - 200) + 100;
        locY=new Random ().nextInt (720 - 200) + 100;
        stamina =100;

        degree = 45;
        imageAddress = "Images/Tanks/red315.png";
        try {
            BufferedImage image = ImageIO.read (new File (getImageAddress ()));
            width = image.getWidth ();
            height = image.getHeight ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

    }


    public int getHeight () {
        return height;
    }

    public int getWidth () {
        return width;
    }

    public boolean looseStamina (int damage)
    {
        stamina -= damage;
        return stamina <= 0;
    }

    public KeyHandler getKeyHandler()
    {
        return keyHandler;
    }

    public MouseHandler getMouseHandler()
    {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener()
    {
        return mouseHandler;
    }

    public int getCanonStartX ()  {

        return locX + width/2 - 2 +
                ((int)(Math.sqrt (968) * Math.cos (Math.toRadians (degree))));
    }

    public int getCanonStartY () {
        return locY + height/2 - 2 +
                ((int)(Math.sqrt (968) * Math.sin (Math.toRadians (degree))));
    }




    public String getImageAddress () {
        return imageAddress;
    }

    public int getLocX()
    {
        return locX;
    }

    public void setLocX(int locX)
    {
        this.locX = locX;
    }

    public void addLocX(int adder)
    {
        locX += adder;
    }

    public int getLocY()
    {
        return locY;
    }

    public void setLocY(int locY)
    {
        this.locY = locY;
    }

    public void addLocY(int adder)
    {
        locY += adder;
    }

    public int getStamina()
    {
        return stamina;
    }



    public int getDegree()
    {
        return degree;
    }

    public void increaseDegree()
    {
        degree+=10;
        if(degree>=360)
            degree=0;
    }

    public void decreaseDegree()
    {
        degree-=10;
        if(degree<=0)
        {
            degree=359;
        }
    }

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
                    if(wall.getY()-locY<=60 && wall.getY()-locY>=0 && degree>=0 && degree<=150)
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
                    if(wall.getX()-locX<=60 && wall.getX()-locX>=0 &&
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

    public void update()
    {
        if(mousePress)
        {
            this.setLocY( mouseY - 30 / 2 );
            this.setLocX( mouseX - 30 / 2 );
        }

        int forX = (int) (8*Math.cos(  Math.toRadians(this.getDegree())  ));
        int forY = (int) (8*Math.sin(  Math.toRadians(this.getDegree())  ));

        if(keyUP && canMoveForward())
        {
            this.addLocX(forX);
            this.addLocY(forY);
        }
        if(keyDOWN && canMoveForward())
        {
            this.addLocX(-1*forX);
            this.addLocY(-1*forY);
        }


        if(keyRIGHT && !keyLEFT)
            this.increaseDegree();

        if(!keyRIGHT  && keyLEFT)
            this.decreaseDegree();

        this.setLocX(Math.max(this.getLocX(), 0));
        this.setLocX(Math.min(this.getLocX(), GameFrame.GAME_WIDTH - 30));
        this.setLocY(Math.max(this.getLocY(), 0));
        this.setLocY(Math.min(this.getLocY(), GameFrame.GAME_HEIGHT - 30));
    }


    @Override
    public void run()
    {
        this.update();
    }

    public boolean isShot () {
        return shot;
    }

    public String getFireImageAddress ()
    {
        return "./Images/Bullet/shotLarge.png";
    }

    private class KeyHandler extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
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

        @Override
        public void keyReleased(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
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
                case KeyEvent.VK_SPACE :

                    if (canShot)
                    {
                        Music music = new Music();
                        music.setFilePath("Files/Sounds/Bullet.au",false);
                        music.execute();
                        bullets.add (new Bullet (getCanonStartX (), getCanonStartY () ,
                                getDegree (), System.currentTimeMillis (),walls,tanks,bullets));
                        canShot = false;
                        shot = true;
                        new Thread (new Runnable () {
                            @Override
                            public void run () {
                                try {
                                    Thread.sleep (100);
                                    shot = false;
                                } catch (InterruptedException e)
                                {
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
                                } catch (InterruptedException e)
                                {
                                    e.printStackTrace ();
                                }
                            }
                        }).start ();
                    }

            }
        }
    }

    private class MouseHandler extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e)
        {
            mouseX = e.getX();
            mouseY = e.getY();
            mousePress = true;
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            mousePress = false;
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
}

