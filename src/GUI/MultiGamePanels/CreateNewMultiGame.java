package GUI.MultiGamePanels;

import GUI.GridBagSetter;
import GUI.Music;
import GUI.PictureJLabel;
import GUI.Selecting;
import GameData.MultiGame;
import GameData.GameFinishType;
import GameData.GameMemberShipType;
import GameData.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class represents a new game panel in network game
 *
 * @author Amir Naziri
 */
public class CreateNewMultiGame extends JPanel
{
    private JTextField gameNameTextField; // name of game
    private JSpinner numOfPlayers; // number of players
    private JSlider tanksStamina;
    private Selecting typeOfPlaying;
    private Selecting endTypeOfPlaying;
    private JSlider canonPower;
    private JSlider wallsStamina;
    private JButton create;
    private JLabel back;
    private ServerButtonPanel owner;
    private JFrame frame;
    private JPanel pre;
    private boolean finishedProcessed;
    private boolean typeOfPlayingChanged;
    private User user;

    /**
     * create a new game panel
     */
    public CreateNewMultiGame (ServerButtonPanel owner, JFrame frame, JPanel pre, User user)
    {
        super();
        finishedProcessed = false;
        typeOfPlayingChanged = false;
        this.user = user;
        this.owner = owner;
        this.pre = pre;
        this.frame = frame;
        setBorder (new EmptyBorder (10,10,10,10));
        setLayout (new FlowLayout ());
        createBasePanel ();
        addComponentListener (new ComponentAdapter () {
            @Override
            public void componentResized (ComponentEvent e) {
                repaint ();
            }
        });
        new Thread (new Runnable () {
            @Override
            public void run () {

                while (!finishedProcessed)
                {

                    if (typeOfPlaying.getCurrentValue ().equals ("Single Player"))
                    {
                        numOfPlayers.setModel (new SpinnerNumberModel ((int)(numOfPlayers.getValue ())
                                ,1,100,1));
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setEditable (false);
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setFocusable (false);
                    }
                    else
                    {
                        if ((int)(numOfPlayers.getValue ()) % 2 == 1)
                            numOfPlayers.setValue (numOfPlayers.getNextValue ());

                        numOfPlayers.setModel (new SpinnerNumberModel ((int)(numOfPlayers.getValue ())
                                ,1,100,2));
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setEditable (false);
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setFocusable (false);

                    }

                    try{
                        Thread.sleep (250);
                    } catch (InterruptedException e)
                    {
                        System.out.println ("Interrupted");
                    }
                }

            }

        }).start ();

    }


