package GUI;

import GameData.User;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * this panel is for ranking of players
 */
public class ListOfUsers extends JPanel
{


    /**
     * creates list of players
     * @param users users
     */
    public ListOfUsers (ArrayList<User> users)
    {
        setBackground (Color.GRAY);
        setLayout (new BoxLayout (this,BoxLayout.Y_AXIS));
        ArrayList<User> userArrayList = new ArrayList<> (users);
        User[] users1 = new User[userArrayList.size ()];
        for (int i = 0; i < userArrayList.size (); i++)
            users1[i] = userArrayList.get (i);
        sort (users1,0,users1.length - 1);
        add (new JLabel ("                     "));
        int j = 1;
        for (User user : users1)
        {
            JPanel panel = new JPanel (new GridLayout (1,5));
            panel.setPreferredSize (new Dimension (1000,45));
            panel.setMinimumSize (new Dimension (1000,45));
            panel.setMaximumSize (new Dimension (1000,45));
            panel.setBackground (Color.GRAY);
            JLabel label1 = new JLabel (j + " ) ");
            label1.setFont (new Font ("arial",Font.PLAIN,18));
            label1.setForeground (new Color(74,201,255));
            label1.setHorizontalTextPosition (SwingConstants.CENTER);
            label1.setHorizontalAlignment (SwingConstants.CENTER);
            JLabel label2 = new JLabel (user.getUserName ());
            label2.setFont (new Font ("arial",Font.PLAIN,18));
            label2.setForeground (new Color(74,201,255));
            label2.setHorizontalTextPosition (SwingConstants.CENTER);
            label2.setHorizontalAlignment (SwingConstants.CENTER);
            JLabel label3 = new JLabel ("Win : " + user.getNumOfWinMultiGames ());
            label3.setFont (new Font ("arial",Font.PLAIN,18));
            label3.setForeground (new Color(74,201,255));
            label3.setHorizontalTextPosition (SwingConstants.CENTER);
            label3.setHorizontalAlignment (SwingConstants.CENTER);
            JLabel label4 = new JLabel ("                  ");
            label4.setFont (new Font ("arial",Font.PLAIN,18));
            label4.setHorizontalTextPosition (SwingConstants.CENTER);
            label4.setHorizontalAlignment (SwingConstants.CENTER);
            panel.add(label1);
            panel.add (label2);
            panel.add (label4);
            panel.add (label3);
            panel.add (label4);
            add (panel);
            j++;
        }
    }

    /**
     * change elements in quick sort Algorithm
     * @param arr arr
     * @param low low
     * @param high high
     * @return last index
     */
    private int part(User[] arr, int low,int high)
    {
        int pivot = arr[high].getNumOfWinMultiGames (), i = (low-1);
        {
            for (; low<high; low++)
            {
                if (arr[low].getNumOfWinMultiGames () > pivot)
                {
                    i++;
                    User temp = arr[i];
                    arr[i] = arr[low];
                    arr[low] = temp;
                }
            }
            User temp = arr[++i];
            arr[i] = arr[high];
            arr[high] = temp;
        }
        return i;
    }

    /**
     * quick sort Algorithm
     * @param arr arr
     * @param low low
     * @param high high
     */
    private void sort(User[] arr, int low,int high)
    {
        if (low < high)
        {
            int pi = part(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }


}
