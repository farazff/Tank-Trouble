package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GameWithPC extends JPanel
{

    private RoundJLabel down;
    private RoundJLabel mode;
    private RoundJLabel up;

    private ArrayList <String> gameMods;
    private int gameModesCounter;

    public GameWithPC()
    {
        gameMods = new ArrayList<>();
        gameMods.add("Death Match");
        gameMods.add("League");
        gameModesCounter=1;
        setLayout(new GridLayout(7,1,5,5));
        setBackground(Color.GRAY);
        setOpaque(true);

        JLabel header = new JLabel(" Single mode ");
        header.setFont (new Font ("DialogInput",Font.BOLD,23));
        header.setHorizontalAlignment(JLabel.CENTER);

        add(header);
        
        JLabel titleMode = new JLabel(" Game Mode: ");
        titleMode.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleMode);

        Selecting selectMode = new Selecting(gameMods.get(0));
        add(selectMode);
    }


    private class Selecting extends JPanel
    {
        private Selecting(String defaultMode)
        {

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


            mode = new RoundJLabel(defaultMode,Color.pink);
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

    }
    private class MouseHandler implements MouseMotionListener , MouseListener
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
                if(gameModesCounter==gameMods.size()-1)
                {
                    gameModesCounter=0;
                }
                else
                {
                    gameModesCounter++;
                }
                mode.setText(gameMods.get(gameModesCounter));
            }
            if(e.getSource().equals(down))
            {
                if(gameModesCounter==0)
                {
                    gameModesCounter=gameMods.size()-1;
                }
                else
                {
                    gameModesCounter--;
                }
                mode.setText(gameMods.get(gameModesCounter));
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
