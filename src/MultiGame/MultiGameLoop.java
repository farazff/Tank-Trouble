package MultiGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 *
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class MultiGameLoop implements Runnable
{

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 54;

    private MoveTranslator moveTranslator;
    private MultiGameFrame canvas;
    private BufferedImage bufferedImage;
    private JFrame menuFrame;
    private String ip;
    private int port;


    public MultiGameLoop(MultiGameFrame frame , JFrame menuFrame)
    {
        moveTranslator = new MoveTranslator ();
        this.menuFrame = menuFrame;
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init(String ip, int port)
    {
        this.port = port;
        this.ip = ip;
        canvas.addKeyListener (moveTranslator.getKeyListener ());
    }

    @Override
    public void run()
    {
        DataOutputStream out = null;
        InputStream in = null;
        try (Socket socket = new Socket (ip,port)){
            out = new DataOutputStream (socket.getOutputStream ());
            in = socket.getInputStream ();

            while(true)
            {
                long start = System.currentTimeMillis();
                //
                out.writeUTF (moveTranslator.getCommandString ());
                out.flush ();
                bufferedImage = ImageIO.read (in);
                canvas.render(bufferedImage);

                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

            }

        } catch (InterruptedException e)
        {
            e.printStackTrace ();
        } catch (IllegalArgumentException e)
        {
            System.err.println ("Some went Wrong in start");
        }
        catch (ConnectException e)
        {
            System.err.println ("Couldn't connect to Server");
        }
        catch (SocketException e)
        {
            System.err.println ("Server Not Responding");
        } catch (IOException e)
        {
            System.err.println ("Some went Wrong");
        } finally {
            try {
                if (out != null)
                    out.close ();
            }
            catch (SocketException ignore)
            {
            }
            catch (IOException e)
            {
                System.err.println ("Some thing went wrong in closing ServerOutputStream");
            }
            try {
                if (in != null)
                    in.close ();
            }
            catch (SocketException ignore)
            {
            }
            catch (IOException e)
            {
                System.err.println ("Some thing went wrong in closing ServerInputStream");
            }
        }


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                    canvas.setVisible(false);
                    menuFrame.setVisible(true);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        try
        {
            canvas.render(bufferedImage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
