package DefinitelyStolenGame;

import student.TestCase;

/**
 * Tests counterfeit items, inspection, relabeling, and starting characters
 */
public class ItemAndCharacterTest extends TestCase {
    private Item authentic;
    private Item counterfeit;

    public void setUp() {
        authentic = new Item(ItemLibrary.CHOCOLATE, true);
        counterfeit = new Item(ItemLibrary.BATTERY, false);
    }


    public void testAuthenticItem() {
        assertTrue(authentic.isAuthentic());
        assertFalse(authentic.isInspected());
        assertFalse(authentic.isRelabeled());

        assertEquals(18, authentic.getSellingPrice());
        assertEquals(15, authentic.getPurchasePrice());
    }


    public void testCounterfeitPrices() {
        assertFalse(counterfeit.isAuthentic());
        assertFalse(counterfeit.isInspected());
        assertEquals(36, counterfeit.getSellingPrice());
        assertEquals(30, counterfeit.getPurchasePrice());
    }


    public void testInspectAuthenticItem() {
        assertTrue(authentic.inspect());
        assertTrue(authentic.isInspected());
        assertEquals(18, authentic.getSellingPrice());
        assertEquals(15, authentic.getPurchasePrice());
    }


    public void testInspectCounterfeitReducesPrices() {
        assertFalse(counterfeit.inspect());
        assertTrue(counterfeit.isInspected());
        assertEquals(18, counterfeit.getSellingPrice());
        assertEquals(15, counterfeit.getPurchasePrice());
    }


    public void testRelabelCounterfeitPrice() {
        counterfeit.inspect();
        counterfeit.relabelAsAuthentic();

        assertTrue(counterfeit.isRelabeled());
        assertEquals(36, counterfeit.getSellingPrice());
        assertEquals(30, counterfeit.getPurchasePrice());
    }


    public void testRelabelDoesNotAffectAuthentic() {
        authentic.relabelAsAuthentic();
        assertFalse(authentic.isRelabeled());
    }


    public void testStartingCharacters() {
        assertEquals(500, StartingCharacter.ROOKIE.getStartingCoins());
        assertEquals(350, StartingCharacter.SCAVENGER.getStartingCoins());
        assertEquals(700, StartingCharacter.MERCHANT.getStartingCoins());

        assertEquals(0, StartingCharacter.ROOKIE.getExtraRent());
        assertEquals(0, StartingCharacter.SCAVENGER.getExtraRent());
        assertEquals(50, StartingCharacter.MERCHANT.getExtraRent());
    }


    public void testTradingSystemWithCoins() {
        LinkedChain<Item> inventory = new LinkedChain<>();
        TradingSystem system = new TradingSystem(inventory,
            StartingCharacter.MERCHANT.getStartingCoins(),
            StartingCharacter.MERCHANT.getExtraRent());
        
        assertEquals(700, system.getCoins());
    }
}
