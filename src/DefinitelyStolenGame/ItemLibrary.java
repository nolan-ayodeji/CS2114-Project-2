package DefinitelyStolenGame;

public enum ItemLibrary
{
    CHOCOLATE("Chocolate", "A bag of tasty chocolate", "Food", 10),
    TOOTHPASTE("Toothpaste", "A tube of toothpaste", "Daily Necessity", 20),
    BANDAGE("Bandage", "A roll of bandage for minor injuries", "Medical", 15),
    BATTERY("Battery", "A fully-charged universal battery", "Electronic", 30),
    CANNED_LUNCHEON_MEAT("Canned Luncheon Meat", "A sealed can of processed meat.", "Food", 25),
    TISSUE_PACK("Pocket Tissues", "A small pack of soft tissues for everyday use.", "Daily Necessity", 10),
    POVIDONE_IODINE("Povidone-Iodine", "A bottle of antiseptic solution for cleaning minor wounds.", "Medical", 40);

    private String name;
    private String description;
    private String type;
    private int value;

    private ItemLibrary(String n, String d, String t, int v)
    {
        name = n;
        description = d;
        type = t;
        value = v;
    }


    public String getName()
    {
        return name;
    }


    public String getDescription()
    {
        return description;
    }


    public String getType()
    {
        return type;
    }


    public int getValue()
    {
        return value;
    }

}
