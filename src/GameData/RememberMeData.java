package GameData;

import java.io.Serializable;

/**
 * this class built for Remember me function
 */
public class RememberMeData implements Serializable
{
    private boolean tickOn;
    private String userName;
    private char[] password;

    /**
     * creates a remember me data
     */
    public RememberMeData ()
    {
        this.tickOn = false;
    }

    /**
     * sets username and password
     * @param userName userName
     * @param password password
     */
    public void set (String userName, char[] password)
    {
        this.userName = userName;
        this.password = password;
    }

    /**
     * sets tick for remember Me
     * @param tickOn tickOn
     */
    public void setTickOn (boolean tickOn) {
        this.tickOn = tickOn;
    }

    /**
     *
     * @return username
     */
    public String getUserName () {
        return userName;
    }

    /**
     *
     * @return passWord
     */
    public char[] getPassword () {
        return password;
    }

    /**
     *
     * @return isTickOn
     */
    public boolean isTickOn () {
        return tickOn;
    }
}