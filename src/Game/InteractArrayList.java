package Game;

import java.util.ArrayList;

/**
 * this class will reduce concurrent modification
 * @param <E> any Thing
 */
public class InteractArrayList <E> extends ArrayList<E>
{

    private boolean iterate = false;


    @Override
    public boolean add (E e) {

        while (iterate)
        {
            try{
                Thread.sleep (10);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace ();
            }
        }
        return super.add (e);
    }

    /**
     * sets Iterate that means now the list is iterating
     * @param iterate iterate
     */
    public void setIterate (boolean iterate) {
        this.iterate = iterate;
    }
}
