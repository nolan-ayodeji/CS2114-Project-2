package DefinitelyStolenGame;

public enum ServiceType {
    STOCK_INSURANCE("Stock Insurance", 25, "Reimburses the base value of stock stolen tonight."),
    SECURITY("Overnight Security", 35, "Reduces tonight's robbery chance from 25% to 5%."),
    ADVERTISING("Advertising", 30, "Brings two extra buyers tomorrow.");
    
    private final String displayName;
    private final String description;
    private final int price;
    
    private ServiceType(String displayName, int price, String description){
        this.displayName = displayName;
        this.price = price;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public int getPrice() {
        return price;
    }
    
    public String getDescription() {
        return description;
    }
}
