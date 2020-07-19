package GUI.MainPage;

import GUI.Setting.Setting;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel
{

    private JPanel sett;
    private JPanel sing;
    private JPanel mul;


    public void setSett(JPanel sett)
    {
        this.sett = sett;
    }

    public void setSing(JPanel sing)
    {
        this.sing = sing;
    }

    public void setMul(JPanel mul)
    {
        this.mul = mul;
    }

    public JPanel getPanel()
    {
        return this;
    }
    private JPanel down;
    private JPanel top;
    private JPanel middle;
    private JPanel DMiddle;
    private MouseListener mouse = new MouseHandler();

    private ShapeLabel about;
    private ShapeLabel setting;
    private ShapeLabel single;
    private ShapeLabel multi;
    private ExitJLabel exit;

    private JFrame frame;

    public Main(JFrame frame)
    {
        super();
        this.frame = frame;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(900,530));
        this.setBorder(new EmptyBorder(5,18,18,18));
        this.setOpaque(false);

        top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(900,100));
        top.setOpaque(false);

        down = new JPanel();
        down.setLayout(new BorderLayout());
        down.setPreferredSize(new Dimension(900,100));
        down.setOpaque(false);

        middle = new JPanel();
        middle.setLayout(new BorderLayout());
        middle.setPreferredSize(new Dimension(900,300));
        middle.setOpaque(false);

        DMiddle = new JPanel();
        DMiddle.setLayout(new FlowLayout(FlowLayout.CENTER,80,5));
        DMiddle.setPreferredSize(new Dimension(900,110));
        DMiddle.setOpaque(false);

        this.add(top,BorderLayout.NORTH);
        this.add(middle,BorderLayout.CENTER);
        this.add(down,BorderLayout.SOUTH);
        middle.add(DMiddle,BorderLayout.SOUTH);

        setTop();
        setDown();
        setMiddle();
    }

    public void setTop()
    {
        int[] x = {40 ,47, 52 ,59 ,77, 74, 79, 90, 98, 86, 86 ,98 ,90, 77, 74, 77, 59, 52, 49 ,40 ,22 ,26, 24, 10, 2, 14 ,14 ,2,9 ,23 ,26,22};
        int[] y = {2, 14 ,14, 2, 9 ,23 ,26 ,23 ,41 ,48 ,51 ,59 ,77 ,74 ,76 ,91 ,98 ,86 ,86, 98, 90, 77 ,74 ,77, 59, 52 ,48 ,40 ,23, 26, 24, 10};
        setting = new ShapeLabel("setting",x,y);
        setting.setPreferredSize(new Dimension(100,100));
        setting.addMouseListener(mouse);

        top.add(setting,BorderLayout.EAST);
    }

    public void setDown()
    {
        int[] x = {40 ,47, 52 ,59 ,77, 74, 79, 90, 98, 86, 86 ,98 ,90, 77, 74, 77, 59, 52, 49 ,40 ,22 ,26, 24, 10, 2, 14 ,14 ,2,9 ,23 ,26,22};
        int[] y = {2, 14 ,14, 2, 9 ,23 ,26 ,23 ,41 ,48 ,51 ,59 ,77 ,74 ,76 ,91 ,98 ,86 ,86, 98, 90, 77 ,74 ,77, 59, 52 ,48 ,40 ,23, 26, 24, 10};
        about = new ShapeLabel("About",x,y);
        about.setPreferredSize(new Dimension(100,100));
        about.addMouseListener(mouse);


        int[] x1 = {10,88,87,79,80,19,18,78,79,87,88,10};
        int[] y1 = {1,1,31,25,10,8,90,92,76,71,99,99};

        int[] x2 = {35,34,60,60,93,60,61};
        int[] y2 = {39,61,62,76,50,24,38};

        exit = new ExitJLabel(" Exit",x1,y1,x2,y2);
        exit.setPreferredSize(new Dimension(100,100));
        exit.addMouseListener(mouse);

        down.add(about,BorderLayout.WEST);
        down.add(exit,BorderLayout.EAST);
    }

    public void setMiddle()
    {
        int[] x = {8,192,198,0};
        int[] y = {10,29,98,90};
        single = new ShapeLabel("Single Player",x,y);
        single.setPreferredSize(new Dimension(200,100));
        single.addMouseListener(mouse);

        int[] x1 = {0,200,180,15};
        int[] y1 = {25,10,90,82};
        multi = new ShapeLabel("Multi Player",x1,y1);
        multi.setPreferredSize(new Dimension(200,100));
        multi.addMouseListener(mouse);

        DMiddle.add(single);
        DMiddle.add(multi);
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
            if(e.getSource().equals(setting))
            {
                setting.rePaintHlolding();
            }
            if(e.getSource().equals(single))
            {
                single.rePaintHlolding();
            }
            if(e.getSource().equals(multi))
            {
                multi.rePaintHlolding();
            }
            if(e.getSource().equals(about))
            {
                about.rePaintHlolding();
            }
            if(e.getSource().equals(exit))
            {
                exit.rePaintHlolding();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if(e.getSource().equals(setting))
            {
                setting.rePaintExited();
                frame.setContentPane(sett);
                frame.setVisible(false);
                frame.setVisible(true);
            }
            if(e.getSource().equals(single))
            {
                single.rePaintExited();
                frame.setContentPane(sing);
                frame.setVisible(false);
                frame.setVisible(true);
            }
            if(e.getSource().equals(multi))
            {
                multi.rePaintExited();
                frame.setContentPane(mul);
                frame.setVisible(false);
                frame.setVisible(true);
            }
            if(e.getSource().equals(about))
            {
                about.rePaintExited();
                StringBuilder string = new StringBuilder();
                string.append("Developers: \n 1) Amirreza Naziri     9726081\n  ##Email##\n2) Faraz Farangizadeh     9726060\n");
                string.append("  f.farangizadeh@gmail.com");
                JOptionPane.showMessageDialog(null,string.toString(),"About",JOptionPane.INFORMATION_MESSAGE);
            }
            if(e.getSource().equals(exit))
            {
                exit.rePaintExited();
                int ans = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?",
                        "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
                System.out.println(ans);
                if(ans == 0) //yes
                {
                    System.exit(0);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if(e.getSource().equals(setting))
            {
                setting.rePaintEntered();
            }
            if(e.getSource().equals(single))
            {
                single.rePaintEntered();
            }
            if(e.getSource().equals(multi))
            {
                multi.rePaintEntered();
            }
            if(e.getSource().equals(about))
            {
                about.rePaintEntered();
            }
            if(e.getSource().equals(exit))
            {
                exit.rePaintEntered();
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            if(e.getSource().equals(setting))
            {
                setting.rePaintExited();
            }
            if(e.getSource().equals(single))
            {
                single.rePaintExited();
            }
            if(e.getSource().equals(multi))
            {
                multi.rePaintExited();
            }
            if(e.getSource().equals(about))
            {
                about.rePaintExited();
            }
            if(e.getSource().equals(exit))
            {
                exit.rePaintExited();
            }
        }
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        try
        {
            g.drawImage (ImageIO.read (new File("Images/MainPage.jpg")).
                    getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST),0,0,this);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

}
