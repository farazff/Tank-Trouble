package GUI.Setting;

import GUI.MultiGamePanels.CreateNewServer;
import GUI.MultiGamePanels.ServerButtonPanel;
import GUI.MultiGamePanels.ServerListPanel;
import GUI.Music;
import GUI.PictureJLabel;
import GameData.User;
import Login_SignUp_Logout.LogConnector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;

/**
 * the setting panel of the game
 */

public class Setting extends JPanel
{
    private ColorJLabel temp = null;
    private int code;
    public JPanel getPanel()
    {
        return this;
    }
    private JFrame frame;
    private JPanel pre;
    private User user;

    /**
     * set the pre Panel , the panel before Setting
     * @param pre the JPanel before setting
     */
    public void setPre(JPanel pre)
    {
        this.pre = pre;
    }

    private PictureJLabel pictureJLabel = new PictureJLabel("Images/Setting.jpg");
    private ColorJLabel userInfo;
    private ColorJLabel tank;
    private ColorJLabel rank;
    private ColorJLabel defaults;
    private ColorJLabel server;
    private JPanel userInfoPanel;
    private JPanel tankPanel;
    private JPanel defaultsPanel;
    private JPanel serversListPanelInSetting;
    private JPanel serverListPanel;
    private JButton next1;
    private JLabel back1;
    private JSlider sliderTank = new JSlider(10,100,100);
    private JPanel tempTank;
    private JSlider sliderCanon;
    private JPanel tempCanon;
    private JSlider sliderWall;
    private JPanel tempWall;
    private JLabel setDefault;
    private JLabel save;
    private JLabel back;
    private JButton createNewServer;
    private JButton removeServer;
    private MouseHandler mouse = new MouseHandler();
    private PictureJLabel pictureJLabel1;
    private JButton s;

    /**
     * the constructor of the setting
     * @param frame the miaint frame of the game the the setting panel will be shown on it
     * @param user the user info
     */
    public Setting(JFrame frame, User user)
    {
        this.frame = frame;
        this.serverListPanel = new ServerListPanel (null,
                frame,user,pre);
        JScrollPane scrollPane1 = new JScrollPane (serverListPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        serversListPanelInSetting = new JPanel (new BorderLayout ());
        serversListPanelInSetting.add (scrollPane1,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800,600));
        this.user = user;
        code = user.getTankCode();
        createLeft();
        createMain();
    }

    /**
     * finds players' Level
     * @return Level
     */
    private String findLevel ()
    {
        String level;
        if (user.getScore () < 10)
            level = "Private";
        else if (user.getScore () < 20)
            level = "Specialist";
        else if (user.getScore () < 30)
            level = "Corporal";
        else if (user.getScore () < 40)
            level = "Sergeant";
        else if (user.getScore () < 50)
            level = "Commander";
        else if (user.getScore () < 60)
            level = "Officer";
        else if (user.getScore () < 70)
            level = "Chief";
        else if (user.getScore () < 80)
            level = "Captain";
        else if (user.getScore () < 90)
            level = "Colonel";
        else
            level = "General";
        return  level;
    }

