package ictgradschool.industry.practice01.q2;

import java.util.Objects;

public class Aeroplane {

    // TODO Step 1. Define instance variables for make, model, and year
    String make;
    String model;
    int year;
    public Aeroplane(String make, String model, int year) {

        // TODO Step 2. Assign the instance variables appropriately in the constructor
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {

        // TODO Step 3. Return a string of the form "[year] [make] [model]", as you can see in the test handout.
        return year + " " + make + " " + model;
    }
    
    public boolean isOlderThan(Aeroplane other) {

        // TODO Step 4. Return a value indicating whether this Aeroplane is older than the given one (i.e. its year is smaller).
        return year < other.year;
    }

    @Override
    public boolean equals(Object other) {

        // TODO Step 5. Implement the equals method as follows:
        // If the object is an Aeroplane, return true if its make, model and year are the same as this one's.
        // If the object is not an Aeroplane, just return false.
        // Hint: remember the instanceOf keyword.
        if (other == this)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != this.getClass())
            return false;
        Aeroplane that = (Aeroplane)other;
        return Objects.equals(that.make, this.make)
                && Objects.equals(that.model, this.model)
                && that.year == this.year;

    }

}