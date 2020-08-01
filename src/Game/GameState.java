package Game;

import java.util.ArrayList;
import java.util.Iterator;
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
	private InteractArrayList<Bullet> bullets;
	public int gameOver;
	private Maps maps;
	private Prizes prizes;
	private Thread t1;

	public GameState(int level,int tankStamina,int canonPower,int wallStamina)
	{
		maps = new Maps(wallStamina);
		bullets = new InteractArrayList<> ();
		tanks = new ArrayList<> ();
		prizes = new Prizes(maps,tanks);

		Tank tank1 = new Tank(bullets, maps.getWalls (), tanks,prizes ,
				tankStamina,canonPower,maps,"Images/Tanks/"+"1"+".png");
		tanks.add (tank1);

		for(int i=2;i<=level+1;i++)
		{
			Tank tank2 = new IntelligentTank(bullets, maps.getWalls(), tanks, prizes,
					tankStamina,canonPower,maps,"Images/Tanks/"+(i)+".png");
			tanks.add (tank2);
		}

		gameOver = 0;
		t1 = new Thread(prizes);
		t1.start();
	}

	public Prizes getPrizes()
	{
		return prizes;
	}

	public ArrayList<Tank> getTanks ()
	{
		return tanks;
	}

	public Maps getMaps()
	{
		return maps;
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
		bullets.setIterate (true);
		while (bulletIterator.hasNext ())
		{
			Bullet bullet = bulletIterator.next ();
			if (bullet.hasExpired ())
				bulletIterator.remove ();
			else
				executorService.execute (bullet);
		}
		if (!executorService.isTerminated ())
		{
			try {
				Thread.sleep (1);
			} catch (InterruptedException e)
			{
				e.printStackTrace ();
			}
		}
		bullets.setIterate (false);

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
		if(numberOfNormalTanks == 0 )
		{
			gameOver = -1;
			t1.stop();
		}

		if(numberOfIntelligentTanks == 0)
		{
			gameOver = 1;
			t1.stop();
		}

		try
		{
			while (!executorService.isTerminated ())
			{
				Thread.sleep (1);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace ();
		}
	}



	public ArrayList<Bullet> getBullets () {
		return bullets;
	}

}

