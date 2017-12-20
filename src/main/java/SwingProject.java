/*******************************************************************************
*  file:SwingProject
*  author:Omar Rodriguez
          Nahid Enayatzadeh
          Marc Deaso
          Christopher Santos
          Jazmin Guerrero
*   class: CS245 - Programming Graphical User Interfaces
* 
*  assignment: Swing Project V1.2
*  date last modified: October/23/2016
* 
* Purpose: This program implements the traditional game of Hangman
* Program working with following functions:
* 1)Centered the screen on 600 x 400 pixel window
* 2)Program will have a start up screen which display group name for 3 second
* 3)After showing three function buttons, play, High Score and credit on new screen
* 4)By clicking on each button take us to the new page 
* 5)Must display current time and date
* 6)This game a random word will be selected from the following list(abstract,
* cemetery, nurse,pharmacy,climbing). user may click on one of the alphabet 
* button to guess the letter which may be in selected word of guessing, the line 
* makes by the lines and the button will be display. if the guessing is not found
* it will give us alert
* 
* V1.1: Addition of color button game. Implementation of high scores file.
* Known bugs in high scores implementation: Scores are sometimes added multiple
* times causing a false representation of previous high scores.
* 
* V1.2: Addition of Sudoku game. Corrected high score bugs. Addition of escape
* hotkey to exit the program, tool tips for all components, and F1 hotkey.
* 
*/
package main.java;

import java.awt.Color;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import main.java.controller.*;
import main.java.model.*;
import main.java.view.*;

public class SwingProject {
    public static final String PROJECT_NAME = "CS 245 - Swing Project v1.2";
    public static final String DICTIONARY = "src/main/resources/dictionary.txt";
    public static final String CONTRIBUTORS[] = {
        "Omar Rodriguez, 008796203", 
        "Nahid Enayatzadeh, 010164622",
        "Marc Deaso, 011179285",
        "Christopher Santos, 010163647",
        "Jazmin Guerrero, 009007193"};
    
    //Placeholder from v1.0
    /*public static final String SCORE_NUMBER[] = {
        "Omar Rodriguez..00",
        "Nahid Enayatzadeh..00",
        "Marc Deaso..00",
        "Christopher Santos..00",
        "Jazmin Guerrero..00"};*/
    
    public static final String SPLASH_KEY = "splashscreen";
    public static final String FUNCTION_KEY = "functionscreen";
    public static final String HIGH_SCORE_KEY = "highscorescreen";
    public static final String CREDITS_KEY = "creditsscreen";
    public static final String GAME_KEY = "gamescreen";
    public static final String GAME_OVER_KEY = "gameoverscreen";
    public static final String CIRCLE_GAME_KEY = "circlegamescreen";
    public static final String SUDOKU_KEY = "sudokuscreen";

    private MainFrameController mainFrameController;
    
    private SplashController splashController;
    private FunctionController functionController;
    private GameController gameController;
    private CircleGameController circleGameController;
    private SudokuController sudokuController;
    private CreditsController creditsController;
    private GameOverController gameoverController;
    private HighScoreController highScoreController;
    
    private SwingProject(){
    }
    
    //method: setup
    //purpose: Create the various panels (game screens) for our game
    // and attach them to the main frame.
    private void setup(){
       mainFrameController = new MainFrameController(
                new MainFrameModel(PROJECT_NAME,600,400,null,EXIT_ON_CLOSE), 
                new MainFrame()
        );
        
        splashController = new SplashController(
                new SplashPanel(),
                new SplashModel(PROJECT_NAME,"REDS",Color.BLACK,3000),
                mainFrameController
        );
        
        functionController = new FunctionController(
                new FunctionPanel(),
                new FunctionModel(Color.BLACK,"Play","HighScores","Credits"),
                mainFrameController
        );
        
        gameController = new GameController(
                new GamePanel(),
                new GameModel(DICTIONARY),
                mainFrameController
        );
        
        circleGameController = new CircleGameController(new CircleGamePanel(), mainFrameController);
        
        sudokuController = new SudokuController(
                new SudokuPanel(),
                mainFrameController
        );
        
        creditsController = new CreditsController(
                new CreditsPanel(),
                new CreditsModel("Credits", CONTRIBUTORS, Color.BLACK),
                mainFrameController
        );
        
        highScoreController = new HighScoreController(
                new HighScorePanel(),
                new HighScoreModel("High Scores", Color.BLACK),
                mainFrameController
        );
                
        gameoverController = new GameOverController(
                new GameOverPanel(),
                new GameOverModel(),
                mainFrameController
        );
                              
        mainFrameController.addPanel(splashController.getPanel(),SPLASH_KEY);
        mainFrameController.addPanel(functionController.getPanel(),FUNCTION_KEY);
        mainFrameController.addPanel(gameController.getPanel(),GAME_KEY);
        mainFrameController.addPanel(circleGameController.getPanel(),CIRCLE_GAME_KEY);
        mainFrameController.addPanel(sudokuController.getPanel(), SUDOKU_KEY);
        mainFrameController.addPanel(creditsController.getPanel(),CREDITS_KEY);
        mainFrameController.addPanel(highScoreController.getPanel(),HIGH_SCORE_KEY);
        mainFrameController.addPanel(gameoverController.getPanel(),GAME_OVER_KEY);
        
        functionController.setGameControllerReference(gameController);
        gameoverController.setGameControllerReference(gameController);
        gameoverController.setCircleGameControllerReference(circleGameController);
        gameoverController.setSudokuReference(sudokuController);
        gameoverController.setHighScoreControllerReference(highScoreController);
    }
    
    //method: setupAndStart
    //purpose: call setup method, switch to first application screen (splash)
    //then set the whole thing visible
    private void setupAndStart(){
        javax.swing.SwingUtilities.invokeLater(() -> {
            setup();
            mainFrameController.changeVisibleCard(SPLASH_KEY);
            mainFrameController.getFrame().setVisible(true);
        });
    }

    //method: main
    //purpose: the entry-point to our application
    public static void main(String[] args) {
        SwingProject game = new SwingProject();
        game.setupAndStart();
    }
    
}
