package GUI.MultiGame;


import GameData.MultiGame;
import GameData.Server;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.InputMismatchException;


public class ServerButtonPanel extends JPanel {
    private JLabel url;
    private JLabel numOfActiveGames;
    private JLabel currentCapacity;
    private Server server;
    private MultiGamePanel mainPanel;
    private JPanel multiGameListPanel;


    public ServerButtonPanel (Server server, MultiGamePanel mainPanel)
    {
        super();
        setLayout (new FlowLayout (FlowLayout.LEFT));
        setBackground (Color.GRAY);
        if (server == null)
            throw new InputMismatchException ("Server is Null");
        this.mainPanel = mainPanel;
        this.url = new JLabel ("Url : " + server.getUrl ());
        this.url.setFont (new Font ("Arial",Font.PLAIN,15));
        this.server = server;
        this.numOfActiveGames = new JLabel ("ActiveGames : " + server.getNumOfActiveGames ());
        this.numOfActiveGames.setFont (new Font ("Arial",Font.PLAIN,15));
        this.currentCapacity = new JLabel ("CurrentCapacity : " + server.getCurrentCapacity ());
        currentCapacity.setFont (new Font ("Arial",Font.PLAIN,15));

        multiGameListPanel = new MultiGameListPanel (server.getMultiGames (),mainPanel);
        createBasePanel ();
    }




    private void createBasePanel ()
    {
        add (url);
        add (new JLabel (" "));
        add (numOfActiveGames);
        add (new JLabel (" "));
        add(currentCapacity);
    }

    public JPanel getMultiGameListPanel () {
        return multiGameListPanel;
    }

    @Override
    public Dimension getMinimumSize () {
        return new Dimension (400,32);
    }

    @Override
    public Dimension getMaximumSize () {
        return new Dimension (1800,32);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (400,32);
    }

}
