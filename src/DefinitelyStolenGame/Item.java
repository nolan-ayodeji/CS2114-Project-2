package DefinitelyStolenGame;
public class Item
{
    private ItemLibrary item;
    private String name;
    private String description;
    private String type;
    private int value;
    
    private boolean authentic;
    private boolean inspected;
    private boolean relabeled;
    
    public Item(ItemLibrary item) {
        this(item, true);
    }
    
    public Item (ItemLibrary item, boolean authentic) {
        this.item = item;
        name = item.getName();
        description = item.getDescription();
        type = item.getType();
        value = item.getValue();
        this.authentic=authentic;
        inspected=false;
        relabeled=false;
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

    public boolean isAuthentic() {
        return authentic;
    }
    
    public boolean isInspected() {
        return inspected;
    }
    
    public boolean isRelabeled() {
        return relabeled;
    }
    
    public boolean inspect() {
        inspected=true;
        return authentic;
    }
    
    public void relabelAsAuthentic() {
        if(!authentic&&inspected) {
            relabeled = true;
        }
    }
    
    public int getSellingPrice() {
        if(!authentic && inspected && !relabeled) {
            return (int)Math.round(value*0.6);
        }
        return (int)Math.round(value * 1.2);
    }
    
    public int getPurchasePrice() {
        if(!authentic&&inspected&&!relabeled) {
            return (int)Math.round(value*0.5);
        }
        return value;
    }
}
