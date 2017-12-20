/***************************************************************
* file: HighScoreModel.java
* author: Nahid Enayatzadeh, Jazmin Guerrero, Marc Deaso
* class: CS 245 - Programming Graphical User Interfaces
*
* assignment: Swing Project v1.2
* date last modified: 10/22/2016
*
* purpose: This is the model that contains the business logic for the
* highscore screen. Reads from and writes to the file HIGH_SCORES_FILE
* 
* V1.2: Fixed high score logging bugs, switched local array variables
*       to linked lists.
*
****************************************************************/ 
package main.java.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
//import static main.java.SwingProject.CONTRIBUTORS;

public class HighScoreModel implements PanelModel {
    public static final String HIGH_SCORES_FILE = "src/main/resources/highscores.txt";
    public static final int NUM_SCORES = 5;
    
    private String highScoreTitle;
    private LinkedList<String> leaders;
    private LinkedList<Integer> scores;
    private Color backgroundColor;
    private String backButtonText;
    private File hsFile;
        
    /*
    HighScoreModel default constructor. Does not read from file. 
    */
    public HighScoreModel() {
        hsFile = new File(HIGH_SCORES_FILE);
        this.highScoreTitle = "PLACEHOLDER";
        this.leaders = new LinkedList();
        this.scores = new LinkedList();
        this.backgroundColor = Color.BLACK;
        this.backButtonText = "Back";
    }
    /*
    HighScoreModel constructor accept title name, string array of members name
    * and the color of background and set it up to the private varialbes that
    * declared on the class.
    */
     public HighScoreModel(String title, Color color) {
        hsFile = new File(HIGH_SCORES_FILE);
        this.highScoreTitle = title;
        this.backgroundColor = color;
        this.backButtonText = "Back";
        this.leaders = new LinkedList();
        this.scores = new LinkedList();
        
        //leaders and scores lists also initialized in readScores() method.
        this.readScores();
    }
    
     //Method: getHighScoreTiltle
    //Purpose: Having access to high score title
     public String getHighScoreTitle() {
        return this.highScoreTitle;
    }
    
    //Method: setHighScoreTilte
    //Purpose: Modyfing the High score title
    public void setHighScoreTitle(String highScoreTitle) {
        this.highScoreTitle = highScoreTitle;
    }
    
    //Method: getMember
    //Purpose: Having access to Member names
    public String[] getLeadersLabelsText() {
        String[] labels = new String[NUM_SCORES];
        for(int i = 0; i < NUM_SCORES; i++) {
            if(leaders.get(i).equals("none")) {
                labels[i] = "     ";
            }
            else {
                labels[i] = leaders.get(i) + "     " + String.valueOf(scores.get(i));
            }
        }
        return labels;
    }
    
    //Method: getBackgroundColor
    //Purpose: Having access to back grond color
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
    
    //Method: setBackgroundColor
    //Purpose: Modyfing the background color
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    //Method: getBackButtonText
    //Purpose: Having access to change the back button text
    public String getBackButtonText() {
        return this.backButtonText;
    }

    public void readScores() {
        this.leaders = new LinkedList();
        this.scores = new LinkedList();
        BufferedReader inStream;
        String s = "";
        int nameS;
        int count = 0;
        
        //Initialize local lists with arbitrary values.
        for (int i = 0; i < NUM_SCORES; i++)
            {
                scores.add(-1);
                leaders.add("none");
            }
        
        if(!hsFile.exists())
        {
            // create file if doesn't exist
            try
            {
                hsFile.createNewFile();
            }
            catch(Exception e) {
                System.out.println("Error creating file " + HIGH_SCORES_FILE);
                System.out.println(e);
                return;
            }
            
            /*for (int i = 0; i < NUM_SCORES; i++)
            {
                scores[i] = 0;
                leaders[i] = "null";
            }*/
        }
        else // open file
        {
            try
            {
                inStream = new BufferedReader(new FileReader(HIGH_SCORES_FILE));
            }
            catch(Exception e)
            {
                System.out.println("Error reading file " + HIGH_SCORES_FILE);
                System.out.println(e);
                return;
            }
            
             // read high scores into leaders and scores arrays
            try
            {
                s = inStream.readLine();
            }catch(Exception e){}
            while ((s != null) && (count < HighScoreModel.NUM_SCORES)){
                nameS = s.indexOf('\t');
                scores.set(count, Integer.valueOf(s.substring(0,nameS)));
                leaders.set(count, s.substring(nameS +1, s.length()));
                try
                {
                    s= inStream.readLine();
                }
                catch(Exception e){}
                count++;
            }
            
            // close file
            try
            {
                inStream.close();
            }
            catch(Exception e)
            {
                System.out.println("Error closing file " + HIGH_SCORES_FILE);
                System.out.println(e);
            }
            
        }
    }
    
    public void writeScores() {
        BufferedWriter outStream;
        
        // make sure file exists
        if (!hsFile.exists())
        {
            System.out.println(HIGH_SCORES_FILE + " does not exist.");
            return;
        }
        
        // open output file
        try
        {
            outStream = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE));
        }
        catch(Exception e)
        {
            System.out.println("Error reading file " + HIGH_SCORES_FILE);
            System.out.println(e);
            return;
        }
        
        // write to output file
        try
        {
            for(int i = 0; i < NUM_SCORES; i++)
            {
                outStream.write(scores.get(i) + "\t" + leaders.get(i) + "\n");
            }
        }
        catch(Exception e){}
        
        // close file
        try
        {
            outStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error closing file " + HIGH_SCORES_FILE);
            System.out.println(e);
        }
    }

    public int getLowestScore() {
        return scores.get(NUM_SCORES - 1);
    }

    public void addNewHighScore(String text, int newScore) {
        int index = 0;
        int temp = newScore;
        int move = 0;
        //find index where newScore will be placed.
        while(newScore < this.scores.get(index) && index != NUM_SCORES) {
            index++;
        }
        
        leaders.add(index, text);
        scores.add(index, newScore);
        
        this.writeScores();
    }
    
}
