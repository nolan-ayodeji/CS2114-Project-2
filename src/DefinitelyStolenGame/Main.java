package DefinitelyStolenGame;
import static DefinitelyStolenGame.ToolClass.*;
import static DefinitelyStolenGame.LinkedChain.*;

public class Main
{
    private static String shopName;
    private static int coin;
    private static LinkedChain<Item> inventory;
    
    public static void main(String[] args)
    {
        shopName = askName();
        coin = 500;
        inventory = new LinkedChain<>();
        initializeInventory();
    }
    
    public static void initializeInventory() {
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.BANDAGE));
        inventory.add(new Item(ItemLibrary.TOOTHPASTE));
    }
}
