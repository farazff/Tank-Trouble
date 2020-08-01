package GUI.MultiGamePanels;


import GUI.Music;
import GameData.MultiGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class MultiGameDataPanel extends JPanel
{
    private JLabel name;
    private JLabel typeOfFinish;
    private JLabel memberShip;
    private JLabel remainPlayers;
    private JLabel onlinePlayers;
    private JButton play;


    public MultiGameDataPanel (MultiGame multiGame)
    {
        if (multiGame == null)
            throw new InputMismatchException ("multi multiGame is Null");

        setLayout (new BorderLayout ());
        setBorder (new EmptyBorder (0,0,0,0));
        name = new JLabel (multiGame.getName ());
        setBackground (Color.WHITE);
        name.setFont (new Font ("arial",Font.PLAIN,15));
        name.setForeground (new Color (91, 131, 56));

        typeOfFinish = new JLabel (multiGame.getGameFinishType ().toString ());
        typeOfFinish.setFont (new Font ("arial",Font.PLAIN,15));
        typeOfFinish.setForeground (new Color (91, 131, 56));

        memberShip = new JLabel (multiGame.getGameMemberShipType ().toString ());
        memberShip.setFont (new Font ("arial",Font.PLAIN,15));
        memberShip.setForeground (new Color (91, 131, 56));


        remainPlayers = new JLabel (multiGame.getNumberOfPlayers () - multiGame.getOnlineUsers ().size ()
                + "");
        remainPlayers.setFont (new Font ("arial",Font.PLAIN,15));
        remainPlayers.setForeground (new Color (91, 131, 56));

        onlinePlayers = new JLabel (multiGame.getOnlineUsers () + "");
        onlinePlayers.setFont (new Font ("arial",Font.PLAIN,15));
        onlinePlayers.setForeground (new Color (91, 131, 56));

        createComponents ();
    }


    private void createComponents ()
    {
        JLabel gameName = new JLabel ("MultiGame's  Name:");
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
        middlePart.setBackground (Color.WHITE);
        middlePart.setBorder (new EmptyBorder (5,5,5,5));

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


        add(middlePart,BorderLayout.CENTER);
        play = new JButton ("P l a y !");
        play.setFont (new Font ("Arial",Font.PLAIN,17));
        play.setMinimumSize (new Dimension (5,10));
        play.addActionListener (new ActionHandler ());
        add(play,BorderLayout.SOUTH);

    }

    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e) {
            Music music = new Music ();
            music.execute ();
        }
    }


}
