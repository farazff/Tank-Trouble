package Login_SignUp_Logout;


import GameData.NullUser;
import GameData.User;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;

public class LogConnector implements Runnable
{

    private String ip;
    private int port;
    private String request;
    private User user;
    private String username;
    private char[] password;
    private String res;
    private boolean finished = false;


    public LogConnector (String ip, String username, char[] password, String request)
    {
        if (request == null || request.equals ("Logout"))
            throw new InputMismatchException ("you should use this only for login or signUp");
        this.ip = ip;
        this.port = 8083;
        this.request = request;
        this.username = username;
        this.password = password;
    }

    public LogConnector (String ip, String request, User user)
    {
        if (request == null || request.equals ("SignIn") || request.equals ("Login"))
            throw new InputMismatchException ("you should use this only for logout");
        this.ip = ip;
        this.port = 4787;
        this.request = request;
        this.user = user;
    }

    @Override
    public void run () {

        InputStream in = null;
        OutputStream out = null;
        try (Socket connection = new Socket (ip,port))
        {
            if (port == 8083)
            {
                StringBuilder pass = new StringBuilder ();
                for (char c : password)
                    pass.append (c);
                // send
                String data = request + " " + username + " " + pass.toString ();
                out = new DataOutputStream (connection.getOutputStream ());
                ((DataOutputStream) out).writeUTF (data);
                out.flush ();
                System.out.println ("-> data sent to Server : " +
                        port + ((port == 8083)? " (Load Server) " :
                        " (Save Server) "));

                // receive

                in = new ObjectInputStream (connection.getInputStream ());
                user = (User) ((ObjectInputStream) in).readObject ();

                String[] split = request.split (" ");

                System.out.println ("<- data received from Server : " +
                        port + ((port == 8083)? " (Load Server) " :
                        " (Save Server) "));


            } else if (port == 4787)
            {
                // send
                out = new ObjectOutputStream (connection.getOutputStream ());
                ((ObjectOutputStream) out).writeObject (user);
                out.flush ();
                System.out.println ("-> data sent to Server : " +
                        port + ((port == 8083)? " (Load Server) " :
                        " (Save Server) "));

                // receive
                in = new DataInputStream (connection.getInputStream ());
                res = ((DataInputStream) in).readUTF ();
                System.out.println ("<- data received from Server : " +
                        port + ((port == 8083)? " (Load Server) " :
                        " (Save Server) "));
            }

        } catch (IllegalArgumentException e)
        {
            System.err.println ("Some went Wrong in start");
        }
        catch (ConnectException e)
        {
            System.err.println ("Couldn't connect to Server");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        } catch (SocketException e)
        {
            System.err.println ("Server Not Responding");
        } catch (IOException e)
        {
            System.err.println ("Some went Wrong");
        } finally {
            finished = true;
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
        }
    }

    public User getLoginOrSignUpResult ()
    {
        if (finished)
        {
            if (port == 8083)
            {
                if (user instanceof NullUser)
                    return null;
                else
                    return user;
            }
            else
                return null;
        }
        else
            return null;
    }

    public String getLogoutResult ()
    {
        if (finished)
        {
            if (port == 4787)
                return res;
            else
                return null;
        }
        else
            return null;

    }

    public boolean isFinished () {
        return finished;
    }
}
