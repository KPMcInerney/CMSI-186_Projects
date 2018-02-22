/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-14
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{ //initiates HighRoll class
   // This line uses the two classes to assemble an "input stream" for the user to type
   // text into the program
   public static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   public static String inputLine = null; //string later used to store user inputs
   public static DiceSet ds = null; //DiceSet
   public static int count; //input for DiceSet
   public static int sides; //input for DiceSet
   public static String[] inputLines = null; //array to hold strings of user input from inputLine string (w/o spaces)
   public static String[] blankString = new String [0]; //creates a blank string
   public static boolean quit = false; //quit boolean later used for quitting setInputs() method
   public static boolean quit2 = false; //quit2 boolean later used for quitting main() method
   public static int currScore = 0; //sets currScore variable to store the current DiceSet sum
   public static int highScore = 0; //sets highScore variable to store the high DiceSet sum
   public static boolean invalid = false; //invalid boolean later used for checking user input validity
   public static boolean runOnce = true; //runOnce boolean used to ensure only one DiceSet is created

   public static void menu(){ //method to list all the menu options when called
      System.out.println( "\n\n  Main Manu " );
      System.out.println( "     Your current High Score is: " + highScore );
      System.out.println( "     Your current DiceSet is: " + ds.toString() );
      System.out.println( "     1: Roll all the die." );
      System.out.println( "     2: Roll a single die." );
      System.out.println( "     3: Calculate the score for this set." );
      System.out.println( "     4: Save this score as a High Score." );
      System.out.println( "     5: Display the High Score." );
      System.out.println( "     Q: Press the 'q' key to quit the program.\n" );
   }

   public static void main( String args[] ) { //main method to run the program shell
      if ( !quit ){ //only runs setInputs if the quit boolean is false (its false when program is first run)
         setInputs(args); //method to set the count and sides variables for DiceSet construction
      }
      if ( runOnce ){ //only constructs a new DiceSet when runOnce is true (its true when program is first run)
         ds = new DiceSet(count, sides); //creates new DiceSet
         runOnce = false; //sets runOnce to false so new DiceSets aren't created in following main loops
      }
      System.out.println( "\n  Welcome to the HighRoll Game!!" );

      while( true ) { //main program while loop
         if ( quit2 ){ //breaks out of the main program when quit2 is true (is set to true later when user enters 'q')
            break;
         }
         if ( highScore == (count * sides) || currScore == (count * sides) ){ //displays congratulations when current score or high score is max possible for DiceSet
            System.out.println( "\n___________________________________________________________" );
            System.out.println( "\fCONGRATULATIONS YOU GOT THE MAX SCORE POSSIBLE FOR THIS SET" );
            System.out.println( "___________________________________________________________" );
         }
         menu(); //calls method that displays menu options
         System.out.print( ">>" );
         try {
            inputLine = input.readLine(); //sets inputLine string to the user input
            if ( 0 == inputLine.length() ) { //checks if there was no user input
               System.out.println("You didn't type anything. Enter some text!:"); //explains the error to the user
               throw new IOException(); //throws IOException to exit try statement
            }
            System.out.println( "   You typed: " + inputLine ); //prints the received user input

            if ( '1' == inputLine.charAt(0) ){ //if user input was 1
               ds.roll(); //roll all the die in the DiceSet
               System.out.println( "    You rolled all the Die" ); //explains process to user
               System.out.println( "    The new set is: " + ds.toString() ); //prints new DiceSet die for user
            }
            if ( '2' == inputLine.charAt(0) ){ //if user input was 2
               System.out.println( "\f    Which die index would you like to Roll? (Valid numbers range from 1 to " + count + ")" ); //explains valid range to user
               System.out.print( ">>" );
               inputLine = input.readLine(); //sets inputLine string to user input
               if ( inputLine.length() == 0 || Integer.parseInt(inputLine) > count ){ //if there was no user input or input was out of range
                  System.out.println( "The index you tried to roll was invalid" ); //explain error to user
                  throw new IOException(); //throws IOException to exit try loop
               }
               ds.rollIndividual( Integer.parseInt(inputLine) ); //roll individual die at the valid given input
               System.out.println( "    The die at index " + Integer.parseInt(inputLine) + " is now " + ds.getIndividual(Integer.parseInt(inputLine)) ); //explains process to user
            }
            if ( '3' == inputLine.charAt(0) ){ //if user input was 3
               currScore = ds.sum(); //set current sum variable to the sum of the current DiceSet
               System.out.println( "    The total score for this set is: " + currScore ); //tells user the score of this set
            }
            if ( '4' == inputLine.charAt(0) ){ //if user input was 4
               highScore = ds.sum(); //set the high score variable to the sum of the current DiceSet
               System.out.println( "   " + highScore + " is now your High Score." ); //tells user new highscore was set
            }
            if ( '5' == inputLine.charAt(0) ){ //if user input was 5
               System.out.println( "     Your current High Score is: " + highScore ); //returns highscore to user
            }
            if ( 'q' == inputLine.charAt(0) ){ //if user input was q
               quit2 = true; //set quit2 variable to true so next loop breaks the while loop
            }
         }
         catch( IOException ioe ) { //catches any thrown IOExceptions
            main(args); //then re-calls main method giving user chance to choose valid inputs
         }
      }
   }

   public static void setInputs( String args[] ){ //method to set the value of the count and sides variables for DiceSet
      try {
         if ( args.length == 2 ){ //if there are 2 args given
            if ( Integer.parseInt(args[0]) < 2 || Integer.parseInt(args[1]) < 4 ){ //if the args are invalid inputs
               System.out.println( "\n   Either input 1 is lower than 2, or input 2 is less than 4" ); //explain the error to user
               invalid = true; //set invalid boolean to true
               throw new IOException(); //throw IOException to break the try loop
            }
            count = Integer.parseInt(args[0]); //otherwise if args are valid set count to arg 0
            sides = Integer.parseInt(args[1]); //otherwise if args are valid set sides to arg 1
         } else { //otherwise if arg length is not 2...
            System.out.println( "\n   Input the number of Die and number of Sides for each die. Ex: '2 4'"); //gives user instruction for valid inputs
            System.out.println( "   Or press q to quit program," ); //tells user about quit program option
            System.out.print( ">>" );
            inputLine = input.readLine(); //sets inputLine string to user input
            if ( inputLine.length() == 0 ){ //if length of user input is 0
               throw new IOException(); //throw an IOException to break the try loop
            }
            if( 'q' == inputLine.charAt(0) ){ //if the user input was q... (set booleans that quit the program)
               quit = true; //set quit boolean to true (this boolean quits the setInputs() method)
               quit2 = true; //set quit2 boolean to true (this boolean quits the main method)
            }
            inputLines = inputLine.split( "\\s+" ); //divide the inputLine string by any spaces and add the new strings to the array
            if ( inputLines.length == 2 ){ //if the length of the array is 2 (user gave 2 inputs)
               if ( Integer.parseInt(inputLines[0]) < 2 || Integer.parseInt(inputLines[1]) < 4 ){ //if given inputs aren't...
                  throw new IOException(); //throw IOException to break try loop
               }
               count = Integer.parseInt( inputLines[0] ); //set count variable to first input
               sides = Integer.parseInt( inputLines[1] ); //set sides variable to second input
               invalid = false; //set invalid boolean to false (so setInputs function isn't recalled later)
               quit = true; //set quit boolean to true (so setInputs function isn't recalled later)
            } else { //if inputLines array length is not 2
               throw new IOException(); //throw new IOException to break try loop
            }
         }
      } catch( IOException ioe ){ //catches any thrown IOExceptions
         if ( invalid == true ){ //if invalid boolean is true re-call the setInputs() method (it must be looped until inputs for DiceSet are valid)
            setInputs(blankString); //re-call setInputs() method with a blank string with args so it skips first if statement
         }
         if ( !quit ){ //if quit boolean is false
            setInputs(args); //call setInputs() method again with the previous args because inputs for DiceSet haven't been set yet
         }
      }
   }

}
