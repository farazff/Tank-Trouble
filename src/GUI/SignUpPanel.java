package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * create's sign up panel
 *
 *
 * @author Faraz Farangizadeh
 */


public class SignUpPanel extends JPanel
{
    private JTextField username; // user name
    private JPasswordField password1; // password
    private JPasswordField password2; // check password
    private JButton signUp; // sign up button
    private JButton cancel; // sign up button
    private JFrame frame;
    private JPanel signIn;

    /**
     * creates new sign up panel
     */
    public SignUpPanel(JFrame frame,JPanel signIn)
    {
        super();
        this.frame = frame;
        this.signIn = signIn;
        setBorder(new EmptyBorder (10,10,10,10));
        setLayout(new FlowLayout (FlowLayout.CENTER));
        createBasePanel ();
    }

    /**
     * create the info panel
     */
    private void createBasePanel ()
    {
        KeyHandler keyHandler = new KeyHandler ();

        JPanel base = new JPanel();
        base.setBackground (Color.WHITE);
        base.setBorder (new LineBorder (Color.GRAY,6,true));

        GridBagLayout layout = new GridBagLayout ();
        GridBagConstraints constraints = new GridBagConstraints ();
        base.setLayout (layout);

        JLabel header = new JLabel("sign up");
        header.setForeground(Color.WHITE);
        header.setFont (new Font ("DialogInput",Font.BOLD,23));
        header.setHorizontalTextPosition (SwingConstants.CENTER);
        header.setHorizontalAlignment (SwingConstants.CENTER);
        header.setBackground(Color.GRAY);
        header.setOpaque(true);

        // title of the username textfield
        JLabel userNameText = new JLabel("User Name:");
        username = new JTextField();
        username.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        username.addKeyListener(keyHandler);

        // title of the password1 textfield
        JLabel password1text = new JLabel("Password*:");
        password1 = new JPasswordField();
        password1.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        password1.addKeyListener(keyHandler);

        // title of the password2 textfield
        JLabel password2text = new JLabel("Renter Password:");
        password2 = new JPasswordField();
        password2.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
                new EmptyBorder (0,2,0,2)));
        password2.addKeyListener(keyHandler);


        signUp = new JButton("Sign up!");
        signUp.addActionListener(e -> checkData());

        cancel = new JButton("Cancel!");
        cancel.addActionListener(e -> ShowSignIn());

        // hint for entering the correct password
        JTextArea hint = new JTextArea("* Password must contain minimum 8 letters!! \n* Password must contain Both letters and numbers!!");
        hint.setFont(new Font("Arial",Font.ITALIC,10));
        hint.setEditable(false);


        constraints.ipadx = 0;
        constraints.ipady = 15;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        GridBagSetter.addComponent(header,0,0,4,1,layout,constraints,base);

        constraints.insets = new Insets(0,5 ,0 ,5 );

        GridBagSetter.addComponent(userNameText,1,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(username,2,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password1text,3,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password1,4,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password2text,5,0,4,1,layout,constraints,base);

        GridBagSetter.addComponent(password2,6,0,4,1,layout,constraints,base);

        constraints.insets = new Insets(10,5 ,0 ,5);

        //constraints.fill = GridBagConstraints.CENTER;
        GridBagSetter.addComponent(cancel,7,0,2,1,layout,constraints,base);
        GridBagSetter.addComponent(signUp,7,2,2,1,layout,constraints,base);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,1 ,0 ,0);
        GridBagSetter.addComponent(hint,8,0,4,1,layout,constraints,base);

        this.add(base);

    }

    /**
     * check if the given data for signing up is ok or nor
     * if it is ok it will save the users info
     */
    private void checkData()
    {
        boolean ans = true;
        if(username.getText().length()==0)
        {
            username.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.RED,2,true),
                    new EmptyBorder (0,2,0,2)));
            ans=false;
        }
        else
        {
            username.setBorder (BorderFactory.createCompoundBorder (new LineBorder (Color.BLACK,2,true),
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

        if(ans)
        {
            System.out.println("User saved");
        }
    }

    public void ShowSignIn()
    {
        frame.setContentPane(signIn);
        frame.setVisible(false);
        frame.setVisible(true);
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
                if(e.getSource().equals(username) || e.getSource().equals(password1) || e.getSource().equals(password2))
                    checkData();
        }
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        try
        {
            g.drawImage (ImageIO.read (new File ("./Images/login.jpg")).
                    getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST),0,0,this);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }
}
