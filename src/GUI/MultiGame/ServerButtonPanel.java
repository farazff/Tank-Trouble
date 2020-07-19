package GUI.MultiGame;


import GameData.MultiGame;
import GameData.Server;
import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;


public class ServerButtonPanel extends JPanel {
    private JLabel url;
    private JLabel numOfActiveGames;
    private JLabel currentCapacity;
    private Server server;
    private MultiGamePanel mainPanel;
    private JPanel multiGameListPanel;
    private boolean selected;


    public ServerButtonPanel (Server server, MultiGamePanel mainPanel)
    {
        super();
        selected = false;
        setLayout (new FlowLayout (FlowLayout.LEFT));
        setBackground (Color.GRAY);
        if (server == null)
            throw new InputMismatchException ("Server is Null");
        this.mainPanel = mainPanel;
        setBackground (Color.WHITE);
        this.url = new JLabel ("Url : " + server.getUrl ());
        this.url.setFont (new Font ("Arial",Font.PLAIN,14));
        this.url.setForeground (Color.DARK_GRAY);
        this.server = server;
        this.numOfActiveGames = new JLabel ("ActiveGames : " + server.getNumOfActiveGames ());
        this.numOfActiveGames.setFont (new Font ("Arial",Font.PLAIN,14));
        this.numOfActiveGames.setForeground (Color.DARK_GRAY);
        this.currentCapacity = new JLabel ("CurrentCapacity : " + server.getCurrentCapacity ());
        currentCapacity.setFont (new Font ("Arial",Font.PLAIN,14));
        this.currentCapacity.setForeground (Color.DARK_GRAY);
        multiGameListPanel = new MultiGameListPanel (server.getMultiGames (),mainPanel);
        createBasePanel ();
    }

    public void setSelected (boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected () {
        return selected;
    }

    public void addNewGame (MultiGame multiGame)
    {
        getServer ().addGame (multiGame);
        MultiGameListPanel multiGameListPanel = (MultiGameListPanel)getMultiGameListPanel ();
        multiGameListPanel.addNewMultiGame (multiGame);
    }

    public Server getServer () {
        return server;
    }

    public void changeFontAndColor (Font font, Color color)
    {
        url.setFont (font);
        numOfActiveGames.setFont (font);
        currentCapacity.setFont (font);
        url.setForeground (color);
        numOfActiveGames.setForeground (color);
        currentCapacity.setForeground (color);

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
        return new Dimension (1000,32);
    }

    @Override
    public Dimension getMaximumSize () {
        return new Dimension (1000,32);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (1000,32);
    }

}
