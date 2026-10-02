package DefinitelyStolenGame;
public class Buyer extends Visitor
{
    private int budget;
    
    public Buyer(String name, String dialogue, int budget) {
        super(name, dialogue);
        this.budget = budget;
    }
    
    public int getBudget() {
        return budget;
    }
}
