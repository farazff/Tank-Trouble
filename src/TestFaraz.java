import GUI.*;
import GUI.MainPage.Main;
import javax.swing.*;


public class TestFaraz
{
    public static void main (String[] args)
    {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace ();
        }

        JFrame frame = new JFrame ();
        frame.setLocation (40, 40);
        frame.setSize ((720 * 16) / 9, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        Loading loading = new Loading(frame);
        SignInPanel signInPanel = new SignInPanel(frame);
        SignUpPanel signUp = new SignUpPanel(frame,signInPanel);
        Main main = new Main (frame);
        main.setPre (signInPanel);


        loading.setNex(signInPanel);
        signInPanel.setNex(main);


        frame.setContentPane(loading);
        frame.setVisible(true);
        loading.fill();

    }
}