    /**
     * creating different panels if the setting
     */
    public void createMain()
    {
        userInfoPanel = new JPanel(new GridLayout(11,2,5,5));
        userInfoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,7));
        userInfoPanel.setBackground(Color.GRAY);
        userInfoPanel.setOpaque(true);
        userInfoPanel.add(new LeftPartLabel("User Name: ",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getUserName (),18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Join Time:  ",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(
                ((System.currentTimeMillis () - user.getSignedUpTime ()) / (1000L*60) > 100 ?
                        (System.currentTimeMillis () - user.getSignedUpTime ()) / (1000L*60*60)
                + "  Hours" : (System.currentTimeMillis () - user.getSignedUpTime ()) / (1000L*60)
                        + "  Minutes"),18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Total Single Games:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getNumOfSingleGames () + "",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Total multiPlayer Games:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getNumOfMultiGames () + "",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Winning numbers of SinglePlayer mode:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getNumOfWinSingleGames () +"",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Winning numbers of MultiPlayer mode:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getNumOfWinMultiGames () +"",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Total number of kills:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(user.getScore () +"",18,Color.CYAN));
        userInfoPanel.add(new LeftPartLabel("Level:",18,Color.WHITE));
        userInfoPanel.add(new LeftPartLabel(findLevel (),18,Color.CYAN));

        ////////////////////////////////////////
        ////////////////////////////////////////

        tankPanel = new JPanel(new BorderLayout());
        tankPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,7));
        tankPanel.setBackground(Color.GRAY);
        tankPanel.setOpaque(true);

        JLabel text1 = new JLabel("Select you Tank : ");
        tankPanel.add(text1,BorderLayout.NORTH);

        s = new JButton("Save");
        tankPanel.add(s,BorderLayout.SOUTH);
        s.addMouseListener(mouse);

        JPanel center1 = new JPanel(new GridLayout(1,3,5,5));
        back1 = new JLabel("<");
        back1.setOpaque(true);
        back1.setBackground(Color.RED);
        next1 = new JButton(">");
        back1.addMouseListener(mouse);
        next1.addMouseListener(mouse);
        tankPanel.add(center1,BorderLayout.CENTER);
        center1.add(back1);
        pictureJLabel1 = new PictureJLabel("Images/Tanks/"+ code +".png");
        center1.add(pictureJLabel1);
        center1.add(next1);



        ////////////////////////////////////////
        ////////////////////////////////////////

        defaultsPanel = new JPanel(new GridLayout(7,1,5,5));
        defaultsPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,7));
        defaultsPanel.setBackground(Color.GRAY);
        defaultsPanel.setOpaque(true);
        JLabel tankStamina = new JLabel("Tank Stamina:");
        tankStamina.setFont(new Font("Arial",Font.BOLD,20));
        sliderTank = new JSlider(10,100,user.getDefaultTankStamina ());
        sliderTank.setMajorTickSpacing(10);
        sliderTank.setPaintLabels(true);
        sliderTank.setSnapToTicks(true);
        tempTank = new JPanel(new FlowLayout());
        sliderTank.setPreferredSize(new Dimension(500,100));
        tempTank.add(sliderTank);

        JLabel canonPower = new JLabel("Canon Power:");
        canonPower.setFont(new Font("Arial",Font.BOLD,20));
        sliderCanon = new JSlider(10,100,user.getDefaultCanonPower ());
        sliderCanon.setMajorTickSpacing(10);
        sliderCanon.setPaintLabels(true);
        sliderCanon.setSnapToTicks(true);
        tempCanon = new JPanel(new FlowLayout());
        sliderCanon.setPreferredSize(new Dimension(500,100));
        tempCanon.add(sliderCanon);

        JLabel wall = new JLabel("Destroyable Walls Stamina:");
        wall.setFont(new Font("Arial",Font.BOLD,20));
        sliderWall = new JSlider(10,100,user.getDefaultWallStamina ());
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


        JPanel buttonsPanel = new JPanel (new GridLayout (1,2));
        createNewServer = new JButton ("Create New ServerInformation");
        removeServer = new JButton ("Remove ServerInformation");
        buttonsPanel.add (createNewServer);
        buttonsPanel.add (removeServer);
        serversListPanelInSetting.add (buttonsPanel,BorderLayout.SOUTH);
        createNewServer.addMouseListener (mouse);
        removeServer.addMouseListener (mouse);

        this.add(pictureJLabel,BorderLayout.CENTER);
    }

    /**
     * creating the menu of the setting that user can select each of them
     */
    private void createLeft()
    {
        JPanel left = new JPanel(new GridLayout(10,1,10,10));
        left.setPreferredSize(new Dimension(200,600));
        left.setBackground(new Color(74,201,255));
        this.add(left,BorderLayout.WEST);

        back = new JLabel("        Back");
        back.setFont(new Font("Arial",Font.BOLD,22));
        back.setBackground(Color.ORANGE);
        back.addMouseListener(mouse);
        back.setOpaque(true);

        userInfo = new ColorJLabel("    User info");
        userInfo.addMouseListener(mouse);

        tank = new ColorJLabel("    Tank Color");
        tank.addMouseListener(mouse);

        defaults = new ColorJLabel("    Defaults");
        defaults.addMouseListener(mouse);

        rank = new ColorJLabel("    Rankings");
        rank.addMouseListener(mouse);

        server = new ColorJLabel("    ServerInformation");
        server.addMouseListener(mouse);

        left.add(back);
        left.add(userInfo);
        left.add(tank);
        left.add(defaults);
        left.add(rank);
        left.add(server);
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
            if(e.getSource().equals(s))
            {
                user.setTankCode(code);
                System.out.println(user.getTankCode());
                connect();
            }

            if(e.getSource().equals(back1))
            {
                code--;
                if(code==0)
                    code = 5;
                pictureJLabel1.changeImage("Images/Tanks/"+ code +".png");
            }

            if(e.getSource().equals(next1))
            {
                code++;
                if(code==6)
                    code = 1;
                System.out.println(code);
                pictureJLabel1.changeImage("Images/Tanks/"+ code +".png");
            }


            if(e.getSource().equals(rank))
            {
                rank.setBackground(new Color(78,35,78));
                userInfo.setBackground(new Color(163,73,164));
                tank.setBackground(new Color(163,73,164));
                defaults.setBackground(new Color(163,73,164));
                server.setBackground(new Color(163,73,164));
                Music music = new Music();
                music.execute();
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().remove(userInfoPanel);
                getPanel().remove(tankPanel);
                getPanel ().remove (serversListPanelInSetting);
                //Todo uncomment next 2 lines
                //getPanel().remove(your panel);
                //getPanel().add(your panel , BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }

            if(e.getSource().equals(tank))
            {
                rank.setBackground(new Color(163,73,164));
                userInfo.setBackground(new Color(163,73,164));
                tank.setBackground(new Color(78,35,78));
                defaults.setBackground(new Color(163,73,164));
                server.setBackground(new Color(163,73,164));
                Music music = new Music();
                music.execute();
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().remove(userInfoPanel);
                getPanel().remove(tankPanel);
                getPanel ().remove (serversListPanelInSetting);
                //Todo uncomment next line
                //getPanel().remove(your panel);
                getPanel().add(tankPanel,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(userInfo))
            {
                rank.setBackground(new Color(163,73,164));
                userInfo.setBackground(new Color(78,35,78));
                tank.setBackground(new Color(163,73,164));
                defaults.setBackground(new Color(163,73,164));
                server.setBackground(new Color(163,73,164));
                Music music = new Music();
                music.execute();
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().remove(userInfoPanel);
                getPanel().remove(tankPanel);
                getPanel ().remove (serversListPanelInSetting);
                //Todo uncomment next line
                //getPanel().remove(your panel);
                getPanel().add(userInfoPanel,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(defaults))
            {
                rank.setBackground(new Color(163,73,164));
                defaults.setBackground(new Color(78,35,78));
                tank.setBackground(new Color(163,73,164));
                userInfo.setBackground(new Color(163,73,164));
                server.setBackground(new Color(163,73,164));
                Music music = new Music();
                music.execute();
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().remove(userInfoPanel);
                getPanel().remove(tankPanel);
                getPanel ().remove (serversListPanelInSetting);
                //Todo uncomment next line
                //getPanel().remove(your panel);
                getPanel().add(defaultsPanel,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(server))
            {
                rank.setBackground(new Color(163,73,164));
                server.setBackground(new Color(78,35,78));
                tank.setBackground(new Color(163,73,164));
                defaults.setBackground(new Color(163,73,164));
                userInfo.setBackground(new Color(163,73,164));
                Music music = new Music();
                music.execute();
                getPanel().remove(pictureJLabel);
                getPanel().remove(defaultsPanel);
                getPanel().remove(userInfoPanel);
                getPanel().remove(tankPanel);
                getPanel ().remove (serversListPanelInSetting);
                //Todo uncomment next line
                //getPanel().remove(your panel);
                getPanel().add(serversListPanelInSetting,BorderLayout.CENTER);
                getPanel().setVisible(false);
                getPanel().setVisible(true);
            }
            if(e.getSource().equals(setDefault))
            {
                Music music = new Music();
                music.execute();
                sliderTank.setValue(user.getDefaultTankStamina ());
                sliderCanon.setValue(user.getDefaultCanonPower ());
                sliderWall.setValue(user.getDefaultWallStamina ());
            }
            if(e.getSource().equals(save))
            {
                Music music = new Music();
                music.execute();
                user.setDefaultTankStamina (sliderTank.getValue ());
                user.setDefaultCanonPower (sliderCanon.getValue ());
                user.setDefaultWallStamina (sliderWall.getValue ());
            }
            if(e.getSource().equals(back))
            {
                Music music = new Music();
                music.execute();
                frame.setContentPane(pre);
                frame.setVisible(false);
                frame.setVisible(true);
            }
            if (e.getSource () == createNewServer)
            {
                Music music = new Music();
                music.execute();
                frame.setContentPane (new CreateNewServer (frame, (ServerListPanel)serverListPanel,
                        getPanel (),user));
                frame.setVisible(false);
                frame.setVisible(true);
            }

            if (e.getSource () == removeServer)
            {
                Music music = new Music();
                music.execute();
                ServerListPanel serverListPanel2 = (ServerListPanel)serverListPanel;
                for (ServerButtonPanel serverButtonPanel : serverListPanel2.getServerButtonPanels ())
                    if (serverButtonPanel.isSelected ())
                    {
                        String ans = JOptionPane.showInputDialog ("Enter ServerInformation Password");
                        if (ans != null)
                        {
                            if (Arrays.equals (serverButtonPanel.getServerInformation ().getPassword (),
                                    ans.toCharArray ()))
                            {
                                serverListPanel2.removeServer (serverButtonPanel);
                                connect ();
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog (getPanel (),"Password is Wrong",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
            }

        }

        /**
         * Mouse handler of the setting panel
         * @param e Mouse event
         */
        @Override
        public void mouseEntered(MouseEvent e)
        {
//            if(e.getSource().equals(userInfo) || e.getSource().equals(server) || e.getSource().equals(defaults))
//            {
//                if(e.getSource().equals(userInfo))
//                    temp = userInfo;
//                if(e.getSource().equals(server))
//                    temp = server;
//                if(e.getSource().equals(defaults))
//                    temp = defaults;
//                Thread a = new Thread(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        for(int i=0;i<4;i++)
//                        {
//
//                            switch (i)
//                            {
//                                case 0 : temp.setBackground(new Color(141,63,141));
//                                    break;
//                                case 1 : temp.setBackground(new Color(118,52,118));
//                                    break;
//                                case 2 : temp.setBackground(new Color(78,35,78));
//                                    break;
//                                case 3 : temp.setBackground(new Color(63,29,63));
//                                    break;
//                            }
//                            try
//                            {
//                                Thread.sleep(50);
//                            }
//                            catch (InterruptedException ex)
//                            {
//                                ex.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                a.start();
//            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
//            if(e.getSource().equals(userInfo) || e.getSource().equals(server) || e.getSource().equals(defaults))
//            {
//                if(e.getSource().equals(userInfo))
//                    temp = userInfo;
//                if(e.getSource().equals(server))
//                    temp = server;
//                if(e.getSource().equals(defaults))
//                    temp = defaults;
//                Thread a = new Thread(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        for(int i=0;i<4;i++)
//                        {
//
//                            switch (i)
//                            {
//                                case 0 : temp.setBackground(new Color(78,35,78));
//                                    break;
//                                case 1 : temp.setBackground(new Color(118,52,118));
//                                    break;
//                                case 2 : temp.setBackground(new Color(141,63,141));
//                                    break;
//                                case 3 : temp.setBackground(new Color(163,73,164));
//                                    break;
//                            }
//                            try
//                            {
//                                Thread.sleep(50);
//                            }
//                            catch (InterruptedException ex)
//                            {
//                                ex.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                a.start();
//            }
        }
    }

    private void connect ()
    {
        LogConnector logConnector = new LogConnector ("127.0.0.1","Logout",user);
        new Thread (logConnector).start ();
    }


}
