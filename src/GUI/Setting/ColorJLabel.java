package GUI.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * with this class we can make JLabels that have a Blue rectangle  the left side of them
 */

public class ColorJLabel extends JLabel
{
    public ColorJLabel(String text)
    {
        super(text);
        this.setBackground(new Color(163,73,164));
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setFont(new Font("Arial",Font.BOLD,20));
    }

    /**
     * paint the component
     * @param g the Graphic of the component
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setPaint(new Color(90,90,90));
        g2d.draw(new Line2D.Double(2,0,2,this.getHeight()));
    }
}
