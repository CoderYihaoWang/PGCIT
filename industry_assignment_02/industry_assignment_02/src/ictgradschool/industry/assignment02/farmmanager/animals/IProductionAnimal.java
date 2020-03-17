package ictgradschool.industry.assignment02.farmmanager.animals;

/**
 * This interface defines animals that can produce products such as milk.
 * Do not modify this interface.
 */
public interface IProductionAnimal {
    /**
     * Determines if the animal can be harvested.
     * @return true if the animal is harvestable.
     */
    boolean harvestable();

    /**
     * Harvest a product from the animal.
     * @return the money made from harvesting a product from the animal. If the animal is not harvestable, return zero.
     */
    int harvest();
}
