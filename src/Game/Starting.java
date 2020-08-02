/*** In The Name of Allah ***/
package Game;

import GameData.User;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Starting
{

	public Starting(JFrame menuFrame, int level, int tankStamina, int canonPower, int wallStamina,
					User user)
	{

		// Initialize the global thread-pool
		ThreadPool.init();

		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				GameFrame frame = null;
				frame = new GameFrame("Simple Ball !");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();

				GameLoop game = new GameLoop(frame,menuFrame,level,tankStamina,canonPower,wallStamina);
				game.init(user);
				ThreadPool.execute(game);
			}
		});
	}
}
