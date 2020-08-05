package GUI.MultiGamePanels;


import GUI.MainPage.Main;
import GUI.Music;
import Game.MultiGameStarting;
import GameData.MultiGame;
import GameData.User;
import Login_SignUp_Logout.LogConnector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * this panel is game details panel
 */
public class MultiGameDataPanel extends JPanel
{
    private JLabel name;
    private JLabel typeOfFinish;
    private JLabel memberShip;
    private JLabel remainPlayers;
    private JLabel onlinePlayers;
    private JButton play;
    private MultiGame multiGame;
    private JFrame frame;
    private User user;
    private JPanel main;

    /**
     * creates a new Multi game data panel
     * @param multiGame multiGame
     * @param frame frame
     * @param user user
     */
    public MultiGameDataPanel (MultiGame multiGame, JFrame frame, User user, JPanel main)
    {
        if (multiGame == null || frame == null || user == null)
            throw new InputMismatchException ("input is Null");

        this.frame = frame;
        this.main = main;
        this.user = user;
        setLayout (new BorderLayout ());
        setBorder (new EmptyBorder (0,0,0,0));
        name = new JLabel (multiGame.getName ());
        setBackground (Color.WHITE);
        name.setFont (new Font ("arial",Font.PLAIN,15));
        name.setForeground (new Color (91, 131, 56));
        this.multiGame = multiGame;
        typeOfFinish = new JLabel (multiGame.getGameFinishType ().toString ());
        typeOfFinish.setFont (new Font ("arial",Font.PLAIN,15));
        typeOfFinish.setForeground (new Color (91, 131, 56));

        memberShip = new JLabel (multiGame.getGameMemberShipType ().toString ());
        memberShip.setFont (new Font ("arial",Font.PLAIN,15));
        memberShip.setForeground (new Color (91, 131, 56));


        remainPlayers = new JLabel (multiGame.getNumberOfPlayers () - multiGame.getOnlineUsersNumber ()
                + "");
        remainPlayers.setFont (new Font ("arial",Font.PLAIN,15));
        remainPlayers.setForeground (new Color (91, 131, 56));

        onlinePlayers = new JLabel (multiGame.getOnlineUsersNumber () + "");
        onlinePlayers.setFont (new Font ("arial",Font.PLAIN,15));
        onlinePlayers.setForeground (new Color (91, 131, 56));

        createComponents ();
    }

    /**
     *
     * @return get This panel
     */
    private JPanel getThis ()
    {
        return this;
    }
    /**
     * creates components
     */
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

    /**
     * connects to server
     */
    private void disConnect ()
    {
        LogConnector logConnector = new LogConnector ("127.0.0.1","Logout",user);
        new Thread (logConnector).start ();
    }

    /**
     * this class handles components
     */
    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e) {
            Music music = new Music ();
            music.execute ();

            if (e.getSource () == play)
            {
                if (multiGame.isExpired ())
                {
                    JOptionPane.showMessageDialog (getThis (),"Game has been Expired",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    multiGame.addUser ();
                    if (multiGame.getNumberOfPlayers () == multiGame.getOnlineUsersNumber ())
                        multiGame.setExpired (true);
                    disConnect ();
                    frame.setVisible (false);
                    System.out.println (multiGame.getPort ());
                    MultiGameStarting multiGameStarting = new MultiGameStarting (frame,user,multiGame);
                    user.setNumOfMultiGames (user.getNumOfMultiGames () + 1);
                    frame.setContentPane (main);
                }

            }
        }
    }


}
