package Game;

import GameData.MultiGame;
import GameData.User;
import MultiGame.MultiGameFrame;
import MultiGame.MultiGameLoop;

import javax.swing.*;
import java.awt.*;

public class MultiGameStarting
{
    public MultiGameStarting (JFrame menuFrame, User user, MultiGame multiGame)
    {
        ThreadPool.init();

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MultiGameFrame frame = null;
                frame = new MultiGameFrame ("Client Sideup!");
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                MultiGameLoop game = new MultiGameLoop (frame,menuFrame);
                game.init("127.0.0.1",multiGame.getPort (), user);
                ThreadPool.execute(game);
            }
        });
    }
}
