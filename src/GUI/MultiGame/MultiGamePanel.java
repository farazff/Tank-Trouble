package GUI.MultiGame;


import GUI.NullPanel;
import GameData.Server;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MultiGamePanel extends JPanel
{

    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;

    public MultiGamePanel (ArrayList<Server> servers)
    {
        setLayout (new GridLayout (1,3));
        firstPanel = new ServerListPanel (servers,this);
        secondPanel = new NullPanel ();
        thirdPanel = new NullPanel ();

        scrollPane1 = new JScrollPane (firstPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2 = new JScrollPane (secondPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3 = new JScrollPane (thirdPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane1);
        add(scrollPane2);
        add(scrollPane3);
    }

    public void setSecondPanel (JPanel secondPanel) {
        this.setVisible (false);
        this.remove (this.scrollPane2);
        this.secondPanel = secondPanel;
        scrollPane2 = new JScrollPane (secondPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add (scrollPane2,1);
        this.setVisible (true);
    }

    public void setThirdPanel (JPanel thirdPanel) {
        this.setVisible (false);
        this.remove (this.scrollPane3);
        this.thirdPanel = thirdPanel;
        scrollPane3 = new JScrollPane (thirdPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add (scrollPane3,2);
        this.setVisible (true);
    }
}
