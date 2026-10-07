package DefinitelyStolenGame;

public class Seller extends Visitor
{
    private static final double WHOLESALE_RATE = 0.7;
    private LinkedChain<Item> inventory;
    
    public Seller(String name, String dialogue, LinkedChain<Item> inventory) {
        super(name, dialogue);
        this.inventory = inventory;
    }
    
    public String getInventory() {
        return inventory.toString();
    }

    public LinkedChain<Item> getItems() {
        return inventory;
    }

    public int getWholesalePrice() {
        int total = 0;
        for (Item item : inventory.toArray(new Item[inventory.size()])) {
            total += item.getValue();
        }
        return (int)Math.round(total * WHOLESALE_RATE);
    }

    /** Displays the remaining goods using their current counter numbers. */
    public void show() {
        Item[] items = inventory.toArray(new Item[inventory.size()]);
        for (int i = 0; i < items.length; i++) {
            ToolClass.println("[" + (i + 1) + "] " + items[i].getName()
                + " " + items[i].getValue() + "G");
        }
        ToolClass.println("Buy everything: " + getWholesalePrice()
            + "G (30% off).");
    }
}
