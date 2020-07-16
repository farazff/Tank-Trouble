package GUI;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel
{
    private JLabel url;
    private JLabel creatorName;
    private JLabel numOfPlayer;
    private JLabel numOfActiveGames;


    public ServerPanel (String url, String creatorName, int numOfPlayer, int numOfActiveGames)
    {
        super();
        setLayout (new FlowLayout ());
        this.url = new JLabel (url);
        this.creatorName = new JLabel (creatorName);
        this.numOfActiveGames = new JLabel (numOfActiveGames + "");
        this.numOfPlayer = new JLabel (numOfPlayer + "");
        createBasePanel ();
    }

    private void createBasePanel ()
    {
        JPanel basePanel = new JPanel (new FlowLayout (FlowLayout.LEFT));
        basePanel.setBackground (Color.WHITE);
        basePanel.add (url);
        basePanel.add (new JSeparator (SwingConstants.VERTICAL));
        basePanel.add (creatorName);
        basePanel.add (new JSeparator (SwingConstants.VERTICAL));
        basePanel.add (numOfPlayer);
        basePanel.add (new JSeparator (SwingConstants.VERTICAL));
        basePanel.add (numOfActiveGames);
        add(basePanel);

    }

    @Override
    public Dimension getMinimumSize () {
        return new Dimension (350,38);
    }

    @Override
    public Dimension getMaximumSize () {
        return new Dimension (720,38);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (350,38);
    }

}
