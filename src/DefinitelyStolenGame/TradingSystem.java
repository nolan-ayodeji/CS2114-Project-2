package DefinitelyStolenGame;

import static DefinitelyStolenGame.ToolClass.*;

/** Handles the player's money, purchases, sales, and counter menus. */
public class TradingSystem
{
    private LinkedChain<Item> inventory;
    private int coins;
    private LinkedChain<Item> activeListing;

    public TradingSystem(LinkedChain<Item> inventory, int coins)
    {
        this.inventory = inventory;
        this.coins = coins;
    }

    public int getCoins()
    {
        return coins;
    }

    /** Inventory and money are available only on the shop screen. */
    public int askPlayerAction(String[] temporaryOptions)
    {
        String[] options = getTempOptions(temporaryOptions);
        while (true)
        {
            int choice = askOption(options, 1, options.length);
            String action = options[choice - 1];
            if ("Check Inventory".equals(action))
            {
                displayInventory(activeListing);
            }
            else if ("Check Coins".equals(action))
            {
                println("Coins: " + coins + "G");
            }
            else
            {
                return choice;
            }
        }
    }

    public void serveVisitor(Visitor visitor)
    {
        try
        {
            if (visitor instanceof Seller)
            {
                serveSeller((Seller)visitor);
            }
            else if (visitor instanceof Buyer)
            {
                serveBuyer((Buyer)visitor);
            }
        }
        finally
        {
            activeListing = null;
        }
    }

    private void serveSeller(Seller seller)
    {
        String dialogue = seller.getDialogue();
        while (!seller.getItems().isEmpty())
        {
            seller.say(dialogue);
            seller.say("Buy the whole lot and I'll take 30% off. That's "
                + seller.getWholesalePrice() + "G in total.");
            int action = askPlayerAction(new String[] {
                "View Counter", "Dismiss Customer"});
            if (action == 2)
            {
                return;
            }
            boolean purchased = sellerCounter(seller);
            if (purchased)
            {
                dialogue = "Good choice! Anything else you'd like?";
            }
        }
        seller.say("Nice! Thanks for taking the lot. See you around!");
        println(seller.getName() + " left.");
    }

    /** Returns to the shop after a successful purchase or an explicit exit. */
    private boolean sellerCounter(Seller seller)
    {
        while (true)
        {
            seller.show();
            println("Enter item numbers to buy. Separate multiple item numbers"
                + " with commas, without spaces (e.g. 1,2,3).");
            println("[0] Buy everything at the wholesale price\n[-1] Exit Counter");
            String input = readInput();
            if ("-1".equals(input))
            {
                return false;
            }
            Item[] items = seller.getItems().toArray(
                new Item[seller.getItems().size()]);
            try
            {
                boolean[] selected = parseSelection(input, items.length,
                    "0".equals(input));
                LinkedChain<Item> purchase = new LinkedChain<>();
                for (int i = 0; i < items.length; i++)
                {
                    if (selected[i])
                    {
                        purchase.add(items[i]);
                    }
                }
                int before = coins;
                if (buyItems(seller, purchase))
                {
                    println("Paid " + (before - coins) + "G. Coins: " + coins + "G");
                    return true;
                }
                println("You do not have enough coins. Nothing was purchased.");
            }
            catch (IllegalArgumentException e)
            {
                println("Invalid selection. Use the displayed item numbers.");
            }
        }
    }

    /** Validates the entire purchase before moving any money or goods. */
    public boolean buyItems(Seller seller, LinkedChain<Item> purchase)
    {
        Item[] items = purchase.toArray(new Item[purchase.size()]);
        if (items.length == 0)
        {
            return false;
        }
        LinkedChain<Item> checked = new LinkedChain<>();
        int price = 0;
        for (Item item : items)
        {
            if (!seller.getItems().contains(item) || checked.contains(item))
            {
                return false;
            }
            checked.add(item);
            price += item.getValue();
        }
        if (items.length == seller.getItems().size())
        {
            price = seller.getWholesalePrice();
        }
        if (price > coins)
        {
            return false;
        }
        for (Item item : items)
        {
            seller.getItems().remove(item);
            inventory.add(item);
        }
        coins -= price;
        return true;
    }

    private void serveBuyer(Buyer buyer)
    {
        // Listing state lasts for this visit, including exits from either screen.
        LinkedChain<Item> listed = new LinkedChain<>();
        activeListing = listed;
        String dialogue = buyer.getDialogue();
        while (buyer.getBudget() > 0)
        {
            buyer.say(dialogue);
            println("Remaining budget: " + buyer.getBudget() + "G");
            int action = askPlayerAction(new String[] {
                "Stock Counter", "Dismiss Customer" });
            if (action == 2)
            {
                return;
            }
            if (buyerCounter(buyer, listed))
            {
                dialogue = "Thanks! Got anything else I might need?";
            }
        }
        buyer.say("Thanks! That's all I can spend today. See you around!");
        println(buyer.getName() + " left.");
    }

