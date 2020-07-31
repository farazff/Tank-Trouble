package MultiGame;


import MultiGame.Status.GameStatus;

import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;


public class MultiGameLoop implements Runnable
{

    public static final int FPS = 54;

    private MoveTranslator moveTranslator;
    private MultiGameFrame canvas;
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

        try(Socket client = new Socket (ip,port))
        {

            OutputStream outputStream = client.getOutputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());

            while(true)
            {
                long start = System.currentTimeMillis();

                System.out.println("Connected to server.");

                String temp = moveTranslator.getCommandString();
                System.out.println(temp);
                outputStream.write(temp.getBytes());
                GameStatus status = (GameStatus) objectInputStream.readObject();

                //canvas.render(bufferedImage);
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

            }

        }
        catch (ClassNotFoundException e)
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
