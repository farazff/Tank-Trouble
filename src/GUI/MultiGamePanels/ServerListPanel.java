package GUI.MultiGamePanels;


import GUI.Music;
import GameData.ServerInformation;
import GameData.ServerInformationStorage;
import GameData.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * this panel is list of servers
 */
public class ServerListPanel extends JPanel
{


    private MultiGamePanel mainPanel;
    private ArrayList<ServerButtonPanel> serverButtonPanels;
    private JFrame frame;
    private User user;
    private JPanel main;

    /**
     * creates server list panel
     * @param mainPanel mainPanel
     * @param frame frame
     * @param user user
     */
    public ServerListPanel (MultiGamePanel mainPanel,
                            JFrame frame, User user, JPanel main)
    {
        super();
        if (user == null)
            throw new InputMismatchException ("input is Null");
        this.mainPanel = mainPanel;
        this.frame = frame;
        this.main = main;
        this.user = user;
        serverButtonPanels = new ArrayList<> ();
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.WHITE);
        setBorder (new EmptyBorder (5,5,5,5));
        MouseHandler mouseHandler = new MouseHandler ();
        for (ServerInformation serverInformation : user.getServerInformationStorage ().getServerData ())
        {
            ServerButtonPanel serverButtonPanel = new ServerButtonPanel (serverInformation,mainPanel,
                    frame,user,main);
            add(serverButtonPanel);
            serverButtonPanel.addMouseListener (mouseHandler);
            serverButtonPanels.add (serverButtonPanel);
        }

    }

    /**
     * add new server
     * @param serverInformation serverInformation
     */
    public void addNewServer (ServerInformation serverInformation)
    {
        MouseHandler mouseHandler = new MouseHandler ();
        user.getServerInformationStorage ().addNewServer (serverInformation);
        ServerButtonPanel serverButtonPanel = new ServerButtonPanel (serverInformation,mainPanel,
                frame,user,main);
        serverButtonPanels.add (serverButtonPanel);
        serverButtonPanel.addMouseListener (mouseHandler);
        this.setVisible (false);
        add(serverButtonPanel);
        this.setVisible (true);
    }

    /**
     * remove server
     * @param serverButtonPanel serverButtonPanel
     */
    public void removeServer (ServerButtonPanel serverButtonPanel)
    {

        serverButtonPanel.setVisible (false);
        serverButtonPanel.remove (serverButtonPanel);
        user.getServerInformationStorage ().removeServer (serverButtonPanel.getServerInformation ());
        this.setVisible (false);
        this.setVisible (true);
    }


    /**
     *
     * @return get ServerButtonPanels
     */
    public ArrayList<ServerButtonPanel> getServerButtonPanels () {
        return serverButtonPanels;
    }

    /**
     *
     * @return get MainPanel
     */
    public MultiGamePanel getMainPanel () {
        return mainPanel;
    }

    /**
     * this class handles components
     */
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
