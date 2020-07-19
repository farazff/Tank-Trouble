package GUI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Music extends SwingWorker<Long, Object>
{
    Clip clip;

    public void start()
    {
        clip.start();
    }

    @Override
    protected Long doInBackground()
    {
        try
        {
            AudioInputStream audioInputStream;
            String filePath = "Files/Sounds/Button.au";
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            start();
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
