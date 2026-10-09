package DefinitelyStolenGame;

/** Stores the common information for different kinds of special customers. */
public enum SCList
{
    LANDOWNER("Landowner", "How is your business?");

    private final String name;
    private final String dialogue;

    private SCList(String name, String dialogue)
    {
        this.name = name;
        this.dialogue = dialogue;
    }

    public String getName() { return name; }
    public String getDialogue() { return dialogue; }
}