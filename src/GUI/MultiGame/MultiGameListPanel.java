package GUI.MultiGame;

import GameData.MultiGame;
import GameData.Server;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MultiGameListPanel extends JPanel
{
    private MultiGamePanel mainPanel;
    private ArrayList<MultiGameButtonPanel> multiGameButtonPanels;

    public MultiGameListPanel (ArrayList<MultiGame> multiGames, MultiGamePanel mainPanel)
    {
        super();
        if (multiGames == null)
            throw new InputMismatchException ("servers in Null");
        this.mainPanel = mainPanel;
        multiGameButtonPanels = new ArrayList<> ();
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.GRAY);
        setBorder (new LineBorder (Color.GRAY,10,true));
        MouseHandler mouseHandler = new MouseHandler ();
        for (MultiGame multiGame : multiGames)
        {
            MultiGameButtonPanel multiGameButtonPanel = new MultiGameButtonPanel (multiGame,mainPanel);
            add(multiGameButtonPanel);
            multiGameButtonPanel.addMouseListener (mouseHandler);
            multiGameButtonPanels.add (multiGameButtonPanel);
        }
    }

    private class MouseHandler extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e) {
            e.getComponent ().setBackground (Color.LIGHT_GRAY);
        }

        @Override
        public void mouseExited (MouseEvent e) {
            e.getComponent ().setBackground (Color.GRAY);
        }

        @Override
        public void mouseClicked (MouseEvent e) {
            MultiGameButtonPanel multiGameButtonPanel = (MultiGameButtonPanel)e.getComponent ();
            mainPanel.setThirdPanel (multiGameButtonPanel.getMultiGameDataPanel ());
        }
    }
}
