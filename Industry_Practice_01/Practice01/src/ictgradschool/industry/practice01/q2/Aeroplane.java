package ictgradschool.industry.practice01.q2;

public class Aeroplane {

    // TODO Step 1. Define instance variables for make, model, and year

    public Aeroplane(String make, String model, int year) {

        // TODO Step 2. Assign the instance variables appropriately in the constructor

    }

    @Override
    public String toString() {

        // TODO Step 3. Return a string of the form "[year] [make] [model]", as you can see in the test handout.
        return "";
    }
    
    public boolean isOlderThan(Aeroplane other) {

        // TODO Step 4. Return a value indicating whether this Aeroplane is older than the given one (i.e. its year is smaller).
        return false;
    }

    @Override
    public boolean equals(Object other) {

        // TODO Step 5. Implement the equals method as follows:
        // If the object is an Aeroplane, return true if its make, model and year are the same as this one's.
        // If the object is not an Aeroplane, just return false.
        // Hint: remember the instanceOf keyword.
        
        return false;

    }

}