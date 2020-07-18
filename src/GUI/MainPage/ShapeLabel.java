package GUI.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class ShapeLabel extends JLabel
{

    private Color inside = Color.RED;
    private Color round = Color.BLACK;

    private  int[] x;
    private  int[] y;


    public ShapeLabel(String text,int[] x,int []y)
    {
        super(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial",Font.PLAIN,18));
        this.x = x;
        this.y = y;
    }

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

    public void rePaintEntered()
    {
        inside = Color.CYAN;
        round = Color.RED;
        this.repaint();
    }

    public void rePaintExited()
    {
        inside = Color.RED;
        round = Color.BLACK;
        this.repaint();
    }

    public void rePaintHlolding()
    {
        inside = Color.GRAY;
        round = Color.GREEN;
        this.repaint();
    }
}
