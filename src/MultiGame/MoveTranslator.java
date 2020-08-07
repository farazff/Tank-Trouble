package MultiGame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import MultiGame.*;

/**
 * this class is for change keyBoard to a String like "00100"
 * first bit is KeyUp
 * second bit is keyDown
 * third bit is left
 * 4th bit is right
 * 5th bit is shot
 */
public class MoveTranslator
{
    private String commandString;
    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean shot;
    private KeyListener keyListener;

    /**
     * creates a new MoveTranslator
     */
    public MoveTranslator ()
    {
        commandString = "00000";
        keyDOWN = false;
        keyLEFT = false;
        keyUP = false;
        keyRIGHT = false;
        shot = false;
        keyListener = new KeyHandler ();
    }

    /**
     *
     * @return KeyListener
     */
    public KeyListener getKeyListener () {
        return keyListener;
    }

    /**
     *
     * @return CommandString
     */
    public String getCommandString () {
        return commandString;
    }

    /**
     * translate key board to String
     */
    private void translate ()
    {
        StringBuilder stringBuilder = new StringBuilder ();

        if (keyUP)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");



        if (keyDOWN)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");



        if (keyLEFT)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");



        if (keyRIGHT)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");



        if (shot)
            stringBuilder.append ("1");
        else
            stringBuilder.append ("0");

        commandString = stringBuilder.toString ();
    }

    /**
     * this class catch keyboard
     */
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

