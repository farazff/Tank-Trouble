package GUI.MainPage;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class ExitJLabel extends JLabel
{

    private Color inside = Color.RED;
    private Color round = Color.BLACK;

    private  int[] x;
    private  int[] y;

    private  int[] x1;
    private  int[] y1;

    public ExitJLabel(String text,int[] x,int []y , int x1[] , int[] y1)
    {
        super(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial",Font.BOLD,17));
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    public void paintComponent(Graphics g)
    {

        GeneralPath temp1 = new GeneralPath();
        temp1.moveTo(x[0],y[0]);
        for(int i=1;i<x.length;i++)
        {
            temp1.lineTo(x[i],y[i]);
        }
        temp1.closePath();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(inside);
        g2d.fill(temp1);

        g2d.setStroke(new BasicStroke(3));
        g2d.setPaint(round);
        g2d.draw(temp1);


        GeneralPath temp2 = new GeneralPath();
        temp2.moveTo(x1[0],y1[0]);
        for(int i=1;i<x1.length;i++)
        {
            temp2.lineTo(x1[i],y1[i]);
        }
        temp2.closePath();
        g2d.setPaint(inside);
        g2d.fill(temp2);

        g2d.setStroke(new BasicStroke(3));
        g2d.setPaint(round);
        g2d.draw(temp2);


        super.paintComponent(g);
    }

    public void rePaintEntered()
    {
        inside = Color.CYAN;
        round = Color.BLACK;
        this.repaint();
    }

    public void rePaintExited()
    {
        inside = Color.RED;
        round = Color.BLACK;
        this.repaint();
    }

    public void rePaintHolding()
    {
        inside = Color.GRAY;
        round = Color.GREEN;
        this.repaint();
    }
}
