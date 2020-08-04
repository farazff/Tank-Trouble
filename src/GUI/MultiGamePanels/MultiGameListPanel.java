package GUI.MultiGamePanels;

import GUI.Music;
import GameData.MultiGame;
import GameData.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MultiGameListPanel extends JPanel
{
    private MultiGamePanel mainPanel;
    private ArrayList<MultiGameButtonPanel> multiGameButtonPanels;
    private User user;
    private JFrame frame;

    public MultiGameListPanel (ArrayList<MultiGame> multiGames,MultiGamePanel mainPanel, JFrame frame,
                               User user)
    {
        super();
        if (multiGames == null || frame == null || user == null)
            throw new InputMismatchException ("input is Null");
        this.mainPanel = mainPanel;
        this.frame = frame;
        this.user = user;
        setBackground (Color.WHITE);
        multiGameButtonPanels = new ArrayList<> ();
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));

        setBorder (new EmptyBorder (5,5,5,5));
        MouseHandler mouseHandler = new MouseHandler ();
        for (MultiGame multiGame : multiGames)
        {
            MultiGameButtonPanel multiGameButtonPanel = new MultiGameButtonPanel (multiGame,mainPanel,
                    frame,user);
            add(multiGameButtonPanel);
            multiGameButtonPanel.addMouseListener (mouseHandler);
            multiGameButtonPanels.add (multiGameButtonPanel);
        }
    }

    public void addNewMultiGame (MultiGame multiGame)
    {
        MouseHandler mouseHandler = new MouseHandler ();
        MultiGameButtonPanel multiGameButtonPanel = new MultiGameButtonPanel (multiGame,mainPanel,
                frame,user);
        multiGameButtonPanels.add (multiGameButtonPanel);
        multiGameButtonPanel.addMouseListener (mouseHandler);
        this.setVisible (false);
        add(multiGameButtonPanel);
        this.setVisible (true);
    }

    public ArrayList<MultiGameButtonPanel> getMultiGameButtonPanels () {
        return multiGameButtonPanels;
    }

    private class MouseHandler extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e) {
            e.getComponent ()
                    .setBackground (new Color (91, 131, 56));
        }

        @Override
        public void mouseExited (MouseEvent e) {
            e.getComponent ().setBackground (Color.WHITE);
        }

        @Override
        public void mouseClicked (MouseEvent e) {

            Music music = new Music ();
            music.execute ();
            for (MultiGameButtonPanel multiGameButtonPanel : multiGameButtonPanels)
            {
                if (e.getComponent () == multiGameButtonPanel)
                {
                    mainPanel.setThirdPanel (multiGameButtonPanel.getMultiGameDataPanel (),multiGameButtonPanel);
                    multiGameButtonPanel.changeFontAndColor (
                            new Font ("Arial",Font.ITALIC,14),Color.BLACK);
                    multiGameButtonPanel.setSelected (true);
                }
                else
                {
                    multiGameButtonPanel.changeFontAndColor (new Font ("Arial",Font.PLAIN,14),
                            Color.DARK_GRAY);
                    multiGameButtonPanel.setSelected (false);
                }
            }

        }
    }
}
