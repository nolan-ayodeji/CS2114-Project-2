package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;

public class Main
{
    private static String shopName;
    private static TradingSystem tradingSystem;
    private static int day;
    private static LinkedChain<Item> inventory;
    private static int visitorNum;
    private static int visitorLeft;
    
    public static void main(String[] args)
    {
        try
        {
            runGame();
        }
        catch (java.util.NoSuchElementException e)
        {
            println("Input closed. Game ended.");
        }
    }

    private static void runGame()
    {
        shopName = askName();
        day = 1;
        visitorNum = 5;
        inventory = new LinkedChain<>();
        initializeInventory();
        tradingSystem = new TradingSystem(inventory, 500);

        GameSystem gameSystem = new GameSystem(visitorNum);
        while (true)
        {
            // TODO: Add events that happen before the shop opens each day.
            visitorLeft = visitorNum;
            displayShop();
            askPlayerAction(new String[] { "Open the shop." });
            gameSystem.startDay();

            while (gameSystem.hasVisitors())
            {
                displayShop();
                askPlayerAction(new String[] { "Next Customer" });
                Visitor visitor = gameSystem.nextVisitor();
                visitorLeft--;
                println(visitor.getName() + " arrived.");
                tradingSystem.serveVisitor(visitor);
            }

            println("All visitors are done.");
            String[] closeOption = {"Close the shop."};
            askPlayerAction(closeOption);
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
        return tradingSystem.askPlayerAction(temporaryOptions);
    }

    /**
     * Displays the items currently owned by the player.
     */
    public static void displayInventory()
    {
        tradingSystem.browseInventory();
    }
    
    public static void displayShop() {
        println("[" + shopName + "]   Day: " + day);
        println("" + visitorLeft + " customers waiting.");
    }
    
    
    public static void initializeInventory() {
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.BANDAGE));
        inventory.add(new Item(ItemLibrary.TOOTHPASTE));
    }
}
