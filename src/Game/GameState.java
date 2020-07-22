/*** In The Name of Allah ***/
package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {

	private ArrayList<Tank> tanks;
	private ArrayList<Bullet> bullets;
	public boolean gameOver;



	public GameState()
	{
		bullets = new ArrayList<> ();
		tanks = new ArrayList<> ();
		Tank tank1 = new Tank(bullets);
		Tank tank2 = new Tank (bullets);
		Tank tank3 = new Tank (bullets);
		tanks.add (tank1);
		tanks.add (tank2);
		tanks.add (tank3);
		checkBulletsTimeExpiration ();
		gameOver = false;
	}


	public ArrayList<Tank> getTanks () {
		return tanks;
	}

	/**
	 * The method which updates the game state.
	 */
	public void update()
	{
		for (Bullet bullet : bullets)
		{
			bullet.run ();
		}

		for (Tank tank : tanks)
			tank.run ();
	}




	private void checkBulletsTimeExpiration ()
	{
		new Thread (new Runnable () {

			@Override
			public void run () {
				while (!gameOver) {
					ArrayList<Bullet> removeBullets = new ArrayList<> ();
					ArrayList<Bullet> bullets = new ArrayList<> (getBullets ());
					int i = 1;
					for (Bullet bullet : bullets) {
						System.out.println (i);
						if (bullet.hasExpired ())
							removeBullets.add (bullet);
						i++;
					}


					getBullets ().removeAll (removeBullets);

					try {
						Thread.sleep (200);
					} catch (InterruptedException e) {
						e.printStackTrace ();
					}
				}
			}
		}).start ();
	}






	public ArrayList<Bullet> getBullets () {
		return bullets;
	}

}

