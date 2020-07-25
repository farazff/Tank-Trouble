package Game;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntelligentTank extends Tank
{
    private ArrayList<SignalBullet> signalBullets;
    private boolean timeToSignal;

    public IntelligentTank (ArrayList<Bullet> bullets, ArrayList<Wall> walls, ArrayList<Tank> tanks) {
        super (bullets, walls, tanks);
        timeToSignal = true;
    }

    public void sendSignals ()
    {
        Iterator<Tank> tankIterator = getTanks ().iterator ();
        ExecutorService executorService = Executors.newCachedThreadPool ();
        while (tankIterator.hasNext ())
        {
            Tank tank = tankIterator.next ();
            if (!(tank instanceof IntelligentTank))
            {
                // executorService.execute (new SignalBullet ());
                // TODO : send signal to enemy tank
            }

        }
    }

    @Override
    public void update () {
        if (timeToSignal)
        {
            sendSignals ();
            timeToSignal = false;

            new Thread (new Runnable () {
                @Override
                public void run () {
                    try {
                        Thread.sleep (5000);
                        timeToSignal = true;
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace ();
                    }
                }
            }).start ();
        }
        else
        {
            //TODO : AI move
        }

    }

    @Override
    public KeyHandler getKeyHandler () {
        return new KeyHandler ();
    }

    protected class KeyHandler extends Tank.KeyHandler {

        @Override
        public void keyPressed (KeyEvent e) {

        }

        @Override
        public void keyReleased (KeyEvent e) {

        }
    }
}
