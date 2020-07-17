package GameData;

import java.io.Serializable;

public class NewMultiGameData implements Serializable
{
    private final String name;
    private final GameFinishType gameFinishType;
    private final GameMemberShipType gameMemberShipType;
    private final int numberOfPlayers;
    private final int tankStamina;
    private final int wallStamina;
    private final int canonPower;

    public NewMultiGameData (String name, GameFinishType gameFinishType,
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
    }

    public String getName () {
        return name;
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
