package DefinitelyStolenGame;

import student.TestCase;

public class SellerTest extends TestCase {
    private Seller seller;
    private LinkedChain<Item> items;

    public void setUp() {
        items = new LinkedChain<>();
        items.add(new Item(ItemLibrary.CHOCOLATE));
        items.add(new Item(ItemLibrary.BATTERY));

        seller = new Seller("Scavenger", "Want to buy?", items);
    }


    public void testGetters() {
        assertEquals("Scavenger", seller.getName());
        assertEquals("Want to buy?", seller.getDialogue());
        assertEquals(items, seller.getItems());
    }


    public void testGetInventory() {
        String result = seller.getInventory();
        
        assertTrue(result.contains("Chocolate"));
        assertTrue(result.contains("Battery"));
    }


    public void testGetWholesalePrice() {
        assertEquals(Math.round((15+30)*0.7), seller.getWholesalePrice());
    }


    public void testgetWholesalePriceEmptyInventory() {
        Seller emptySeller = new Seller("Scavenger", "Nothing",
            new LinkedChain<Item>());

        assertEquals(0, emptySeller.getWholesalePrice());
    }
    
    public void testShowDisplaysItems() {
        seller.show();
        
        String output = systemOut().getHistory();
        assertTrue(output.contains("[1] Battery 30G"));
        assertTrue(output.contains("[2] Chocolate 15G"));
    }
}
