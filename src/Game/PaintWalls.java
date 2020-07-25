package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PaintWalls extends SwingWorker<Long, Object>
{
    private Graphics2D g2d;
    private GameState state;

    public PaintWalls(Graphics2D g2d, GameState state)
    {
        this.g2d = g2d;
        this.state = state;
    }

    @Override
    protected Long doInBackground() throws Exception
    {
        for(int i=0;i<state.getMaps().getWalls().size();i++)
        {
            Wall temp = state.getMaps().getWalls().get(i);
            if(temp.getType().equals("H")) // ofoqi
            {
                int y = temp.getY();
                if(y==0)
                {
                    y+=31;
                }
                if(720-y<=5)
                {
                    y-=20;
                }
                BufferedImage wallND = ImageIO.read (new File("Images/Walls/RedH.png"));
                BufferedImage wallD = ImageIO.read (new File("Images/Walls/YellowH.png"));
                int w = wallND.getWidth();
                if(!temp.isDestructible())
                {
                    for(int p=0;p<=temp.getLength()/w;p++)
                    {
                        g2d.drawImage(wallND, temp.getX() + p * w, y, null);
                    }
                }
                else
                {
                    if(temp.isOK())
                        for(int p=0;p<=temp.getLength()/w;p++)
                        {
                            g2d.drawImage(wallD, temp.getX() + p * w, y, null);
                        }
                }
            }
            if(temp.getType().equals("V")) // amodi
            {
                //System.out.println(temp.getX() + " " + temp.getY());
                int x = temp.getX();
                if (x == 0)
                {
                    x += 6;
                }
                if(1280 - x < 5)
                {
                    x-=20;
                }

                BufferedImage wallND = ImageIO.read(new File("Images/Walls/RedV.png"));
                BufferedImage wallD = ImageIO.read(new File("Images/Walls/YellowV.png"));
                int h = wallND.getHeight();
                if(!temp.isDestructible())
                {
                    for(int p=0;p<=temp.getLength()/h;p++)
                    {
                        g2d.drawImage(wallND,x,temp.getY()+p*h,null);
                    }
                }
                else
                {
                    if(temp.isOK())
                        for(int p=0;p<=temp.getLength()/h;p++)
                        {
                            g2d.drawImage(wallD,x,temp.getY()+p*h,null);
                        }
                }
            }
        }

        return null;
    }
}
