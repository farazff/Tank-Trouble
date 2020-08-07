package GUI.MultiGamePanels;

import GUI.MainPage.Main;
import GameData.MultiGame;
import GameData.User;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

/**
 * this class represents multi game item in list
 */
public class MultiGameButtonPanel extends JPanel
{
    private JLabel gameName;
    private JLabel onlinePlayers;
    private JLabel remainPlayers;
    private MultiGame multiGame;
    private MultiGamePanel mainPanel;
    private JPanel multiGameDataPanel;
    private boolean selected;

    /**
     * creates new multi game button panel
     * @param multiGame multiGame
     * @param mainPanel mainPanel
     * @param frame frame
     * @param user user
     * @param main main
     */
    public MultiGameButtonPanel (MultiGame multiGame, MultiGamePanel mainPanel, JFrame frame, User user,
                                 JPanel main)
    {
        super();
        selected = false;
        setLayout (new FlowLayout (FlowLayout.LEFT));
        setBackground (Color.GRAY);
        if (multiGame == null)
            throw new InputMismatchException ("multi multiGame is Null");
        this.mainPanel = mainPanel;
        setBackground (Color.WHITE);
        this.multiGame = multiGame;
        gameName = new JLabel ("Name : " + multiGame.getName ());
        gameName.setFont (new Font ("Arial",Font.PLAIN,14));
        gameName.setForeground (Color.DARK_GRAY);
        onlinePlayers = new JLabel ("Online Players : " +
                multiGame.getOnlineUsersNumber () + "");
        onlinePlayers.setFont (new Font ("Arial",Font.PLAIN,14));
        onlinePlayers.setForeground (Color.DARK_GRAY);
        remainPlayers = new JLabel ("Remain Players : " +
                (-multiGame.getOnlineUsersNumber () + multiGame.getNumberOfPlayers ()));
        remainPlayers.setFont (new Font ("Arial",Font.PLAIN,14));
        remainPlayers.setForeground (Color.DARK_GRAY);
        multiGameDataPanel = new MultiGameDataPanel (multiGame,frame,user,main);
        createComponents ();
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
     * @return isSelected
     */
    public boolean isSelected () {
        return selected;
    }

    /**
     *
     * @return get MultiGameDataPanel
     */
    public JPanel getMultiGameDataPanel () {
        return multiGameDataPanel;
    }

    /**
     * creates components
     */
    private void createComponents ()
    {
        add (gameName);
        add (new JLabel (" "));
        add (onlinePlayers);
        add (new JLabel (" "));
        add(remainPlayers);
    }

    /**
     * change font and color
     * @param font font
     * @param color color
     */
    public void changeFontAndColor (Font font, Color color)
    {
        gameName.setFont (font);
        onlinePlayers.setFont (font);
        remainPlayers.setFont (font);
        gameName.setForeground (color);
        onlinePlayers.setForeground (color);
        remainPlayers.setForeground (color);

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
