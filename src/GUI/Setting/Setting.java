package GUI.Setting;


import GUI.PictureJLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Setting extends JPanel
{

    public JPanel getPanel()
    {
        return this;
    }

    private PictureJLabel pictureJLabel = new PictureJLabel("Images/Setting.jpg");
    private ColorJLabel userInfo;
    private ColorJLabel defaults;
    private JPanel userInfoPanel;
    private JPanel defaultsPanel;
    private JSlider sliderTank = new JSlider(50,150,100);
    private JPanel tempTank;
    private JSlider sliderCanon;
    private JPanel tempCanon;
    private JSlider sliderWall;
    private JPanel tempWall;
    private JLabel setDefault;
    private JLabel save;

    private int tank;
    private int canon;
    private int wallfirst;


    private MouseHandler mouse = new MouseHandler();

    public Setting()
    {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,600));
        readFile();
        createLeft();
        createMain();
    }

    public void readFile()
    {
        int num=0,i=1;
        try
        {
            FileReader reader = new FileReader(new File("Files/Setting.txt"));
            while(reader.ready())
            {
                char temp = (char)(reader.read());

                if(temp=='\n')
                {
                    if(i==1)
                        tank=num;
                    if(i==2)
                        canon=num;
                    if(i==3)
                        wallfirst=num;
                    num = 0;
                    i++;
                }
                else
                {
                    num = num*10 + Integer.parseInt(String.valueOf(temp));
                }
            }
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createMain()
    {
        userInfoPanel = new JPanel(new GridLayout(9,2,5,5));
        userInfoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,7));
        userInfoPanel.setBackground(Color.GRAY);
        userInfoPanel.setOpaque(true);
        userInfoPanel.add(new LeftPartLabel("User Name: ",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("Faraz",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Playing Time:  ",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("528 minutes ",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Total Single Games:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("25",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Total multiPlayer Games:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("15",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("winning numbers of SinglePlayer mode:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("10",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("winning numbers of MultiPlayer mode:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel("3",18,Color.CYAN));


        ////////////////////////////////////////
        ////////////////////////////////////////

        defaultsPanel = new JPanel(new GridLayout(7,1,5,5));
        defaultsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,7));
        defaultsPanel.setBackground(Color.GRAY);
        defaultsPanel.setOpaque(true);
        JLabel tankStamina = new JLabel("Tank Stamina:");
        tankStamina.setFont(new Font("Arial",Font.BOLD,20));
        sliderTank = new JSlider(50,150,tank);
        sliderTank.setMajorTickSpacing(10);
        sliderTank.setPaintLabels(true);
        sliderTank.setSnapToTicks(true);
        tempTank = new JPanel(new FlowLayout());
        sliderTank.setPreferredSize(new Dimension(500,100));
        tempTank.add(sliderTank);

        JLabel canonPower = new JLabel("Canon Power:");
        canonPower.setFont(new Font("Arial",Font.BOLD,20));
        sliderCanon = new JSlider(50,150,canon);
        sliderCanon.setMajorTickSpacing(10);
        sliderCanon.setPaintLabels(true);
        sliderCanon.setSnapToTicks(true);
        tempCanon = new JPanel(new FlowLayout());
        sliderCanon.setPreferredSize(new Dimension(500,100));
        tempCanon.add(sliderCanon);

        JLabel wall = new JLabel("Destroyable Walls Stamina:");
        wall.setFont(new Font("Arial",Font.BOLD,20));
        sliderWall = new JSlider(50,150,wallfirst);
        sliderWall.setMajorTickSpacing(10);
        sliderWall.setPaintLabels(true);
        sliderWall.setSnapToTicks(true);
        tempWall = new JPanel(new FlowLayout());
        sliderWall.setPreferredSize(new Dimension(500,100));
        tempWall.add(sliderWall);

        setDefault = new JLabel("Set as Default",JLabel.CENTER);
        setDefault.addMouseListener(mouse);
        setDefault.setBackground(Color.PINK);
        setDefault.setOpaque(true);
        setDefault.setPreferredSize(new Dimension(250,50));
        setDefault.setFont(new Font("Arial",Font.PLAIN,20));


        save = new JLabel("save",JLabel.CENTER);
        save.addMouseListener(mouse);
        save.setBackground(Color.PINK);
        save.setOpaque(true);
        save.setPreferredSize(new Dimension(250,50));
        save.setFont(new Font("Arial",Font.PLAIN,20));

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setOpaque(false);
        buttons.add(setDefault);
        buttons.add(save);

        defaultsPanel.add(tankStamina);
        defaultsPanel.add(tempTank);
        defaultsPanel.add(canonPower);
        defaultsPanel.add(tempCanon);
        defaultsPanel.add(wall);
        defaultsPanel.add(tempWall);
        defaultsPanel.add(buttons);

        this.add(pictureJLabel,BorderLayout.CENTER);
    }

    private void createLeft()
    {
        JPanel left = new JPanel(new GridLayout(10,1,10,10));
        left.setPreferredSize(new Dimension(300,600));
        left.setBackground(Color.PINK);
        this.add(left,BorderLayout.WEST);

        JLabel back = new JLabel("        Back");
        back.setFont(new Font("Arial",Font.BOLD,22));
        back.setBackground(Color.ORANGE);
        back.setOpaque(true);

        userInfo = new ColorJLabel("    User info");
        userInfo.addMouseListener(mouse);

        defaults = new ColorJLabel("    Game Defaults");
        defaults.addMouseListener(mouse);

        left.add(back);
        left.add(userInfo);
        left.add(defaults);
    }


    private class MouseHandler implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(userInfo))
            {
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().add(userInfoPanel,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(defaults))
            {
                getPanel().remove(pictureJLabel);
                getPanel().remove(userInfoPanel);
                getPanel().add(defaultsPanel,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(setDefault))
            {
                sliderTank.setValue(100);
                sliderCanon.setValue(100);
                sliderWall.setValue(100);
            }
            if(e.getSource().equals(save))
            {
                try
                {
                    FileWriter writer = new FileWriter(new File("Files/Setting.txt"));
                    writer.write(Integer.toString(sliderTank.getValue()));
                    writer.write("\n");
                    writer.write(Integer.toString(sliderCanon.getValue()));
                    writer.write("\n");
                    writer.write(Integer.toString(sliderWall.getValue()));
                    writer.write("\n");
                    writer.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }

            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }
}
