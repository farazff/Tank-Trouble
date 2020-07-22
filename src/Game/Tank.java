package Game;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank implements Runnable
{
    private int locX,locY,Health,power;
    private int degree;
    private String image;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;


    public Tank()
    {
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        locX=200;
        locY=200;
        Health=100;
        power=50;
        degree = 45;
        image = "Images/Tanks/red315.png";
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

    public int getCenterX() throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(image));
        int width = sourceImage.getWidth();
        return locX + width/2;
    }

    public int getCenterY() throws IOException
    {
        BufferedImage sourceImage;
        sourceImage = ImageIO.read(new File(image));
        int height = sourceImage.getHeight();
        return locY + height/2;
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

    public String getImage()
    {
        return image;
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

    class KeyHandler extends KeyAdapter
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
            }
        }
    }

    class MouseHandler extends MouseAdapter
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
            System.out.println("hiiiiiiiiii");
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
}
