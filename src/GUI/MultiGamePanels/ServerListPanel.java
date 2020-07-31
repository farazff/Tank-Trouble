package GUI.MultiGamePanels;


import GUI.Music;
import GameData.ServerInformation;
import GameData.ServerInformationStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class ServerListPanel extends JPanel
{


    private MultiGamePanel mainPanel;
    private ArrayList<ServerButtonPanel> serverButtonPanels;
    private ServerInformationStorage serverInformationStorage;

    public ServerListPanel (ServerInformationStorage serverInformationStorage, MultiGamePanel mainPanel)
    {
        super();
        if (serverInformationStorage == null)
            throw new InputMismatchException ("servers in Null");
        this.mainPanel = mainPanel;
        this.serverInformationStorage = serverInformationStorage;
        serverButtonPanels = new ArrayList<> ();
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.WHITE);
        setBorder (new EmptyBorder (5,5,5,5));
        MouseHandler mouseHandler = new MouseHandler ();
        for (ServerInformation serverInformation : serverInformationStorage.getServerData ())
        {
            ServerButtonPanel serverButtonPanel = new ServerButtonPanel (serverInformation,mainPanel);
            add(serverButtonPanel);
            serverButtonPanel.addMouseListener (mouseHandler);
            serverButtonPanels.add (serverButtonPanel);
        }

    }

    public void addNewServer (ServerInformation serverInformation)
    {
        MouseHandler mouseHandler = new MouseHandler ();
        serverInformationStorage.addNewServer (serverInformation);
        ServerButtonPanel serverButtonPanel = new ServerButtonPanel (serverInformation,mainPanel);
        serverButtonPanels.add (serverButtonPanel);
        serverButtonPanel.addMouseListener (mouseHandler);
        this.setVisible (false);
        add(serverButtonPanel);
        this.setVisible (true);
    }

    public void removeServer (ServerButtonPanel serverButtonPanel)
    {

        serverButtonPanel.setVisible (false);
        serverButtonPanel.remove (serverButtonPanel);
        serverInformationStorage.removeServer (serverButtonPanel.getServerInformation ());
        this.setVisible (false);
        this.setVisible (true);
    }


    public ArrayList<ServerButtonPanel> getServerButtonPanels () {
        return serverButtonPanels;
    }

    public MultiGamePanel getMainPanel () {
        return mainPanel;
    }

    private class MouseHandler extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e) {
            e.getComponent ().
                    setBackground (new Color (91, 131, 56));
        }

        @Override
        public void mouseExited (MouseEvent e) {
            e.getComponent ().setBackground (Color.WHITE);
        }

        @Override
        public void mouseClicked (MouseEvent e) {
            Music music = new Music ();
            music.execute ();
            for (ServerButtonPanel serverButtonPanel : serverButtonPanels)
            {
                if (e.getComponent () == serverButtonPanel)
                {
                    if (mainPanel != null)
                        mainPanel.setSecondPanel (serverButtonPanel.getMultiGameListPanel (),serverButtonPanel);
                    serverButtonPanel.changeFontAndColor (
                            new Font ("Arial",Font.ITALIC,14),Color.BLACK);
                    serverButtonPanel.setSelected (true);
                }
                else
                {
                    serverButtonPanel.changeFontAndColor (new Font ("Arial",Font.PLAIN,14),
                            Color.DARK_GRAY);
                    serverButtonPanel.setSelected (false);
                }
            }
        }
    }
}
