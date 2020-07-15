import GUI.*;

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
        ServerPanel serverPanel = new ServerPanel
                ("url","Crename",23,12);
        CreateNewGamePanel newGamePanel = new CreateNewGamePanel ();


        frame.setContentPane (serverPanel);

        frame.setLocation (0,0);

        frame.setSize ((720 * 16) / 9,720);


        frame.setVisible (true);
    }
}