    private boolean buyerCounter(Buyer buyer, LinkedChain<Item> listed)
    {
        while (true)
        {
            displayInventory(listed);
            println("Enter item numbers to list or unlist. Separate multiple item"
                + " numbers with commas, without spaces (e.g. 1,2,3).");
            println("[0] Review Transaction\n[-1] Exit Counter");
            String input = readInput();
            if ("-1".equals(input))
            {
                return false;
            }
            if ("0".equals(input))
            {
                if (listed.isEmpty())
                {
                    println("No items are listed yet.");
                    continue;
                }
                return reviewSale(buyer, listed);
            }
            Item[] items = inventory.toArray(new Item[inventory.size()]);
            try
            {
                boolean[] selected = parseSelection(input, items.length, false);
                for (int i = 0; i < items.length; i++)
                {
                    if (!selected[i])
                    {
                        continue;
                    }
                    Item item = items[i];
                    if (listed.contains(item))
                    {
                        listed.remove(item);
                    }
                    else if (!buyer.getNeed().equals(item.getType()))
                    {
                        buyer.say("I don't need " + item.getName() + " right now.");
                    }
                    else
                    {
                        listed.add(item);
                    }
                }
            }
            catch (IllegalArgumentException e)
            {
                println("Invalid selection. Use the displayed item numbers.");
            }
        }
    }

    /** -1 returns to the shop, leaving the listing intact. */
    private boolean reviewSale(Buyer buyer, LinkedChain<Item> listed)
    {
        while (true)
        {
            int total = 0;
            for (Item item : listed.toArray(new Item[listed.size()]))
            {
                println(item.getName() + " " + item.getSellingPrice() + "G");
                total += item.getSellingPrice();
            }
            println("Total: " + total + "G");
            int action = askOption("[1] Confirm Transaction\n[-1] Exit Transaction",
                -1, 1);
            if (action == -1)
            {
                return false;
            }
            if (action == 0)
            {
                println("Please enter 1 or -1.");
                continue;
            }
            if (total > buyer.getBudget())
            {
                buyer.say("I've only got " + buyer.getBudget()
                    + "G left. That's all I can pay, even for the whole lot."
                    + " Still want to sell it all?");
                if (askOption("[1] Confirm Transaction\n[2] Cancel Transaction",
                    1, 2) == 2)
                {
                    continue;
                }
            }
            int before = coins;
            if (sellItems(buyer, listed, true))
            {
                println("Received " + (coins - before) + "G. Coins: " + coins + "G");
                return true;
            }
            println("The sale could not be completed.");
            return false;
        }
    }

    /** Sells all listed items, with explicit consent to accept a budget cap. */
    public boolean sellItems(Buyer buyer, LinkedChain<Item> listed,
        boolean acceptBudgetLimit)
    {
        if (listed.isEmpty() || buyer.getBudget() <= 0)
        {
            return false;
        }
        int total = 0;
        LinkedChain<Item> checked = new LinkedChain<>();
        Item[] items = listed.toArray(new Item[listed.size()]);
        for (Item item : items)
        {
            if (!inventory.contains(item) || checked.contains(item)
                || !buyer.getNeed().equals(item.getType()))
            {
                return false;
            }
            checked.add(item);
            total += item.getSellingPrice();
        }
        if (total > buyer.getBudget() && !acceptBudgetLimit)
        {
            return false;
        }
        int payment = Math.min(total, buyer.getBudget());
        for (Item item : items)
        {
            inventory.remove(item);
        }
        buyer.spend(payment);
        coins += payment;
        listed.clear();
        return true;
    }

    /** Each physical item has its own number, even when names are identical. */
    public void displayInventory(LinkedChain<Item> listed)
    {
        println("Inventory:");
        Item[] items = inventory.toArray(new Item[inventory.size()]);
        if (items.length == 0)
        {
            println("(empty)");
        }
        for (int i = 0; i < items.length; i++)
        {
            Item item = items[i];
            String marker = listed != null && listed.contains(item) ? " (Listed)" : "";
            println("[" + (i + 1) + "] " + item.getName() + " "
                + item.getValue() + "G" + marker);
        }
    }

    /** Validates comma-separated numbers before the caller changes any state. */
    private boolean[] parseSelection(String text, int size, boolean selectAll)
    {
        boolean[] selected = new boolean[size];
        if (selectAll)
        {
            java.util.Arrays.fill(selected, true);
            return selected;
        }
        for (String token : text.split(",", -1))
        {
            int number = Integer.parseInt(token.trim());
            if (number < 1 || number > size)
            {
                throw new IllegalArgumentException("Invalid item number");
            }
            selected[number - 1] = true;
        }
        return selected;
    }
}
