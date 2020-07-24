/*** In The Name of Allah ***/
package Game;

import GUI.GameWithPC;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
public class GameFrame extends JFrame {

	public static final int GAME_HEIGHT = 720;                  // 720p game resolution
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio

	//uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
	/*private BufferedImage image;*/

	private long lastRender;
	private ArrayList<Float> fpsHistory;

	private BufferStrategy bufferStrategy;

	public GameFrame(String title) {
		super(title);
		setResizable(false);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		lastRender = -1;
		fpsHistory = new ArrayList<>(100);

	/*	try{
			image = ImageIO.read(new File("Icon.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}*/
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
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) throws IOException {
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
					doRendering(graphics, state);
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
		} while (bufferStrategy.contentsLost());
	}

	/**
	 * Rendering all game elements based on the game state.
	 */
	private void doRendering(Graphics2D g2d, GameState state) throws IOException
	{
		//System.out.println(state.getTank().getDegree() + " " + state.getTank().getLocX() + " " +state.getTank().getLocY());

		g2d.setColor(Color.GRAY);
		g2d.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);


		try
		{

			drawRoads(g2d);

			for (Tank tank : state.getTanks ()) {
				if (tank.isFireDestroyed ()) {
					BufferedImage image1 = ImageIO.read
							(new File (tank.getFireDestroyedImageAddress ()));
					g2d.drawImage (image1, tank.getCenterX () - image1.getWidth () / 2 + 3,
							tank.getCenterY () - image1.getHeight () / 2 + 2, null);
				} else {
					BufferedImage image = ImageIO.read (new File (tank.getImageAddress ()));

					g2d.drawImage (rotateImage (image, tank.getDegree () - 45),
							tank.getLocX (), tank.getLocY (), null);

					if (tank.isShot ()) {
						BufferedImage image1 = rotateImageBullet (ImageIO.read
										(new File (tank.getFireImageAddress ()))
								, tank.getDegree () - 90);

						g2d.drawImage (image1,
								tank.getCanonStartX () - image1.getWidth () / 2 + 3,
								tank.getCanonStartY () - image1.getHeight () / 2 + 2
								, null);
					}
				}


			}

			ArrayList<Bullet> bullets = new ArrayList<> (state.getBullets ());

			for (Bullet bullet : bullets)
			{
				BufferedImage image2 = rotateImageBullet
						(ImageIO.read (new File (bullet.getImageAddress ()))
						,bullet.getDegree () + 90);

				g2d.drawImage (image2,
						bullet.getX () - image2.getWidth () / 2 + 3
						,bullet.getY () - image2.getHeight () / 2 + 2,null);
			}

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
					if(GAME_HEIGHT-y<=5)
					{
						y-=20;
					}
					BufferedImage wallND = ImageIO.read (new File("Images/Walls/RedH.png"));
					BufferedImage wallD = ImageIO.read (new File("Images/Walls/YellowH.png"));
					int w = wallND.getWidth();
					if(!temp.isDestructible())
					{
						for(int p=0;p<=temp.getLength()/w;p++)
						{
							g2d.drawImage(wallND, temp.getX() + p * w, y, null);
						}
					}
					else
					{
						if(temp.isOK())
							for(int p=0;p<=temp.getLength()/w;p++)
							{
								g2d.drawImage(wallD, temp.getX() + p * w, y, null);
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
					if(GAME_WIDTH - x < 5)
					{
						x-=20;
					}

					BufferedImage wallND = ImageIO.read(new File("Images/Walls/RedV.png"));
					BufferedImage wallD = ImageIO.read(new File("Images/Walls/YellowV.png"));
					int h = wallND.getHeight();
					if(!temp.isDestructible())
					{
						for(int p=0;p<=temp.getLength()/h;p++)
						{
							g2d.drawImage(wallND,x,temp.getY()+p*h,null);
						}
					}
					else
					{
						if(temp.isOK())
							for(int p=0;p<=temp.getLength()/h;p++)
							{
								g2d.drawImage(wallD,x,temp.getY()+p*h,null);
							}
					}
				}
			}

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
//		// Print user guide
//		String userGuide
//				= "Use the MOUSE or ARROW KEYS to move the BALL. "
//				+ "Press ESCAPE to end the game.";
//		g2d.setFont(g2d.getFont().deriveFont(18.0f));
//		g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
//		// Draw GAME OVER
//		if (state.gameOver)
//		{
//			String str = "GAME OVER";
//			g2d.setColor(Color.WHITE);
//			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
//			int strWidth = g2d.getFontMetrics().stringWidth(str);
//			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
//		}
	}

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



	public void drawRoads(Graphics2D g2d) throws IOException
	{
		BufferedImage chaharrahsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadCrossing.png"));
		BufferedImage pichpayinberastsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadCornerLR.png"));
		BufferedImage pichpayinbechapsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadCornerLL.png"));
		BufferedImage pichbalabechapsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadCornerUL.png"));
		BufferedImage pichbalaberastsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadCornerUR.png"));
		BufferedImage rastbechapsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadEast.png"));
		BufferedImage balabepayinsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadNorth.png"));
		BufferedImage serahberastsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadSplitE.png"));
		BufferedImage serahbebalasabz = ImageIO.read(new File("Images/Roads/tileGrass_roadSplitN.png"));
		BufferedImage serahbepayinsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadSplitS.png"));
		BufferedImage serahbechapsabz = ImageIO.read(new File("Images/Roads/tileGrass_roadSplitW.png"));
		BufferedImage chamansabz = ImageIO.read(new File("Images/Roads/tileGrass2.png"));

		int w = chamansabz.getWidth();
		int h = chamansabz.getHeight();


		g2d.drawImage(pichpayinberastsabz, 0, 31, null);
		g2d.drawImage(rastbechapsabz, w, 31, null);
		g2d.drawImage(rastbechapsabz, 2*w, 31, null);
		g2d.drawImage(pichpayinbechapsabz, 3*w, 31, null);
		g2d.drawImage(pichpayinberastsabz,4*w,31,null);
		for(int i=5;i<=18;i++)
			g2d.drawImage(rastbechapsabz,i*w,31,null);
		g2d.drawImage(pichpayinbechapsabz,19*w,31,null);

		////////////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+h, null);
		g2d.drawImage(pichpayinberastsabz, w, 31+h, null);
		g2d.drawImage(pichpayinbechapsabz, 2*w, 31+h, null);
		g2d.drawImage(balabepayinsabz, 3*w, 31+h, null);
		g2d.drawImage(balabepayinsabz,4*w,31+h,null);
		g2d.drawImage(pichpayinberastsabz,5*w,31+h,null);
		for(int i=6;i<=18;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+h,null);
		g2d.drawImage(serahbechapsabz,19*w,31+h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+2*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+2*h, null);
		g2d.drawImage(pichbalaberastsabz, 2*w, 31+2*h, null);
		g2d.drawImage(chaharrahsabz, 3*w, 31+2*h, null);
		g2d.drawImage(pichbalabechapsabz,4*w,31+2*h,null);
		g2d.drawImage(balabepayinsabz,5*w,31+2*h,null);
		g2d.drawImage(pichpayinberastsabz,6*w,31+2*h,null);
		g2d.drawImage(rastbechapsabz,7*w,31+2*h,null);
		g2d.drawImage(rastbechapsabz,8*w,31+2*h,null);
		g2d.drawImage(rastbechapsabz,9*w,31+2*h,null);
		g2d.drawImage(pichpayinbechapsabz,10*w,31+2*h,null);
		for(int i=11;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+2*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+2*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+3*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+3*h, null);
		g2d.drawImage(pichpayinberastsabz, 2*w, 31+3*h, null);
		g2d.drawImage(chaharrahsabz, 3*w, 31+3*h, null);
		g2d.drawImage(rastbechapsabz,4*w,31+3*h,null);
		g2d.drawImage(pichbalabechapsabz,5*w,31+3*h,null);
		g2d.drawImage(balabepayinsabz,6*w,31+3*h,null);
		g2d.drawImage(pichpayinberastsabz,7*w,31+3*h,null);
		g2d.drawImage(rastbechapsabz,8*w,31+3*h,null);
		g2d.drawImage(pichpayinbechapsabz,9*w,31+3*h,null);
		g2d.drawImage(balabepayinsabz,10*w,31+3*h,null);
		for(int i=11;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+3*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+3*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+4*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+4*h, null);
		g2d.drawImage(balabepayinsabz, 2*w, 31+4*h, null);
		g2d.drawImage(balabepayinsabz, 3*w, 31+4*h, null);
		g2d.drawImage(pichpayinberastsabz,4*w,31+4*h,null);
		g2d.drawImage(rastbechapsabz,5*w,31+4*h,null);
		g2d.drawImage(pichbalabechapsabz,6*w,31+4*h,null);
		g2d.drawImage(pichbalaberastsabz,7*w,31+4*h,null);
		g2d.drawImage(pichpayinbechapsabz,8*w,31+4*h,null);
		g2d.drawImage(balabepayinsabz,9*w,31+4*h,null);
		g2d.drawImage(balabepayinsabz,10*w,31+4*h,null);
		for(int i=11;i<=12;i++)
			g2d.drawImage(chamansabz,i*w,31+4*h,null);
		g2d.drawImage(pichpayinberastsabz,13*w,31+4*h,null);
		g2d.drawImage(rastbechapsabz,14*w,31+4*h,null);
		g2d.drawImage(pichpayinbechapsabz,15*w,31+4*h,null);
		for(int i=16;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+4*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+4*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+5*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+5*h, null);
		g2d.drawImage(pichbalaberastsabz, 2*w, 31+5*h, null);
		g2d.drawImage(pichbalabechapsabz, 3*w, 31+5*h, null);
		g2d.drawImage(balabepayinsabz,4*w,31+5*h,null);
		g2d.drawImage(pichpayinberastsabz,5*w,31+5*h,null);
		g2d.drawImage(rastbechapsabz,6*w,31+5*h,null);
		g2d.drawImage(rastbechapsabz,7*w,31+5*h,null);
		g2d.drawImage(serahbechapsabz,8*w,31+5*h,null);
		g2d.drawImage(balabepayinsabz,9*w,31+5*h,null);
		g2d.drawImage(balabepayinsabz,10*w,31+5*h,null);
		for(int i=11;i<=12;i++)
			g2d.drawImage(chamansabz,i*w,31+5*h,null);
		g2d.drawImage(balabepayinsabz,13*w,31+5*h,null);
		g2d.drawImage(chamansabz,14*w,31+5*h,null);
		g2d.drawImage(balabepayinsabz,15*w,31+5*h,null);
		for(int i=16;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+5*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+5*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+6*h, null);
		g2d.drawImage(serahberastsabz, w, 31+6*h, null);
		g2d.drawImage(pichpayinbechapsabz, 2*w, 31+6*h, null);
		g2d.drawImage(pichpayinberastsabz, 3*w, 31+6*h, null);
		g2d.drawImage(pichbalabechapsabz,4*w,31+6*h,null);
		g2d.drawImage(pichbalaberastsabz,5*w,31+6*h,null);
		g2d.drawImage(rastbechapsabz,6*w,31+6*h,null);
		g2d.drawImage(rastbechapsabz,7*w,31+6*h,null);
		g2d.drawImage(pichbalabechapsabz,8*w,31+6*h,null);
		g2d.drawImage(balabepayinsabz,9*w,31+6*h,null);
		g2d.drawImage(balabepayinsabz,10*w,31+6*h,null);
		for(int i=11;i<=12;i++)
			g2d.drawImage(chamansabz,i*w,31+6*h,null);
		g2d.drawImage(pichbalaberastsabz,13*w,31+6*h,null);
		g2d.drawImage(serahbepayinsabz,14*w,31+6*h,null);
		g2d.drawImage(pichbalabechapsabz,15*w,31+6*h,null);
		for(int i=16;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+6*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+6*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+7*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+7*h, null);
		g2d.drawImage(balabepayinsabz, 2*w, 31+7*h, null);
		g2d.drawImage(balabepayinsabz, 3*w, 31+7*h, null);
		g2d.drawImage(pichpayinberastsabz,4*w,31+7*h,null);
		g2d.drawImage(rastbechapsabz,5*w,31+7*h,null);
		g2d.drawImage(rastbechapsabz,6*w,31+7*h,null);
		g2d.drawImage(rastbechapsabz,7*w,31+7*h,null);
		g2d.drawImage(rastbechapsabz,8*w,31+7*h,null);
		g2d.drawImage(pichbalabechapsabz,9*w,31+7*h,null);
		g2d.drawImage(balabepayinsabz,10*w,31+7*h,null);
		for(int i=11;i<=13;i++)
			g2d.drawImage(chamansabz,i*w,31+7*h,null);
		g2d.drawImage(balabepayinsabz,14*w,31+7*h,null);
		for(int i=15;i<=18;i++)
			g2d.drawImage(chamansabz,i*w,31+7*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+7*h,null);


		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+8*h, null);
		g2d.drawImage(balabepayinsabz, w, 31+8*h, null);
		g2d.drawImage(pichbalaberastsabz, 2*w, 31+8*h, null);
		g2d.drawImage(pichbalabechapsabz, 3*w, 31+8*h, null);
		g2d.drawImage(pichbalaberastsabz,4*w,31+8*h,null);
		g2d.drawImage(rastbechapsabz,5*w,31+8*h,null);
		g2d.drawImage(rastbechapsabz,6*w,31+8*h,null);
		g2d.drawImage(rastbechapsabz,7*w,31+8*h,null);
		g2d.drawImage(rastbechapsabz,8*w,31+8*h,null);
		g2d.drawImage(rastbechapsabz,9*w,31+8*h,null);
		g2d.drawImage(serahbechapsabz,10*w,31+8*h,null);
		for(int i=11;i<=13;i++)
			g2d.drawImage(chamansabz,i*w,31+8*h,null);
		g2d.drawImage(pichbalaberastsabz,14*w,31+8*h,null);
		for(int i=15;i<=17;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+8*h,null);
		g2d.drawImage(pichpayinbechapsabz,18*w,31+8*h,null);
		g2d.drawImage(balabepayinsabz,19*w,31+8*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(balabepayinsabz, 0, 31+9*h, null);
		g2d.drawImage(pichbalaberastsabz, w, 31+9*h, null);
		for(int i=2;i<=9;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+9*h,null);
		g2d.drawImage(chaharrahsabz, 10*w, 31+9*h, null);
		for(int i=11;i<=17;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+9*h,null);
		g2d.drawImage(pichbalabechapsabz, 18*w, 31+9*h, null);
		g2d.drawImage(balabepayinsabz,19*w,31+9*h,null);

		////////////////////////////////////////////////////////////////////////////

		g2d.drawImage(pichbalaberastsabz, 0, 31+10*h, null);
		for(int i=1;i<=9;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+10*h,null);
		g2d.drawImage(serahbebalasabz, 10*w, 31+10*h, null);
		for(int i=11;i<=18;i++)
			g2d.drawImage(rastbechapsabz,i*w,31+10*h,null);
		g2d.drawImage(pichbalabechapsabz,19*w,31+10*h,null);


	}
}
