package DefinitelyStolenGame;

import student.TestCase;

public class ItemTest extends TestCase{
    private Item authentic;
    private Item counterfeit;
    
    public void setUp() {
        authentic = new Item(ItemLibrary.CHOCOLATE, true);
        counterfeit = new Item(ItemLibrary.BATTERY, false);
    }
    
    public void testDefaultConstructorAuthenticItem() {
        Item item = new Item(ItemLibrary.BANDAGE);
        
        assertTrue(item.isAuthentic());
        assertFalse(item.isInspected());
        assertFalse(item.isRelabeled());
    }
    
    public void testGetters() {
        assertEquals("Chocolate", authentic.getName());
        assertEquals("A bag of chocolate", authentic.getDescription());
        assertEquals("Food", authentic.getType());
        assertEquals(15, authentic.getValue());
    }
    
    public void testAuthenticPrices() {
        assertEquals(18, authentic.getSellingPrice());
        assertEquals(15, authentic.getPurchasePrice());
    }
    
    public void testCounterfeitPricesBeforeInspection() {
        assertEquals(36, counterfeit.getSellingPrice());
        assertEquals(30, counterfeit.getPurchasePrice());
    }
    
    public void testInspectAuthenticItem() {
        assertTrue(authentic.inspect());
        assertTrue(authentic.isInspected());
        assertEquals(18, authentic.getSellingPrice());
        assertEquals(15, authentic.getPurchasePrice());
    }
    
    public void testInspectCounterfeitItem() {
        assertFalse(counterfeit.inspect());
        assertTrue(counterfeit.isInspected());
        assertEquals(18, counterfeit.getSellingPrice());
        assertEquals(15, counterfeit.getPurchasePrice());
    }
    
    public void testRelabelCounterfeitItem() {
        counterfeit.inspect();
        counterfeit.relabelAsAuthentic();
        
        assertTrue(counterfeit.isRelabeled());
        assertEquals(36, counterfeit.getSellingPrice());
        assertEquals(30, counterfeit.getPurchasePrice());
    }
    
    public void testRelabelDoesntChangeAuthentic() {
        authentic.relabelAsAuthentic();
        
        assertFalse(authentic.isRelabeled());
        assertEquals(18, authentic.getSellingPrice());
    }
    
    public void testRelabeledBeforeInspection() {
        counterfeit.relabelAsAuthentic();
        
        assertFalse(counterfeit.isRelabeled());
        assertEquals(36, counterfeit.getSellingPrice());
    }
    
    public void testToString() {
        assertEquals("Chocolate", authentic.toString());
    }
}
