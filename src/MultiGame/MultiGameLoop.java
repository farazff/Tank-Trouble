package MultiGame;


import GameData.User;
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
    private User user;


    public MultiGameLoop(MultiGameFrame frame , JFrame menuFrame)
    {
        moveTranslator = new MoveTranslator ();
        this.menuFrame = menuFrame;
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init(String ip, int port, User user)
    {
        this.port = port;
        this.ip = ip;
        this.user = user;
        canvas.addKeyListener (moveTranslator.getKeyListener ());
    }

    @Override
    public void run()
    {

        try(Socket client = new Socket (ip,port))
        {
            System.out.println("Connected to server.");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream (client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());

            while(true)
            {
                long start = System.currentTimeMillis();


                String temp = moveTranslator.getCommandString();
                objectOutputStream.writeObject (new TransferData (temp,user));

                //System.out.println(temp);

                GameStatus status = (GameStatus) objectInputStream.readObject();
                if(status.getTanks().size()>=1)
                    canvas.render(status);


                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

            }

        }
        catch (ClassNotFoundException | InterruptedException e)
        {
            e.printStackTrace ();
        } catch (IllegalArgumentException e)
        {
            System.err.println ("Some went Wrong in start");
        }
        catch (ConnectException e)
        {
            System.err.println ("Couldn't connect to ServerInformation");
        }
        catch (SocketException e)
        {
            System.err.println ("ServerInformation Not Responding");
        } catch (IOException e)
        {
            System.err.println ("Some went Wrong");
        }

    }
}
