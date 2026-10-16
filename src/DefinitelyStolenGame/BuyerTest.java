package DefinitelyStolenGame;

import student.TestCase;

public class BuyerTest extends TestCase{
    private Buyer buyer;
    
    public void setUp() {
        buyer = new Buyer("Resident", "Got food?", "Food", 100);
    }
    
    public void testGetters() {
        assertEquals("Resident", buyer.getName());
        assertEquals("Got food?", buyer.getDialogue());
        assertEquals("Food", buyer.getNeed());
        assertEquals(100, buyer.getBudget());
    }
    
    public void testSpendReducesBudget() {
        buyer.spend(40);
        
        assertEquals(60, buyer.getBudget());
    }
    
    public void testSpendAllBudget() {
        buyer.spend(100);
        
        assertEquals(0, buyer.getBudget());
    }
    
    public void testSpendNegativeAmount() {
        Exception thrown = null;
        
        try {
            buyer.spend(-1);
        }
        catch(IllegalArgumentException e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertEquals(100, buyer.getBudget());
    }
    
    public void testSpendMoreThanBudget() {
        Exception thrown = null;
        
        try {
            buyer.spend(101);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertEquals(100, buyer.getBudget());
    }
}
