package ictgradschool.industry.oop;

public  class Random {
    public static int random(int start, int end) {
        return (int) (Math.random() * (end + 1 - start) + start);
    }
}
