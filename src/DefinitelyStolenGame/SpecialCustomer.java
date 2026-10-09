package DefinitelyStolenGame;

/** A visitor identified by an entry in the special customer library. */
public class SpecialCustomer extends Visitor
{
    private final SCList customerType;

    public SpecialCustomer(SCList customerType)
    {
        super(customerType.getName(), customerType.getDialogue());
        this.customerType = customerType;
    }

    public SCList getCustomerType()
    {
        return customerType;
    }
}