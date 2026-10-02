package DefinitelyStolenGame;
public class Item
{
    private ItemLibrary item;
    private String name;
    private String description;
    private String type;
    private int value;
    
    public Item (ItemLibrary item) {
        this.item = item;
        name = item.getName();
        description = item.getDescription();
        type = item.getType();
        value = item.getValue();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public String getType() {
        // this will be modified since some item may have multiple types.
        return type;
    }

    public int getValue() {
        return value;
    }
}
