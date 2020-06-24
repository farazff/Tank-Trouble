package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameWithPC extends JPanel
{


    public GameWithPC()
    {

        setLayout(new GridLayout(10,1,0,0));
        setBackground(Color.GRAY);
        setOpaque(true);


        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(new Color(246,246,246));
        PictureJLabel pictureJLabel = new PictureJLabel("Images/toright.jpg");
        pictureJLabel.setPreferredSize(new Dimension(120,70));
        PictureJLabel pictureJLabel2 = new PictureJLabel("Images/toleft.jpg");
        pictureJLabel2.setPreferredSize(new Dimension(120,70));
        JLabel header = new JLabel(" Single mode ");
        header.setFont (new Font ("DialogInput",Font.BOLD,23));
        header.setHorizontalAlignment(JLabel.CENTER);

        top.add(pictureJLabel);
        top.add(header);
        top.add(pictureJLabel2);

        add(top);
        
        JLabel titleMode = new JLabel(" Game Mode: ");
        titleMode.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleMode);

        ArrayList<String> dataMode = new ArrayList<>();
        dataMode.add("Death Match");
        dataMode.add("League Match");
        Selecting selectMode = new Selecting(dataMode,0);
        add(selectMode);

        JLabel titleTank = new JLabel(" Tank Stamina: ");
        titleTank.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleTank);

        ArrayList<String> dataTank = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataTank.add(Integer.toString(i));
        }
        Selecting selectTank = new Selecting(dataTank,99);
        add(selectTank);

        JLabel titleCanon = new JLabel(" Canon Power: ");
        titleCanon.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleCanon);

        ArrayList<String> dataCanon = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataCanon.add(Integer.toString(i));
        }
        Selecting selectCanon = new Selecting(dataCanon,99);
        add(selectCanon);

        JLabel titleWall = new JLabel(" Destroyable wall Stamina: ");
        titleWall.setFont (new Font ("DialogInput",Font.BOLD,23));

        add(titleWall);

        ArrayList<String> dataWall = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataWall.add(Integer.toString(i));
        }
        Selecting selectWall = new Selecting(dataWall,99);
        add(selectWall);

        JPanel startButton = new JPanel(new FlowLayout());
        JButton start = new JButton("START!!!");
        start.setPreferredSize(new Dimension(200,50));
        startButton.add(start);
        add(startButton);

    }


}
