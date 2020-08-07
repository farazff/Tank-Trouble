package GameData;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is Users Storage
 */
public class UsersStorage implements Serializable
{

    private ArrayList<User> users;
    private boolean isIterate;

    /**
     * creates a User Storage
     */
    public UsersStorage ()
    {
        users = new ArrayList<> ();
        isIterate = false;
    }

    /**
     * sets Users
     * @param users users
     */
    public void setUsers (ArrayList<User> users) {
        this.users = users;
    }

    /**
     * get Users
     * @return users
     */
    public ArrayList<User> getUsers () {
        return users;
    }

    /**
     * add User
     * @param user user
     */
    public void addUser (User user)
    {
        if (user == null)
            return;
        try {
            while (isIterate)
            {
                Thread.sleep (250);
            }

        } catch (InterruptedException e)
        {
            e.printStackTrace ();
        } finally {
            users.add (user);
        }
    }

    /**
     * update users
     * @param user user
     * @return result
     */
    public boolean update (User user)
    {
        isIterate = true;
        for (User user1 : users)
        {
            if (user1.equals (user))
            {
                user1.setRank (user.getRank ());
                user1.setScore (user.getScore ());
                user1.setDefaultCanonPower (user.getDefaultCanonPower ());
                user1.setDefaultTankStamina (user.getDefaultTankStamina ());
                user1.setDefaultWallStamina (user.getDefaultWallStamina ());
                user1.setNumOfMultiGames (user.getNumOfMultiGames ());
                user1.setNumOfSingleGames (user.getNumOfSingleGames ());
                user1.setNumOfWinMultiGames (user.getNumOfWinMultiGames ());
                user1.setNumOfWinSingleGames (user.getNumOfWinSingleGames ());
                user1.getServerInformationStorage ().setServerData
                        (user.getServerInformationStorage ().getServerData ());
                user1.setTankCode (user.getTankCode ());
                isIterate = false;
                return true;

            }
        }
        isIterate = false;
        return false;
    }

    /**
     * get user
     * @param userName userName
     * @param password password
     * @return user
     */
    public User getUser (String userName, char[] password)
    {
        isIterate = true;
        User user1 = new User (userName,password,null);
        for (User user : users)
        {
            if (user.equals (user1))
            {
                isIterate = false;
                return user;
            }
        }
        isIterate = false;
        return null;
    }

    /**
     * has username used
     * @param userName userName
     * @return result
     */
    public boolean hasUserNameUsed (String userName)
    {
        User user = new User (userName,new char[]{'.','.','.'},null);
        isIterate = true;
        for (User user1 : users)
        {
            if (user1.getUserName ().equals (user.getUserName ()))
            {
                isIterate = false;
                return true;
            }
        }
        isIterate = false;
        return false;
    }
}
