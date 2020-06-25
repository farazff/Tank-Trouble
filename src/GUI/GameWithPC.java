package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GameWithPC extends JPanel
{




    public GameWithPC()
    {

        setLayout(new GridLayout(7,1,5,5));
        setBackground(Color.GRAY);
        setOpaque(true);

        JLabel header = new JLabel(" Single mode ");
        header.setFont (new Font ("DialogInput",Font.BOLD,23));
        header.setHorizontalAlignment(JLabel.CENTER);

        add(header);
        
        JLabel titleMode = new JLabel(" Game Mode: ");
        titleMode.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleMode);

        ArrayList<String> data = new ArrayList<>();
        data.add("Death Match");
        data.add("League Match");
        Selecting selectMode = new Selecting(data,1);
        add(selectMode);
    }


}
