package Game;

import GUI.Music;
import GameData.User;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame
{
	public static final int GAME_HEIGHT = 720;                  // 720p game resolution
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio

	private long lastRender;
	private ArrayList<Float> fpsHistory;
	private BufferStrategy bufferStrategy;
	private int mapTheme ;
	private BufferedImage theme;
	private BufferedImage wallNDH;
	private BufferedImage wallDH;
	private BufferedImage wallNDV;
	private BufferedImage wallDV;

	/**
	 * creates new game frame
	 * @param title title
	 */
	public GameFrame(String title)
	{
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		lastRender = -1;
		fpsHistory = new ArrayList<>(100);

		Random random = new Random();
		mapTheme = random.nextInt(2) + 1;

		try
		{
			if(mapTheme == 1)
				theme = ImageIO.read(new File("Images/Roads/Light.png"));
			if(mapTheme == 2)
				theme = ImageIO.read(new File("Images/Roads/Dark.png"));

			wallNDH = ImageIO.read (new File("Images/Walls/RedH.png"));
			wallDH = ImageIO.read (new File("Images/Walls/YellowH.png"));
			wallNDV = ImageIO.read(new File("Images/Walls/RedV.png"));
			wallDV  = ImageIO.read(new File("Images/Walls/YellowV.png"));
		}
		catch (IOException e)
		{
			System.out.println("hii");
		}
	}

	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	/**
	 * MultiGame rendering with triple-buffering using BufferStrategy.
	 * @param kills kills
	 * @param state state
	 * @param user user
	 * @throws IOException IOException
	 */
	public void render(GameState state,int[] kills, User user) throws IOException
	{
		// Render single frame
		do
		{
			// The following loop ensures that the contents of the drawing buffer
			// are consistent in case the underlying surface was recreated
			do
			{
				// Get a new graphics context every time through the loop
				// to make sure the strategy is validated
				Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
				try
				{
					doRendering(graphics, state,kills,user);
				}
				finally
				{
					// Dispose the graphics
					graphics.dispose();
				}
				// Repeat the rendering if the drawing buffer contents were restored
			} while (bufferStrategy.contentsRestored());

			// Display the buffer
			bufferStrategy.show();
			// Tell the system to do the drawing NOW;
			// otherwise it can take a few extra ms and will feel jerky!
			Toolkit.getDefaultToolkit().sync();

			// Repeat the rendering if the drawing buffer was lost
		}while(bufferStrategy.contentsLost());
	}

	/**
	 * Rendering all game elements based on the game state.
	 * @param user user
	 * @param state state
	 * @param kills kills
	 * @param g2d g2d
	 * @throws IOException IOException
	 */
	private void doRendering(Graphics2D g2d, GameState state, int[] kills, User user) throws IOException
	{
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);

		try
		{
			drawRoads(g2d);

			for (Tank tank : state.getTanks ()) {
				if (tank.isFireDestroyed ()) {
					Music music = new Music();
					music.setFilePath("Files/Sounds/Blast.au",false);
					music.execute();
					BufferedImage image1 = Tank.getFireDestroyImage ();
					g2d.drawImage (image1, tank.getCenterX () - image1.getWidth () / 2 + 3,
							tank.getCenterY () - image1.getHeight () / 2 + 2, null);
				} else {
					BufferedImage image = tank.getTankImage ();

					g2d.drawImage (rotateImage (image, tank.getDegree () - 45),
							tank.getLocX (), tank.getLocY (), null);

					if (tank.isShot ()) {
						BufferedImage image1 = rotateImageBullet (Tank.getFireImage ()
								, tank.getDegree () - 90);

						g2d.drawImage (image1,
								tank.getCanonStartX () - image1.getWidth () / 2 + 3,
								tank.getCanonStartY () - image1.getHeight () / 2 + 2
								, null);
					}

					g2d.setStroke(new BasicStroke(1));
					g2d.setPaint(Color.BLACK);
					g2d.setFont(g2d.getFont().deriveFont(14.0f));
					if (!(tank instanceof IntelligentTank))
						g2d.drawString (tank.getUser ().getUserName ().length () <= 8 ?
										tank.getUser ().getUserName () :
										tank.getUser ().getUserName ().substring (0,8) + "...",
								tank.getLocX () + 5,tank.getLocY () - 18);
					g2d.draw(new Rectangle2D.Double(tank.getLocX()+5,tank.getLocY()-12,55,6));

					if(tank.getStamina()>=90)
						g2d.setPaint(Color.GREEN);

					if(tank.getStamina()>=55 && tank.getStamina()<90)
						g2d.setPaint(Color.YELLOW);

					if(tank.getStamina()<55)
						g2d.setPaint(Color.RED);

					g2d.fill(new Rectangle2D.Double(tank.getLocX()+5,tank.getLocY()-12,
							tank.getStamina()/2,6));

					if(tank.getPrizeOwn()!=null)
					{
						if(tank.getPrizeOwn().getType().equals("Protect"))
						{
							g2d.setStroke(new BasicStroke(4));
							g2d.setPaint(Color.ORANGE);
						}
						if(tank.getPrizeOwn().getType().equals("Laser"))
						{
							g2d.setStroke(new BasicStroke(4));
							g2d.setPaint(Color.YELLOW);
						}
						if(tank.getPrizeOwn().getType().equals("Health"))
						{
							g2d.setStroke(new BasicStroke(4));
							g2d.setPaint(Color.GREEN);
						}
						if(tank.getPrizeOwn().getType().equals("Power2"))
						{
							g2d.setStroke(new BasicStroke(4));
							g2d.setPaint(Color.BLACK);
						}
						if(tank.getPrizeOwn().getType().equals("Power3"))
						{
							g2d.setStroke(new BasicStroke(6));
							g2d.setPaint(Color.BLACK);
						}
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
								0,30,Arc2D.OPEN));
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
								135,30,Arc2D.OPEN));
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
								180,30,Arc2D.OPEN));
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
								225,30,Arc2D.OPEN));
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
								270,30,Arc2D.OPEN));
						g2d.draw(new Arc2D.Double(tank.getLocX()-11,tank.getLocY()-11,84,84,
							315,30,Arc2D.OPEN));
					}
				}
			}

			ArrayList<Bullet> bullets = new ArrayList<> (state.getBullets ());

			for (Bullet bullet : bullets)
			{
				BufferedImage image2 = rotateImageBullet
						(bullet.getImage (),bullet.getDegree () + 90);

				g2d.drawImage (image2,
						bullet.getX () - image2.getWidth () / 2 + 3
						,bullet.getY () - image2.getHeight () / 2 + 2,null);
			}

			if(state.getPrizes()!=null)
			for(int i=0;i<state.getPrizes().getPrizes().size();i++)
			{
				Prize prize = state.getPrizes().getPrizes().get(i);
				if(prize.isActive())
					g2d.drawImage(prize.getImg(),prize.getX(),prize.getY(),null);
			}

			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					for(int i=0;i<state.getMaps().getWalls().size();i++)
					{
						Wall temp = state.getMaps().getWalls().get(i);
						if(temp.getType().equals("H")) // ofoqi
						{
							int y = temp.getY();
							if(y==0)
							{
								y+=31;
							}
							if(720-y<=5)
							{
								y-=20;
							}
							int w = wallNDH.getWidth();
							if(!temp.isDestructible())
							{
								for(int p=0;p<=temp.getLength()/w;p++)
								{
									g2d.drawImage(wallNDH, temp.getX() + p * w, y, null);
								}
							}
							else
							{
								if(temp.isOK())
									for(int p=0;p<=temp.getLength()/w;p++)
									{
										g2d.drawImage(wallDH, temp.getX() + p * w, y, null);
									}
							}
						}
						if(temp.getType().equals("V")) // amodi
						{
							//System.out.println(temp.getX() + " " + temp.getY());
							int x = temp.getX();
							if (x == 0)
							{
								x += 6;
							}
							if(1280 - x < 5)
							{
								x-=20;
							}

							int h = wallNDV.getHeight();
							if(!temp.isDestructible())
							{
								for(int p=0;p<=temp.getLength()/h;p++)
								{
									g2d.drawImage(wallNDV,x,temp.getY()+p*h,null);
								}
							}
							else
							{
								if(temp.isOK())
									for(int p=0;p<=temp.getLength()/h;p++)
									{
										g2d.drawImage(wallDV,x,temp.getY()+p*h,null);
									}
							}
						}
					}

				}
			}).run();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		// Print FPS info
		long currentRender = System.currentTimeMillis();
		if (lastRender > 0)
		{
			fpsHistory.add(1000.0f / (currentRender - lastRender));
			if (fpsHistory.size() > 100)
			{
				fpsHistory.remove(0); // remove oldest
			}
			float avg = 0.0f;
			for (float fps : fpsHistory)
			{
				avg += fps;
			}
			avg /= fpsHistory.size();
			String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
					avg, (currentRender - lastRender));
			g2d.setColor(Color.CYAN);
			g2d.setFont(g2d.getFont().deriveFont(18.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			int strHeight = g2d.getFontMetrics().getHeight();
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
		}
		lastRender = currentRender;

		g2d.setPaint(new GradientPaint(300,150,Color.RED,600,450,Color.BLACK, true));

		if(state.gameOver != 0)
		{

			g2d.fill(new RoundRectangle2D.Double(300,150,680,420,30,30));

			g2d.setPaint(Color.WHITE);
			g2d.setFont(new Font("Arial",Font.BOLD,28));
			g2d.drawString("Round Over",565,195);

			g2d.setFont(new Font("Arial",Font.BOLD,22));

			String[] names = new String[kills.length];
			int[] scores = new int[kills.length];

			names[0] = user.getUserName ();
			scores[0] = kills[0];
			for(int i=1;i<kills.length;i++)
			{
				names[i] = "PC" + (i+1);
				scores[i] = kills[i];
			}

			for(int i=0;i<kills.length;i++)
			{
				for(int j=i+1;j<kills.length;j++)
				{
					if(scores[i]<scores[j])
					{
						String name = names[i];
						int temp = kills[i];
						names[i] = names[j];
						scores[i] = scores[j];
						names[j] = name;
						scores[j] = temp;
					}
				}
			}

			for(int i=0;i<kills.length;i++)
			{
				String temp =  (i+1) + " ) " + names[i] + ": " + scores[i] + "\n";
				g2d.drawString(temp, 340, 250 + 55*i);
			}
		}
	}

	/**
	 * rotate image from center of pic
	 * @param sourceImage sourceImage
	 * @param angle angle
	 * @return rotated pic
	 */
	private static BufferedImage rotateImage(BufferedImage sourceImage, double angle)
	{
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = destImage.createGraphics();

		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians (angle), width / 2 , height / 2);
		g2d.drawRenderedImage(sourceImage, transform);

		//g2d.dispose();
		return destImage;
	}

	/**
	 * rotate image from x , y  of edge of pic
	 * @param img img
	 * @param angle angle
	 * @return rotated pic
	 */
	public BufferedImage rotateImageBullet(BufferedImage img, double angle) {

		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();

		return rotated;
	}

	/**
	 * draws roads
	 * @param g2d g2d
	 * @throws IOException IOException
	 */
	public void drawRoads(Graphics2D g2d) throws IOException
	{
		g2d.drawImage(theme,5,31,null);
	}
}
