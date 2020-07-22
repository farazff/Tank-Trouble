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

	private Tank tank1;
	private Tank tank2;
	private ArrayList<Bullet> bullets;
	public boolean gameOver;



	public GameState()
	{
		bullets = new ArrayList<> ();
		tank1 = new Tank(bullets);
		tank2 = new Tank (bullets);
		checkBulletsTimeExpiration ();
		gameOver = false;
	}



	public Tank getTank1 () {
		return tank1;
	}

	public Tank getTank2 () {
		return tank2;
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

		tank1.run();
		tank2.run ();
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

