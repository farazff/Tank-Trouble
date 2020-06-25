package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameWithPC extends JPanel
{

    public GameWithPC()
    {

        setLayout(new GridLayout(11,1,0,0));
        setBackground(new Color(246,246,246));
        setOpaque(true);
        setBorder(null);

        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(new Color(246,246,246));
        PictureJLabel pictureJLabel = new PictureJLabel("Images/toright.jpg");
        pictureJLabel.setPreferredSize(new Dimension(120,60));
        PictureJLabel pictureJLabel2 = new PictureJLabel("Images/toleft.jpg");
        pictureJLabel2.setPreferredSize(new Dimension(120,60));
        JLabel header = new JLabel(" Single mode ");
        header.setFont (new Font ("DialogInput",Font.BOLD,30));
        header.setHorizontalAlignment(JLabel.CENTER);

        top.add(pictureJLabel);
        top.add(header);
        top.add(pictureJLabel2);

        add(top);
        
        JLabel titleMode = new JLabel(" Game Mode: ");
        titleMode.setForeground(Color.WHITE);
        titleMode.setFont (new Font ("Arial",Font.BOLD,25));

        add(titleMode);

        ArrayList<String> dataMode = new ArrayList<>();
        dataMode.add("Death Match");
        dataMode.add("League Match");
        Selecting selectMode = new Selecting(dataMode,0);
        add(selectMode);

        JLabel titleTank = new JLabel(" Tank Stamina: ");
        titleTank.setForeground(Color.WHITE);
        titleTank.setFont (new Font ("Arial",Font.BOLD,25));

        add(titleTank);

        ArrayList<String> dataTank = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataTank.add(Integer.toString(i));
        }
        Selecting selectTank = new Selecting(dataTank,99);
        add(selectTank);

        JLabel titleCanon = new JLabel(" Canon Power: ");
        titleCanon.setForeground(Color.WHITE);
        titleCanon.setFont (new Font ("Arial",Font.BOLD,25));

        add(titleCanon);

        ArrayList<String> dataCanon = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataCanon.add(Integer.toString(i));
        }
        Selecting selectCanon = new Selecting(dataCanon,99);
        add(selectCanon);

        JLabel titleWall = new JLabel(" Destroyable wall Stamina: ");
        titleWall.setForeground(Color.WHITE);
        titleWall.setFont (new Font ("Arial",Font.BOLD,25));

        add(titleWall);

        ArrayList<String> dataWall = new ArrayList<>();
        for(int i=1;i<=150;i++)
        {
            dataWall.add(Integer.toString(i));
        }
        Selecting selectWall = new Selecting(dataWall,99);
        add(selectWall);


        JLabel empty = new JLabel();
        add(empty);

        JPanel startButton = new JPanel(new FlowLayout());
        JLabel start = new JLabel("START");
        start.setHorizontalAlignment(JLabel.CENTER);
        start.setFont(new Font("Arial",Font.BOLD,20));
        startButton.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        start.setOpaque(true);
        start.setPreferredSize(new Dimension(200,50));
        startButton.add(start);
        add(startButton);

    }
    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        try
        {
            g.drawImage (ImageIO.read (new File("Images/backstart.jpg")).
                    getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST),0,10,this);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

}
