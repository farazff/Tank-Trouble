import GUI.*;
import GUI.MainPage.Main;
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



        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Loading temp = new Loading();
        frame.setSize ((720 * 16) / 9, 720);
        frame.add(new Setting());
        frame.setLocation (230,60);
        frame.setVisible (true);
        //temp.fill();
    }
}
