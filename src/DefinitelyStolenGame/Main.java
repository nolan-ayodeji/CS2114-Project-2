package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;

public class Main
{
    private static String shopName;
    private static int coin;
    private static int day;
    private static LinkedChain<Item> inventory;
    
    public static void main(String[] args)
    {
        shopName = askName();
        coin = 500;
        day = 1;
        inventory = new LinkedChain<>();
        initializeInventory();

        GameSystem gameSystem = new GameSystem();
        while (true)
        {
            // TODO: Add events that happen before the shop opens each day.
            println("Day " + day);
            gameSystem.startDay();

            while (gameSystem.hasVisitors())
            {
                Visitor visitor = gameSystem.nextVisitor();
                println(visitor.getName() + " arrived ("
                    + visitor.getClass().getSimpleName() + ").");
                // TODO: Add dialogue and trading before moving to the next visitor.
            }

            int choice = askOption("All visitors are done.\n"
                + "1. Close shop and start the next day\n"
                + "2. Close shop and quit", 1, 2);
            // TODO: Add events that happen after the player closes the shop.
            if (choice == 2)
            {
                break;
            }
            day++;
        }
    }
    
    public static void initializeInventory() {
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.BANDAGE));
        inventory.add(new Item(ItemLibrary.TOOTHPASTE));
    }
}
