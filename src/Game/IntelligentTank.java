package Game;



import GUI.Music;
import GameData.User;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IntelligentTank extends Tank
{
    private ArrayList<SignalBullet> signalBullets;
    private Object target;
    private boolean timeToSignal;
    private boolean timeToInterrupt;
    private boolean timeToAct;
    private int[] kills;

    public IntelligentTank (ArrayList<Bullet> bullets, ArrayList<Wall> walls, ArrayList<Tank> tanks
            , Prizes prizes , int tankStamina, int canonPower , Maps maps, String imageLoc, User user,int code
    ,int[] kills)
    {
        super (bullets, walls, tanks , prizes,tankStamina,canonPower,maps,imageLoc,user,code,kills);
        timeToSignal = true;
        this.kills = kills;
        timeToInterrupt = false;
        timeToAct = false;
        target = null;
        signalBullets = new ArrayList<> ();
    }

    public ArrayList<SignalBullet> getSignalBullets () {
        return signalBullets;
    }

    public void sendSignals ()
    {
        signalBullets.clear ();
        for (int i = 0; i < 360; i++)
        {
            signalBullets.add (new SignalBullet (getCenterX (),getCenterY (),
                    i, System.currentTimeMillis (),getWalls (),
                    getTanks (),this,getCanonPower(),this.getCode(),kills));
            i += 10;
        }
    }

//    private int[] findXAndY (Object object)
//    {
//        int[] coordinates = new int[4];
//        coordinates[0] = getCenterX ();
//        coordinates[1] = getCenterY ();
//
//        if (object instanceof Tank)
//        {
//            Tank enemyTank = (Tank) object;
//
//            coordinates[2] = enemyTank.getCenterX ();
//            coordinates[3] = enemyTank.getCenterY ();
//        } else if (object instanceof Wall)
//        {
//            Wall wall = (Wall) object;
//
//            coordinates[2] = wall.getCenterX ();
//            coordinates[3] = wall.getCenterY ();
//        } else if (object instanceof NullTarget)
//        {
//
//            NullTarget nullTarget = (NullTarget) object;
//
//            coordinates[2] = nullTarget.getX ();
//            coordinates[3] = nullTarget.getY ();
//        } else
//        {
//            coordinates[2] = getCenterX ();
//            coordinates[3] = getCenterY ();
//        }
//
//        return coordinates;
//    }

    private int findDegree (Object object)
    {
        if (object == null)
            return 45;
        int x1;
        int x2;
        int y1;
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

        int degree;
        double m = Math.abs ((1.0 * (y2 - y1)) / (x2 - x1));

        degree = (int)Math.toDegrees (Math.atan (m));

        switch (degree % 10)
        {
            case 1 : degree -= 1;
                break;
            case 2 : degree -= 2;
                break;
            case 3 : degree -= 3;
                break;
            case 4 : degree -= 4;
                break;
            case 5 : degree += 5;
                break;
            case 6 : degree += 4;
                break;
            case 7 : degree += 3;
                break;
            case 8 : degree += 2;
                break;
            case 9 : degree += 1;
        }




        if (y2 > y1)
        {
            if (x2 > x1)
            {
                //System.out.println (degree % 360);
                return degree % 360;
            }
            else
            {
                //System.out.println ((180 - degree) % 360);
                return (180 - degree) % 360;
            }

        }
        else if (y1 > y2)
        {
            if (x2 > x1)
            {
                //System.out.println ((360 -degree) % 360);
                return (360 -degree) % 360;
            }
            else
            {
                //System.out.println ((180 + degree) % 360);
                return (180 + degree) % 360;
            }
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
    public void update ()  {

        if (timeToSignal)
        {
            sendSignals ();
            //System.out.println ("Signals Sent");
            timeToSignal = false;
            timeToInterrupt = true;
            timeToAct = false;
            new Thread (new Runnable () {
                @Override
                public void run () {
                    try {
                        Thread.sleep (1300);
                        analyseReceivedData ();
                        timeToSignal = false;
                        timeToInterrupt = false;
                        timeToAct = true;
                        Thread.sleep (1300);
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
            try {
                executorService.awaitTermination (3800, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e)
            {
                e.printStackTrace ();
            }

        } else if (timeToAct)
        {

            if (getDegree () < findDegree (target))
            {
                setDegree (getDegree () + 10);
                setDegree (getDegree () % 360);
            } else if (getDegree () > findDegree (target))
            {
                setDegree (getDegree () - 10);
                setDegree (getDegree () % 360);
            }
                if (target instanceof Tank ||
                        (target instanceof Wall && ((Wall)target).isDestructible ()))
                {
                    if ((target instanceof Tank && !((Tank)target).isDestroyed ()) ||
                            (target instanceof Wall && ((Wall)target).isOK ()))
                        if (isCanShot ()) {
                        Music music = new Music ();
                        music.setFilePath ("Files/Sounds/Bullet.au", false);
                        music.execute ();

                            if (getBulletType ().equals ("Laser"))
                            {
                                getBullets ().add (new LaserBullet (getCanonStartX (), getCanonStartY (),
                                        getDegree (), System.currentTimeMillis (), getWalls (), getTanks (),
                                        getCanonPower (),this.getCode(),kills));
                                setBulletType ("Normal");
                            }
                            else
                            {
                                getBullets ().add (new Bullet (getCanonStartX (), getCanonStartY (),
                                        getDegree (), System.currentTimeMillis (), getWalls (), getTanks (),
                                        getCanonPower (),this.getCode(),kills));
                            }
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
                } else if (target instanceof NullTarget ||
                        (target instanceof Wall && !((Wall)target).isDestructible ()))
                {
                    int forX = (int) (6 * Math.cos (Math.toRadians (this.getDegree ())));
                    int forY = (int) (6 * Math.sin (Math.toRadians (this.getDegree ())));

                    if(canMoveForward() && isEmpty(forX,forY,1))
                    {
                        this.addLocX(forX);
                        this.addLocY(forY);
                    }
                    checkPrize ();
                    this.setLocX(Math.max(this.getLocX(), 0));
                    this.setLocX(Math.min(this.getLocX(), GameFrame.GAME_WIDTH - 30));
                    this.setLocY(Math.max(this.getLocY(), 0));
                    this.setLocY(Math.min(this.getLocY(), GameFrame.GAME_HEIGHT - 30));
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
