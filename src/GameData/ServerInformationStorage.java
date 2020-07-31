package GameData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ServerInformationStorage implements Serializable
{
    private ArrayList<ServerInformation> serverData;

    public ServerInformationStorage ()
    {
        this.serverData = new ArrayList<> ();
    }

    public ArrayList<ServerInformation> getServerData () {
        return serverData;
    }

    public void addNewServer (ServerInformation serverInformation)
    {
        this.serverData.add (serverInformation);
    }

    public void removeServer (ServerInformation serverInformation)
    {
        this.serverData.remove (serverInformation);
    }
}
