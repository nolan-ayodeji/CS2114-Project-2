package DefinitelyStolenGame;

public enum StartingCharacter {
    ROOKIE("Rookie", 500, 0, "A balanced beginner with standard savings."),
    SCAVENGER(
        "Scavenger",
        350,
        0,
        "Starts with less money but begins with extra supplies."),
    MERCHANT(
        "Merchant",
        700,
        50,
        "Startings with more money, but pays higher rent.");

    private final String displayName;
    private final int startingCoins;
    private final int extraRent;
    private final String description;

    private StartingCharacter(
        String displayName,
        int startingCoins,
        int extraRent,
        String description) {
        this.displayName = displayName;
        this.startingCoins = startingCoins;
        this.extraRent = extraRent;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getStartingCoins() {
        return startingCoins;
    }
    
    public int getExtraRent() {
        return extraRent;
    }
    
    public String getDescription() {
        return description;
    }
}
