package GameData;

import java.io.Serializable;
import java.util.ArrayList;

public class Server implements Serializable
{
    private final String url;
    private static final int maxCapacity = 100;
    private final ArrayList<MultiGame> multiGames;

    public Server (String url, ArrayList<MultiGame> multiGames)
    {
        this.url = url;

        if (multiGames != null)
            this.multiGames = new ArrayList<> (multiGames);
        else
            this.multiGames = new ArrayList<> ();
    }

    public String getUrl () {
        return url;
    }

    public void addGame (MultiGame multiGame)
    {
        multiGames.add (multiGame);
    }

    public void removeMultiGame (int index)
    {
        try {
            multiGames.remove (index);
        } catch (IndexOutOfBoundsException e)
        {
            System.out.println ("Some Thing went Wrong in removing indexed multi game");
        }
    }

    public ArrayList<MultiGame> getMultiGames () {
        return multiGames;
    }

    public MultiGame getMultiGame (int index)
    {
        try {
            return multiGames.get (index);
        } catch (IndexOutOfBoundsException e)
        {
            System.out.println ("Some Thing went Wrong in getting indexed multi game");
            return null;
        }
    }
}
