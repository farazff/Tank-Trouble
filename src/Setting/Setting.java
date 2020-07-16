package Setting;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Setting extends JPanel
{
    private ColorJLabel userInfo;
    private ColorJLabel defaults;
    private MouseHandler mouse = new MouseHandler();

    public Setting()
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,600));
        createLeft();
    }

    private void createLeft()
    {
        JPanel left = new JPanel(new GridLayout(10,1,10,10));
        left.setPreferredSize(new Dimension(200,600));
        left.setBackground(Color.PINK);
        this.add(left,BorderLayout.WEST);

        userInfo = new ColorJLabel("    User info");
        userInfo.addMouseListener(mouse);

        defaults = new ColorJLabel("    Game Defaults");

        left.add(userInfo);
        left.add(defaults);
    }

    private class MouseHandler implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(userInfo))
            {
                System.out.println("Hiiii");
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
