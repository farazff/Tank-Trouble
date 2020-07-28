package SocketConnectionForTankServer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveTranslator
{
    private String commandString;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean shot;
    private KeyListener keyListener;

    public MoveTranslator ()
    {
        keyDOWN = false;
        keyLEFT = false;
        keyUP = false;
        keyRIGHT = false;
        shot = false;
        keyListener = new KeyHandler ();
    }

    public KeyListener getKeyListener () {
        return keyListener;
    }

    public String getCommandString () {
        return commandString;
    }

    private void translate ()
    {
        StringBuilder stringBuilder = new StringBuilder ();

        if (keyUP)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        stringBuilder.append ("\n");

        if (keyDOWN)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        stringBuilder.append ("\n");

        if (keyLEFT)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        stringBuilder.append ("\n");

        if (keyRIGHT)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        stringBuilder.append ("\n");

        if (shot)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        commandString = stringBuilder.toString ();
    }


    private class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed (KeyEvent e) {
                switch (e.getKeyCode ()) {
                    case KeyEvent.VK_DOWN:
                        keyDOWN = true;
                        break;
                    case KeyEvent.VK_UP:
                        keyUP = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        keyLEFT = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        keyRIGHT = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        shot = true;
                        break;
                }
            translate ();
            }

        @Override
        public void keyReleased (KeyEvent e) {
                switch (e.getKeyCode ()) {
                    case KeyEvent.VK_UP:
                        keyUP = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        keyDOWN = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        keyRIGHT = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        keyLEFT = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        shot = false;
                        break;
                }
            translate ();
            }

        }
}

