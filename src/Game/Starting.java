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
	/**
	 * creates single game starting
	 * @param menuFrame menuFrame
	 * @param level level
	 * @param tankStamina tankStamina
	 * @param canonPower canonPower
	 * @param wallStamina wallStamina
	 * @param user user
	 * @param type type
	 */
	public Starting(JFrame menuFrame, int level, int tankStamina, int canonPower, int wallStamina,
					User user , int type)
	{
		// Initialize the global thread-pool

		if(type==1 || type==5)
		{
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

					GameLoop game = new GameLoop(frame, menuFrame, level, tankStamina, canonPower, wallStamina,type);
					game.init(user);
					ThreadPool.execute(game);
				}
			});
		}
	}
}
