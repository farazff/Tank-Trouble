package Game;

import java.util.ArrayList;
import java.util.Random;

public class Prizes implements Runnable
{

    private ArrayList<Prize> prizes = new ArrayList<>();

    public void createNewPrize() throws InterruptedException {
        Thread.sleep(1000);
        Random random = new Random();

        int x = random.nextInt(720*16/9);
        int y = random.nextInt(720);

        Prize prize = new Prize("health",x,y);

        prizes.add(prize);
    }

    public ArrayList<Prize> getPrizes()
    {
        return prizes;
    }

    @Override
    public void run()
    {
        for(int i=0;;i++)
        {
            try
            {
                createNewPrize();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
