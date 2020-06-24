package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


/**
 * create's JPanel with 2 bottoms and a JTextfield for choosing the game options
 *
 *
 * @author Faraz Farangizadeh
 */
public class Selecting extends JPanel
{
    private RoundJLabel down; // left button
    private RoundJLabel mode; // mode
    private RoundJLabel up; // right button

    private ArrayList<String> data; // the choices that we can select
    private int dataCounter;


    /**
     * constructor of the class
     * @param data the array list containing the choices of that data
     */
    public Selecting(ArrayList<String> data)
    {

        this.data = data;
        dataCounter = 1;

        MouseHandler mouseHandler = new MouseHandler();

        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setBackground(Color.WHITE);
        setOpaque(true);

        down = new RoundJLabel("<",Color.GREEN);
        down.setFont(new Font("Arial",Font.BOLD,20));
        down.setForeground(Color.WHITE);
        down.setBackground(Color.GREEN);
        down.setOpaque(true);
        down.setPreferredSize(new Dimension(80,50));
        down.setHorizontalAlignment(JLabel.CENTER);
        down.addMouseListener(mouseHandler);


        mode = new RoundJLabel(data.get(0),Color.pink);
        mode.setFont(new Font("Arial",Font.BOLD,20));
        mode.setForeground(Color.WHITE);
        mode.setBackground(Color.PINK);
        mode.setOpaque(true);
        mode.setPreferredSize(new Dimension(200,50));
        mode.setHorizontalAlignment(JLabel.CENTER);


        up = new RoundJLabel(">",Color.GREEN);
        up.setFont(new Font("Arial",Font.BOLD,20));
        up.setForeground(Color.WHITE);
        up.setBackground(Color.GREEN);
        up.setOpaque(true);
        up.setPreferredSize(new Dimension(80,50));
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