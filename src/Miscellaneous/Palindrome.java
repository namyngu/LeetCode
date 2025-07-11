package Miscellaneous;
import java.util.*;

// You have been given a string s that contains n characters.
//
// You need to write an algorithm that will check if the string is a palindrome. It will return true if it is, and false otherwise.
//
// A palindrome is a string that is written the same forward and backward e.g. Kayak, Hannah, Anna, etc.
//
// Your algorithm must use a Queue or a Queue derived datatype. Select the most appropriate so that the complexity is as low as possible. Justify your choice and the associated complexity of your algorithm.


/**
 *Class to check if a String is a palindrome.
 *@author Hoang Nguyen
 */
public class Palindrome
{
    private ArrayDeque<Character> deque;

    public Palindrome()
    {
        deque = new ArrayDeque<Character>();
    }

     //Method to check if a string is palindrome
    public boolean checkPal(String string)
    {
        int n = string.length();
        String str = string.toLowerCase();

        //Add each character in the string to the deque.
        for (int i = 0; i < n; i++)
        {
            deque.addLast(str.charAt(i));
        }

        //If string is of even length
        if (n % 2 == 0)
        {
            while (!deque.isEmpty())
            {
                if (deque.pollFirst() != deque.pollLast())          //Compares the first and last character of the string.
                    return false;
            }
            return true;
        }
        //If string is of odd length
        else
        {
            while (deque.size() > 1)
            {
                if (deque.pollFirst() != deque.pollLast())         //Compares the first and last character of the string.
                    return false;
            }
            return true;
        }
    }

    /**
     *The main method which begins the program execution
     *@param args        An array of string passed in as command line parameters.
     */
    public static void main(String[] args)
    {
        Palindrome pal = new Palindrome();
        pal.startProg();
    }

    /**
     *Method to start the Palindrome program.
     */
    public void startProg()
    {
        int n;
        String str = "";
        do
        {
            Scanner console = new Scanner(System.in);
            System.out.println("Enter String: ");
            str = console.nextLine().trim();

            if (str.length() < 1)
                System.out.println("Invalid string, try again.");
        }
        while (str.length() < 1);

        if (checkPal(str))
            System.out.println(str + " is a palindrome");
        else
            System.out.println(str + " is NOT a palindrome!");
    }
}
