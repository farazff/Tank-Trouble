package Game;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maps
{
    private ArrayList<Wall> walls;
    private ArrayList< ArrayList<Character> > data = new ArrayList<>();
    private int x = 0;
    private int y = 0;
    private int horizontal = 0; //ofoqi
    private int vertical = 0; //amodi
    private int houseX;
    private int houseY;

    public ArrayList<Wall> getWalls()
    {
        return walls;
    }

    public Maps()
    {
        walls = new ArrayList<>();
        readFromFile();
        x = data.get(0).size();


        if(x%2==1)
            houseX = (x-1)/2;
        else
            houseX = x/2;

        horizontal = (16*720/9)/(houseX);


        if(y%2==1)
            houseY = (y-1)/2;
        else
            houseY = y/2;

        vertical = (720)/(houseY);

        createWalls();

    }

    public void createWalls()
    {
        for(int j=0;j<data.size();j++)
        {
            if(j%2==0)
            {
                for(int i=1;i<x;i+=2)
                {
                    if(data.get(j).get(i) == '1')
                    {
                        walls.add(new Wall(horizontal * (i - 1)/2, vertical * (j)/2, horizontal, "H"));
                    }
                }
            }
            if(j%2==1)
            {
                for(int i=0;i<x;i+=2)
                {
                    if(data.get(j).get(i) == '1')
                    {
                        walls.add(new Wall(horizontal * (i)/2, vertical * (j-1)/2, vertical, "V"));
                    }
                }
            }
        }


    }


    public void readFromFile()
    {
        File map = new File("Files/Maps/map1.txt");
        try
        {
            FileReader reader = new FileReader(map);
            data.add(new ArrayList<Character>());
            while(reader.ready())
            {
                int temp = reader.read();
                if((char)temp == '1' || (char)temp == '0')
                {
                    data.get(y).add((char)temp);
                }
                if((char)temp == '\n')
                {
                    data.add(new ArrayList<Character>());
                    y++;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
