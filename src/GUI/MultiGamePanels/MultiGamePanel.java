package GUI.MultiGamePanels;


import GUI.Music;
import GUI.NullPanel;
import GUI.PictureJLabel;
import GameData.ServerInformationStorage;
import GameData.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MultiGamePanel extends JPanel
{

    private JPanel pre;
    private JPanel nex;
    private JFrame frame;
    private User user;

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

    public MultiGamePanel (JFrame frame, User user)
    {

        main = new JPanel (new GridLayout (1,3));
        setLayout (new BorderLayout ());

        this.frame = frame;
        // header part
        this.user = user;

        JLabel multiPlayerGame = new JLabel("MultiPlayer MultiGame");
        multiPlayerGame.setBackground(null);
        multiPlayerGame.setHorizontalAlignment(JLabel.CENTER);
        multiPlayerGame.setOpaque(true);
        multiPlayerGame.setFont(new Font("Arial",Font.BOLD,30));
        PictureJLabel toRight = new PictureJLabel("Images/toright.jpg");
        toRight.setPreferredSize(new Dimension(60, 60));
        PictureJLabel toLeft = new PictureJLabel("Images/toleft.jpg");
        toLeft.setPreferredSize(new Dimension(60, 60));
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(Color.WHITE);
        top.setOpaque(true);
        top.add(toRight);
        top.add(multiPlayerGame);
        top.add(toLeft);
        JPanel topTop = new JPanel(new BorderLayout());
        topTop.add(top,BorderLayout.CENTER);
        JPanel back2 = new JPanel(new GridLayout(3,1));

        back = new JLabel (new ImageIcon ("./Images/back1.png"));

        back.addMouseListener(new MouseHandler ());
        back2.add(back);
        back2.setBackground(Color.WHITE);
        back2.setOpaque(true);
        topTop.add(back2,BorderLayout.WEST);
        add(topTop,BorderLayout.NORTH);

        setBackground (Color.WHITE);

        setBorder (new LineBorder (Color.GRAY,8,true));

        createNewGame = new JButton ("Create New MultiGame");
        createNewGame.setFont (new Font ("Arial",Font.PLAIN,17));
        createNewGame.setMinimumSize (new Dimension (5,10));
        createNewGame.addActionListener (new ActionHandler ());


        firstPanel = new JPanel (new BorderLayout ());
        firstPanel.setBackground (Color.WHITE);
        JScrollPane scrollPane1 = new JScrollPane (new ServerListPanel (user.getServerInformationStorage (),this),
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
        setThirdPanel (new NullPanel (),null);
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
                Music music = new Music ();
                music.execute ();
                frame.setContentPane (new CreateNewMultiGame (serverButtonPanel,frame,getThis (),user));
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
                Music music = new Music ();
                music.execute ();
                if (pre != null)
                {
                    frame.setContentPane (pre);
                    frame.setVisible (false);
                    frame.setVisible (true);
                }

            }
        }
    }
}
