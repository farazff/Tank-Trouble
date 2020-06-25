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
    private JLabel down; // left button
    private JLabel mode; // mode
    private JLabel up; // right button

    private ArrayList<String> data; // the choices that we can select
    private int dataCounter; // the number of the data that is on the screen

    /**
     * constructor of the class
     * @param data the array list containing the choices of that data
     */
    public Selecting(ArrayList<String> data , int dataCounter)
    {
        this.data = data;
        this.dataCounter = dataCounter;
        MouseHandler mouseHandler = new MouseHandler();

        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        setOpaque(true);

        down = new JLabel("<");
        down.setFont(new Font("Arial",Font.BOLD,20));
        down.setForeground(Color.WHITE);
        down.setBackground(new Color(85,131,32));
        down.setOpaque(true);
        down.setPreferredSize(new Dimension(80,40));
        down.setHorizontalAlignment(JLabel.CENTER);
        down.addMouseListener(mouseHandler);


        mode = new JLabel(data.get(dataCounter));
        mode.setFont(new Font("Arial",Font.BOLD,20));
        mode.setForeground(Color.WHITE);
        mode.setBackground(new Color(201,133,41));
        mode.setOpaque(true);
        mode.setPreferredSize(new Dimension(200,40));
        mode.setHorizontalAlignment(JLabel.CENTER);


        up = new JLabel(">");
        up.setFont(new Font("Arial",Font.BOLD,20));
        up.setForeground(Color.WHITE);
        up.setBackground(new Color(85,131,32));
        up.setOpaque(true);
        up.setPreferredSize(new Dimension(80,40));
        up.setHorizontalAlignment(JLabel.CENTER);
        up.addMouseListener(mouseHandler);

        add(down);
        add(mode);
        add(up);
    }

    /**
     * handles mouse events
     */
    private class MouseHandler implements MouseMotionListener, MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(up))
            {
                if(dataCounter==data.size()-1)
                {
                    dataCounter=0;
                }
                else
                {
                    dataCounter++;
                }
                mode.setText(data.get(dataCounter));
            }
            if(e.getSource().equals(down))
            {
                if(dataCounter==0)
                {
                    dataCounter=data.size()-1;
                }
                else
                {
                    dataCounter--;
                }
                mode.setText(data.get(dataCounter));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}