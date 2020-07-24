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
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private Maps maps;

	public Maps getMaps() {
		return maps;
	}

	public GameState()
	{
		maps = new Maps();
		bullets = new ArrayList<> ();
		tanks = new ArrayList<> ();
		Tank tank1 = new Tank(bullets, maps.getWalls (), tanks);
//		Tank tank2 = new Tank (bullets, maps.getWalls ());
//		Tank tank3 = new Tank (bullets, maps.getWalls ());
		tanks.add (tank1);
//		tanks.add (tank2);
//		tanks.add (tank3);
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
		ExecutorService executorService = Executors.newCachedThreadPool ();

		Iterator<Bullet> bulletIterator = bullets.iterator ();
		while (bulletIterator.hasNext ())
		{
			Bullet bullet = bulletIterator.next ();
			if (bullet.hasExpired ())
				bulletIterator.remove ();
			else
				executorService.execute (bullet);
		}

		Iterator<Tank> tankIterator = tanks.iterator ();
		while (tankIterator.hasNext ())
		{
			Tank tank = tankIterator.next ();
			if (tank.isDestroyed ())
				tankIterator.remove ();
			else
				executorService.execute (tank);
		}
		executorService.shutdown();

		try {
			while (!executorService.isTerminated ())
			{
				Thread.sleep (1);
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace ();
		}
	}









	public ArrayList<Bullet> getBullets () {
		return bullets;
	}

}

