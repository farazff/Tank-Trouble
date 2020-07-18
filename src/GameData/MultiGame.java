package GameData;

import java.io.Serializable;

public class MultiGame implements Serializable
{
    private final String name;
    private final GameFinishType gameFinishType;
    private final GameMemberShipType gameMemberShipType;
    private final int numberOfPlayers;
    private final int tankStamina;
    private final int wallStamina;
    private final int canonPower;
    private int onlineUsers;

    public MultiGame (String name, GameFinishType gameFinishType,
                      GameMemberShipType gameMemberShipType, int numberOfPlayers,
                      int tankStamina, int wallStamina, int canonPower)
    {
        this.name = name;
        this.canonPower = canonPower;
        this.gameFinishType = gameFinishType;
        this.gameMemberShipType = gameMemberShipType;
        this.tankStamina = tankStamina;
        this.wallStamina = wallStamina;
        this.numberOfPlayers = numberOfPlayers;
        this.onlineUsers = 0;
    }

    public String getName () {
        return name;
    }

    public void addUser ()
    {
        //TODO : add user
        onlineUsers++;
    }

    public int getOnlineUsers () {
        return onlineUsers;
    }

    public GameFinishType getGameFinishType () {
        return gameFinishType;
    }

    public GameMemberShipType getGameMemberShipType () {
        return gameMemberShipType;
    }

    public int getCanonPower () {
        return canonPower;
    }

    public int getNumberOfPlayers () {
        return numberOfPlayers;
    }

    public int getTankStamina () {
        return tankStamina;
    }

    public int getWallStamina () {
        return wallStamina;
    }
}
