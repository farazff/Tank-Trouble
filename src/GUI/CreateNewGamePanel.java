package GUI;

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

/**
 * this class represents a new game panel in network game
 *
 * @author Amir Naziri
 */
public class CreateNewGamePanel extends JPanel
{
    private JTextField gameNameTextField; // name of game
    private JSpinner numOfPlayers; // number of players
    private JSlider tanksStamina;
    private Selecting typeOfPlaying;
    private Selecting endTypeOfPlaying;
    private JSlider canonPower;
    private JSlider wallsStamina;
    private JButton create;
    private JLabel cancel;


    /**
     * create a new game panel
     */
    public CreateNewGamePanel ()
    {
        super();
        setBorder (new EmptyBorder (10,10,10,10));
        setLayout (new FlowLayout (FlowLayout.CENTER));
        createBasePanel ();
        addComponentListener (new ComponentAdapter () {
            @Override
            public void componentResized (ComponentEvent e) {
                repaint ();
            }
        });
    }

    /**
     * create base panel
     */
    private void createBasePanel ()
    {
        ChangeHandler changeHandler = new ChangeHandler ();
        JPanel basePanel = new JPanel ();

        basePanel.setBackground (Color.WHITE);
        basePanel.setBorder (new LineBorder (Color.GRAY,6,true));

        GridBagLayout layout = new GridBagLayout ();
        GridBagConstraints constraints = new GridBagConstraints ();
        basePanel.setLayout (layout);

        JLabel header = new JLabel ("New Game");
        header.setForeground (Color.WHITE);
        header.setOpaque (true);
        header.setBackground (Color.GRAY);
        header.setHorizontalAlignment (SwingConstants.CENTER);
        header.setHorizontalTextPosition (SwingConstants.CENTER);
        header.setFont (new Font ("arial",Font.BOLD,18));

        JLabel gameName = new JLabel ("Game's  Name*: ");
        gameName.setFont (new Font ("arial",Font.PLAIN,15));
        gameNameTextField = new JTextField ();
        gameNameTextField.setFont (new Font ("arial",Font.PLAIN,14));
        gameNameTextField.setBorder (BorderFactory.createCompoundBorder (new
                        LineBorder (Color.LIGHT_GRAY,3,true),
                new EmptyBorder (5,5,3,5)));


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
        endTypeOfPlaying = new Selecting(dataMode,0);


        ArrayList<String> dataMode1 = new ArrayList<>();
        dataMode1.add("Single Player");
        dataMode1.add("Team Player");
        typeOfPlaying = new Selecting(dataMode1,0);

        JLabel numberOfPlayers = new JLabel ("Number Of Players:");
        numberOfPlayers.setFont (new Font ("arial",Font.PLAIN,15));
        SpinnerModel spinnerModel;

        spinnerModel = new SpinnerNumberModel (1,1,100,1);
        numOfPlayers = new JSpinner ();
        numOfPlayers.setModel (spinnerModel);
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setEditable (false);
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setFocusable (false);



        JLabel tanksStaminaLabel = new JLabel ("Tank Stamina");
        tanksStaminaLabel.setFont (new Font ("arial",Font.PLAIN,15));
        tanksStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        editSlider (tanksStamina);
        tanksStamina.addChangeListener (changeHandler);

        JLabel canonPowerLabel = new JLabel ("Canon Power");
        canonPowerLabel.setFont (new Font ("arial",Font.PLAIN,15));
        canonPower = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        editSlider (canonPower);
        canonPower.addChangeListener (changeHandler);

        JLabel wallsStaminaLabel = new JLabel ("Destroyable Walls Stamina");
        wallsStaminaLabel.setFont (new Font ("arial",Font.PLAIN,15));
        wallsStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        editSlider (wallsStamina);
        wallsStamina.addChangeListener (changeHandler);



        create = new JButton ("Create!");
        create.setFont (new Font ("arial",Font.PLAIN,18));
        create.addActionListener (new ActionHandler ());
        constraints.fill = GridBagConstraints.BOTH;


        constraints.insets = new Insets (0,0,17,0);


        constraints.ipadx = 70;
        constraints.ipady = 20;
        GridBagSetter.addComponent (header,0,1,19,3,layout,constraints,basePanel);

        constraints.ipady = 7;
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (gameName,3,0,5,1,layout,constraints,basePanel);

        GridBagSetter
                .addComponent (gameNameTextField,3,5,15,1,
                        layout,constraints,basePanel);
        GridBagSetter
                .addComponent (playersType,4,0,20,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,95,12,35);
        constraints.insets = new Insets (0,15,15,15);
        constraints.ipadx = 0;
        constraints.ipady = 0;

        GridBagSetter
                .addComponent (typeOfPlaying,5,0,20,1,layout,constraints,basePanel);
        constraints.ipady = 7;
        constraints.ipadx = 70;
        constraints.insets = new Insets (0,5,12,5);

        GridBagSetter
                .addComponent (endType,6,0,20,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,15,15,15);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        GridBagSetter
                .addComponent (endTypeOfPlaying,7,0,20,1,
                        layout,constraints,basePanel);
        constraints.ipady = 7;
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (numberOfPlayers,8,0,8,
                        1,layout,constraints,basePanel);
        constraints.ipadx = 0;
        constraints.insets = new Insets (0,135,12,5);
        GridBagSetter
                .addComponent (numOfPlayers,8,8,10,1,layout,constraints,basePanel);
        constraints.insets = new Insets (12,5,12,5);
        constraints.ipady = 7;
        constraints.ipadx = 70;
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (new JSeparator (SwingConstants.HORIZONTAL),9,0,20,1,
                        layout,constraints,basePanel);
        GridBagSetter
                .addComponent (tanksStaminaLabel,10,0,20,1,
                        layout,constraints,basePanel);
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (tanksStamina,11,5,15,1,layout,
                        constraints,basePanel);

        GridBagSetter
                .addComponent (canonPowerLabel,12,0,20,1,layout,
                        constraints,basePanel);
        GridBagSetter
                .addComponent (canonPower,13,5,15,1,layout,constraints,
                        basePanel);
        GridBagSetter
                .addComponent (wallsStaminaLabel,14,0,20,1,layout,constraints,
                        basePanel);
        GridBagSetter
                .addComponent (wallsStamina,15,5,15,1,layout,
                        constraints,basePanel);
        GridBagSetter
                .addComponent (create,16,0,20,5,layout,constraints,basePanel);


        add(new JScrollPane (basePanel));
    }


