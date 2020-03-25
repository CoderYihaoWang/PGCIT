package ictgradschool.industry.practice01.q2;

public class Main {
    public void start() {
        // TODO Step 6. Declare an array of Aeroplane objects (named planes)
        // TODO Step 7. Construct the array of 10 Aeroplane objects
        Aeroplane[] planes = new Aeroplane[10];
        
        // Initialise the array of Aeroplane objects
        fillPlanesArray(planes);
        
        // Print the array of Aeroplane objects
        printPlanesArray(planes);
        
        // Print details of the fleet's oldest Aeroplane.
        Aeroplane oldestAeroplane = getOldestAeroplane(planes);
        System.out.println();
        System.out.println("Oldest aeroplane is: " + oldestAeroplane);
    }
    
    private void fillPlanesArray(Aeroplane[] aeroplanes) {
        aeroplanes[0] = new Aeroplane("Piper", "Cherokee", 1968);
        aeroplanes[1] = new Aeroplane("Cessna", "152", 1980);
        aeroplanes[2] = new Aeroplane("Cessna", "172", 2006);
        aeroplanes[3] = new Aeroplane("Piper", "Tomahawk", 1980);
        aeroplanes[4] = new Aeroplane("Piper", "Cub", 1956);
        aeroplanes[5] = new Aeroplane("Learjet", "23", 1970);
        aeroplanes[6] = new Aeroplane("Piper", "Cherokee", 1968);
        aeroplanes[7] = new Aeroplane("Airbus", "A320", 2001);
        aeroplanes[8] = new Aeroplane("Cirrus", "SR22", 2006);
        aeroplanes[9] = new Aeroplane("Cessna", "Caravan", 1986);
    }
    
    // TODO Step 8.  Write the printPlanesArray() method
    private void printPlanesArray(Aeroplane[] planes) {
        for (Aeroplane plane : planes)
            System.out.println(plane);
    }
    
    
    
    // TODO Step 9.  Write the getOldestAeroplane() method.
    private Aeroplane getOldestAeroplane(Aeroplane[] planes) {
        Aeroplane oldest = planes[0];
        for (Aeroplane plane : planes)
            if (plane.isOlderThan(oldest))
                oldest = plane;
        return oldest;
    }

    public static void main(String[] args) {
        new Main().start();
    }


}
