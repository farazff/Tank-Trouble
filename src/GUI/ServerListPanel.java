package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ServerListPanel extends ListPanel
{
    private ArrayList<JPanel> serverPanels;


    public ServerListPanel (ArrayList<ButtonPanel> serverPanels)
    {
        super(serverPanels);
    }



}
