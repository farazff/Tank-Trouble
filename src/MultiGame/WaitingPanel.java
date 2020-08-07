package MultiGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * creates a panel with Waiting image
 */

public class WaitingPanel extends JPanel
{
    /**
     * the constructor
     */
    public WaitingPanel()
    {
        super();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        try
        {
            BufferedImage image = ImageIO.read(new File("Images/Waiting.jpg"));
            g2d.drawImage(image, 0, 0, null);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
