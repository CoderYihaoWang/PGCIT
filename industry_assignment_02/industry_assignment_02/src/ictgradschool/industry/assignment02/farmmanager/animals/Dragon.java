package ictgradschool.industry.assignment02.farmmanager.animals;

public class Dragon extends Animal {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private int costToFeed;

    public Dragon() {
        value = 9999;
        costToFeed = 0;
    }

    @Override
    public void feed() {
        // to feed a dragon, 1% chance to make it grow by 9999, otherwise no growth at all
        int rand = (int)(Math.random() * 100);
        if (rand == 0)
            value += 9999;

        // each time you feed it, it becomes a little more expensive to feed
        ++costToFeed;
    }

    @Override
    public int costToFeed() {
        // costToFeed increases as you feed the dragon
        // however, this value stays the same between two calls of costToFeed(),
        // if you have not actually fed it.
        // So, it is still valid to decide whether one can afford to feed it by calling this method
        return costToFeed;
    }

    @Override
    public String getType() {
        return "Dragon";
    }

    @Override
    public String toString() {
        return getType() + " - $" + value;
    }
}
