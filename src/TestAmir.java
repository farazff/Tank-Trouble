
import GUI.GameWithPC;
import GUI.Loading;
import GUI.MainPage.Main;
import GUI.MultiGame.CreateNewServer;
import GUI.MultiGame.MultiGamePanel;
import GUI.Setting.Setting;
import GUI.SignInPanel;
import GUI.SignUpPanel;
import Game.Tank;
import GameData.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;


public class TestAmir {
    public static void main (String[] args) {

//        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
//            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace ();
//        }
//
//        JFrame frame = new JFrame ();
//        frame.setLocation (0, 0);
//        frame.setSize ((720 * 16) / 9, 720);
//
//
//        ArrayList<MultiGame> multiGames = new ArrayList<> ();
//        multiGames.add (new MultiGame ("google",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames.add (new MultiGame ("google2",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames.add (new MultiGame ("google3",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames.add (new MultiGame ("google4",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        Server server1 = new Server ("Haasd",multiGames,new char[]{'1','0','x'});
//        ArrayList<MultiGame> multiGames2 = new ArrayList<> ();
//        multiGames2.add (new MultiGame ("google",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames2.add (new MultiGame ("gooe2",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames2.add (new MultiGame ("ggle3",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        multiGames2.add (new MultiGame ("goasdfsadfasdfogle4",GameFinishType.DEATH_MATCH,
//                GameMemberShipType.SINGLE,22,30,30,30));
//        Server server2 = new Server ("Amsadfasdfsadfasdfafdad",multiGames2,new char[]{'1','0','y'});
//
//        ServerDataBase servers = new ServerDataBase (null);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        servers.addNewServer (server2);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        servers.addNewServer (server1);
//        Loading loading = new Loading(frame);
//        SignInPanel signInPanel = new SignInPanel(frame);
//        SignUpPanel signUp = new SignUpPanel(frame,signInPanel);
//        Main main = new Main (frame);
//        GameWithPC gameWithPC = new GameWithPC(frame);
//        MultiGamePanel multiGamePanel = new MultiGamePanel (frame,servers);
//        Setting setting = new Setting(frame,servers);
//
//
//        loading.setNex(signInPanel);
//        signInPanel.setNex(main);
//
//        main.setSing(gameWithPC);
//        main.setMul(multiGamePanel);
//        main.setSett(setting);
//        gameWithPC.setPre(main);
//        multiGamePanel.setPre(main);
//        setting.setPre(main);
//
//
//        frame.setContentPane(setting);
//        frame.setVisible(true);
////        loading.fill();

        System.out.println (findDegree (12,35,12,10));
        System.out.println (findDegree (12,35,16,20));
        System.out.println (findDegree (12,35,25,35));
        System.out.println (findDegree (12,35,17,45));
        System.out.println (findDegree (12,35,12,50));
        System.out.println (findDegree (12,35,4,38));
        System.out.println (findDegree (12,35,2,35));
        System.out.println (findDegree (12,35,4,25));


    }

    private static int findDegree (int x1, int y1, int x2, int y2)
    {
        int degree;
        if (x2 == x1)
        {
            if (y2 > y1)
                return  90;
            else if (y1 > y2)
                return  270;
        }

        if (y1 == y2)
        {
            if (x2 > x1)
                return 0;
            else if (x1 > x2)
                return 180;
        }

        double m = Math.abs ((1.0 * (y2 - y1)) / (x2 - x1));

        degree = (int)Math.atan (m);

        if (y2 > y1)
        {
            if (x2 > x1)
                return degree;
            else
            {
                return 180 - degree;
            }

        }
        else if (y1 > y2)
        {
            if (x2 > x1)
                return 360 -degree;
            else
                return 180 + degree;
        }
        return 45;
    }
}
