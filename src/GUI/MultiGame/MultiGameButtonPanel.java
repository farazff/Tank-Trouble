package GUI.MultiGame;

import GameData.MultiGame;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

public class MultiGameButtonPanel extends JPanel
{
    private JLabel gameName;
    private JLabel onlinePlayers;
    private JLabel remainPlayers;
    private MultiGame multiGame;
    private MultiGamePanel mainPanel;
    private JPanel multiGameDataPanel;
    private boolean selected;


    public MultiGameButtonPanel (MultiGame multiGame, MultiGamePanel mainPanel)
    {
        super();
        selected = false;
        setLayout (new FlowLayout (FlowLayout.LEFT));
        setBackground (Color.GRAY);
        if (multiGame == null)
            throw new InputMismatchException ("multi game is Null");
        this.mainPanel = mainPanel;
        setBackground (Color.WHITE);
        this.multiGame = multiGame;
        gameName = new JLabel ("Name : " + multiGame.getName ());
        gameName.setFont (new Font ("Arial",Font.PLAIN,14));
        gameName.setForeground (Color.DARK_GRAY);
        onlinePlayers = new JLabel ("Online Players : " +
                multiGame.getOnlineUsers () + "");
        onlinePlayers.setFont (new Font ("Arial",Font.PLAIN,14));
        onlinePlayers.setForeground (Color.DARK_GRAY);
        remainPlayers = new JLabel ("Remain Players : " +
                (-multiGame.getOnlineUsers () + multiGame.getNumberOfPlayers ()));
        remainPlayers.setFont (new Font ("Arial",Font.PLAIN,14));
        remainPlayers.setForeground (Color.DARK_GRAY);
        multiGameDataPanel = new MultiGameDataPanel (multiGame);
        createComponents ();
    }

    public void setSelected (boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected () {
        return selected;
    }

    public JPanel getMultiGameDataPanel () {
        return multiGameDataPanel;
    }

    private void createComponents ()
    {
        add (gameName);
        add (new JLabel (" "));
        add (onlinePlayers);
        add (new JLabel (" "));
        add(remainPlayers);
    }

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
