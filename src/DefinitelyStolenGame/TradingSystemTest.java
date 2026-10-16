package DefinitelyStolenGame;

import student.TestCase;

public class TradingSystemTest extends TestCase {
    private LinkedChain<Item> inventory;
    private TradingSystem system;

    public void setUp() {
        inventory = new LinkedChain<>();
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        system = new TradingSystem(inventory, 500);
    }


    public void testConstructor() {
        assertEquals(500, system.getCoins());
        assertFalse(system.isGameOver());
    }


    public void testPayRent() {
        assertTrue(system.payRent(200));
        assertEquals(300, system.getCoins());
    }


    public void testPayRentMoreThanCoins() {
        assertFalse(system.payRent(501));
        assertEquals(500, system.getCoins());
    }


    public void testPayNegativeRent() {
        Exception thrown = null;

        try {
            system.payRent(-1);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }

        assertNotNull(thrown);
        assertEquals(500, system.getCoins());
    }


    public void testBuyItems() {
        LinkedChain<Item> sellerItems = new LinkedChain<>();
        Item chocolate = new Item(ItemLibrary.CHOCOLATE);
        Item battery = new Item(ItemLibrary.BATTERY);
        sellerItems.add(chocolate);
        sellerItems.add(battery);

        Seller seller = new Seller("Scavenger", "Selling", sellerItems);

        LinkedChain<Item> purchase = new LinkedChain<>();
        purchase.add(chocolate);

        assertTrue(system.buyItems(seller, purchase));
        assertEquals(485, system.getCoins());
        assertTrue(inventory.contains(chocolate));
        assertFalse(seller.getItems().contains(chocolate));
        
        assertTrue(seller.getItems().contains(battery));
    }


    public void testBuyItemsWithoutEnoughCoins() {
        TradingSystem poorSystem = new TradingSystem(new LinkedChain<Item>(),
            10);

        LinkedChain<Item> sellerItems = new LinkedChain<>();
        Item battery = new Item(ItemLibrary.BATTERY);
        sellerItems.add(battery);

        Seller seller = new Seller("Scavenger", "Selling", sellerItems);

        LinkedChain<Item> purchase = new LinkedChain<>();
        purchase.add(battery);

        assertFalse(poorSystem.buyItems(seller, purchase));
        assertEquals(10, poorSystem.getCoins());
    }


    public void testBuyEmptyPurchase() {
        Seller seller = new Seller("Scavenger", "Selling",
            new LinkedChain<Item>());

        assertFalse(system.buyItems(seller, new LinkedChain<Item>()));
    }


    public void testBuyItemNotOwnedBySeller() {
        Item fakeItem = new Item(ItemLibrary.CHOCOLATE);

        Seller seller = new Seller("Scavenger", "Selling",
            new LinkedChain<Item>());

        LinkedChain<Item> purchase = new LinkedChain<>();
        purchase.add(fakeItem);

        assertFalse(system.buyItems(seller, purchase));
    }


    public void testSellItems() {
        Buyer buyer = new Buyer("Resident", "Need food", "Food", 100);
        Item chocolate = new Item(ItemLibrary.CHOCOLATE);
        inventory.add(chocolate);

        LinkedChain<Item> listed = new LinkedChain<>();
        listed.add(chocolate);

        assertTrue(system.sellItems(buyer, listed, false));
        assertEquals(518, system.getCoins());
        assertEquals(82, buyer.getBudget());
        assertFalse(inventory.contains(chocolate));
    }


    public void testSellItemsBudgetLimit() {
        Buyer buyer = new Buyer("Resident", "Need food", "Food", 10);
        Item chocolate = new Item(ItemLibrary.CHOCOLATE);
        inventory.add(chocolate);

        LinkedChain<Item> listed = new LinkedChain<>();
        listed.add(chocolate);

        assertTrue(system.sellItems(buyer, listed, true));
        assertEquals(510, system.getCoins());
        assertEquals(0, buyer.getBudget());
    }


    public void testSellItemsRejectsBudgetLimit() {
        Buyer buyer = new Buyer("Resident", "Need food", "Food", 10);
        Item chocolate = new Item(ItemLibrary.CHOCOLATE);
        inventory.add(chocolate);

        LinkedChain<Item> listed = new LinkedChain<>();
        listed.add(chocolate);

        assertFalse(system.sellItems(buyer, listed, false));
        assertEquals(500, system.getCoins());
        assertTrue(inventory.contains(chocolate));
    }


    public void testSellItemsRequiresMatchingNeed() {
        Buyer buyer = new Buyer("Resident", "Need medical", "Medical", 100);
        Item chocolate = new Item(ItemLibrary.CHOCOLATE);
        inventory.add(chocolate);

        LinkedChain<Item> listed = new LinkedChain<>();
        listed.add(chocolate);

        assertFalse(system.sellItems(buyer, listed, false));
    }


    public void testSellEmptyListing() {
        Buyer buyer = new Buyer("Resident", "Need food", "Food", 100);

        assertFalse(system.sellItems(buyer, new LinkedChain<Item>(), false));
    }


    public void testDisplayInventoryShowsItems() {
        LinkedChain<Item> listed = new LinkedChain<>();
        system.displayInventory(listed);

        String output = systemOut().getHistory();
        assertTrue(output.contains("Inventory:"));
        assertTrue(output.contains("Chocolate"));
    }
}
