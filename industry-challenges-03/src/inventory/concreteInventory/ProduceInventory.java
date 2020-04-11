package inventory.concreteInventory;

import inventory.abstractInventory.NonCleaningInventory;

public class ProduceInventory extends NonCleaningInventory {
    @Override
    public String getCategory() {
        return "PRODUCE";
    }
}
