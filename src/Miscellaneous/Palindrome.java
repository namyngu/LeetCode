package Miscellaneous;
import java.util.*;
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

    /**
     *Method to check if a string is palindrome
     *@param string      A string to check if it's a palindrome.
     *@param n           The size of the string.
     *@return            Returns true if string is a palindrome, otherwise returns false.
     */
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
