package Game;

import GUI.Music;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Tank implements Runnable
{
    private int locX,locY,Health,power;
    private int degree;
    private String imageAddress;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean shot;
    private boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private int remainBullet;
    private ArrayList<Bullet> bullets;


    public Tank(ArrayList<Bullet> bullets)
    {
        this.bullets = bullets;
        remainBullet = 2;
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
        Health=100;
        power=50;
        degree = 45;
        imageAddress = "Images/Tanks/red315.png";
        checkRemainBullet ();
    }

    private void checkRemainBullet ()
    {
        new Thread (new Runnable () {
            @Override
            public void run () {
                while (getHealth () > 0)
                {

                    remainBullet = 2;

                    try {
                        Thread.sleep (1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace ();
                    }
                }
            }
        }).start ();
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

    public int getCanonStartX () throws IOException {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(imageAddress));
        int width = sourceImage.getWidth();
        return locX + width/2 - 2 +
                ((int)(Math.sqrt (968) * Math.cos (Math.toRadians (degree))));
    }

    public int getCanonStartY () throws IOException {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(imageAddress));
        int height = sourceImage.getHeight();
        return locY + height/2 - 2 +
                ((int)(Math.sqrt (968) * Math.sin (Math.toRadians (degree))));
    }

    public int getFireStartX () throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(imageAddress));
        int width = sourceImage.getWidth();
        return locX + width/2 - 2;
    }

    public int getFireStartY () throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(imageAddress));
        int height = sourceImage.getHeight();
        return locY + height/2 - 2;
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

    public int getHealth()
    {
        return Health;
    }

    public int getPower()
    {
        return power;
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


    public void update()
    {
        if(mousePress)
        {
            this.setLocY( mouseY - 30 / 2 );
            this.setLocX( mouseX - 30 / 2 );
        }

        if(keyUP)
		{
			this.addLocX((int) (8*Math.cos(  Math.toRadians(this.getDegree())  )));
			this.addLocY((int) (8*Math.sin(  Math.toRadians(this.getDegree())  )));
		}
		if(keyDOWN)
		{
			this.addLocX((int) (-8*Math.cos(  Math.toRadians(this.getDegree())  )));
			this.addLocY((int) (-8*Math.sin(  Math.toRadians(this.getDegree())  )));
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
                    try {
                        if (remainBullet > 0)
                        {
                            Music music = new Music();
                            music.setFilePath("C:\\Users\\ffara\\Downloads\\weapon_gun_1911_A_33-_AudioTrimmer.com_.au",false);
                            music.execute();
                            bullets.add (new Bullet (getCanonStartX (), getCanonStartY () ,
                                    getDegree (), System.currentTimeMillis ()));
                            remainBullet--;
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
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace ();
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
