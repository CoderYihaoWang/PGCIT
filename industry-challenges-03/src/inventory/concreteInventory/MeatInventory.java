package inventory.concreteInventory;

import inventory.abstractInventory.NonCleaningInventory;

public class MeatInventory extends NonCleaningInventory {
    @Override
    public String getCategory() {
        return "MEAT";
    }
  }
