package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;

/**
 * Manages the visitors for one day of the game.
 */
public class GameSystem
{
    private int visitorNum;
    private LinkedChain<Visitor> visitors;
    private final SpecialCustomer landowner;

    public GameSystem(int visitorNum)
    {
        this.visitorNum = visitorNum;
        visitors = new LinkedChain<>();
        landowner = new SpecialCustomer(SCList.LANDOWNER);
    }

    /**
     * Creates a new set of visitors at the start of each day.
     */
    public void startDay()
    {
        startDay(1);
    }

    /** Creates normal visitors and appends the landowner every seventh day. */
    public void startDay(int day)
    {
        if (day < 1)
        {
            throw new IllegalArgumentException("Day must be positive.");
        }
        visitors.clear();
        for (int i = 1; i <= visitorNum; i++)
        {
            visitors.addToEnd(generateRandomVisitor(i));
        }
        if (day % 7 == 0)
        {
            visitors.addToEnd(landowner);
        }
    }

    public int getWaitingCount()
    {
        return visitors.size();
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
            String dialogue = "Hey, got " + describeNeed(need) + "? I've got "
                + budget + "G to spend.";
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
        String dialogue = "Picked up some good stuff. Wanna take a look?";
        return new Seller(name, dialogue, sellerInventory);
    }

    /** Gives spoken names to the categories used for item matching. */
    private String describeNeed(String need)
    {
        switch (need)
        {
            case "Food":
                return "something to eat";
            case "Daily Necessity":
                return "a few everyday basics";
            case "Medical":
                return "any medical supplies";
            case "Electronic":
                return "any electronics";
            default:
                return need.toLowerCase();
        }
    }
}
