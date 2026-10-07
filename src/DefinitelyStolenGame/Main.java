package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;

public class Main
{
    private static String shopName;
    private static int coin;
    private static int day;
    private static LinkedChain<Item> inventory;
    private static int visitorNum;
    private static int visitorLeft;
    
    public static void main(String[] args)
    {
        shopName = askName();
        coin = 500;
        day = 1;
        visitorNum = 5;
        inventory = new LinkedChain<>();
        initializeInventory();

        GameSystem gameSystem = new GameSystem(visitorNum);
        while (true)
        {
            // TODO: Add events that happen before the shop opens each day.
            gameSystem.startDay();
            visitorLeft = visitorNum;

            while (gameSystem.hasVisitors())
            {
                Visitor visitor = gameSystem.nextVisitor();
                println(visitor.getName() + " arrived.");
                // TODO: Add dialogue and trading actions for this visitor.
                askPlayerAction(new String[0]);
                visitorLeft--;
            }

            println("All visitors are done.");
            String[] closeOption = {"Close the shop."};
            while (askPlayerAction(closeOption) != 1)
            {
                println("There are no more customers. Close the shop to continue.");
            }
            // TODO: Add events that happen after the player closes the shop.
            day++;
        }
    }

    /**
     * Asks for an action, then returns it to the current game scene.
     * Checking inventory keeps the player in the same scene.
     *
     * @param temporaryOptions actions available only in the current scene
     * @return the one-based number of the chosen action
     */
    public static int askPlayerAction(String[] temporaryOptions)
    {
        String[] options = getTempOptions(temporaryOptions);
        while (true)
        {
            int choice = askOption(options, 1, options.length);
            if ("Check Inventory".equals(options[choice - 1]))
            {
                displayInventory();
            }
            else
            {
                return choice;
            }
        }
    }

    /**
     * Displays the items currently owned by the player.
     */
    public static void displayInventory()
    {
        println("Inventory:");
        if (inventory.isEmpty())
        {
            println("(empty)");
            return;
        }
        for (int i = 0; i < inventory.size(); i++)
        {
            Item item = inventory.get(i);
            println((i + 1) + ". " + item.getName() + " - $" + item.getValue());
        }
    }
    
    public static void displayShop() {
        println("[" + shopName + "]   Day: " + day);
        println("" + visitorLeft + " customer left.");
    }
    
    
    public static void initializeInventory() {
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.BANDAGE));
        inventory.add(new Item(ItemLibrary.TOOTHPASTE));
    }
}
