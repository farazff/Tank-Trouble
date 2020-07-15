package GUI;
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

    public GameWithPC()
    {
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


        JLabel mode = new JLabel("Game Mode:");
        mode.setFont(new Font("Arial",Font.BOLD,20));
        ArrayList<String> data = new ArrayList<>();
        data.add("Death Match");
        data.add("League Match");
        Selecting selecting = new Selecting(data,0,Color.WHITE,Color.PINK,
                new Font("Arial",Font.BOLD,20));

        JLabel tankStamina = new JLabel("Tank Stamina:");
        tankStamina.setFont(new Font("Arial",Font.BOLD,20));
        JSlider sliderTank = new JSlider(50,150,100);
        sliderTank.setMajorTickSpacing(10);
        sliderTank.setPaintLabels(true);
        sliderTank.setSnapToTicks(true);

        JLabel canonPower = new JLabel("Canon Power:");
        canonPower.setFont(new Font("Arial",Font.BOLD,20));
        JSlider sliderCanon = new JSlider(50,150,100);
        sliderCanon.setMajorTickSpacing(10);
        sliderCanon.setPaintLabels(true);
        sliderCanon.setSnapToTicks(true);

        JLabel wall = new JLabel("Destroyable Walls Stamina:");
        wall.setFont(new Font("Arial",Font.BOLD,20));
        JSlider sliderWall = new JSlider(50,150,100);
        sliderWall.setMajorTickSpacing(10);
        sliderWall.setPaintLabels(true);
        sliderWall.setSnapToTicks(true);


        JPanel startPanel = new JPanel(new FlowLayout());
        startPanel.setBackground(Color.WHITE);
        startPanel.setOpaque(true);
        JLabel start = new JLabel("Start");
        start.setHorizontalAlignment(JLabel.CENTER);
        start.setBackground(Color.PINK);
        start.setOpaque(true);
        start.setPreferredSize(new Dimension(120,50));
        start.setFont(new Font("Arial",Font.BOLD,26));
        startPanel.add(start);


        c.fill = GridBagConstraints.HORIZONTAL;

        GridBagSetter.addComponent(topTop,0,0,2,6,layout,c,base);

        c.insets = new Insets(7,8 ,7 ,8);

        GridBagSetter.addComponent(mode,6,0,2,1,layout,c,base);
        GridBagSetter.addComponent(selecting,7,0,2,1,layout,c,base);
        GridBagSetter.addComponent(tankStamina,8,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderTank,9,0,2,1,layout,c,base);
        GridBagSetter.addComponent(canonPower,10,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderCanon,11,0,2,1,layout,c,base);
        GridBagSetter.addComponent(wall,12,0,1,1,layout,c,base);
        GridBagSetter.addComponent(sliderWall,13,0,2,1,layout,c,base);
        GridBagSetter.addComponent(startPanel,14,0,2,2,layout,c,base);
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
                System.out.println("BACK");
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
