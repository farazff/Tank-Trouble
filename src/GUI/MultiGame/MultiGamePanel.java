package GUI.MultiGame;

import GUI.ButtonPanel;
import GUI.NullPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;


public class MultiGamePanel extends JPanel
{

    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private ServerListPanel serverListPanel;


    public MultiGamePanel (ArrayList<ButtonPanel> serverPanels)
    {
        setBackground (Color.GRAY);
        setLayout (new BorderLayout ());

        serverListPanel = new ServerListPanel (serverPanels);



        splitPane1 = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT);
        splitPane2 = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT);
        splitPane1.setDividerSize (3);
        splitPane2.setDividerSize (3);

        splitPane2.setRightComponent (new NullPanel ());
        splitPane2.setLeftComponent (new NullPanel ());
        JScrollPane scrollPane = new JScrollPane
                (serverListPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAutoscrolls (true);
        scrollPane.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane.setBorder (new LineBorder (Color.GRAY,1));
        splitPane1.setLeftComponent (serverListPanel);
        splitPane1.setRightComponent (splitPane2);
        add(splitPane1);
    }
}
