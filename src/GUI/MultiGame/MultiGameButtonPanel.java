package GUI.MultiGame;

import GUI.ButtonPanel;
import GameData.MultiGame;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

public class MultiGameButtonPanel extends ButtonPanel
{

    private final MultiGame multiGame;
    private JLabel gameName;

    public MultiGameButtonPanel (MultiGame multiGame)
    {
        super();
        if (multiGame == null)
            throw new InputMismatchException ("multi game is Null");
        this.multiGame = multiGame;
        this.gameName = new JLabel ("Game Name : " + multiGame.getName ());
    }

    public MultiGame getMultiGame () {
        return multiGame;
    }

    private void createBasePanel ()
    {
        add(gameName);
    }

    @Override
    public Dimension getMinimumSize () {
        return new Dimension (450,34);
    }


    @Override
    public Dimension getPreferredSize () {
        return new Dimension (450,34);
    }
}
