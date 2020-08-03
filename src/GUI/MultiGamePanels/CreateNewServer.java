package GUI.MultiGamePanels;

import GUI.GridBagSetter;
import GUI.Music;
import GUI.PictureJLabel;
import GameData.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CreateNewServer extends JPanel
{
    private JTextField url; // user name
    private JPasswordField password1; // password
    private JPasswordField password2; // check password

    private ServerListPanel serverListPanel;
    private JButton create; // sign up button
    private JLabel back;
    private JFrame frame;
    private JPanel pre;
    private User user;

    /**
     * creates new sign up panel
     */
    public CreateNewServer(JFrame frame, ServerListPanel serverListPanel, JPanel pre, User user)
    {
        super();
        this.serverListPanel = serverListPanel;
        this.frame = frame;
        this.pre = pre;
        setBorder(new EmptyBorder (10,10,10,10));
        setLayout(new FlowLayout (FlowLayout.CENTER));
        createBasePanel ();
        addComponentListener (new ComponentAdapter () {
            @Override
            public void componentResized (ComponentEvent e) {
                repaint ();
            }
        });
        this.user = user;
    }

    public void setPre (JPanel pre) {
        this.pre = pre;
    }

    /**
     * create the info panel
     */
    private void createBasePanel ()
    {
        KeyHandler keyHandler = new KeyHandler ();
        GridBagConstraints constraints = new GridBagConstraints ();
        GridBagLayout layout = new GridBagLayout ();

        JPanel base = new JPanel();
        base.setBackground (Color.WHITE);
        base.setBorder (new LineBorder (Color.GRAY,6,true));


        base.setLayout (layout);

        JLabel server = new JLabel("Create New ServerInformation");
        server.setHorizontalAlignment(JLabel.CENTER);
        server.setBackground(null);
        server.setFont(new Font("Arial",Font.BOLD,30));
        server.setOpaque(true);
        PictureJLabel toLeft = new PictureJLabel("Images/toleft.jpg");
        toLeft.setPreferredSize(new Dimension(60, 60));
        PictureJLabel toRight = new PictureJLabel("Images/toright.jpg");
        toRight.setPreferredSize(new Dimension(60, 60));
        JPanel top = new JPanel(new FlowLayout());
        top.add(toRight);
        top.add(server);
        top.add(toLeft);
        top.setBackground(Color.WHITE);
        top.setOpaque(true);
        JPanel topTop = new JPanel(new BorderLayout());
        JPanel back2 = new JPanel(new GridLayout(3,1));
        topTop.add(top,BorderLayout.CENTER);

        back = new JLabel (new ImageIcon ("./Images/back1.png"));
        back.addMouseListener (new MouseHandler ());

        back2.add(back);
        back2.setBackground(Color.WHITE);
        back2.setOpaque(true);
        topTop.add(back2,BorderLayout.WEST);
        JLabel a = new JLabel ("      ");
        topTop.add (a,BorderLayout.EAST);
        a.setOpaque (true);
        a.setBackground (Color.WHITE);

        // title of the password2 textfield
        JLabel password2text = new JLabel("Renter Password:");
        password2 = new JPasswordField();
        password2.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        password2.addKeyListener(keyHandler);
        // title of the password1 textfield
        JLabel password1text = new JLabel("Password*:");
        password1 = new JPasswordField();
        password1.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        password1.addKeyListener(keyHandler);
        // title of the username textfield
        JLabel userNameText = new JLabel("URL :");
        url = new JTextField();
        url.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        url.addKeyListener(keyHandler);




        create = new JButton("Create");
        create.addActionListener(new ActionHandler ());



        // hint for entering the correct password
        JTextArea hint = new JTextArea("* Password must contain minimum 8 letters!! \n* Password must contain Both letters and numbers!!");
        hint.setFont(new Font("Arial",Font.ITALIC,10));
        hint.setEditable(false);
        hint.setFocusable (false);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 15;
        constraints.ipadx = 0;


        GridBagSetter.addComponent(topTop,0,0,4,1,layout,constraints,base);

        constraints.insets = new Insets(0,5 ,0 ,5 );

        GridBagSetter.addComponent(userNameText,1,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(url,2,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password1text,3,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password1,4,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password2text,5,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password2,6,0,4,1,layout,constraints,base);

        constraints.insets = new Insets(10,5 ,0 ,5);

        //constraints.fill = GridBagConstraints.CENTER;

        GridBagSetter.addComponent(create,7,0,4,1,layout,constraints,base);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,1 ,0 ,0);
        GridBagSetter.addComponent(hint,8,0,4,1,layout,constraints,base);




        this.add(base);
    }

    /**
     * check if the given data for signing up is ok or nor
     * if it is ok it will save the users info
     */
    private boolean checkData()
    {
        boolean ans = true;
        if(url.getText().length()==0)
        {
            url.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.RED,2,true),
                    new EmptyBorder (0,2,0,2)));
            ans=false;
        }
        else
        {
            url.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                    new EmptyBorder (0,2,0,2)));
        }

        if(password1.getPassword().length<8 || !containsNumber(Arrays.toString(password1.getPassword())) ||
                !containsLetter(Arrays.toString(password1.getPassword())))
        {
            password1.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.RED,2,true),
                    new EmptyBorder (0,2,0,2)));
            ans=false;
        }
        else
        {
            password1.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                    new EmptyBorder (0,2,0,2)));
        }

        if(!Arrays.equals(password2.getPassword(), password1.getPassword()))
        {
            password2.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.RED,2,true),
                    new EmptyBorder (0,2,0,2)));
            ans=false;
        }
        else
        {
            password2.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                    new EmptyBorder (0,2,0,2)));
        }


        return ans;
    }



    /**
     * check if the given string contains number
     * @param temp the given string
     * @return return's true if the string contains number otherwise false
     */
    private boolean containsNumber(String temp)
    {
        char[] chars = temp.toCharArray();
        for(char c : chars)
        {
            if(Character.isDigit(c))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the given string contains letter
     * @param temp the given string
     * @return return's true if the string contains letter otherwise false
     */
    private boolean containsLetter(String temp)
    {
        char[] chars = temp.toCharArray();
        for(char c : chars)
        {
            if(Character.isLetter(c))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * handles key events
     */
    private class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed (KeyEvent e)
        {

            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                if(e.getSource().equals(url) || e.getSource().equals(password1) || e.getSource().equals(password2))
                {
                    Music music = new Music ();
                    music.execute ();
                    if (!checkData ())
                        return;
                    ServerInformation serverInformation = new ServerInformation (url.getText (),password1.getPassword ());
                    serverListPanel.addNewServer (serverInformation);
                    frame.setContentPane (pre);
                }


        }
    }


    /**
     * handles Action events
     */
    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource () == create)
            {
                Music music = new Music ();
                music.execute ();
                if (!checkData ())
                    return;

                ServerInformation serverInformation = new ServerInformation
                        (url.getText (),password1.getPassword ());
                serverListPanel.addNewServer (serverInformation);
                frame.setContentPane (pre);

            }
        }
    }


    /**
     * this class handles Mouse
     */
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseEntered (MouseEvent e) {
            if (e.getSource () == back)
                back.setIcon (new ImageIcon ("./Images/back2.png"));
        }

        @Override
        public void mouseExited (MouseEvent e) {
            if (e.getSource () == back)
                back.setIcon (new ImageIcon ("./Images/back1.png"));
        }

        @Override
        public void mouseReleased (MouseEvent e) {
            if (e.getSource () == back)
            {
                Music music = new Music ();
                music.execute ();
                frame.setContentPane (pre);
            }
        }
    }


    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent (g);
        try {


            g.drawImage (ImageIO.read (new File ("./Images/login.jpg")).
                            getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST)
                    ,0,0,this);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


}
