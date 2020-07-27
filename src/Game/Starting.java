/*** In The Name of Allah ***/
package Game;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Starting
{

	public Starting(JFrame menuFrame,int level,int tankStamina,int canonPower,int wallStamina)
	{

		// Initialize the global thread-pool
		ThreadPool.init();

		// Show the game menu ...

		// After the player clicks 'PLAY' ...
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
				game.init();
				ThreadPool.execute(game);

			}
		});

	}
}
