package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;

/**
 * Manages the visitors for one day of the game.
 */
public class GameSystem
{
    private int visitorNum;
    private LinkedChain<Visitor> visitors;

    public GameSystem(int visitorNum)
    {
        this.visitorNum = visitorNum;
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
        // Generate buyer or seller randomly
        if (randomInt(0, 1) == 0)
        {
            String name = "Lower District Resident";
            String need = randomNeed();
            int budget = randomInt(50, 200);
            String dialogue = "I need some " + need + ". Do you have any? I only have " + budget + " bucks.";
            return new Buyer(name, dialogue, need, budget);
        }

        LinkedChain<Item> sellerInventory = new LinkedChain<>();
        ItemLibrary[] availableItems = ItemLibrary.values();
        for (int i = 0; i < 3; i++)
        {
            int itemIndex = randomInt(0, availableItems.length - 1);
            sellerInventory.add(new Item(availableItems[itemIndex]));
        }
        String name = "Scavenger";
        String dialogue = "I found some good stuff, want to take a look? I’ll give you 30% off the wholesale price if you buy everything.";
        return new Seller(name, dialogue, sellerInventory);
    }
}
