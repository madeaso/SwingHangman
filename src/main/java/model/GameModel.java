/***************************************************************
* file: GameModel.java
* author:   Christopher Santos
*           Omar Rodriguez
* class: CS 245 - Programming Graphical User Interfaces
*
* assignment: Swing Project v1.0
* date last modified: 10/11/2016
*
* purpose: This is the model component for the game screen
*
****************************************************************/ 
package main.java.model;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class GameModel implements PanelModel{
    private int incorrectCount;
    private int correctCount;
    private LocalDateTime dateTime;
    private int gameScore;
    private int[] lettersUsed;
    private ArrayList<String> dictionary;
    private Scanner scan;
    private String randomWord;
    private char[] randomWordCharArray;
       
    public GameModel(){
    }
    
    public GameModel(String fileName){
        dictionary = new ArrayList<String>();
        this.readDictionary(fileName);
        randomWord = selectRandomWord();
        randomWordCharArray = randomWord.toCharArray();
        incorrectCount = 0;
        correctCount = 0;
        gameScore = 100;
    }
    
    //method: reset
    //purpose: reset this game model for a new game
    public void reset(){
        randomWord = selectRandomWord();
        randomWordCharArray = randomWord.toCharArray();
        incorrectCount = 0;
        correctCount = 0;
        gameScore = 100;
    }

    //setDateTime
    //purpose: sets game date/time to system date/time
    public void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }
    
    //method: makeGuess
    //purpose: check if user guess is in string. Return a
    // list of positions if character is found in string
    public ArrayList<Integer> makeGuess(String guess){
        char guessChar = guess.charAt(0);
        ArrayList<Integer> positions = new ArrayList<>();
        for(int i = 0; i < randomWordCharArray.length; i++){
            if(randomWordCharArray[i] == guessChar){
                positions.add(i);
            }
        }
        if(positions.size() == 0){
            incorrectCount++;
            gameScore -= 10;
        } else {
            correctCount += positions.size();
        }
        return positions;
        
    }
    
    //getDateTime
    //purpose: returns current displayed date/time
    public String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM-dd-uuuu hh:mm:ss a");
        return dtf.format(dateTime);
    }

    //setScore
    //purpose: sets score value to points
    public void setScore(int score) {
        this.gameScore = score;
    }
    
    //getScore
    //purpose: returns current score value
    public int getScore() {
        return gameScore;
    }

    //name: readDictionary()
    //purpose: reads a given file that contains words for game (dictionary)
    private void readDictionary(String dict) {
        try{
            this.scan = new Scanner(new File(dict));
        } catch(Exception e) {
            System.out.println("The file " + dict + " deos not exist.\n"
                    + "No dictionary file was loaded.");
            return;
        }
        while(scan.hasNextLine()) {
            this.dictionary.add(this.scan.nextLine().toUpperCase());
        }
        scan.close();
    }
    
    //name: selectRandomWord()
    //purpose: selects random word from dictionary
    private String selectRandomWord() {
        Random rand = new Random();
        return this.dictionary.get(rand.nextInt(this.dictionary.size()));
    }

    //method: getIncorrectCount
    //purpose: return number of incorrect guesses made so far
    public int getIncorrectCount() {
        return incorrectCount;
    }

    //method: getCorrectCount
    //purpose: return number of correct guesses made so far
    public int getCorrectCount() {
        return correctCount;
    }

    //method: getGameScore
    //purpose: return current score
    public int getGameScore() {
        return gameScore;
    }

    //method: setGameScore
    //purpose: set current game score
    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
    
    //method: getWordLength
    //purpose: return length of current word
    public int getWordLength(){
        return randomWord.length();
    }
    
}
