import GUI.CreateNewGamePanel;
import GUI.GameWithPC;
import GUI.LoginPanel;
import GUI.SignUpPanel;

import javax.swing.*;


public class TestMain
{
    public static void main (String[] args) {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e)
        {
            e.printStackTrace ();
        }

        JFrame frame = new JFrame ();
        LoginPanel loginPanel = new LoginPanel ();
        SignUpPanel signUpPanel = new SignUpPanel ();
        GameWithPC gameWithPC = new GameWithPC();
        CreateNewGamePanel newGamePanel = new CreateNewGamePanel ();

        frame.setContentPane (signUpPanel);
        frame.setLocation (0,0);

        frame.setSize (750,680);


        frame.setVisible (true);
    }
}
