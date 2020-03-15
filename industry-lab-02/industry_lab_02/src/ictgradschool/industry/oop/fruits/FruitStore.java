package ictgradschool.industry.oop.fruits;

public class FruitStore {
    public static void main(String[] args) {
        Fruit apple = new Fruit("red apple");
        Fruit orange = new Fruit("orange");
        Fruit greenApple = apple;

        System.out.println("The fruit is " + apple.getFruitName());
        System.out.println("The fruit is " + orange.getFruitName());
        System.out.println("The fruit is " + greenApple.getFruitName());

        orange.setFruitName("navel orange");
        greenApple.setFruitName("green apple");

        System.out.println("The fruit is " + apple.getFruitName());
        System.out.println("The fruit is " + orange.getFruitName());
        System.out.println("The fruit is " + greenApple.getFruitName());
    }
}
