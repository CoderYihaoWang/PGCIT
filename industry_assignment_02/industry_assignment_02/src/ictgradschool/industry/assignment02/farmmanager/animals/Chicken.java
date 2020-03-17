package ictgradschool.industry.assignment02.farmmanager.animals;

public class Chicken extends Animal implements IProductionAnimal {
    private static final int MAX_VALUE = 300;

    public Chicken() {
        value = 200;
    }

    @Override
    public void feed() {
        // mathematically, the value will never exceed MAX_VALUE, therefore no need to check
        value += (MAX_VALUE - value) / 2;
    }

    @Override
    public int costToFeed() {
        return 3;
    }

    @Override
    public String getType() {
        return "Chicken";
    }

    @Override
    public String toString() {
        return getType() + " - $" + value;
    }

    @Override
    public boolean harvestable() {
        return true;
    }

    @Override
    public int harvest() {
        return 5;
    }
}
