package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ServerListPanel extends JPanel
{
    private ArrayList<ServerPanel> serverPanels;


    public ServerListPanel (ArrayList<ServerPanel> serverPanels)
    {
        super();

        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.GRAY);
        setBorder (new LineBorder (Color.GRAY,10,true));
        this.serverPanels = new ArrayList<> (serverPanels);
        MouseHandler mouseHandler = new MouseHandler ();
        for (ServerPanel serverPanel : serverPanels)
            {
                add(serverPanel);
                serverPanel.addMouseListener (mouseHandler);
            }
    }

    /**
     * this class handles mouse for components in this panel
     */
    private class MouseHandler extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e) {

            e.getComponent ().setBackground (Color.LIGHT_GRAY);

        }

        @Override
        public void mouseExited (MouseEvent e) {

            e.getComponent ().setBackground (Color.GRAY);

        }

        @Override
        public void mouseClicked (MouseEvent e) {

        }
    }
}
