package Login_SignUp_Logout;



import GameData.User;
import GameData.UsersStorage;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;

/**
 * this class is for login or logout or signUP
 */
public class LogConnector implements Runnable
{

    private String ip;
    private int port;
    private String request;
    private User user;
    private String username;
    private char[] password;
    private String res = "Error";
    private UsersStorage usersStorage;
    private boolean finished = false;

    /**
     * create a UserStorage process
     * @param ip ip
     */
    public LogConnector (String ip)
    {
        this.ip = ip;
        this.port = 6050;
    }

    /**
     * creates a login or signUp process
     * @param ip ip
     * @param username username
     * @param password password
     * @param request request
     */
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

    /**
     * creates a logout process
     * @param ip ip
     * @param request request
     * @param user user
     */
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
                System.out.println ("-> data sent to ServerInformation : " +
                        port + getServerName (port));

                // receive

                in = new ObjectInputStream (connection.getInputStream ());
                user = (User) ((ObjectInputStream) in).readObject ();

                System.out.println ("<- data received from ServerInformation : " +
                        port + getServerName (port));


            } else if (port == 4787)
            {
                // send
                out = new ObjectOutputStream (connection.getOutputStream ());
                ((ObjectOutputStream) out).writeObject (user);
                out.flush ();
                System.out.println ("-> data sent to ServerInformation : " +
                        port + getServerName (port));

                // receive
                in = new DataInputStream (connection.getInputStream ());
                res = ((DataInputStream) in).readUTF ();
                System.out.println ("<- data received from ServerInformation : " +
                        port + getServerName (port));
            } else if (port == 6050)
            {
                // send
                out = new DataOutputStream (connection.getOutputStream ());
                ((DataOutputStream) out).writeUTF ("list");
                out.flush ();
                System.out.println ("-> data sent to ServerInformation : " +
                        port + getServerName (port));

                // receive
                in = new ObjectInputStream (connection.getInputStream ());
                usersStorage = (UsersStorage) ((ObjectInputStream) in).readObject ();
                System.out.println ("<- data received from ServerInformation : " +
                        port + getServerName (port));
            }

        } catch (IllegalArgumentException e)
        {
            System.err.println ("Some went Wrong in start");
        }
        catch (ConnectException e)
        {
            System.err.println ("Couldn't connect to ServerInformation");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        } catch (SocketException e)
        {
            System.err.println ("ServerInformation Not Responding");
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

    /**
     *
     * @return get LoginOrSignUpResult
     */
    public User getLoginOrSignUpResult ()
    {
        if (finished)
        {
            if (port == 8083)
            {
                return user;
            }
            else
                return null;
        }
        else
            return null;
    }

    /**
     *
     * @return get LogoutResult
     */
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

    /**
     *
     * @return userStorage
     */
    public UsersStorage getUsersStorage ()
    {
        if (finished)
        {
            if (port == 6050)
                return usersStorage;
            else
                return null;
        }
        return null;
    }

    /**
     *
     * @return is Finished
     */
    public boolean isFinished () {
        return finished;
    }

    /**
     * gets server's name depend on port
     * @param port port
     * @return name of server
     */
    private String getServerName (int port)
    {
        switch (port)
        {
            case 8083 : return " (Load Server) ";
            case 4787 : return " (Save Server) ";
            case 6050 : return " (List Server) ";
        }
        return "";
    }
}
