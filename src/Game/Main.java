/*** In The Name of Allah ***/
package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {
	
    public static void main(String[] args)
	{
		// Initialize the global thread-pool
		ThreadPool.init();

		// Show the game menu ...

		// After the player clicks 'PLAY' ...
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				GameFrame frame = new GameFrame("Simple Ball !");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();
				// Create and execute the game-loop
				Prizes prizes = new Prizes();
				GameLoop game = new GameLoop(frame,prizes);
				game.init();
				ThreadPool.execute(game);
				ThreadPool.execute(prizes);
				// and the game starts ...
			}
		});

    }
}
