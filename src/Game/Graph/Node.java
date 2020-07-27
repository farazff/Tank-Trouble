package Game.Graph;

import java.util.ArrayList;

public class Node
{
    private boolean seen;
    private int name;
    private ArrayList<Node> neighbors;

    public Node(int name)
    {
        seen = false;
        this.name = name;
        neighbors = new ArrayList<>();
    }

    public void see()
    {
        seen = true;
    }

    public void addNeighbors(Node node)
    {
        neighbors.add(node);
    }

    public int getName()
    {
        return name;
    }

    public ArrayList<Node> getNeighbors()
    {
        return neighbors;
    }

    public boolean isSeen()
    {
        return seen;
    }
}