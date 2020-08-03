package GameData;

import java.io.Serializable;

public class RememberMeData implements Serializable
{
    private boolean tickOn;
    private String userName;
    private char[] password;

    public RememberMeData ()
    {
        this.tickOn = false;
    }

    public void set (String userName, char[] password)
    {
        this.userName = userName;
        this.password = password;
    }

    public void setTickOn (boolean tickOn) {
        this.tickOn = tickOn;
    }

    public String getUserName () {
        return userName;
    }

    public char[] getPassword () {
        return password;
    }

    public boolean isTickOn () {
        return tickOn;
    }
}