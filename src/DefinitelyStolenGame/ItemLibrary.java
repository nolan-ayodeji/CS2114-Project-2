package DefinitelyStolenGame;

public enum ItemLibrary
{
    CHOCOLATE("Chocolate", "A bag of chocolate", "Food", 15),
    TOOTHPASTE("Toothpaste", "A Tube of Toothpaste", "Daily Necessity", 10),
    BANDAGE("Bandage", "A roll of bandage for minor injuries", "Medical", 15),
    BATTERY("Battery", "A fully-charged universal battery", "Electronic", 30);
    
    private String name;
    private String description;
    private String type;
    private int value;
    
    private ItemLibrary (String n, String d, String t, int v) {
        name = n;
        description = d;
        type = t;
        value = v;
    }
    

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    
    public int getValue() {
        return value;
    }
    
}
