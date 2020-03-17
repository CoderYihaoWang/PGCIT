package ictgradschool.industry.assignment02.farmmanager;

import ictgradschool.industry.assignment02.farmmanager.animals.*;

import java.util.Arrays;

/**
 * This class consists of methods for the farm operations.
 *
 * @author Write your name and UPI here.
 */
public class Farm {
    private Animal[] animals;
    private final int STARTING_MONEY;
    private int money;

    /**
     * Constructor of Farm
     *
     * @param money The amount of money for starting the farm
     */
    public Farm(int money) {
        // TODO Task Two. Complete the constructor so that money and STARTING_MONEY are
        // TODO initialised to the money from the constructor, and the animals array is
        // TODO big enough to hold 10 Animal objects.
        this.money = money;
        this.STARTING_MONEY = money;
        this.animals = new Animal[10];
    }

    /**
     * Constructor of Farm. The default starting money is $10000.
     */
    public Farm() {
        this(10000);
    }

    /**
     * Returns how much money the farm currently has.
     *
     * @return amount of money the farm currently has.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Returns how much money the farm started with.
     */
    public int getStartingMoney() {
        return this.STARTING_MONEY;
    }

    /**
     * Purchases an animal and bills the farm for it.
     *
     * @param animalType The name of the type of animal you wish to buy.
     * @return true if the animal can be bought, otherwise false.
     */
    public boolean buyAnimal(String animalType) {
        Animal newAnimal = getAnimal(animalType);
        // Returns false if the animal type does not exist
        if (newAnimal == null) {
            return false;
        }
        // Calculates the price of the new animal
        int price = (int) (newAnimal.getValue() * 1.15);

        // Returns false if you have insufficient funds
        if (money < price) {
            return false;
        }

        // TODO Task Three. Go through the animals array, and add the new animal
        // TODO to the animals array if the animals array is not already full.
        // TODO Also, deduct the animal price from money once the new animal has been
        // TODO added to the animals array.

        // find an empty place in the animals array
        int pos = 0;
        for (; pos < animals.length; ++pos)
            if (animals[pos] == null)
                break;

        // no empty place, purchase fails
        if (pos == animals.length)
            return false;

        // otherwise, update the array and money
        animals[pos] = newAnimal;
        money -= price;

        return true;
    }

    /**
     * Returns an Animal object based on the given animal type.
     *
     * @param animalType name of the animal type.
     * @return an Animal object if the given animal type exists, otherwise null.
     */
    private Animal getAnimal(String animalType) {
        switch (animalType.toLowerCase()) {
            case "cow":
                return new Cow();
            case "chicken":
                return new Chicken();
            case "dragon":
                return new Dragon();
            default:
                // Animal type does not exist;
                return null;
        }
    }

    /**
     * Sells all of the animals on the farm.
     *
     * @return the total price of all the animals on the farm.
     */
    public int sell() {
        // Calculate the value of all of your animals
        int totalPrice = 0;

        for (int i = 0; i < animals.length; i++) {
            Animal a = animals[i];
            if (a == null) {
                break;
            }
            totalPrice += a.getValue();
        }

        // Remove the animals from your farm and add their values to your money.
        // Using java.util.Arrays to set all elements in animals to null.
        Arrays.fill(animals, null);
        money += totalPrice;
        return totalPrice;
    }

    /**
     * Feeds all animals on the farm.
     */
    public void feedAll() {
        for (int i = 0; i < animals.length; i++) {
            Animal a = animals[i];
            // Stop going through if there is no more animal
            if (a == null) {
                break;
            }
            // See if you have enough money
            if (money >= a.costToFeed()) {
                // Subtract the cost to feed the animal
                money = money - a.costToFeed();
                a.feed();
            }
        }
    }

    /**
     * Feeds all animals of the type specified on the farm.
     */
    public void feed(String animalType) {
        // TODO Task Four. Go through the animals array, and feed each animal of the type specified
        // TODO if you have enough money to feed it. When you fed an animal, don't forget to subtract
        // TODO the cost to feed from the money you have on the farm, and call the feed method on the
        // TODO animal.

        // warning: this part relies on the fact that the animals array is used in a certain manner:
        // the current animals are stored continuously from the start of the array.
        // This is the proper assumption to make, as other parts of this application rely on this as well.
        // Nevertheless, note that this part of code may not perform as expected if, in the future, the
        // implementation of buyAnimal has changed, or other functionality is added (e.g. sellAnimal),
        // and the newAnimals are no longer in the array continuously from the start

        for (int i = 0; i < animals.length && animals[i] != null; ++i) {
            if (animals[i].getType().equalsIgnoreCase(animalType)) {
                int cost = animals[i].costToFeed();
                if (money >= cost) {
                    money -= cost;
                    animals[i].feed();
                }
            }
        }

    }

    /**
     * Prints information for all animals on the farm.
     */
    public void printStock() {
        // TODO Task Six. Go through the animals array, and print information for each animal on the farm.
        // TODO If there are no animals on the farm, simply print "No animals on the farm!"

        // warning: again, this piece of code relies on the continuous usage of the animals array,
        // just like the given methods sell and feedAll do.
        int pos = 0;
        for (; pos < animals.length && animals[pos] != null; ++pos)
            System.out.println(animals[pos]);
        if (pos == 0)
            System.out.println("No animals on the farm!");
    }

    /**
     * Harvests products from all animals that can produce products such as milk.
     */
    public void harvestAll() {
        // TODO Task Seven. Go through the animals array, and harvest the product from each animal on the farm
        // TODO if the animal is an instance of IProductionAnimal and it is harvestable. When you have harvested an
        // TODO animal, don't forget to increase the money you have on the farm with the money you made.

        // warning: again, the assumption of continuous array usage is made
        for (int i = 0; i < animals.length && animals[i] != null; ++i)
            if (animals[i] instanceof IProductionAnimal && ((IProductionAnimal)animals[i]).harvestable())
                money += ((IProductionAnimal)animals[i]).harvest();
    }
}
