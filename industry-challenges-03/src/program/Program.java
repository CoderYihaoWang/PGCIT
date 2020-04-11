package program;

import inventory.abstractInventory.Inventory;
import inventory.concreteInventory.*;
import store.LocalStore;
import util.Keyboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    private enum Status {
        INIT,
        SET_CAPACITY,
        ADD_INVENTORY,
        LOAD_INVENTORY,
        QUERY_INVENTORY,
        QUERY_CATEGORY,
        QUIT
    }

    public static void main(String[] args) {
        LocalStore store = new LocalStore();
        Status status = Status.INIT;
        System.out.println("Welcome the inventory management system");
        while (status != Status.QUIT) {
            switch (status) {
                case INIT:
                    status = init(store);
                    break;
                case SET_CAPACITY:
                    status = setCapacity(store);
                    break;
                case ADD_INVENTORY:
                    status = addInventory(store);
                    break;
                case LOAD_INVENTORY:
                    status = loadInventory(store);
                    break;
                case QUERY_INVENTORY:
                    status = queryInventory(store);
                    break;
                case QUERY_CATEGORY:
                    status = queryCategory(store);
                    break;
            }
        }
        System.out.println();
        System.out.println("Thank you!");
        System.out.println("Author: Yihao Wang, 11/04/2020");
    }

    private static Status init(LocalStore store) {
        System.out.println();
        System.out.println("Please enter a store name:");
        store.setName(Keyboard.readInput());

        System.out.println();
        System.out.println("Please enter the store location:");
        store.setLocation(Keyboard.readInput());

        return Status.SET_CAPACITY;
    }

    private static Status setCapacity(LocalStore store) {
        System.out.println();
        System.out.println("Welcome to " + store.getName() + " at " + store.getLocation());
        System.out.println("Please enter the capacity");
        try {
            store.setCapacity(Integer.parseInt(Keyboard.readInput()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format, please try again");
            return Status.SET_CAPACITY;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Status.SET_CAPACITY;
        }
        return Status.ADD_INVENTORY;
    }

    private static Status addInventory(LocalStore store) {
        System.out.println();
        System.out.println("Please choose the category of inventory you want to add:");
        System.out.println("1 - PRODUCE");
        System.out.println("2 - MEAT");
        System.out.println("3 - DELI");
        System.out.println("4 - BAKERY");
        System.out.println("5 - CLEANING");
        System.out.println("6 - load inventories from csv");
        System.out.println("7 - see added inventories");
        System.out.println("q - exit");
        String input = Keyboard.readInput();
        Inventory inventory;
        switch (input) {
            case "1":
                inventory = new ProduceInventory();
                break;
            case "2":
                inventory = new MeatInventory();
                break;
            case "3":
                inventory = new DeliInventory();
                break;
            case "4":
                inventory = new BakeryInventory();
                break;
            case "5":
                inventory = new CleaningInventory();
                break;
            case "6":
                return Status.LOAD_INVENTORY;
            case "7":
                return Status.QUERY_INVENTORY;
            case "q":
                return Status.QUIT;
            default:
                System.out.println("Invalid input, please try again");
                return Status.ADD_INVENTORY;
        }
        inventory.build();
        if (store.add(inventory))
            System.out.println("1 item has been successfully added");
        else
            System.out.println("The max capacity has been reached, unable to add!");
        return Status.ADD_INVENTORY;
    }

    private static Status loadInventory(LocalStore store) {
        System.out.println();
        System.out.println("Please input a file name, or 'b' to get back to menu");
        String filename = Keyboard.readInput();
        if (filename.equals("b"))
            return Status.ADD_INVENTORY;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter(",|[\\r\\n]+");
            while (scanner.hasNext()) {
                String category = scanner.next();
                Inventory inventory;
                switch (category) {
                    case "PRODUCE":
                        inventory = new ProduceInventory();
                        break;
                    case "MEAT":
                        inventory = new MeatInventory();
                        break;
                    case "DELI":
                        inventory = new DeliInventory();
                        break;
                    case "BAKERY":
                        inventory = new BakeryInventory();
                        break;
                    case "CLEANING":
                        inventory = new CleaningInventory();
                        break;
                    default:
                        System.out.println("Illegal category name encountered: " + category);
                        System.out.println("Loading failed");
                        return Status.ADD_INVENTORY;
                }
                try {
                    inventory.build(scanner);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return Status.ADD_INVENTORY;
                }
                if (store.add(inventory)) {
                    System.out.println("1 " + category + " item has been successfully added");
                } else {
                    System.out.println("The max capacity has been reached, unable to add!");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filename + "does not exist, please try again");
            return Status.LOAD_INVENTORY;
        }
        System.out.println("Load successfully");
        return Status.ADD_INVENTORY;
    }

    private static Status queryInventory(LocalStore store) {
        System.out.println();
        System.out.println("What would you like to see?");
        System.out.println("1 - all inventories");
        System.out.println("2 - inventories under a category");
        System.out.println("3 - continue to add more inventories");
        System.out.println("q - exit");
        String input = Keyboard.readInput();
        switch (input) {
            case "1":
                store.printAll();
                return Status.QUERY_INVENTORY;
            case "2":
                return Status.QUERY_CATEGORY;
            case "3":
                return Status.ADD_INVENTORY;
            case "q":
                return Status.QUIT;
        }
        System.out.println("Invalid input, please try again");
        return Status.QUERY_INVENTORY;
    }

    private static Status queryCategory(LocalStore store) {
        System.out.println();
        System.out.println("Please select a category:");
        System.out.println("1 - PRODUCE");
        System.out.println("2 - MEAT");
        System.out.println("3 - DELI");
        System.out.println("4 - BAKERY");
        System.out.println("5 - CLEANING");
        System.out.println("6 - back to menu");
        String input = Keyboard.readInput();
        switch (input) {
            case "1":
                store.print("PRODUCE");
                break;
            case "2":
                store.print("MEAT");
                break;
            case "3":
                store.print("DELI");
                break;
            case "4":
                store.print("BAKERY");
                break;
            case "5":
                store.print("CLEANING");
                break;
            case "6":
                return Status.QUERY_INVENTORY;
            default:
                System.out.println("Invalid input, please try again");
                return Status.QUERY_CATEGORY;
        }
        return Status.QUERY_INVENTORY;
    }
}
