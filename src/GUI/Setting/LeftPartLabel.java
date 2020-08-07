package GUI.Setting;

import javax.swing.*;
import java.awt.*;

/**
 * creating a group of same JLabels
 */

public class LeftPartLabel extends JLabel
{
    /**
     * creates leftPartLabel
     * @param text text
     * @param FontSize FontSize
     * @param FontColor FontColor
     */
    public LeftPartLabel(String text,int FontSize,Color FontColor)
    {
        super(text);
        this.setFont(new Font("Arial",Font.BOLD,FontSize));
        this.setForeground(FontColor);
    }
}
