package inventory.concreteInventory;

import inventory.abstractInventory.Inventory;

public class CleaningInventory extends Inventory {
    @Override
    public String getCategory() {
        return "CLEANING";
    }

    @Override
    public String getAdditionalInfo() {
        return "";
    }
}
