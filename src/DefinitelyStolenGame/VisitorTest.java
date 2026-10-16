package DefinitelyStolenGame;

import student.TestCase;

public class VisitorTest extends TestCase{
    private Visitor visitor;
    
    public void setUp() {
        visitor = new Visitor("Customer", "Hello!");
    }
    
    public void testGetters() {
        assertEquals("Customer", visitor.getName());
        assertEquals("Hello!", visitor.getDialogue());
    }
    
    public void testSayPrintsNameAndMessage() {
        visitor.say("I need food.");
        
        assertTrue(systemOut().getHistory().contains("Customer: I need food."));
    }
    
    public void testSayHandlesNullMessage() {
        visitor.say(null);
        
        assertTrue(systemOut().getHistory().contains("Customer: ..."));
    }
}