    /**
     * create base panel
     */
    private void createBasePanel ()
    {

        JPanel basePanel = new JPanel (new BorderLayout ());
        JPanel leftPanel = new JPanel ();
        JPanel rightPanel = new JPanel ();

        basePanel.setBackground (Color.WHITE);
        basePanel.setBorder (new LineBorder (Color.GRAY,6,true));

        rightPanel.setBackground (Color.WHITE);
        leftPanel.setBackground (Color.WHITE);


        GridBagLayout layoutL = new GridBagLayout ();
        GridBagConstraints constraintsL = new GridBagConstraints ();
        leftPanel.setLayout (layoutL);

        GridBagLayout layoutR = new GridBagLayout ();
        GridBagConstraints constraintsR = new GridBagConstraints ();
        rightPanel.setLayout (layoutR);

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




        JLabel gameName = new JLabel ("MultiGame's  Name*: ");
        gameName.setFont (new Font ("arial",Font.PLAIN,15));
        gameNameTextField = new JTextField ();
        gameNameTextField.setFont (new Font ("arial",Font.PLAIN,14));
        gameNameTextField.setBorder (BorderFactory.createCompoundBorder (new
                        LineBorder (Color.LIGHT_GRAY,3,true),
                new EmptyBorder (5,5,3,5)));
        gameNameTextField.setPreferredSize (new Dimension (10,35));


        JLabel playersType = new JLabel ("Type  Of  Playing");
        playersType.setHorizontalAlignment (SwingConstants.CENTER);
        playersType.setHorizontalTextPosition (SwingConstants.CENTER);
        playersType.setFont (new Font ("arial",Font.PLAIN,15));




        JLabel endType = new JLabel ("End Type of game");
        endType.setHorizontalAlignment (SwingConstants.CENTER);
        endType.setHorizontalTextPosition (SwingConstants.CENTER);
        endType.setFont (new Font ("arial",Font.PLAIN,15));

        ArrayList<String> dataMode = new ArrayList<>();
        dataMode.add("Death Match");
        dataMode.add("League Match");
        endTypeOfPlaying = new Selecting(dataMode,0,Color.WHITE,
                Color.GRAY,new Font("Arial",Font.BOLD,16));


        ArrayList<String> dataMode1 = new ArrayList<>();
        dataMode1.add("Single Player");
        dataMode1.add("Team Player");
        typeOfPlaying = new Selecting(dataMode1,0,Color.WHITE,
                Color.GRAY,new Font("Arial",Font.BOLD,16));

        JLabel numberOfPlayers = new JLabel ("Number Of Players:");
        numberOfPlayers.setFont (new Font ("arial",Font.PLAIN,15));
        SpinnerModel spinnerModel;

        spinnerModel = new SpinnerNumberModel (1,1,100,1);
        numOfPlayers = new JSpinner ();
        numOfPlayers.setModel (spinnerModel);
        numOfPlayers.addChangeListener (new ChangeListener () {
            @Override
            public void stateChanged (ChangeEvent e) {
                Music music = new Music ();
                music.execute ();
            }
        });
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setEditable (false);
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setFocusable (false);



        JLabel tanksStaminaLabel = new JLabel ("Tank Stamina");
        tanksStaminaLabel.setFont (new Font ("arial",Font.PLAIN,15));
        tanksStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,
                user.getDefaultTankStamina ());
        editSlider (tanksStamina);


        JLabel canonPowerLabel = new JLabel ("Bullet Power");
        canonPowerLabel.setFont (new Font ("arial",Font.PLAIN,15));
        canonPower = new JSlider (SwingConstants.HORIZONTAL,10,100,
                user.getDefaultCanonPower ());
        editSlider (canonPower);


