package GUI.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * we can create different shapes of JPanel with this class
 */

public class ShapeLabel extends JLabel
{
    private Color inside = new Color(1,114,23 );
    private Color round = Color.BLACK;

    private  int[] x;
    private  int[] y;


    /**
     * the constructor of the ShapeJLabel
     * @param text the text of the JPanel
     * @param x the x coordinate of it
     * @param y the Y coordinate of it
     */
    public ShapeLabel(String text,int[] x,int []y)
    {
        super(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial",Font.PLAIN,18));
        this.x = x;
        this.y = y;
    }

    /**
     * paint the component
     * @param g the Graphic of the component
     */
    public void paintComponent(Graphics g)
    {

        GeneralPath temp = new GeneralPath();
        temp.moveTo(x[0],y[0]);
        for(int i=1;i<x.length;i++)
        {
            temp.lineTo(x[i],y[i]);
        }
        temp.closePath();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(inside);
        g2d.fill(temp);

        g2d.setStroke(new BasicStroke(3));
        g2d.setPaint(round);
        g2d.draw(temp);

        super.paintComponent(g);
    }

    /**
     * paint the component if muse entered it
     */
    public void rePaintEntered()
    {
        inside = Color.CYAN;
        round = new Color(1,114,23 );
        this.repaint();
    }

    /**
     * paint the component if mouse exited
     */
    public void rePaintExited()
    {
        inside = new Color(1,114,23 );
        round = Color.BLACK;
        this.repaint();
    }

    /**
     * paint component if mouse is holding the panel
     */
    public void rePaintHolding()
    {
        inside = Color.GRAY;
        round = Color.GREEN;
        this.repaint();
    }
}
