package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * this class represents a new game panel in network game
 *
 * @author Amir Naziri
 */
public class CreateNewGamePanel extends JPanel
{
    private JTextArea gameNameTextField; // name of game
    private JRadioButton singlePlayer;
    private JRadioButton teamPlayer;
    private JRadioButton deathMatch;
    private JRadioButton leagueMatch;
    private JSpinner numOfPlayers; // number of players
    private JSlider tanksStamina;
    private JSlider canonPower;
    private JSlider wallsStamina;
    private JButton create;

    /**
     * create a new game panel
     */
    public CreateNewGamePanel ()
    {
        super();
        setBorder (new EmptyBorder (10,10,10,10));
        setLayout (new FlowLayout (FlowLayout.CENTER));
        createBasePanel ();
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
        header.setFont (new Font ("arial",Font.BOLD,16));

        JLabel gameName = new JLabel ("Game's  Name ");
        gameName.setFont (new Font ("arial",Font.PLAIN,13));
        gameNameTextField = new JTextArea ();
        gameNameTextField.setFont (new Font ("arial",Font.PLAIN,14));
        gameNameTextField.setBorder (BorderFactory.createCompoundBorder (new
                        LineBorder (Color.LIGHT_GRAY,3,true),
                new EmptyBorder (7,5,0,5)));
        ButtonGroup gamePlayerType = new ButtonGroup ();

        JLabel playersType = new JLabel ("Type  Of  Playing");
        playersType.setHorizontalAlignment (SwingConstants.CENTER);
        playersType.setHorizontalTextPosition (SwingConstants.CENTER);
        playersType.setFont (new Font ("arial",Font.PLAIN,13));
        singlePlayer = new JRadioButton ("Single Player game");
        teamPlayer = new JRadioButton ("Team game");
        gamePlayerType.add (singlePlayer);
        gamePlayerType.add (teamPlayer);
        teamPlayer.addItemListener (changeHandler);
        singlePlayer.addItemListener (changeHandler);

        ButtonGroup gameEndType = new ButtonGroup ();

        JLabel endType = new JLabel ("End Type of game");
        endType.setHorizontalAlignment (SwingConstants.CENTER);
        endType.setHorizontalTextPosition (SwingConstants.CENTER);
        endType.setFont (new Font ("arial",Font.PLAIN,13));

        deathMatch = new JRadioButton ("Death Match");
        leagueMatch = new JRadioButton ("League Match");
        gameEndType.add (deathMatch);
        gameEndType.add (leagueMatch);

        JLabel numberOfPlayers = new JLabel ("Number Of Players");
        numberOfPlayers.setFont (new Font ("arial",Font.PLAIN,13));
        SpinnerModel spinnerModel;
        if (teamPlayer.isSelected ())
        {
            spinnerModel = new SpinnerNumberModel (2,1,100,2);
        }
        else
        {
            spinnerModel = new SpinnerNumberModel (1,1,100,1);
        }

        numOfPlayers = new JSpinner ();
        numOfPlayers.setModel (spinnerModel);
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setEditable (false);
        ((JSpinner.DefaultEditor)numOfPlayers.getEditor ()).getTextField ().setFocusable (false);



        JLabel tanksStaminaLabel = new JLabel ("Tank Stamina");
        tanksStaminaLabel.setFont (new Font ("arial",Font.PLAIN,13));
        tanksStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        tanksStamina.setSnapToTicks (true);
        tanksStamina.setMinorTickSpacing (10);
        tanksStamina.setToolTipText (tanksStamina.getValue () + " %");
        tanksStamina.addChangeListener (changeHandler);

        JLabel canonPowerLabel = new JLabel ("Canon Power");
        canonPowerLabel.setFont (new Font ("arial",Font.PLAIN,13));
        canonPower = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        canonPower.setMinorTickSpacing (10);
        canonPower.setSnapToTicks (true);
        canonPower.setToolTipText (canonPower.getValue () + " %");
        canonPower.addChangeListener (changeHandler);

        JLabel wallsStaminaLabel = new JLabel ("Destroyable Walls Stamina");
        wallsStaminaLabel.setFont (new Font ("arial",Font.PLAIN,13));
        wallsStamina = new JSlider (SwingConstants.HORIZONTAL,10,100,20);
        wallsStamina.setSnapToTicks (true);
        wallsStamina.setMinorTickSpacing (10);
        wallsStamina.setValueIsAdjusting (true);
        wallsStamina.setToolTipText (wallsStamina.getValue () + " %");
        wallsStamina.addChangeListener (changeHandler);

        create = new JButton ("Create!");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 70;
        constraints.ipady = 20;
        constraints.weightx = 0.5f;

        constraints.insets = new Insets (0,0,17,0);
        GridBagSetter.addComponent (header,0,0,20,3,layout,constraints,basePanel);

        constraints.ipady = 7;
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (gameName,3,0,1,1,layout,constraints,basePanel);

        GridBagSetter
                .addComponent (gameNameTextField,3,1,10,1,
                        layout,constraints,basePanel);
        GridBagSetter
                .addComponent (playersType,4,0,20,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,95,12,35);
        GridBagSetter
                .addComponent (singlePlayer,5,0,5,1,layout,constraints,basePanel);
        GridBagSetter
                .addComponent (teamPlayer,5,5,5,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (endType,6,0,20,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,95,12,35);
        GridBagSetter
                .addComponent (deathMatch,7,0,5,1,layout,constraints,basePanel);
        GridBagSetter
                .addComponent (leagueMatch,7,5,5,1,layout,constraints,basePanel);
        constraints.insets = new Insets (0,5,12,5);
        GridBagSetter
                .addComponent (numberOfPlayers,8,0,8,
                        1,layout,constraints,basePanel);
        GridBagSetter
                .addComponent (numOfPlayers,8,8,10,1,layout,constraints,basePanel);
        constraints.insets = new Insets (12,5,12,5);
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
                .addComponent (canonPower,13,5,10,1,layout,constraints,
                        basePanel);
        GridBagSetter
                .addComponent (wallsStaminaLabel,14,0,20,1,layout,constraints,
                        basePanel);
        GridBagSetter
                .addComponent (wallsStamina,15,5,10,1,layout,
                        constraints,basePanel);
        GridBagSetter
                .addComponent (create,16,0,20,5,layout,constraints,basePanel);

        add(basePanel);
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
                if (e.getSource () == teamPlayer)
                {
                    if (teamPlayer.isSelected ())
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
                } else if (e.getSource () == singlePlayer)
                {
                    if (singlePlayer.isSelected ())
                    {
                        numOfPlayers.setModel (new SpinnerNumberModel ((int)(numOfPlayers.getValue ())
                                ,1,100,1));
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setEditable (false);
                        ((JSpinner.DefaultEditor)numOfPlayers.
                                getEditor ()).getTextField ().setFocusable (false);
                    }

                }
            }

        }
    }

}
