/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.Random;

public class Hangman extends ConsoleProgram {

    public void run() {
        println("Welcome to Hangman Game!");

        canvas.reset();

        while(NTURNS > 0){

            println("Your word looks like this: " + hiddenWord);
            println("You have left " + NTURNS + " guesses.");

            while(true){
                getLetter = readLine("Please enter the letter: ");
                if (getLetter.length() == 1) {
                    break;
                }
                else {
                    println("Please enter only one letter");
                }

            }

            if (checkLetter(getLetter.charAt(0)) == false) {
                println("Sorry, your guess is incorect.");
                NTURNS --;
                canvas.addBody(NTURNS);
                canvas.noteIncorrectGuess(hiddenWord);
            }
            else{
                println("You have guessed correctly. ");
                hiddenWord = changeWord(getLetter.charAt(0), hiddenWord);
                println(hiddenWord);
                canvas.noteIncorrectGuess(hiddenWord);
            }

            if (gameOver(hiddenWord)){
                println("You Won!");
                canvas.displayWord(word);
                break;
            }

        }

        if (NTURNS == 0){
            println("You lose!");
            println("Your word was " + word);
            canvas.displayWord(word);
        }

	}


	// checks if letter is in a word
	private boolean checkLetter(char let){
        let = Character.toUpperCase(let);

        for(int i = 0; i < word.length(); i++){
            if (word.charAt(i) == let) return true;
        }

        return false;
    }

    // converts words into coded version; if letter is in a word, it adds a letter, if not adds " - "

    private String changeWord(char let, String hiddenWord){
        String codedWord = "";
        let = Character.toUpperCase(let);

        for(int i = 0; i < word.length(); i++){
            if (word.charAt(i) == let) {
                codedWord += let;
            }
            else if(Character.isLetter(hiddenWord.charAt(i))){
                codedWord += hiddenWord.charAt(i);
            }
            else {
                codedWord += "-";
            }
        }

        return codedWord;
    }

    private String hideWord(){
        String hiddenWord = "";
        for(int i =0; i < word.length(); i++) hiddenWord += "-";
        return hiddenWord;
    }


    // checks if all user guessed all the letters, if yes, returns true, else false
    private boolean gameOver(String wrd){
        int count  = 0;
        for(int i = 0; i < wrd.length(); i ++){
            if(Character.isLetter(wrd.charAt(i))) count++;
        }
        if (count == wrd.length()) return true;

        return false;
    }

    public void init(){
        canvas = new HangmanCanvas();
        add(canvas);
    }


	// instantce variables

    Random rand = new Random();
    HangmanLexicon Lexicon = new HangmanLexicon();
    String word = Lexicon.getWord(rand.nextInt(Lexicon.getWordCount())); // returns random word from Hangman Lexicon
    private int NTURNS = 8;
    String hiddenWord = hideWord();
    String getLetter;
    private HangmanCanvas canvas;

}
