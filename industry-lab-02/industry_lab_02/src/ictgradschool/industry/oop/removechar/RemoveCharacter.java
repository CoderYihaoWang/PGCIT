package ictgradschool.industry.oop.removechar;

import ictgradschool.Keyboard;
import ictgradschool.industry.oop.Random;

/**
 * Write a program that prompts the user to enter a sentence, then prints out the sentence with a random character
 * missing. The program is to be written so that each task is in a separate method. See the comments below for the
 * different methods you have to write.
 */
public class RemoveCharacter {

    private void start() {

        String sentence = getSentenceFromUser();

        int randomPosition = getRandomPosition(sentence);

        printCharacterToBeRemoved(sentence, randomPosition);

        String changedSentence = removeCharacter(sentence, randomPosition);

        printNewSentence(changedSentence);
    }

    /**
     * Gets a sentence from the user.
     * @return
     */
    private String getSentenceFromUser() {

        // Prompt the user to enter a sentence, then get their input.
        return Keyboard.prompt("Enter a sentence: ");
    }

    /**
     * Gets an int corresponding to a random position in the sentence.
     */
    private int getRandomPosition(String sentence) {

        // Use a combination of Math.random() and sentence.length() to get the desired result.
        return Random.random(0, sentence.length() - 1);
    }

    /**
     * Prints a message stating the character to be removed, and its position.
     */
    private void printCharacterToBeRemoved(String sentence, int position) {

        // Implement this method
        System.out.println("Removing " + sentence.charAt(position) + " from position " + position);
    }

    /**
     * Removes a character from the given sentence, and returns the new sentence.
     */
    private String removeCharacter(String sentence, int position) {

        // Implement this method
        return sentence.substring(0,position) + sentence.substring(position + 1);

    }

    /**
     * Prints a message which shows the new sentence after the removal has occured.
     */
    private void printNewSentence(String changedSentence) {

        // Implement this method
        System.out.println("New sentence is " + changedSentence);
    }

    public static void main(String[] args) {
        RemoveCharacter ex = new RemoveCharacter();
        ex.start();
    }
}
