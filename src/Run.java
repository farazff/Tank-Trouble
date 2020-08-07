import GUI.*;
import GUI.MainPage.Main;
import javax.swing.*;


public class Run
{
    public static void main (String[] args)
    {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace ();
        }

        Program program = new Program ();
        program.start ();

    }
}
