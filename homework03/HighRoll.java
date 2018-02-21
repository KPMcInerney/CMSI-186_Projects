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

public class HighRoll{
   // This line uses the two classes to assemble an "input stream" for the user to type
   // text into the program
   public static DiceSet ds = null;
   public static int count;
   public static int sides;
   public static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   public static String inputLine = null;
   public static String[] inputLines = null;
   public static String[] blankString = new String [0];
   public static boolean quit = false;
   public static boolean quit2 = false;
   public static int currScore = 0;
   public static int highScore = 0;
   public static boolean invalid = false;
   public static boolean runOnce = true;

   public static void menu(){
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

   public static void main( String args[] ) {
      if ( !quit ){
         setInputs(args);
      }
      if ( runOnce ){
         ds = new DiceSet(count, sides);
         runOnce = false;
      }
      System.out.println( "\n  Welcome to the HighRoll Game!!" );

      while( true ) {
         if ( quit2 ){
            break;
         }
         if ( highScore == (count * sides) || currScore == (count * sides) ){
            System.out.println( "\n___________________________________________________________" );
            System.out.println( "\fCONGRATULATIONS YOU GOT THE MAX SCORE POSSIBLE FOR THIS SET" );
            System.out.println( "___________________________________________________________" );
         }
         menu();
         System.out.print( ">>" );
         try {
            inputLine = input.readLine();
            if ( 0 == inputLine.length() ) {
               System.out.println("You didn't type anything. Enter some text!:");
               throw new IOException();
            }
            System.out.println( "   You typed: " + inputLine );

            if ( '1' == inputLine.charAt(0) ){
               ds.roll();
               System.out.println( "    You rolled all the Die" );
               System.out.println( "    The new set is: " + ds.toString() );
            }
            if ( '2' == inputLine.charAt(0) ){
               System.out.println( "\f    Which die index would you like to Roll? (Valid numbers range from 1 to " + count + ")" );
               System.out.print( ">>" );
               inputLine = input.readLine();
               if ( inputLine.length() == 0 || Integer.parseInt(inputLine) > count ){
                  System.out.println( "The index you tried to roll was invalid" );
                  throw new IOException();
               }
               ds.rollIndividual( Integer.parseInt(inputLine) );
               System.out.println( "    The die at index " + Integer.parseInt(inputLine) + " is now " + ds.getIndividual(Integer.parseInt(inputLine)) );
            }
            if ( '3' == inputLine.charAt(0) ){
               currScore = ds.sum();
               System.out.println( "    The total score for this set is: " + currScore );
            }
            if ( '4' == inputLine.charAt(0) ){
               highScore = currScore;
               System.out.println( "   " + highScore + " is now your High Score." );
            }
            if ( '5' == inputLine.charAt(0) ){
               System.out.println( "     Your current High Score is: " + highScore );
            }
            if ( 'q' == inputLine.charAt(0) ){
               quit2 = true;
            }
         }
         catch( IOException ioe ) {
            main(args);
         }
      }
   }

   public static void setInputs( String args[] ){
      try {
         if ( args.length == 2 ){
            if ( Integer.parseInt(args[0]) < 2 || Integer.parseInt(args[1]) < 4 ){
               System.out.println( "\n   Either input 1 is lower than 2, or input 2 is less than 4" );
               invalid = true;
               throw new IOException();
            }
            count = Integer.parseInt(args[0]);
            sides = Integer.parseInt(args[1]);
         } else {
            System.out.println( "\n   Input the number of Die and number of Sides for each die. Ex: '2 4'");
            System.out.println( "   Or press q to quit program," );
            System.out.print( ">>" );
            inputLine = input.readLine();
            if ( inputLine.length() == 0 ){
               throw new IOException();
            }
            if( 'q' == inputLine.charAt(0) ){
               quit = true;
               quit2 = true;
            }
            inputLines = inputLine.split( "\\s+" );
            if ( inputLines.length == 2 ){
               if ( Integer.parseInt(inputLines[0]) < 2 || Integer.parseInt(inputLines[1]) < 4 ){
                  throw new IOException();
               }
               count = Integer.parseInt( inputLines[0] );
               sides = Integer.parseInt( inputLines[1] );
               invalid = false;
               quit = true;
            } else {
               throw new IOException();
            }
         }
      } catch( IOException ioe ){
         //System.out.println( "   Invalid Inputs. Need to include number of Die and sides per die. ex:'2 4' \n" );
         if ( invalid == true ){
            setInputs(blankString);
         }
         if ( !quit ){
            setInputs(args);
         }
      }
   }

}
