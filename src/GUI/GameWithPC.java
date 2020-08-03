package GUI;
import Game.Starting;
import GameData.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameWithPC extends JPanel
{

    private PictureJLabel picture;
    private JPanel pre;
    private JFrame frame;
    private JLabel start;
    private Selecting selecting;
    private Selecting selecting2;
    private User user;

    private JSlider sliderTank;
    private JSlider sliderCanon;
    private JSlider sliderWall;


    public GameWithPC(JFrame frame, User user)
    {
        this.frame = frame;
        this.user = user;
        GridBagLayout layout = new GridBagLayout();
        JPanel base = new JPanel(layout);
        base.setBackground(Color.WHITE);
        base.setOpaque(true);
        base.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));

        add(base);

        GridBagConstraints c = new GridBagConstraints();

        JLabel singlePlayer = new JLabel("Single Player");
        singlePlayer.setBackground(null);
        singlePlayer.setHorizontalAlignment(JLabel.CENTER);
        singlePlayer.setOpaque(true);
        singlePlayer.setFont(new Font("Arial",Font.BOLD,30));
        PictureJLabel toRight = new PictureJLabel("Images/toright.jpg");
        toRight.setPreferredSize(new Dimension(60, 60));
        PictureJLabel toLeft = new PictureJLabel("Images/toleft.jpg");
        toLeft.setPreferredSize(new Dimension(60, 60));
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(Color.WHITE);
        top.setOpaque(true);
        top.add(toRight);
        top.add(singlePlayer);
        top.add(toLeft);
        JPanel topTop = new JPanel(new BorderLayout());
        topTop.add(top,BorderLayout.CENTER);
        JPanel back = new JPanel(new GridLayout(3,1));
        picture = new PictureJLabel("Images/back1.png");
        picture.setPreferredSize(new Dimension(30,30));
        picture.addMouseListener(new MouseHandler());
        back.add(picture);
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        topTop.add(back,BorderLayout.WEST);


        JLabel mode = new JLabel("MultiGame Mode:");
        mode.setFont(new Font("Arial",Font.BOLD,20));
        ArrayList<String> data = new ArrayList<>();
        data.add("Death Match");
        data.add("League Match");
        selecting = new Selecting(data,0,Color.WHITE,Color.GRAY,
                new Font("Arial",Font.BOLD,20));

        JLabel level = new JLabel("Level:");
        level.setFont(new Font("Arial",Font.BOLD,20));
        ArrayList<String> levelData = new ArrayList<>();
        levelData.add("EASY");
        levelData.add("MEDIUM");
        levelData.add("HARD");
        selecting2 = new Selecting(levelData,0,Color.WHITE,Color.GRAY,
                new Font("Arial",Font.BOLD,20));

        JLabel tankStamina = new JLabel("Tank Stamina:");
        tankStamina.setFont(new Font("Arial",Font.BOLD,20));
        sliderTank = new JSlider(10,100,user.getDefaultTankStamina ());
        sliderTank.setMajorTickSpacing(10);
        sliderTank.setPaintLabels(true);
        sliderTank.setSnapToTicks(true);

        JLabel canonPower = new JLabel("Canon Power:");
        canonPower.setFont(new Font("Arial",Font.BOLD,20));
        sliderCanon = new JSlider(10,100,user.getDefaultCanonPower ());
        sliderCanon.setMajorTickSpacing(10);
        sliderCanon.setPaintLabels(true);
        sliderCanon.setSnapToTicks(true);

        JLabel wall = new JLabel("Destroyable Walls Stamina:");
        wall.setFont(new Font("Arial",Font.BOLD,20));
        sliderWall = new JSlider(10,100,user.getDefaultWallStamina ());
        sliderWall.setMajorTickSpacing(10);
        sliderWall.setPaintLabels(true);
        sliderWall.setSnapToTicks(true);


        JPanel startPanel = new JPanel(new FlowLayout());
        startPanel.setBackground(Color.WHITE);
        startPanel.setOpaque(true);
        start = new JLabel("Start");
        start.setHorizontalAlignment(JLabel.CENTER);
        start.setBackground(new Color(150,150,150));
        start.setOpaque(true);
        start.setPreferredSize(new Dimension(120,50));
        start.setFont(new Font("Arial",Font.BOLD,26));
        start.addMouseListener(new MouseHandler());
        startPanel.add(start);


        c.fill = GridBagConstraints.HORIZONTAL;

        GridBagSetter.addComponent(topTop,0,0,2,6,layout,c,base);

        c.insets = new Insets(7,8 ,7 ,8);

        GridBagSetter.addComponent(mode,        6,0,2,1,layout,c,base);
        GridBagSetter.addComponent(selecting,   7,0,2,1,layout,c,base);
        GridBagSetter.addComponent(level,       8,0,2,1,layout,c,base);
        GridBagSetter.addComponent(selecting2,  10,0,2,1,layout,c,base);
        GridBagSetter.addComponent(tankStamina, 11,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderTank,  12,0,2,1,layout,c,base);
        GridBagSetter.addComponent(canonPower,  13,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderCanon, 14,0,2,1,layout,c,base);
        GridBagSetter.addComponent(wall,        15,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderWall,  16,0,2,1,layout,c,base);
        GridBagSetter.addComponent(startPanel,  17,0,2,2,layout,c,base);
    }

    public void setPre(JPanel pre)
    {
       this.pre = pre;
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        try
        {
            g.drawImage (ImageIO.read (new File("Images/login.jpg")).
                    getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST),0,0,this);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

    public class MouseHandler implements MouseMotionListener , MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(picture))
            {
                Music music = new Music();
                music.execute();
                frame.setContentPane(pre);
                frame.setVisible(false);
                frame.setVisible(true);
            }
            if(e.getSource().equals(start))
            {
                Music music = new Music();
                music.execute();
                frame.setVisible(false);
                int level = 1;
                if(selecting2.getCurrentValue().equals("EASY"))
                    level=1;
                if(selecting2.getCurrentValue().equals("MEDIUM"))
                    level=2;
                if(selecting2.getCurrentValue().equals("HARD"))
                    level=3;

                int type = 1;
                if(selecting.getCurrentValue().equals("Death Match"))
                {
                    type = 1;
                }

                if(selecting.getCurrentValue().equals("League Match"))
                {
                    type = 5;
                }


                Starting starting = new Starting(frame,level,sliderTank.getValue(),sliderCanon.getValue(),
                        sliderWall.getValue(), user , type);

                user.setNumOfSingleGames (user.getNumOfSingleGames () + 1);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if(e.getSource().equals(start))
            {
                Thread a = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for(int i=0;i<4;i++)
                        {

                            switch (i)
                            {
                                case 0 : start.setBackground(new Color(148,148,148));
                                    break;
                                case 1 : start.setBackground(new Color(128,128,128));
                                    break;
                                case 2 : start.setBackground(new Color(104,104,104));
                                    break;
                                case 3 : start.setBackground(new Color(94,94,94));
                                    break;
                            }
                            try
                            {
                                Thread.sleep(50);
                            }
                            catch (InterruptedException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                a.start();
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            if(e.getSource().equals(start))
            {
                Thread a = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (int i = 0; i < 4; i++)
                        {

                            switch (i)
                            {
                                case 0:
                                    start.setBackground(new Color(104, 104, 104));
                                    break;
                                case 1:
                                    start.setBackground(new Color(128, 128, 128));
                                    break;
                                case 2:
                                    start.setBackground(new Color(148, 148, 148));
                                    break;
                                case 3:
                                    start.setBackground(new Color(150, 150, 150));
                                    break;
                            }
                            try
                            {
                                Thread.sleep(50);
                            }
                            catch (InterruptedException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                a.start();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {

        }

        @Override
        public void mouseMoved(MouseEvent e)
        {

        }
    }

}
