package GUI.MultiGame;


import GUI.NullPanel;
import GameData.Server;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MultiGamePanel extends JPanel
{

    private JPanel pre;
    private JPanel nex;
    private JFrame frame;

    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JPanel main;
    private ServerButtonPanel serverButtonPanel;
    private MultiGameButtonPanel multiGameButtonPanel;

    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JButton createNewGame;

    private JLabel back;

    public MultiGamePanel (JFrame frame, ArrayList<Server> servers)
    {
        main = new JPanel (new GridLayout (1,3));
        setLayout (new BorderLayout ());

        this.frame = frame;
        // header part
        JPanel headerPanel = new JPanel ();
        headerPanel.setLayout (new BorderLayout ());
        headerPanel.setBackground (Color.GRAY);
        JLabel header = new JLabel ("MultiPlayer Game");
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

        add(headerPanel,BorderLayout.NORTH);


        setBackground (Color.WHITE);

        setBorder (new LineBorder (Color.GRAY,4,true));
        main.setBorder (new LineBorder (Color.GRAY,4,true));
        createNewGame = new JButton ("Create New Game");
        createNewGame.setFont (new Font ("Arial",Font.PLAIN,17));
        createNewGame.setMinimumSize (new Dimension (5,10));
        createNewGame.addActionListener (new ActionHandler ());


        firstPanel = new JPanel (new BorderLayout ());
        firstPanel.setBackground (Color.WHITE);
        JScrollPane scrollPane1 = new JScrollPane (new ServerListPanel (servers,this),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.getHorizontalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane1.setAutoscrolls (true);
        scrollPane1.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane1.setBorder (new LineBorder (Color.LIGHT_GRAY,1));

        firstPanel.add (scrollPane1);
        scrollPane2 = new JScrollPane (new NullPanel (),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setAutoscrolls (true);
        scrollPane2.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane2.setBorder (new LineBorder (Color.LIGHT_GRAY,1));
        scrollPane2.getHorizontalScrollBar ().setPreferredSize (new Dimension (10,8));

        secondPanel = new JPanel (new BorderLayout ());
        secondPanel.add (scrollPane2,BorderLayout.CENTER);
        secondPanel.setBackground (Color.WHITE);
        scrollPane3 = new JScrollPane (new NullPanel (),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3.setAutoscrolls (true);
        scrollPane3.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane3.setBorder (new LineBorder (Color.LIGHT_GRAY,1));

        thirdPanel = new JPanel (new BorderLayout ());
        thirdPanel.add (scrollPane3);
        thirdPanel.setBackground (Color.WHITE);

        main.add(firstPanel);
        main.add(secondPanel);
        main.add(thirdPanel);
        add(main,BorderLayout.CENTER);


    }

    private JPanel getThis ()
    {
        return this;
    }



    public void setPre (JPanel pre) {
        this.pre = pre;
    }

    public void setNex (JPanel nex) {
        this.nex = nex;
    }

    public void setSecondPanel (JPanel newSecondPanel, ServerButtonPanel serverButtonPanel) {
        main.setVisible (false);
        this.serverButtonPanel = serverButtonPanel;
        secondPanel.remove (scrollPane2);
        scrollPane2 = new JScrollPane (newSecondPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setAutoscrolls (true);
        scrollPane2.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane2.setBorder (new LineBorder (Color.LIGHT_GRAY,1));
        scrollPane2.getHorizontalScrollBar ().setPreferredSize (new Dimension (10,8));
        secondPanel.add (scrollPane2,BorderLayout.CENTER);
        main.add(secondPanel,1);
        secondPanel.add (createNewGame,BorderLayout.SOUTH);
        main.setVisible (true);
    }

    public void setThirdPanel (JPanel newThirdPanel, MultiGameButtonPanel multiGameButtonPanel) {

        main.setVisible (false);
        this.multiGameButtonPanel = multiGameButtonPanel;
        thirdPanel.remove (scrollPane3);
        scrollPane3 = new JScrollPane (newThirdPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane3.getVerticalScrollBar ().setPreferredSize (new Dimension (10,8));
        scrollPane3.setAutoscrolls (true);
        scrollPane3.setBorder (new LineBorder (Color.LIGHT_GRAY,1));
        thirdPanel.add (scrollPane3);
        main.add(thirdPanel,2);
        main.setVisible (true);


    }

    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource () == createNewGame)
            {
                frame.setContentPane (new CreateNewMultiGame (serverButtonPanel,frame,getThis ()));
            }
        }
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
                if (pre != null)
                    frame.setContentPane (pre);
            }
        }
    }
}
