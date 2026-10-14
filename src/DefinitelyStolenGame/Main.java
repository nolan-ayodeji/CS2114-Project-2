package DefinitelyStolenGame;

import static DefinitelyStolenGame.ToolClass.*;

public class Main {
    private static String shopName;
    private static TradingSystem tradingSystem;
    private static int day;
    private static LinkedChain<Item> inventory;
    private static int visitorNum;
    private static int visitorLeft;
    private static StartingCharacter character;

    public static void main(String[] args) {
        try {
            runGame();
        }
        catch (java.util.NoSuchElementException e) {
            println("Input closed. Game ended.");
        }
    }


    private static void runGame() {
        shopName = askName();
        character = chooseCharacter();
        day = 1;
        visitorNum = 5;
        inventory = new LinkedChain<>();
        initializeInventory();
        tradingSystem = new TradingSystem(inventory, character
            .getStartingCoins(), character.getExtraRent());

        GameSystem gameSystem = new GameSystem(visitorNum);
        while (true) {
            // TODO: Add events that happen before the shop opens each day.
            int startingCoins = tradingSystem.getCoins();
            gameSystem.startDay(day);
            visitorLeft = gameSystem.getWaitingCount();
            displayShop();
            askPlayerAction(new String[] { "Open the shop." });

            while (gameSystem.hasVisitors()) {
                displayShop();
                askPlayerAction(new String[] { "Next Customer" });
                Visitor visitor = gameSystem.nextVisitor();
                visitorLeft--;
                println(visitor.getName() + " arrived.");
                tradingSystem.serveVisitor(visitor);
                if (tradingSystem.isGameOver()) {
                    return;
                }
            }

            println("All visitors are done.");
            String[] closeOption = { "Close the shop." };
            askPlayerAction(closeOption);
            displayDaySummary(startingCoins);
            // TODO: Add events that happen after the player closes the shop.
            day++;
        }
    }


    /**
     * Asks for an action, then returns it to the current game scene.
     * Checking inventory keeps the player in the same scene.
     *
     * @param temporaryOptions
     *            actions available only in the current scene
     * @return the one-based number of the chosen action
     */
    public static int askPlayerAction(String[] temporaryOptions) {
        return tradingSystem.askPlayerAction(temporaryOptions);
    }


    /**
     * Displays the items currently owned by the player.
     */
    public static void displayInventory() {
        tradingSystem.browseInventory();
    }


    public static void displayShop() {
        println("[" + shopName + "]   Day: " + day);
        println("" + visitorLeft + " customers waiting.");
    }


    /**
     * Shows the player's progress at the end of the day.
     *
     * @param startingCoins
     *            the coins the player had when the day began
     */
    public static void displayDaySummary(int startingCoins) {
        int coins = tradingSystem.getCoins();
        int change = coins - startingCoins;
        String sign = change >= 0 ? "+" : "";
        int daysUntilLandowner = 7 - (day % 7);

        println("=== Day " + day + " Summary ===");
        println("Coins: " + coins + "G (" + sign + change + "G today)");
        println("Items in stock: " + inventory.size());
        println("Landowner arrives in " + daysUntilLandowner
            + (daysUntilLandowner == 1 ? " day" : " days"));
    }


    public static void initializeInventory() {
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.CHOCOLATE));
        inventory.add(new Item(ItemLibrary.BANDAGE));
        inventory.add(new Item(ItemLibrary.TOOTHPASTE));
        
        if(character == StartingCharacter.SCAVENGER) {
            inventory.add(new Item(ItemLibrary.BATTERY));
        }
    }


    public static StartingCharacter chooseCharacter() {
        StartingCharacter[] characters = StartingCharacter.values();
        String[] options = new String[characters.length];

        for (int i = 0; i < characters.length; i++) {
            options[i] = characters[i].getDisplayName() + " (" + characters[i]
                .getStartingCoins() + "G) - " + characters[i].getDescription();
        }
        
        int choice=askOption(options, 1, options.length);
        return characters[choice-1];
    }
}
