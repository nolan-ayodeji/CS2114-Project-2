package DefinitelyStolenGame;

// -------------------------------------------------------------------------
/**
 *  A seller who sold his items to player.
 *  Extends from Visitor class
 * 
 *  @author chens
 *  @version 2026.10.2
 */
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
