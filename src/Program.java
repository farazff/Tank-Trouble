import GUI.Loading;
import GUI.MainPage.Main;
import GUI.SignInPanel;
import javax.swing.*;

/**
 * this class connect panel together
 */
public class Program
{
    private JFrame frame;
    private Loading loading;

    /**
     * creates new Program
     */
    public Program ()
    {
        frame = new JFrame ();
        frame.setLocation (40, 40);
        frame.setSize ((720 * 16) / 9, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("Images/Icon.png");
        frame.setIconImage(img.getImage());
        frame.setTitle("Tank Trouble");


        loading = new Loading(frame);
        SignInPanel signInPanel = new SignInPanel(frame);
        Main main = new Main (frame);
        main.setPre (signInPanel);
        loading.setNex(signInPanel);
        signInPanel.setNex(main);


        frame.setContentPane(loading);

    }

    /**
     * starts program
     */
    public void start ()
    {
        frame.setVisible (true);
        loading.fill ();
    }
}
