package GUI.MultiGame;


import GUI.CreateNewMultiGame;
import GUI.NullPanel;
import GameData.Server;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPanel main;
    private JLabel back;

    public MultiGamePanel (ArrayList<Server> servers)
    {
        main = new JPanel (new GridLayout (1,3));
        JLabel header = new JLabel ("MultiPlayer Game");
        JPanel headerPanel = new JPanel ();


        headerPanel.setLayout (new BorderLayout ());
        headerPanel.setBackground (Color.GRAY);

        back = new JLabel (new ImageIcon ("./Images/back1.png"));
        back.addMouseListener (new MouseHandler ());



        headerPanel.add (back,BorderLayout.WEST);

        headerPanel.add (new JLabel (" "),BorderLayout.EAST);
        headerPanel.add (header);
        header.setPreferredSize (new Dimension (300,60));
        header.setForeground (Color.WHITE);
        header.setOpaque (true);
        header.setBackground (Color.GRAY);
        header.setHorizontalAlignment (SwingConstants.CENTER);
        header.setHorizontalTextPosition (SwingConstants.CENTER);
        header.setFont (new Font ("arial",Font.BOLD,18));





        setLayout (new BorderLayout ());
        setBorder (new LineBorder (Color.GRAY,4,true));
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

        main.add(firstPanel);
        main.add(scrollPane2);
        main.add(scrollPane3);
        add(main,BorderLayout.CENTER);
        add(headerPanel,BorderLayout.NORTH);
    }

    public void setSecondPanel (JPanel secondPanel) {
        main.setVisible (false);
        main.remove (this.scrollPane2);
        this.secondPanel = secondPanel;
        scrollPane2 = new JScrollPane (secondPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        main.add (scrollPane2,1);
        main.setVisible (true);
    }

    public void setThirdPanel (JPanel thirdPanel) {
        main.setVisible (false);
        main.remove (this.scrollPane3);
        this.thirdPanel = thirdPanel;
        scrollPane3 = new JScrollPane (thirdPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        main.add (scrollPane3,2);
        main.setVisible (true);
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseEntered (MouseEvent e) {
            if (e.getSource () == back)
                back.setIcon (new ImageIcon ("./Images/back2.png"));
        }

        @Override
        public void mouseExited (MouseEvent e) {
            if (e.getSource () == back)
                back.setIcon (new ImageIcon ("./Images/back1.png"));
        }

        @Override
        public void mouseReleased (MouseEvent e) {
            if (e.getSource () == back)
            {
                System.out.println ("back");
            }
        }
    }
}
