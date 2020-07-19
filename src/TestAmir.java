
import GUI.GameWithPC;
import GUI.MainPage.Main;
import GUI.MultiGame.MultiGamePanel;
import GUI.Setting.Setting;
import GUI.SignInPanel;
import GameData.GameFinishType;
import GameData.GameMemberShipType;
import GameData.MultiGame;
import GameData.Server;

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
        Server server1 = new Server ("Haasd",multiGames);
        ArrayList<MultiGame> multiGames2 = new ArrayList<> ();
        multiGames2.add (new MultiGame ("google",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("gooe2",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("ggle3",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        multiGames2.add (new MultiGame ("goasdfsadfasdfogle4",GameFinishType.DEATH_MATCH,
                GameMemberShipType.SINGLE,22,30,30,30));
        Server server2 = new Server ("Amsadfasdfsadfasdfafdad",multiGames2);

        ArrayList<Server> servers = new ArrayList<> ();
        servers.add (server1);
        servers.add (server1);
        servers.add (server1);
        servers.add (server1);
        servers.add (server2);
        servers.add (server1);
        servers.add (server1);
        servers.add (server1);
        servers.add (server1);
        SignInPanel signInPanel = new SignInPanel (frame);
        MultiGamePanel multiGamePanel = new MultiGamePanel (frame,servers);
        Main main = new Main (frame);
        multiGamePanel.setPre (main);
        signInPanel.setNex (main);
        GameWithPC gameWithPC = new GameWithPC (frame);
        gameWithPC.setPre (main);
        main.setSing (gameWithPC);
        main.setMul (multiGamePanel);
        Setting setting = new Setting (frame);
        setting.setPre (main);

        frame.setContentPane (signInPanel);
        frame.setVisible (true);
    }
}
