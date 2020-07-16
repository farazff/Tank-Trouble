package Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class ColorJLabel extends JLabel
{
    public ColorJLabel(String text)
    {
        super(text);
        this.setBackground(Color.GREEN);
        this.setOpaque(true);
        this.setFont(new Font("Arial",Font.BOLD,20));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setPaint(Color.BLUE);
        g2d.draw(new Line2D.Double(2,0,2,this.getHeight()));
    }
}
