/*** In The Name of Allah ***/
package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

			for (Tank tank : state.getTanks ())
			{
				BufferedImage image = ImageIO.read (new File (tank.getImageAddress ()));

				g2d.drawImage (rotateImage(image,tank.getDegree()-45),
						tank.getLocX() ,tank.getLocY(),null);

				if (tank.isShot ())
				{
					BufferedImage image1 = rotateImageBullet (ImageIO.read
									(new File (tank.getFireImageAddress ()))
							,tank.getDegree () - 90);

					g2d.drawImage (image1,
							tank.getCanonStartX () - image1.getWidth () / 2 + 3 ,
							tank.getCanonStartY () - image1.getHeight () / 2 + 2
							, null);
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
		// Print user guide
		String userGuide
				= "Use the MOUSE or ARROW KEYS to move the BALL. "
				+ "Press ESCAPE to end the game.";
		g2d.setFont(g2d.getFont().deriveFont(18.0f));
		g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
		// Draw GAME OVER
		if (state.gameOver)
		{
			String str = "GAME OVER";
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
		}
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
}
