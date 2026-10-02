package DefinitelyStolenGame;
import java.util.*;

public final class ToolClass
{
    public static void print(String txt)
    {
        System.out.print(txt);
    }


    public static void println(String txt)
    {
        System.out.println(txt);
    }
    
    public static int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }


    @SuppressWarnings("resource")
    public static String askName()
    {
        String name = "";
        while (true)
        {
            println("\nEnter the name of your Shop:");
            try
            {
                name = new Scanner(System.in).nextLine();

            }
            catch (NullPointerException e)
            {
                println("Please enter a non-blank name.");
            }
            catch (InputMismatchException e)
            {
                println("Please enter a valid name.");
            }
            if (name.length() > 20)
            {
                println("Please enter a name less than 20 characters.");
            }
            else if (name.length() == 0)
            {
                println("Please enter a non-blank name.");
            }
            else
            {
                break;
            }
        }
        return name;
    }


    public static int askOption(String prompt, int min, int max)
    {
        int option = -1;
        while (true)
        {
            println(prompt);
            print("\n> ");
            try
            {
                option = new Scanner(System.in).nextInt();
            }
            catch (NullPointerException e)
            {
                println("Please enter a number.");
                continue;
            }
            catch (InputMismatchException e)
            {
                println("Please enter a valid integer.");
                continue;
            }
            if (option < min)
            {
                println("Please enter an integer no less than " + min + ".");
            }
            else if (option > max)
            {
                println("Please enter an integer no more than " + max + ".");
            }
            else {
                break;
            }
        }
        return option;
    }
}
