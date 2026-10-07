package DefinitelyStolenGame;
// -------------------------------------------------------------------------
/**
 *  The super class of customers come to the stores
 */
public class Visitor
{
    protected String name;
    protected String dialogue;
    
    public Visitor(String n, String d) {
        name = n;
        dialogue = d;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDialogue() {
        return dialogue;
    }
}
