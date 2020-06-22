package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * this class made for adding a component to gridBagLayout
 *
 * @author Amir Naziri
 */
public class GridBagSetter
{
    /**
     * add a component to gridBag layout in base frame
     * @param component component
     * @param row row
     * @param col col
     * @param width width
     * @param height height
     * @param layout layout
     * @param constraints constrains for layout
     * @param panel base panel
     */
    protected static void addComponent (JComponent component, int row, int col, int width,
                                        int height, GridBagLayout layout,
                                        GridBagConstraints constraints, JPanel panel) {

        if (layout == null || constraints  == null || component == null || panel == null)
            throw new NullPointerException ("inValid input");
        constraints.gridy = row;
        constraints.gridx = col;
        constraints.gridheight = height;
        constraints.gridwidth = width;

        layout.setConstraints (component, constraints);
        panel.add (component);
    }
}
