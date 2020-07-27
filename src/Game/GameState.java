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
public class GameState
{

	private ArrayList<Tank> tanks;
	private ArrayList<Bullet> bullets;

	public boolean gameOver;
	private Maps maps;
	private Prizes prizes;

	public Maps getMaps() {
		return maps;
	}

	public GameState(int level,int tankStamina,int canonPower,int wallStamina)
	{

		maps = new Maps(wallStamina);
		bullets = new ArrayList<> ();
		tanks = new ArrayList<> ();
		prizes = new Prizes(maps,tanks);

		Tank tank1 = new Tank(bullets, maps.getWalls (), tanks,prizes ,
				tankStamina,canonPower);
		tanks.add (tank1);

		for(int i=1;i<=level;i++)
		{
			Tank tank2 = new IntelligentTank(bullets, maps.getWalls(), tanks, prizes,
					tankStamina,canonPower);
			tanks.add (tank2);
		}

		gameOver = false;
		Thread t1 = new Thread(prizes);
		t1.start();
	}

	public Prizes getPrizes()
	{
		return prizes;
	}

	public ArrayList<Tank> getTanks () {
		return tanks;
	}



	/**
	 * The method which updates the game state.
	 */
	public void update()
	{
		int numberOfNormalTanks = 0;
		int numberOfIntelligentTanks = 0;

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
			if (!(tank instanceof IntelligentTank))
				numberOfNormalTanks++;
			else
				numberOfIntelligentTanks++;
			if (tank.isDestroyed ())
				tankIterator.remove ();
			else
				executorService.execute (tank);
		}
		executorService.shutdown();
		if (numberOfNormalTanks == 0 || numberOfIntelligentTanks == 0)
			gameOver = true;

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

