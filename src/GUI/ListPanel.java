package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListPanel extends JPanel
{
    private ArrayList<ButtonPanel> data;


    public ListPanel (ArrayList<ButtonPanel> data)
    {
        super();

        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        setBackground (Color.GRAY);
        setBorder (new LineBorder (Color.GRAY,10,true));
        this.data = new ArrayList<> (data);
        MouseHandler mouseHandler = new MouseHandler ();


        for (JPanel da : data)
        {
            add(da);
            da.addMouseListener (mouseHandler);
        }
    }

    public ArrayList<ButtonPanel> getData () {
        return data;
    }

    /**
     * this class handles mouse for components in this panel
     */
    protected class MouseHandler extends MouseAdapter
    {

        @Override
        public void mouseEntered (MouseEvent e) {

            e.getComponent ().setBackground (Color.LIGHT_GRAY);

        }

        @Override
        public void mouseExited (MouseEvent e) {

            e.getComponent ().setBackground (Color.GRAY);

        }

        @Override
        public void mouseClicked (MouseEvent e) {

        }
    }

}
