package GUI;

import javax.swing.*;
import java.awt.*;

public class ServerButtonPanel extends ButtonPanel
{
    private JLabel url;
    private JLabel creatorName;

    private JLabel numOfActiveGames;


    public ServerButtonPanel (String url, String creatorName, int numOfActiveGames)
    {
        super();
        this.url = new JLabel ("Url : " + url);
        this.url.setFont (new Font ("Arial",Font.PLAIN,15));
        this.creatorName = new JLabel ("Owner : " + creatorName);
        this.creatorName.setFont (new Font ("Arial",Font.PLAIN,15));
        this.numOfActiveGames = new JLabel ("ActiveGames : " + numOfActiveGames);
        this.numOfActiveGames.setFont (new Font ("Arial",Font.PLAIN,15));
        createBasePanel ();
    }

    protected void createBasePanel ()
    {
        setBackground (Color.GRAY);
        add (url);
        add (new JLabel ("|"));
        add (creatorName);
        add (new JLabel ("|"));
        add (numOfActiveGames);
    }

    @Override
    public Dimension getMinimumSize () {
        return new Dimension (450,34);
    }

    @Override
    public Dimension getMaximumSize () {
        return new Dimension (1800,34);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (450,34);
    }

}
