/*
 * Write a tic-tac-toe program where a human user
 * can play against  an AI bot, and where two  AI 
 * bots can play against each other.  Write input
 * and output code so that it's intuitive for the 
 * user.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

public class TicTacToe {

  public static Game game;
  public static int count = 0;
  public static String user_input;
  
  private static int gameMode;
  private static boolean valid_input;
  
  public static void main(String[] args){
  
    int gameSize;
    int minimumGameSize = 1;
    int maximumGameSize = 26;
  
    //When program starts, user is met with a welcome message
    System.out.println("\n\tمرحبا بكم في لعبة اكس او\"");
    System.out.println("\n\tPlease select your Game mode.");
    System.out.println("\n\t    (1) Human vs. Computer");
    System.out.println("\n\t    (2) Computer vs. Computer");
    user_input = getInput("\n\tWhich mode would you like to play? (1/2): ");
    
    //Keep asking for an answer from the user until we get a 1 or a 2
    gameMode(user_input); //gameMode() is defines below
    
    System.out.println("\n\tHow large of a grid would you like to use? ");
    user_input = getInput("\n\tPlease enter an integer between " + minimumGameSize + " and " + maximumGameSize + ": ");
    
    //validate user unput for game size
    valid_input = false;
    while(!valid_input){
    
      if( user_input.length() > 0 && (user_input.substring(0, 1).matches("[1-9]")) && (minimumGameSize <= Integer.parseInt(user_input)) && (Integer.parseInt(user_input) <= maximumGameSize) ){
      
        valid_input = true;
        
      } else {
      
        user_input = getInput("\n\tYou must enter a number between " + minimumGameSize + " and " + maximumGameSize + ": ");
        
      }
    }
    
    //issue warning for game sizes larger than 15
    if(Integer.parseInt(user_input) > 15){
    
      System.out.println("\n\t!!WARNING!!\n\t!!WARNING!!  Games large than 15 will not display correctly if console width is restricted to 80 col (neither will this message)\n\t!!WARNING!!");
      getInput("");
      
    }
    
    gameSize = Integer.parseInt(user_input);
    
    //Create a new Game instance
    game = new Game(gameSize);
    
    //create an array of two players
    Player[] players = new Player[2];
    
    //set players to AI or Human depending on game mode
    if(gameMode==1){
    
      players[0] = new Player("Human");
      players[1] = new Player("AI");
      
    } else {
    
      players[0] = new Player("AI");
      players[1] = new Player("AI");
      
    }
    
    //Draw the blank board initially to show user which columns and rows to choose from
    System.out.println(game.output());
    
    //until the game is over, go back and forth between players in players array
    //output the game map to the screen after each move
    while (!game.finished) {
    
      for(Player player : players){
      
        player.go();
        System.out.println("\n" + game.output());
        count += 1;
        
        if(game.finished){
          break;
        }
        
      }
    }
    
    //output an ending message to the game
    if (game.draw) {
    
      System.out.println("\n\tCat's game!");
      
    } else {
    
      //count variable from earlier is used to decide who went last and therefore won.
      if(count%2==1){
      
        System.out.println("\n\tX's win!");
        
      } else {
      
        System.out.println("\n\tO's win!");
        
      }
    }
  }
  
  //encapsulated code for input stream buffer
  public static String getInput(String prompt) {
  
    BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
    
    System.out.print(prompt);
    System.out.flush();
    
    try {
    
      return stdin.readLine();
      
    } catch (Exception e) {
    
      return "Error: " + e.getMessage();
      
    }
  }
  
  //validates user input and sets the game mode
  private static void gameMode(String user_input) {
    
    valid_input = false;
    
    while(!valid_input){
    
      if((user_input.length() == 1) && (user_input.substring(0,1).matches("[1-2]"))){
      
        valid_input = true;
        
      } else {
      
        user_input = getInput("\n\tYou must enter '1' or '2' for the game mode: ");
        
      }
    }
    
    //Set user input to gameMode for use later
    gameMode = Integer.parseInt(user_input);
    
  }
}