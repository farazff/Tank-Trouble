package GUI;

import GameData.User;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class ListOfUsers extends JPanel
{



    public ListOfUsers (ArrayList<User> users)
    {
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
            StringBuilder s = new StringBuilder (" " + j + ") " + user.getUserName ());
            j++;
            s.append (" ".repeat (Math.max (0, 20 - s.toString ().length ())));
            s.append ("Wins: ").append (user.getNumOfWinMultiGames ());
            JLabel label = new JLabel (s.toString ());
            label.setFont (new Font ("arial",Font.PLAIN,14));
            add(label);
            add (new JLabel ("                     "));
            add (new JLabel ("                     "));
        }
    }

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
