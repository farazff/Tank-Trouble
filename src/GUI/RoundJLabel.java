package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundJLabel extends JLabel
{

    Color color;
    public RoundJLabel(String text , Color color)
    {
        super(text);
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Rectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1));

        g2d.setPaint(color);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new RoundRectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1,18,18));
    }
}
