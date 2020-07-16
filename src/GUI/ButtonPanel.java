package GUI;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {




    public ButtonPanel ()
    {
        super();
        setLayout (new FlowLayout (FlowLayout.LEFT));
    }


    @Override
    public Dimension getMinimumSize () {
        return new Dimension (450,34);
    }

    @Override
    public Dimension getMaximumSize () {
        return new Dimension (1800,34);
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (450,34);
    }
}
