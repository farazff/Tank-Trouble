package GUI;

import javax.swing.*;
import java.util.ArrayList;


public class ServerListPanel extends JPanel
{
    private ArrayList<ServerPanel> serverPanels;


    public ServerListPanel (ArrayList<ServerPanel> serverPanels)
    {
        super();

        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));

        this.serverPanels = new ArrayList<> ();

        if (serverPanels != null)
            for (ServerPanel serverPanel : serverPanels)
                {
                    add(serverPanel);
                    serverPanels.add (serverPanel);
                }
    }
}
