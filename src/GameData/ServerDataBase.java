package GameData;

import java.util.ArrayList;
import java.util.Objects;

public class ServerDataBase
{
    ArrayList<Server> servers;

    public ServerDataBase (ArrayList<Server> servers)
    {
        this.servers = Objects.requireNonNullElseGet (servers, ArrayList::new);
    }

    public ArrayList<Server> getServers () {
        return servers;
    }

    public void addNewServer (Server server)
    {
        servers.add (server);
    }

    public void removeServer (Server server)
    {
        servers.remove (server);
    }
}
