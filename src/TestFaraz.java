import GUI.*;
import GUI.Setting.Setting;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class TestFaraz
{
    public static void main (String[] args)
    {

        try
        { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e)
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

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Loading temp = new Loading();
        frame.setContentPane (temp);
        frame.pack();
        frame.setLocation (230,60);
        frame.setVisible (true);
        temp.fill();
    }
}
