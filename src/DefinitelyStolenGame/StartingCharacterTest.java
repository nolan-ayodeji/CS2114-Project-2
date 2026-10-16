package DefinitelyStolenGame;

import student.TestCase;

public class StartingCharacterTest extends TestCase{
    public void testRookie() {
        assertEquals("Rookie", StartingCharacter.ROOKIE.getDisplayName());
        assertEquals(500, StartingCharacter.ROOKIE.getStartingCoins());
        assertEquals(0, StartingCharacter.ROOKIE.getExtraRent());
        assertFalse(StartingCharacter.ROOKIE.getDescription().isEmpty());
    }

    public void testScavenger() {
        assertEquals("Scavenger", StartingCharacter.SCAVENGER.getDisplayName());
        assertEquals(350, StartingCharacter.SCAVENGER.getStartingCoins());
        assertEquals(0, StartingCharacter.SCAVENGER.getExtraRent());
    }
    
    public void testMerchant() {
        assertEquals("Merchant", StartingCharacter.MERCHANT.getDisplayName());
        assertEquals(700, StartingCharacter.MERCHANT.getStartingCoins());
        assertEquals(50, StartingCharacter.MERCHANT.getExtraRent());
    }
}
