package GUI;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * the loading page of the game
 * it will be shown when the game is staring
 */

public class Loading extends JPanel
{
    private JFrame frame;
    private JPanel nex;
    private JProgressBar progress;
    private Clip clip;

    /**
     * the constructor of the Loading cldss
     * @param frame the mai  frame of the game
     */
    public Loading(JFrame frame)
    {
        this.frame = frame;
        this.setPreferredSize(new Dimension(900,600));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0,60,60,60));

        progress = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
        progress.setStringPainted(true);
        progress.setString("Loading...");
        progress.setFont(new Font("Arial",Font.ITALIC,20));
        progress.setPreferredSize(new Dimension(600,40));

        this.add(progress,BorderLayout.SOUTH);


        Music music = new Music();
        music.setFilePath("Files/Sounds/World-of-Tanks.au",true);
        music.execute();
    }

    /**
     * set the next page after loading finishes
     * @param nex the nex JPanel that will be shown on the main frame
     */
    public void setNex(JPanel nex)
    {
        this.nex = nex;
    }

    /**
     * the JProgressBar on the loading will b filled by this method
     */
    public void fill()
    {
        int i = 0;
        try
        {
            while(i<=100)
            {
                // fill the menu bar
                progress.setValue(i);
                Thread.sleep(13);
                i += 1;
            }
            frame.setContentPane(nex);
            frame.setVisible(false);
            frame.setVisible(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        try
        {
            g.drawImage (ImageIO.read (new File("Images/Loading.jpg")).
                    getScaledInstance (getWidth (),getHeight (),Image.SCALE_FAST),0,0,this);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }
}