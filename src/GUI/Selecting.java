package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * create's JPanel with 2 bottoms and a JTextfield for choosing the game options
 *
 *
 * @author Faraz Farangizadeh
 */
public class Selecting extends JPanel
{
    private String currentValue;
    private JLabel temp;
    private JLabel down; // left button
    private JLabel mode; // mode
    private JLabel up; // right button
    private Color backGround; // color of the background of JPanel
    private Color middlePart; // color of the JLabel where the text is
    private Font font; // the font of the panel


    private ArrayList<String> data; // the choices that we can select
    private int dataCounter; // the number of the data that is on the screen

    /**
     * constructor of the class
     * @param data the array list containing the choices of that data
     */
    public Selecting(ArrayList<String> data , int dataCounter , Color backGround , Color middlePart , Font font)
    {
        this.data = data;
        this.dataCounter = dataCounter;
        this.backGround = backGround;
        this.middlePart = middlePart;
        this.font = font;
        MouseHandler mouseHandler = new MouseHandler();

        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setBackground(backGround);
        setOpaque(true);

        down = new JLabel("<");
        down.setFont(font);
        down.setForeground(Color.WHITE);
        down.setBackground(new Color(85,131,32));
        down.setOpaque(true);
        down.setPreferredSize(new Dimension(78,40));
        down.setHorizontalAlignment(JLabel.CENTER);
        down.addMouseListener(mouseHandler);


        mode = new JLabel(data.get(dataCounter));
        currentValue = data.get(dataCounter);
        mode.setFont(font);
        mode.setForeground(Color.WHITE);
        mode.setBackground(middlePart);
        mode.setOpaque(true);
        mode.setPreferredSize(new Dimension(200,40));
        mode.setHorizontalAlignment(JLabel.CENTER);


        up = new JLabel(">");
        up.setFont(font);
        up.setForeground(Color.WHITE);
        up.setBackground(new Color(85,131,32));
        up.setOpaque(true);
        up.setPreferredSize(new Dimension(78,40));
        up.setHorizontalAlignment(JLabel.CENTER);
        up.addMouseListener(mouseHandler);

        add(down);
        add(mode);
        add(up);
    }

    public String getCurrentValue()
    {
        return currentValue;
    }

    /**
     * handles mouse events
     */
    private class MouseHandler implements MouseMotionListener, MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(up))
            {
                Music music = new Music();
                music.execute();
                if(dataCounter==data.size()-1)
                {
                    dataCounter=0;
                }
                else
                {
                    dataCounter++;
                }
                mode.setText(data.get(dataCounter));
                currentValue = data.get(dataCounter);
            }
            if(e.getSource().equals(down))
            {
                Music music = new Music();
                music.execute();
                if(dataCounter==0)
                {
                    dataCounter=data.size()-1;
                }
                else
                {
                    dataCounter--;
                }
                mode.setText(data.get(dataCounter));
                currentValue = data.get(dataCounter);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if(e.getSource().equals(down) || e.getSource().equals(up))
            {
                if(e.getSource().equals(down))
                    temp = down;
                if(e.getSource().equals(up))
                    temp = up;
                Thread a = new Thread(new Runnable()
                {
                   @Override
                   public void run()
                   {
                       for(int i=0;i<4;i++)
                       {

                           switch (i)
                           {
                               case 0 : temp.setBackground(new Color(71,112,27));
                                break;
                               case 1 : temp.setBackground(new Color(55,85,21));
                                   break;
                               case 2 : temp.setBackground(new Color(39,62,15));
                                   break;
                               case 3 : temp.setBackground(new Color(30,46,12));
                                   break;
                           }
                           try
                           {
                               Thread.sleep(50);
                           }
                           catch (InterruptedException ex)
                           {
                               ex.printStackTrace();
                           }
                       }
                   }
               });
               a.start();
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            if(e.getSource().equals(down) || e.getSource().equals(up))
            {
                if(e.getSource().equals(down))
                    temp = down;
                if(e.getSource().equals(up))
                    temp = up;
                Thread a = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for(int i=0;i<=4;i++)
                        {

                            switch (i)
                            {
                                case 0 : temp.setBackground(new Color(30,46,12));
                                    break;
                                case 1 : temp.setBackground(new Color(39,62,15));
                                    break;
                                case 2 : temp.setBackground(new Color(55,85,21));
                                    break;
                                case 3 : temp.setBackground(new Color(71,112,27));
                                    break;
                                case 4 : temp.setBackground(new Color(85,131,32));
                                    break;
                            }
                            try
                            {
                                Thread.sleep(50);
                            }
                            catch (InterruptedException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                a.start();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}