import GUI.*;
import GUI.MainPage.Main;
import GUI.MultiGamePanels.MultiGamePanel;
import GameData.*;

import javax.swing.*;
import java.util.ArrayList;


public class TestFaraz
{
    public static void main (String[] args) {

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
        GameWithPC gameWithPC = new GameWithPC(frame);




        loading.setNex(signInPanel);
        signInPanel.setNex(main);

        main.setSing(gameWithPC);


        gameWithPC.setPre(main);




        frame.setContentPane(loading);
        frame.setVisible(true);
        loading.fill();

//        ThreadPool.init();

//        EventQueue.invokeLater(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                MultiGameFrame frame = null;
//                frame = new MultiGameFrame("Tank Trouble !");
//                frame.setLocationRelativeTo(null);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//                frame.initBufferStrategy();
//
//                MultiGameLoop game = new MultiGameLoop(frame,null);
//                game.init("127.0.0.1",8080);
//                ThreadPool.execute(game);
//            }
//        });


    }
}
