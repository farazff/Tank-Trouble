package GUI;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Loading extends JPanel
{
    private JProgressBar progress;
    private Clip clip;

    public Loading()
    {
        this.setPreferredSize(new Dimension(900,600));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0,60,60,60));

        progress = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
        progress.setStringPainted(true);
        progress.setString("Loading...");
        progress.setFont(new Font("Arial",Font.ITALIC,20));
        progress.setPreferredSize(new Dimension(600,40));

        this.add(progress,BorderLayout.SOUTH);
//        startMusic();
//        clip.start();
    }


//    public void startMusic()
//    {
//        Long currentFrame;
//
//        // current status of clip
//        String status;
//        try
//        {
//            AudioInputStream audioInputStream;
//            String filePath = "C:\\Users\\ffara\\Downloads\\;
//
//            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
//
//            // create clip reference
//            clip = AudioSystem.getClip();
//
//            // open audioInputStream to the clip
//            clip.open(audioInputStream);
//
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        }
//        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
//        {
//            e.printStackTrace();
//        }
//
//    }

    public void fill()
    {
        Thread a = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int i = 0;
                try
                {
                    while(i<=100)
                    {
                        // fill the menu bar
                        progress.setValue(i);

                        // delay the thread
                        Thread.sleep(300);
                        i += 10;
                    }
                }
                catch (Exception e)
                {

                }
            }
        });
        a.run();
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