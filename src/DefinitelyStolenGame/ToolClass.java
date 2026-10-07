package DefinitelyStolenGame;
import java.util.*;

// -------------------------------------------------------------------------
/**
 *  Helper class with a few methods for typing convenience
 * 
 *  @author chens
 *  @version 2026.10.2
 */
public final class ToolClass
{
    private static ArrayList<String> shopOptions = new ArrayList<String>();
    private static final Scanner input = new Scanner(System.in);
    
    static
    {
        shopOptions.add("Check Inventory");
        shopOptions.add("Check Coins");
    }
    
    // ----------------------------------------------------------
    /**
     * Quick print without next line
     * @param txt
     */
    public static void print(String txt)
    {
        System.out.print(txt);
    }


    // ----------------------------------------------------------
    /**
     * Quick println with next line
     * @param txt
     */
    public static void println(String txt)
    {
        System.out.println(txt);
    }

    /** Uses one scanner so repeated menus do not lose buffered input. */
    public static String readInput() {
        return input.nextLine().trim();
    }


    public static void addOptions(String opt) {
        shopOptions.add(opt);
    }
    
    public static String[] getOptions() {
        String[] currOpt = shopOptions.toArray(new String[0]);
        return currOpt;
    }
    
    public static String[] getTempOptions(String[] tempOpt) {
        String[] currOpt = shopOptions.toArray(new String[0]);
        String[] newOpt = new String[tempOpt.length + currOpt.length];
        for (int i = 0; i < tempOpt.length; i++) {
            newOpt[i] = tempOpt[i];
        }
        for (int i = 0; i < currOpt.length; i++) {
            newOpt[i + tempOpt.length] = currOpt[i];
        }
        return newOpt;
    }
    
    // ----------------------------------------------------------
    /**
     * Quick random integer generator
     * @param min
     * @param max
     * @return random number
     */
    public static int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
 // ----------------------------------------------------------
    /**
     * Quick random need generator
     * @return String need
     */
    public static String randomNeed() {
        String[] allNeeds = {"Food", "Daily Necessity", "Medical", "Electronic"};
        int rndIndex = randomInt(0, allNeeds.length - 1);
        return allNeeds[rndIndex];
    }


    // ----------------------------------------------------------
    /**
     * Prompt user to enter a name for the shop
     * @return name
     */
    @SuppressWarnings("resource")
    public static String askName()
    {
        String name = "";
        while (true)
        {
            println("Enter the name of your Shop:");
            try
            {
                name = readInput();

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


    // ----------------------------------------------------------
    /**
     * Prompt user to type in an integer as choosing options
     * @param prompt
     * @param min
     * @param max
     * @return the option #
     */
    @SuppressWarnings("resource")
    public static int askOption(String prompt, int min, int max)
    {
        int option = -1;
        while (true)
        {
            println(prompt);
            print("> ");
            try
            {
                option = Integer.parseInt(readInput());
            }
            catch (NullPointerException e)
            {
                println("Please enter a number.");
                continue;
            }
            catch (NumberFormatException e)
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
    
    @SuppressWarnings("resource")
    public static int askOption(String[] options, int min, int max)
    {
        int option = -1;
        while (true)
        {
            for (int i = 0; i < options.length; i++) {
                println("[" + (i + 1) + "] " + options[i]);
            }
            print("\n> ");
            try
            {
                option = Integer.parseInt(readInput());
            }
            catch (NullPointerException e)
            {
                println("Please enter a number.");
                continue;
            }
            catch (NumberFormatException e)
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
