package GUI.MultiGame;


import GameData.MultiGame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.InputMismatchException;

public class MultiGameDataPanel extends JPanel
{
    private JLabel name;
    private JLabel typeOfFinish;
    private JLabel memberShip;
    private JLabel remainPlayers;
    private JLabel onlinePlayers;


    public MultiGameDataPanel (MultiGame multiGame)
    {
        if (multiGame == null)
            throw new InputMismatchException ("multi game is Null");

        setLayout (new BorderLayout ());
        setBorder (new EmptyBorder (5,5,5,5));
        name = new JLabel (multiGame.getName ());
        name.setFont (new Font ("arial",Font.PLAIN,15));
        name.setForeground (Color.GREEN.darker ());

        typeOfFinish = new JLabel (multiGame.getGameFinishType ().toString ());
        typeOfFinish.setFont (new Font ("arial",Font.PLAIN,15));
        typeOfFinish.setForeground (Color.GREEN.darker ());

        memberShip = new JLabel (multiGame.getGameMemberShipType ().toString ());
        memberShip.setFont (new Font ("arial",Font.PLAIN,15));
        memberShip.setForeground (Color.GREEN.darker ());


        remainPlayers = new JLabel (multiGame.getNumberOfPlayers () - multiGame.getOnlineUsers ()
                + "");
        remainPlayers.setFont (new Font ("arial",Font.PLAIN,15));
        remainPlayers.setForeground (Color.GREEN.darker ());

        onlinePlayers = new JLabel (multiGame.getOnlineUsers () + "");
        onlinePlayers.setFont (new Font ("arial",Font.PLAIN,15));
        onlinePlayers.setForeground (Color.GREEN.darker ());

        createComponents ();
    }


    private void createComponents ()
    {
        JLabel gameName = new JLabel ("Game's  Name:");
        gameName.setFont (new Font ("arial",Font.PLAIN,15));
        JLabel endType = new JLabel ("End Type of game:");
        endType.setFont (new Font ("arial",Font.PLAIN,15));
        JLabel playersType = new JLabel ("Type  Of  Playing:");
        playersType.setFont (new Font ("arial",Font.PLAIN,15));
        JLabel onlinePlayersH = new JLabel ("Online Players:");
        onlinePlayersH.setFont (new Font ("arial",Font.PLAIN,15));
        JLabel remainPlayersH = new JLabel ("Remain Players:");
        remainPlayersH.setFont (new Font ("arial",Font.PLAIN,15));


        JPanel middlePart = new JPanel (new GridLayout (5,2));

        middlePart.add (gameName);
        middlePart.add (name);
        middlePart.add (endType);
        middlePart.add (typeOfFinish);
        middlePart.add (playersType);
        middlePart.add (memberShip);
        middlePart.add (onlinePlayersH);
        middlePart.add (onlinePlayers);
        middlePart.add (remainPlayersH);
        middlePart.add (remainPlayers);

        add(middlePart);

    }


}
