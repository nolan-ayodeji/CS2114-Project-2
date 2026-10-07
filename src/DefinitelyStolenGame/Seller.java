package DefinitelyStolenGame;

public class Seller extends Visitor
{
    private LinkedChain<Item> inventory;
    
    public Seller(String name, String dialogue, LinkedChain<Item> inventory) {
        super(name, dialogue);
        this.inventory = inventory;
    }
    
    public String getInventory() {
        return inventory.toString();
    }
}
