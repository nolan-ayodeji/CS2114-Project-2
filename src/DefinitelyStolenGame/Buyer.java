package DefinitelyStolenGame;
public class Buyer extends Visitor
{
    private String need;
    private int budget;
    
    public Buyer(String name, String dialogue, String need, int budget) {
        super(name, dialogue);
        this.need = need;
        this.budget = budget;
    }
    
    public String getNeed() {
        return need;
    }
    public int getBudget() {
        return budget;
    }

    public void spend(int amount) {
        if (amount < 0 || amount > budget) {
            throw new IllegalArgumentException("Payment exceeds remaining budget.");
        }
        budget -= amount;
    }
}
