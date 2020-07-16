import GUI.*;
import Setting.Setting;

import javax.swing.*;
import java.util.ArrayList;


public class TestFaraz
{
    public static void main (String[] args) {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e)
        {
            e.printStackTrace ();
        }

        JFrame frame = new JFrame ();

        GameWithPC gameWithPC = new GameWithPC();
        CreateNewMultiGame newGamePanel = new CreateNewMultiGame ();
        ServerPanel serverPanel = new ServerPanel ("www.google.com","Amir"
                ,32);
        ServerPanel serverPanel2 = new ServerPanel ("www.google.com","Amir"
                ,32);
        ServerPanel serverPanel3 = new ServerPanel ("www.google.com","Amir"
                ,32);
        ServerPanel serverPanel4 = new ServerPanel ("www.google.com","Amir"
                ,32);
        ServerPanel serverPanel5 = new ServerPanel ("www.google.com","Amir"
                ,32);

        ArrayList<ServerPanel> serverPanels = new ArrayList<> ();
        serverPanels.add (serverPanel);
        serverPanels.add (serverPanel2);
        serverPanels.add (serverPanel3);
        serverPanels.add (serverPanel4);
        serverPanels.add (serverPanel5);

        frame.setContentPane (new Setting());
        frame.pack();



        frame.setLocation (0,0);

        //frame.setSize ((720 * 16) / 9,720);


        frame.setVisible (true);
    }
}