        JLabel wallsStaminaLabel = new JLabel ("Destroyable Walls Stamina");
        wallsStaminaLabel.setFont (new Font ("arial",Font.PLAIN,15));
        wallsStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,
                user.getDefaultWallStamina ());
        editSlider (wallsStamina);




        create = new JButton ("Create!");
        create.setFont (new Font ("arial",Font.PLAIN,18));
        create.addActionListener (new ActionHandler ());
        create.setPreferredSize (new Dimension (100,50));
        constraintsL.fill = GridBagConstraints.BOTH;

        constraintsL.ipadx = 200;
        constraintsL.ipady = 7;
        constraintsL.insets = new Insets (10,5,12,5);
        GridBagSetter
                .addComponent
                        (new JLabel (" "),0,0,20,1,layoutL,constraintsL,leftPanel);
        GridBagSetter
                .addComponent (gameName,1,0,5,1,layoutL,constraintsL,leftPanel);


        GridBagSetter
                .addComponent (gameNameTextField,1,5,13,1,
                        layoutL,constraintsL,leftPanel);

        GridBagSetter
                .addComponent (playersType,2,0,20,1,layoutL,constraintsL,leftPanel);

        constraintsL.insets = new Insets (0,15,15,15);
        constraintsL.ipadx = 0;
        constraintsL.ipady = 0;

        GridBagSetter
                .addComponent (typeOfPlaying,3,0,20,1,layoutL,constraintsL,leftPanel);
        constraintsL.ipady = 7;
        constraintsL.ipadx = 200;
        constraintsL.insets = new Insets (0,5,12,5);

        GridBagSetter
                .addComponent (endType,4,0,20,1,layoutL,constraintsL,leftPanel);
        constraintsL.insets = new Insets (0,15,15,15);
        constraintsL.ipadx = 0;
        constraintsL.ipady = 0;
        GridBagSetter
                .addComponent (endTypeOfPlaying,5,0,20,1,
                        layoutL,constraintsL,leftPanel);
        constraintsL.ipady = 7;
        constraintsL.insets = new Insets (20,5,12,5);
        GridBagSetter
                .addComponent (numberOfPlayers,6,0,8,
                        1,layoutL,constraintsL,leftPanel);
        constraintsL.ipadx = 0;
        constraintsL.insets = new Insets (20,135,12,5);
        GridBagSetter
                .addComponent (numOfPlayers,6,8,10,1,layoutL,constraintsL,leftPanel);



        constraintsR.fill = GridBagConstraints.BOTH;
        constraintsR.ipady = 7;
        constraintsR.ipadx = 150;
        constraintsR.insets = new Insets (10,5,18,5);

        GridBagSetter
                .addComponent (tanksStaminaLabel,0,0,20,1,
                        layoutR,constraintsR,rightPanel);
        constraintsR.insets = new Insets (0,5,18,5);

        GridBagSetter
                .addComponent (tanksStamina,1,5,20,1,layoutR,
                        constraintsR,rightPanel);

        constraintsR.insets = new Insets (30,5,18,5);
        GridBagSetter
                .addComponent (canonPowerLabel,2,0,20,1,layoutR,
                        constraintsR,rightPanel);
        constraintsR.insets = new Insets (0,5,18,5);
        GridBagSetter
                .addComponent (canonPower,3,5,20,1,layoutR,constraintsR,
                        rightPanel);

        constraintsR.insets = new Insets (30,5,18,5);
        GridBagSetter
                .addComponent (wallsStaminaLabel,4,0,20,1,layoutR,constraintsR,
                        rightPanel);
        constraintsR.insets = new Insets (0,5,18,5);
        GridBagSetter
                .addComponent (wallsStamina,5,5,20,1,layoutR,
                        constraintsR,rightPanel);



        basePanel.add(topTop,BorderLayout.NORTH);
        basePanel.add (leftPanel,BorderLayout.WEST);
        basePanel.add (new JSeparator (SwingConstants.VERTICAL),BorderLayout.CENTER);
        basePanel.add (rightPanel,BorderLayout.EAST);
        basePanel.add (create,BorderLayout.SOUTH);

        add (basePanel);

    }


    /**
     * editing Slider
     * @param slider slider
     */
    private void editSlider (JSlider slider)
    {
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
    }





    /**
     * check if the given data for creating new game is ok or nor
     * @return result
     */
    public boolean checkData ()
    {
        if (gameNameTextField.getText ().length () == 0)
        {
            gameNameTextField.setBorder (BorderFactory.createCompoundBorder (new
                            LineBorder (Color.RED,3,true),
                    new EmptyBorder (5,5,3,5)));;
            return false;
        }
        else
        {
            gameNameTextField.setBorder (BorderFactory.createCompoundBorder (new
                            LineBorder (Color.LIGHT_GRAY,3,true),
                    new EmptyBorder (5,5,3,5)));;
            return true;
        }
    }

    /**
     * handles Action events
     */
    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource () == create)
            {
                Music music = new Music ();
                music.execute ();
                if (!checkData ())
                    return;
                GameFinishType finishType = null;
                GameMemberShipType memberShipType = null;
                if (typeOfPlaying.getCurrentValue ().equals ("Single Player"))
                    memberShipType = GameMemberShipType.SINGLE;
                else
                    memberShipType = GameMemberShipType.TEAM;

                if (endTypeOfPlaying.getCurrentValue ().equals ("Death Match"))
                    finishType = GameFinishType.DEATH_MATCH;
                else
                    finishType = GameFinishType.LEAGUE;

                finishedProcessed = true;

                owner.addNewGame (new MultiGame (gameNameTextField.getText (),
                        finishType, memberShipType,
                        (int)numOfPlayers.getValue (),tanksStamina.getValue (),
                        wallsStamina.getValue (),canonPower.getValue ()));


                frame.setContentPane (pre);

            }
        }
    }

    /**
     * this class handles Mouse
     */
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
                frame.setContentPane (pre);
            }
        }
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent (g);
        try {
            g.drawImage (ImageIO.read (new File ("./Images/login.jpg")).
                            getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST)
                    ,0,0,this);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
