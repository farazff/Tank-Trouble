package GUI.MultiGame;

import GUI.ButtonPanel;
import GUI.ListPanel;
import GameData.MultiGame;
import GameData.Server;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class ServerButtonPanel extends ButtonPanel
{
    private JLabel url;
    private JLabel numOfActiveGames;
    private JLabel currentCapacity;
    private Server server;
    private ListPanel gamesListPanel;


    public ServerButtonPanel (Server server)
    {
        super();
        if (server == null)
            throw new InputMismatchException ("Server is Null");
        this.url = new JLabel ("Url : " + server.getUrl ());
        this.url.setFont (new Font ("Arial",Font.PLAIN,15));
        this.server = server;
        this.numOfActiveGames = new JLabel ("ActiveGames : " + server.getNumOfActiveGames ());
        this.numOfActiveGames.setFont (new Font ("Arial",Font.PLAIN,15));
        this.currentCapacity = new JLabel ("CurrentCapacity : " + server.getCurrentCapacity ());
        currentCapacity.setFont (new Font ("Arial",Font.PLAIN,15));
        ArrayList<ButtonPanel> buttonPanels = new ArrayList<>();

        for (MultiGame multiGame : server.getMultiGames ())
        {
            buttonPanels.add (new MultiGameButtonPanel (multiGame));
        }
        gamesListPanel = new MultiGameListPanel (buttonPanels);
        createBasePanel ();
    }

    public Server getServer () {
        return server;
    }

    public ListPanel getGamesList () {
        return gamesListPanel;
    }

    private void createBasePanel ()
    {
        add (url);
        add (new JLabel ("|"));
        add (numOfActiveGames);
        add (new JLabel ("|"));
        add(currentCapacity);
    }

    @Override
    public Dimension getMinimumSize () {
        return new Dimension (450,34);
    }


    @Override
    public Dimension getPreferredSize () {
        return new Dimension (450,34);
    }

}
