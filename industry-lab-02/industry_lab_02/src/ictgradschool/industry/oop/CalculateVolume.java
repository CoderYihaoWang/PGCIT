package ictgradschool.industry.oop;

/* 1 */
import ictgradschool.Keyboard;

public class CalculateVolume /* 2 */{
    public /* 3 */ void start() {
        double radius /* 4 */;
        System.out.println("\"Volume of s Sphere\"" /* 5 */);
        System.out.println("Enter the radius: " /* 6 */);
        radius = /* 7, 8 */Double.parseDouble(Keyboard.readInput());
        /* 9 */ double volume = /* 10 */ 4.0 / 3 * Math.PI * Math.pow(radius, /* 11 */3);
        System.out.println("Volume: " /* 12 */ + volume);
    }
/* 13: } */

    public static void main(String[] args) {
        new CalculateVolume().start();
    }


}
