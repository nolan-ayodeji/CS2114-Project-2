package DefinitelyStolenGame;

import student.TestCase;

public class ItemLibraryTest extends TestCase{
    public void testChocolate() {
        assertEquals("Chocolate", ItemLibrary.CHOCOLATE.getName());
        assertEquals("A bag of chocolate", ItemLibrary.CHOCOLATE.getDescription());
        assertEquals("Food", ItemLibrary.CHOCOLATE.getType());
        assertEquals(15, ItemLibrary.CHOCOLATE.getValue());
    }
    
    public void testToothpaste() {
        assertEquals("Toothpaste", ItemLibrary.TOOTHPASTE.getName());
        assertEquals("Daily Necessity", ItemLibrary.TOOTHPASTE.getType());
        assertEquals(10, ItemLibrary.TOOTHPASTE.getValue());
    }
    
    public void testBandage() {
        assertEquals("Bandage", ItemLibrary.BANDAGE.getName());
        assertEquals("Medical", ItemLibrary.BANDAGE.getType());
        assertEquals(15, ItemLibrary.BANDAGE.getValue());
    }

    public void testBattery() {
        assertEquals("Battery", ItemLibrary.BATTERY.getName());
        assertEquals("Electronic", ItemLibrary.BATTERY.getType());
        assertEquals(30, ItemLibrary.BATTERY.getValue());
    }
}
