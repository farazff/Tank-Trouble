
import GUI.GameWithPC;
import GUI.Loading;
import GUI.MainPage.Main;
import GUI.MultiGame.CreateNewServer;
import GUI.MultiGame.MultiGamePanel;
import GUI.Setting.Setting;
import GUI.SignInPanel;
import GUI.SignUpPanel;
import GameData.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;


public class TestAmir {
    public static void main (String[] args) {

        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace ();
        }

        JFrame frame = new JFrame ();
        frame.setLocation (0, 0);
        frame.setSize ((720 * 16) / 9, 720);


        ArrayList<MultiGame> multiGames = new ArrayList<> ();
        multiGames.add (new MultiGame ("google",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames.add (new MultiGame ("google2",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames.add (new MultiGame ("google3",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames.add (new MultiGame ("google4",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        Server server1 = new Server ("Haasd",multiGames,new char[]{'1','0','x'});
        ArrayList<MultiGame> multiGames2 = new ArrayList<> ();
        multiGames2.add (new MultiGame ("google",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("gooe2",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("ggle3",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("goasdfsadfasdfogle4",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        Server server2 = new Server ("Amsadfasdfsadfasdfafdad",multiGames2,new char[]{'1','0','y'});

        ServerDataBase servers = new ServerDataBase (null);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        servers.addNewServer (server2);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        servers.addNewServer (server1);
        Loading loading = new Loading(frame);
        SignInPanel signInPanel = new SignInPanel(frame);
        SignUpPanel signUp = new SignUpPanel(frame,signInPanel);
        Main main = new Main (frame);
        GameWithPC gameWithPC = new GameWithPC(frame);
        MultiGamePanel multiGamePanel = new MultiGamePanel (frame,servers);
        Setting setting = new Setting(frame,servers);


        loading.setNex(signInPanel);
        signInPanel.setNex(main);

        main.setSing(gameWithPC);
        main.setMul(multiGamePanel);
        main.setSett(setting);
        gameWithPC.setPre(main);
        multiGamePanel.setPre(main);
        setting.setPre(main);


        frame.setContentPane(setting);
        frame.setVisible(true);
//        loading.fill();
    }
}
