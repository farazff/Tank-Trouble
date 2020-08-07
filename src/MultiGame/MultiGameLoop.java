package MultiGame;


import GameData.User;
import MultiGame.Server.ClientHandler;
import MultiGame.Status.GameStatus;
import MultiGame.Status.NullStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * this is Multi Game Loop
 */
public class MultiGameLoop implements Runnable
{

    public static final int FPS = 54;

    private MoveTranslator moveTranslator;
    private MultiGameFrame canvas;
    private JFrame menuFrame;
    private String ip;
    private int port;
    private User user;


    /**
     * creates new Multi game loop
     * @param frame frame
     * @param menuFrame menuFrame
     */
    public MultiGameLoop(MultiGameFrame frame , JFrame menuFrame)
    {
        moveTranslator = new MoveTranslator();
        this.menuFrame = menuFrame;
        canvas = frame;
        WaitingPanel waitingPanel = new WaitingPanel();
        canvas.setContentPane(waitingPanel);
    }

    /**
     * This must be called before the game loop starts.
     * @param user user
     * @param port port
     * @param ip ip
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
            GameStatus status = null;
            boolean gameOver = false;
            System.out.println("Connected to server.");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream (client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());

            while(!gameOver)
            {
                long start = System.currentTimeMillis();

                String temp = moveTranslator.getCommandString();
                objectOutputStream.writeObject (new TransferData (temp,user));

                status = (GameStatus) objectInputStream.readObject();
                if (!(status instanceof NullStatus))
                {
                    canvas.render(status);
                }

                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

                if(status.isGameOverAll())
                {
                    gameOver = true;
                }
            }

            /////////////////////////////////////////////
            ////////////////////////////////////////////

            for(int i=0;i<status.getKills().length;i++)
            {
                if(status.getNames().get(i).equals(user.getUserName()))
                {
                    user.setScore(user.getScore() + status.getKills()[i]);
                    break;
                }
            }

            int flag = 0;
            int max=0;
            for(int i=0;i<status.getKills().length;i++)
            {
                if(status.getKills()[i]>max)
                {
                    max = status.getKills()[i];
                    flag = i;
                }
            }

            if(status.getNames().get(flag).equals(user.getUserName()))
            {
                user.setNumOfWinMultiGames(user.getNumOfWinMultiGames() + 1);
                System.out.println(user.getNumOfWinMultiGames());
            }

            /////////////////////////////////////////////
            ////////////////////////////////////////////

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(4000);
                        canvas.setVisible(false);
                        menuFrame.setVisible(true);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
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
            System.err.println ("Couldn't connect to Server");
        }
        catch (SocketException e)
        {
            System.err.println ("Server Not Responding");
        } catch (IOException e)
        {
            System.err.println ("Some went Wrong");
        }

    }
}
