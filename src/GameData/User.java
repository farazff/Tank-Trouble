package GameData;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable
{
    private char[] password;
    private String userName;
    private long signedUpTime;
    private int score;
    private int rank;
    private int defaultTankStamina;
    private int defaultWallStamina;
    private int defaultCanonPower;


    public User (String userName, char[] password)
    {
        this.password = password;
        this.userName = userName;
        signedUpTime = System.currentTimeMillis ();
        this.score = 0;
        rank = -1;
    }


    public void setDefaultWallStamina (int defaultWallStamina) {
        this.defaultWallStamina = defaultWallStamina;
    }

    public void setDefaultCanonPower (int defaultCanonPower) {
        this.defaultCanonPower = defaultCanonPower;
    }

    public void setDefaultTankStamina (int defaultTankStamina) {
        this.defaultTankStamina = defaultTankStamina;
    }

    public int getDefaultCanonPower () {
        return defaultCanonPower;
    }

    public int getDefaultTankStamina () {
        return defaultTankStamina;
    }

    public int getDefaultWallStamina () {
        return defaultWallStamina;
    }

    public void setRank (int rank) {
        this.rank = rank;
    }

    public void setScore (int score) {
        this.score = score;
    }

    public long getSignedUpTime () {
        return signedUpTime;
    }

    public int getScore () {
        return score;
    }

    public int getRank () {
        return rank;
    }

    public boolean isSame (String userName, char[] password)
    {
        return Arrays.equals (password,this.password) &&
                userName.equals (this.userName);
    }

    @Override
    public String toString () {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isSame (user.userName, user.password);
    }

    @Override
    public int hashCode () {
        int result = Objects.hash (userName);
        result = 31 * result + Arrays.hashCode (password);
        return result;
    }
}
