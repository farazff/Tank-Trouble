
import SocketConnectionForTankServer.MultiGameFrame;
import SocketConnectionForTankServer.MoveTranslator;


public class TestAmir {
    public static void main (String[] args) {

//        try { // "javax.swing.plaf.nimbus.NimbusLookAndFeel"
//            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace ();
//        }
//
//        JFrame multiGameFrame = new JFrame ();
//        multiGameFrame.setLocation (0, 0);
//        multiGameFrame.setSize ((720 * 16) / 9, 720);
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
//        Loading loading = new Loading(multiGameFrame);
//        SignInPanel signInPanel = new SignInPanel(multiGameFrame);
//        SignUpPanel signUp = new SignUpPanel(multiGameFrame,signInPanel);
//        Main main = new Main (multiGameFrame);
//        GameWithPC gameWithPC = new GameWithPC(multiGameFrame);
//        MultiGamePanel multiGamePanel = new MultiGamePanel (multiGameFrame,servers);
//        Setting setting = new Setting(multiGameFrame,servers);
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
//        multiGameFrame.setContentPane(setting);
//        multiGameFrame.setVisible(true);
////        loading.fill();

        MoveTranslator moveTranslator = new MoveTranslator ();
        MultiGameFrame multiGameFrame = new MultiGameFrame ();
        multiGameFrame.setVisible (true);
        multiGameFrame.addKeyListener (moveTranslator.getKeyListener ());
        while (true)
        {
            try {
                Thread.sleep (24);
            } catch (InterruptedException e)
            {
                e.printStackTrace ();
            }
            System.out.println (moveTranslator.getCommandString ());
        }


    }


}
