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


//<<<<<<< HEAD
        frame.setContentPane ();
//=======
        //frame.setContentPane (newGamePanel);
//>>>>>>> fc532b336a7eab2d3f338de011b2d3115a9fa68b

        frame.setLocation (0,0);

        frame.setSize ((720 * 16) / 9,720);


        frame.setVisible (true);
    }
}
