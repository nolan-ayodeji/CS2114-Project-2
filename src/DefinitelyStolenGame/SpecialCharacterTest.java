package DefinitelyStolenGame;

import student.TestCase;

public class SpecialCharacterTest extends TestCase{
    public void testLandowner() {
        SpecialCustomer landowner = new SpecialCustomer(SCList.LANDOWNER);
        
        assertEquals(SCList.LANDOWNER, landowner.getCustomerType());
        assertEquals("Landowner", landowner.getName());
        assertEquals("How is your business?", landowner.getDialogue());
    }


    public void testSmuggler() {
        SpecialCustomer smuggler = new SpecialCustomer(SCList.SMUGGLER);

        assertEquals(SCList.SMUGGLER, smuggler.getCustomerType());
        assertEquals("Smuggler", smuggler.getName());
        assertEquals("Shh.... don't tell anyone I was here.",
            smuggler.getDialogue());
    }
}
