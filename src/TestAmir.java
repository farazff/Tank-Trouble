import GUI.*;
import GUI.MultiGame.CreateNewMultiGame;
import GUI.MultiGame.MultiGamePanel;
import GUI.MultiGame.ServerButtonPanel;

import javax.swing.*;
import java.util.ArrayList;

public class TestAmir {
    public static void main (String[] args) {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace ();
        }

        JFrame frame = new JFrame ();


        GameWithPC gameWithPC = new GameWithPC ();
        CreateNewMultiGame newGamePanel = new CreateNewMultiGame ();
        ServerButtonPanel serverPanel = new ServerButtonPanel ("www.google.com"
                , 32);
        ServerButtonPanel serverPanel2 = new ServerButtonPanel ("www.google.com"
                , 32);
        ServerButtonPanel serverPanel3 = new ServerButtonPanel ("www.google.com"
                , 32);
        ServerButtonPanel serverPanel4 = new ServerButtonPanel ("www.google.com"
                , 32);
        ServerButtonPanel serverPanel5 = new ServerButtonPanel ("www.google.com"
                , 32);

        ArrayList<ButtonPanel> serverPanels = new ArrayList<> ();
        serverPanels.add (serverPanel);
        serverPanels.add (serverPanel2);
        serverPanels.add (serverPanel3);
        serverPanels.add (serverPanel4);
        serverPanels.add (serverPanel5);

        frame.setContentPane (new MultiGamePanel (serverPanels));




        frame.setLocation (0, 0);

        frame.setSize ((720 * 16) / 9, 720);


        frame.setVisible (true);
    }
}
