package DefinitelyStolenGame;

import static DefinitelyStolenGame.ToolClass.randomInt;

/**
 * Manages the visitors for one day of the game.
 */
public class GameSystem
{
    private static int visitorNum = 5;
    private LinkedChain<Visitor> visitors;

    public GameSystem()
    {
        visitors = new LinkedChain<>();
    }

    /**
     * Creates a new set of visitors at the start of each day.
     */
    public void startDay()
    {
        visitors.clear();
        for (int i = 1; i <= visitorNum; i++)
        {
            visitors.addToEnd(generateRandomVisitor(i));
        }
    }

    public boolean hasVisitors()
    {
        return !visitors.isEmpty();
    }

    /**
     * Takes the next visitor from today's waiting list.
     *
     * @return the next visitor, or null if none remain
     */
    public Visitor nextVisitor()
    {
        return visitors.remove();
    }

    /**
     * Creates a buyer or seller with data for this visit.
     */
    private Visitor generateRandomVisitor(int number)
    {
        String name = "Visitor " + number;
        String dialogue = ""; // Dialogue will be added later
        
        // Generate buyer or seller randomly
        if (randomInt(0, 1) == 0)
        {
            return new Buyer(name, dialogue, randomInt(50, 200));
        }

        LinkedChain<Item> sellerInventory = new LinkedChain<>();
        ItemLibrary[] availableItems = ItemLibrary.values();
        for (int i = 0; i < 3; i++)
        {
            int itemIndex = randomInt(0, availableItems.length - 1);
            sellerInventory.add(new Item(availableItems[itemIndex]));
        }
        return new Seller(name, dialogue, sellerInventory);
    }
}
