package DefinitelyStolenGame;

import student.TestCase;

public class SpecialCharacterTest extends TestCase{
    public void testLandowner() {
        SpecialCustomer landowner = new SpecialCustomer(SCList.LANDOWNER);
        
        assertEquals(SCList.LANDOWNER, landowner.getCustomerType());
        assertEquals("Landowner", landowner.getName());
        assertEquals("How is your business?", landowner.getDialogue());
    }
}
