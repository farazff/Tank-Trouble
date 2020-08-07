package GUI;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * put image on  a JPanel
 *
 */
public class PictureJLabel extends JPanel
{

    private BufferedImage image;

    /**
     * creates a picture JLabel
     * @param address address
     */
    public PictureJLabel(String address)
    {
        try
        {
            image = ImageIO.read(new File(address));
        }
        catch (IOException ex)
        {
            // handle exception...
        }
    }

    /**
     * change the image of a JPanel
     * @param address the address of the image
     */
    public void changeImage(String address)
    {
        try
        {
            image = ImageIO.read(new File(address));
        }
        catch (IOException ex)
        {
            // handle exception...
        }
        finally
        {
            paintComponent(this.getGraphics());
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if(g!=null)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // see javadoc for more info on the parameters
        }
    }

}