    /**
     * editing Slider
     * @param slider slider
     */
    private void editSlider (JSlider slider)
    {
        slider.setMinorTickSpacing (10);
        slider.setSnapToTicks (true);
        slider.setToolTipText (slider.getValue () + " %");
        slider.setPaintTicks (true);
    }

    /**
     * this class handles changes in Sliders and some buttons
     */
    private class ChangeHandler implements ChangeListener , ItemListener
    {

        @Override
        public void stateChanged (ChangeEvent e) {
            if (e.getSource () == tanksStamina)
            {
                tanksStamina.setToolTipText (tanksStamina.getValue () +" %");
            } else if (e.getSource () == wallsStamina)
            {
                wallsStamina.setToolTipText (wallsStamina.getValue () + " %");
            } else if (e.getSource () == canonPower)
            {
                canonPower.setToolTipText (canonPower.getValue () + " %");
            }
        }

        @Override
        public void itemStateChanged (ItemEvent e) {
            if (e.getStateChange () == ItemEvent.SELECTED)
            {
//                if (e.getSource () == teamPlayer)
//                {
//                    if (teamPlayer.isSelected ())
//                    {
//                        if ((int)(numOfPlayers.getValue ()) % 2 == 1)
//                            numOfPlayers.setValue (numOfPlayers.getNextValue ());
//
//                        numOfPlayers.setModel (new SpinnerNumberModel ((int)(numOfPlayers.getValue ())
//                                ,1,100,2));
//                        ((JSpinner.DefaultEditor)numOfPlayers.
//                                getEditor ()).getTextField ().setEditable (false);
//                        ((JSpinner.DefaultEditor)numOfPlayers.
//                                getEditor ()).getTextField ().setFocusable (false);
//                    }
//                } else if (e.getSource () == singlePlayer)
//                {
//                    if (singlePlayer.isSelected ())
//                    {
//                        numOfPlayers.setModel (new SpinnerNumberModel ((int)(numOfPlayers.getValue ())
//                                ,1,100,1));
//                        ((JSpinner.DefaultEditor)numOfPlayers.
//                                getEditor ()).getTextField ().setEditable (false);
//                        ((JSpinner.DefaultEditor)numOfPlayers.
//                                getEditor ()).getTextField ().setFocusable (false);
//                    }
//
//                }
            }

        }
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
                if (!checkData ())
                    return;
                System.out.println ("created");

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
