package GUI.MultiGamePanels;


import GameData.MultiGame;
import GameData.ServerInformation;
import GameData.User;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

/**
 * this class represents server item in list
 */
public class ServerButtonPanel extends JPanel {
    private JLabel url;
    private JLabel numOfActiveGames;
    private JLabel currentCapacity;
    private ServerInformation serverInformation;
    private MultiGamePanel mainPanel;
    private JPanel multiGameListPanel;
    private boolean selected;

    /**
     * creates new Server button panel
     * @param serverInformation serverInformation
     * @param mainPanel mainPanel
     * @param frame frame
     * @param user user
     */
    public ServerButtonPanel (ServerInformation serverInformation, MultiGamePanel mainPanel,
                              JFrame frame, User user)
    {
        super();
        selected = false;
        setLayout (new FlowLayout (FlowLayout.LEFT));
        setBackground (Color.GRAY);
        if (serverInformation == null)
            throw new InputMismatchException ("ServerInformation is Null");
        this.mainPanel = mainPanel;
        setBackground (Color.WHITE);
        this.url = new JLabel ("Url : " + serverInformation.getUrl ());
        this.url.setFont (new Font ("Arial",Font.PLAIN,14));
        this.url.setForeground (Color.DARK_GRAY);
        this.serverInformation = serverInformation;
        this.numOfActiveGames = new JLabel ("ActiveGames : " + serverInformation.getNumOfActiveGames ());
        this.numOfActiveGames.setFont (new Font ("Arial",Font.PLAIN,14));
        this.numOfActiveGames.setForeground (Color.DARK_GRAY);
        this.currentCapacity = new JLabel ("CurrentCapacity : " + serverInformation.getCurrentCapacity ());
        currentCapacity.setFont (new Font ("Arial",Font.PLAIN,14));
        this.currentCapacity.setForeground (Color.DARK_GRAY);
        multiGameListPanel = new MultiGameListPanel (serverInformation.getMultiGames (),mainPanel
                     ,frame,user);
        createBasePanel ();
    }

    /**
     * set selected
     * @param selected selected
     */
    public void setSelected (boolean selected) {
        this.selected = selected;
    }

    /**
     *
     * @return is selected
     */
    public boolean isSelected () {
        return selected;
    }

    /**
     * adds new game
     * @param multiGame multiGame
     */
    public void addNewGame (MultiGame multiGame)
    {
        numOfActiveGames.setVisible (false);
        numOfActiveGames.setVisible (true);
        getServerInformation ().addGame (multiGame);
        MultiGameListPanel multiGameListPanel = (MultiGameListPanel)getMultiGameListPanel ();
        multiGameListPanel.addNewMultiGame (multiGame);
    }

    /**
     *
     * @return get ServerInformation
     */
    public ServerInformation getServerInformation () {
        return serverInformation;
    }

    /**
     * change font and color
     * @param font font
     * @param color color
     */
    public void changeFontAndColor (Font font, Color color)
    {
        url.setFont (font);
        numOfActiveGames.setFont (font);
        currentCapacity.setFont (font);
        url.setForeground (color);
        numOfActiveGames.setForeground (color);
        currentCapacity.setForeground (color);

    }

    /**
     * creates base panel
     */
    private void createBasePanel ()
    {
        add (url);
        add (new JLabel (" "));
        add (numOfActiveGames);
        add (new JLabel (" "));
        add(currentCapacity);
    }

    /**
     *
     * @return get MultiGameListPanel
     */
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
