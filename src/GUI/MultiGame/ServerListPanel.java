package GUI.MultiGame;


import GameData.Server;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class ServerListPanel extends JPanel
{


    private MultiGamePanel mainPanel;
    private ArrayList<ServerButtonPanel> serverButtonPanels;

    public ServerListPanel (ArrayList<Server> servers, MultiGamePanel mainPanel)
    {
        super();
        if (servers == null)
            throw new InputMismatchException ("servers in Null");
        this.mainPanel = mainPanel;
        serverButtonPanels = new ArrayList<> ();
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.GRAY);
        setBorder (new LineBorder (Color.GRAY,10,true));
        MouseHandler mouseHandler = new MouseHandler ();
        for (Server server : servers)
        {
            ServerButtonPanel serverButtonPanel = new ServerButtonPanel (server,mainPanel);
            add(serverButtonPanel);
            serverButtonPanel.addMouseListener (mouseHandler);
            serverButtonPanels.add (serverButtonPanel);
        }

    }



    public MultiGamePanel getMainPanel () {
        return mainPanel;
    }

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
            ServerButtonPanel serverButtonPanel = (ServerButtonPanel)e.getComponent ();
            mainPanel.setSecondPanel (serverButtonPanel.getMultiGameListPanel ());
        }
    }
}
