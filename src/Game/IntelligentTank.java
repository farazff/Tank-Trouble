package Game;



import GUI.Music;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntelligentTank extends Tank
{
    private ArrayList<SignalBullet> signalBullets;
    private Object target;
    private boolean timeToSignal;
    private boolean timeToInterrupt;
    private boolean timeToAct;

    public IntelligentTank (ArrayList<Bullet> bullets, ArrayList<Wall> walls, ArrayList<Tank> tanks , Prizes prizes)
    {
        super (bullets, walls, tanks , prizes);
        timeToSignal = true;
        timeToInterrupt = false;
        timeToAct = false;
        target = null;
    }

    public ArrayList<SignalBullet> getSignalBullets () {
        return signalBullets;
    }

    public void sendSignals ()
    {
        Iterator<Tank> tankIterator = getTanks ().iterator ();
        ExecutorService executorService = Executors.newCachedThreadPool ();
        signalBullets = new ArrayList<> ();
        while (tankIterator.hasNext ())
        {
            Tank tank = tankIterator.next ();
            if (!(tank instanceof IntelligentTank))
            {
                SignalBullet signalBullet = new SignalBullet (getCanonStartX (),getCanonStartY (),
                        findDegree (tank), System.currentTimeMillis (),getWalls (),
                        getTanks (),this);
                signalBullets.add (signalBullet);
                 executorService.execute (signalBullet);
            }
        }
        executorService.shutdown ();
    }

    private double findDegree (Object object)
    {
        if (object == null)
            return 45;
        int x1;
        int y1;
        int x2;
        int y2;

        if (object instanceof Tank)
        {
            Tank enemyTank = (Tank) object;
            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = enemyTank.getCenterX ();
            y2 = enemyTank.getCenterY ();
        } else if (object instanceof Wall)
        {
            Wall wall = (Wall) object;

            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = wall.getCenterX ();
            y2 = wall.getCenterY ();
        } else if (object instanceof NullTarget)
        {

            NullTarget nullTarget = (NullTarget) object;

            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = nullTarget.getX ();
            y2 = nullTarget.getY ();
        } else
        {
            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = getCenterX ();
            y2 = getCenterY ();
        }
        if (x2 == x1)
        {
            if (y2 > y1) {
                return  90;
            }
            else if (y1 > y2) {
                return  270;
            }
        }

        if (y1 == y2)
        {
            if (x2 > x1) {
                return 0;
            }
            else if (x1 > x2) {
                return 180;
            }
        }

        double degree;
        double m = Math.abs ((1.0 * (y2 - y1)) / (x2 - x1));

        degree = Math.atan (m);

        if (y2 > y1)
        {
            if (x2 > x1)
                return degree;
            else
                return 180 - degree;

        }
        else if (y1 > y2)
        {
            if (x2 > x1)
                return 360 -degree;
            else
                return 180 + degree;
        }
        return 45;
    }

    private double findDistance (Object object)
    {
        if (object == null)
            return 0;
        int x1;
        int y1;
        int x2;
        int y2;

        if (object instanceof Tank)
        {
            Tank enemyTank = (Tank) object;
            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = enemyTank.getCenterX ();
            y2 = enemyTank.getCenterY ();
        } else if (object instanceof Wall)
        {
            Wall wall = (Wall) object;

            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = wall.getCenterX ();
            y2 = wall.getCenterY ();
        } else if (object instanceof NullTarget)
        {

            NullTarget nullTarget = (NullTarget) object;

            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = nullTarget.getX ();
            y2 = nullTarget.getY ();
        } else
        {
            x1 = getCenterX ();
            y1 = getCenterY ();

            x2 = getCenterX ();
            y2 = getCenterY ();
        }

        return Math.sqrt (Math.pow ((x2 - x1),2) + Math.pow ((y2 - y1),2));
    }

    private void analyseReceivedData ()
    {
        ArrayList<Wall> normalWalls = new ArrayList<> ();
        ArrayList<Wall> destructiveWalls = new ArrayList<> ();
        ArrayList<Tank> targetTanks = new ArrayList<> ();
        ArrayList<NullTarget> nullTargets = new ArrayList<> ();
        for (SignalBullet signalBullet : getSignalBullets ())
        {
            if (signalBullet.receiveData () == null) {
                nullTargets.add (new NullTarget (signalBullet.getCenterX (),
                        signalBullet.getCenterY ()));
            }
            else if (signalBullet.receiveData () instanceof Tank)
            {
                targetTanks.add ((Tank) signalBullet.receiveData ());
            } else if (signalBullet.receiveData () instanceof Wall){
                Wall wall = (Wall) signalBullet.receiveData ();
                if (wall.isDestructible ())
                    destructiveWalls.add (wall);
                else
                    normalWalls.add (wall);
            }
        }
        double dis = 0;
        int i = 0;
        if (targetTanks.size () > 0)
        {
            for (Tank tank : targetTanks)
            {
                if (i == 0)
                {
                    target = tank;
                    dis = findDistance (tank);
                }
                else
                {
                    if (findDistance (tank) < dis)
                    {
                        target = tank;
                        dis = findDistance (tank);
                    }
                }
                i++;
            }

        } else if (destructiveWalls.size () > 0)
        {
            for (Wall wall : destructiveWalls)
            {
                if (i == 0)
                {
                    target = wall;
                    dis = findDistance (wall);
                }
                else
                {
                    if (findDistance (wall) < dis)
                    {
                        target = wall;
                        dis = findDistance (wall);
                    }
                }
                i++;
            }
        } else if (nullTargets.size () > 0)
        {
            for (NullTarget nullTarget : nullTargets)
            {
                if (i == 0)
                {
                    target = nullTarget;
                    dis = findDistance (nullTarget);
                }
                else
                {
                    if (findDistance (nullTarget) < dis)
                    {
                        target = nullTarget;
                        dis = findDistance (nullTarget);
                    }
                }
                i++;
            }

        } else
        {
            for (Wall wall : normalWalls)
            {
                if (i == 0)
                {
                    target = wall;
                    dis = findDistance (wall);
                }
                else
                {
                    if (findDistance (wall) > dis)
                    {
                        target = wall;
                        dis = findDistance (wall);
                    }
                }
                i++;
            }
        }
    }
    @Override
    public void update () {
        if (timeToSignal)
        {
            sendSignals ();
            timeToSignal = false;
            timeToInterrupt = true;
            timeToAct = false;
            new Thread (new Runnable () {
                @Override
                public void run () {
                    try {
                        Thread.sleep (2200);
                        analyseReceivedData ();
                        timeToSignal = false;
                        timeToInterrupt = false;
                        timeToAct = true;
                        Thread.sleep (4000);
                        timeToAct = false;
                        timeToInterrupt = false;
                        timeToSignal = true;
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace ();
                    }
                }
            }).start ();
        }
        else if (timeToInterrupt)
        {
            ExecutorService executorService = Executors.newCachedThreadPool ();
            for (SignalBullet signalBullet : getSignalBullets ())
            {
                if (!signalBullet.hasExpired ())
                    executorService.execute (signalBullet);
            }
            executorService.shutdown ();
            while (!executorService.isTerminated ())
            {
                try {
                    Thread.sleep (1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace ();
                }
            }

        } else if (timeToAct)
        {
            if (getDegree () < findDegree (target))
            {
                setDegree (getDegree () + 10);
            } else if (getDegree () > findDegree (target))
            {
                setDegree (getDegree () - 10);
            } else
            {
                if (target instanceof Tank ||
                        (target instanceof Wall && ((Wall)target).isDestructible ()))
                {

                    if (isCanShot ()) {
                        Music music = new Music ();
                        music.setFilePath ("Files/Sounds/Bullet.au", false);
                        music.execute ();

                        getBullets ().add (new Bullet (getCanonStartX (), getCanonStartY (),
                                getDegree (), System.currentTimeMillis (), getWalls (), getTanks ()));
                        setCanShot (false);
                        setShot (true);
                        new Thread (new Runnable () {
                            @Override
                            public void run () {
                                try {
                                    Thread.sleep (100);
                                    setShot (false);
                                } catch (InterruptedException e) {
                                    e.printStackTrace ();
                                }
                            }
                        }).start ();
                        new Thread (new Runnable () {
                            @Override
                            public void run () {
                                try {
                                    Thread.sleep (500);
                                    setCanShot (true);
                                } catch (InterruptedException e) {
                                    e.printStackTrace ();
                                }
                            }
                        }).start ();
                    }
                } else if (target instanceof Wall && !((Wall)target).isDestructible ())
                {
                    // TODO : Move to Wall

                } else if (target instanceof NullTarget)
                {
                    // TODO : Move to Null target
                }
            }

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

    private static class NullTarget
    {
        private int x;
        private int y;

        public NullTarget (int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getY () {
            return y;
        }

        public int getX () {
            return x;
        }
    }
}
