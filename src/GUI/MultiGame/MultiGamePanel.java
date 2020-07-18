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
    private JButton createNewGame;

    public MultiGamePanel (ArrayList<Server> servers)
    {
        setLayout (new GridLayout (1,3));
        firstPanel = new JPanel (new BorderLayout ());
        createNewGame = new JButton ("Create New Game");
        createNewGame.setFont (new Font ("Arial",Font.PLAIN,17));
        createNewGame.setMinimumSize (new Dimension (5,10));
        firstPanel.add (createNewGame,BorderLayout.SOUTH);

        secondPanel = new NullPanel ();
        thirdPanel = new NullPanel ();

        scrollPane1 = new JScrollPane (new ServerListPanel (servers,this),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        firstPanel.add (scrollPane1,BorderLayout.CENTER);
        scrollPane2 = new JScrollPane (secondPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3 = new JScrollPane (thirdPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(firstPanel);
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
        this.repaint ();
    }
}
