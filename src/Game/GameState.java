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

	private Tank tank;
	private ArrayList<Bullet> bullets = new ArrayList<> ();
	public boolean gameOver;

	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
	private boolean mousePress;
	private int mouseX, mouseY;
	private KeyHandler keyHandler;

	public GameState()
	{
		tank = new Tank();
		gameOver = false;
		//
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		//
		mousePress = false;
		mouseX = 0;
		mouseY = 0;
		//
		keyHandler = new KeyHandler();
	}

	public Tank getTank()
	{
		return tank;
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

		tank.run();

		tank.setLocX(Math.max(tank.getLocX(), 0));
		tank.setLocX(Math.min(tank.getLocX(), GameFrame.GAME_WIDTH - 30));
		tank.setLocY(Math.max(tank.getLocY(), 0));
		tank.setLocY(Math.min(tank.getLocY(), GameFrame.GAME_HEIGHT - 30));
	}


	public KeyListener getKeyListener()
	{
		return keyHandler;
	}




	/**
	 * The keyboard handler.
	 */
	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
				case KeyEvent.VK_SPACE :
					try {
						bullets.add (new Bullet (tank.getCenterX (),tank.getCenterY (),
								tank.getDegree (), System.currentTimeMillis ()));
					} catch (IOException ex) {
						ex.printStackTrace ();
					}

			}
		}

	}

	public ArrayList<Bullet> getBullets () {
		return bullets;
	}

}

