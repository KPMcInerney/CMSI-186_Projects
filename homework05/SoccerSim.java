/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
import java.awt.geom.Line2D;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Random;

 public class SoccerSim {
    /**
     *  Class field definintions go here
     */
    private static int numBalls;
    private static Ball[] balls;
    private static Timer timer;
    private static double fieldWidth = 1000;
    private static double fieldHeight = 1000;
    private static boolean[] validBalls; //array to keep track of valid balls
    private static boolean[] stillMoving; //array to keep track of moving balls
    private static int invalidBalls = 0;
    private static String[] timeValues = new String[3]; //creates timeValue string to handle hours, mins, and seconds
    private static DecimalFormat formatter = new DecimalFormat("#00.0"); //sets format for the strings
    private static int result = 0;
    /**
     *  Constructor goes here
     */
    public SoccerSim(){
      super();
   }

   public static void handleInitialArgs( String args[] ){ //method to handle initial arguments
      if ( ((args.length % 4) == 0 && args.length > 7) || ((args.length % 4) == 1 && args.length > 7) ) { //validates arg length
         try {
            checkArgs( args ); //ensures validity of given args
            timer = new Timer( args ); //creates timer
            numBalls = args.length/4 + 1; //calculates number of balls
            balls = new Ball[numBalls]; //creates array of balls
            validBalls = new boolean[numBalls]; //creates array of booleans for valid balls
            stillMoving = new boolean[numBalls]; //creates array of booleans for moving balls
            for ( int j = 0; j < validBalls.length; j++ ){ //sets all ball valid and moving values to true
               validBalls[j] = true;
               stillMoving[j] = true;
            }
            for ( int i = 0; i < balls.length - 1; i++ ){ //initiates each ball in the ball array with given values
               balls[i] = new Ball( args[0+(i*4)], args[1+(i*4)], args[2+(i*4)], args[3+(i*4)] );
            }
            balls[numBalls-1] = new Ball( poleRand(fieldWidth), poleRand(fieldHeight), "0", "0" ); //creates a non-moving pole at random position
         } catch( NumberFormatException nfe ){ //catches invalid time slice
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "     Please use positive real numbers for time slice[e.g., '10' for 10 seconds]");
            System.out.println( "       [time slice out of range]" );
            System.out.println( "     Please try again.......\n" );
            System.exit( 1 );
         }
      } else { //explains invalid number of given args
         System.out.println( "   Sorry you must enter 4 arguments per ball for at least 2 balls and/or an optional time slice\n" +
                             "   Usage: java Ball (<ballx> <bally> <ballxSpeed> <ballySpeed>) x n [optional time slice]\n" +
                             "   Please try again...........\n" );
         System.exit( 1 );
      }
   }

   public static void checkArgs( String args[] ){ //checks validity of given args
      try {
         int counter = 0;
         for ( int i = 0; i < args.length; i++ ){
            if ( counter % 4 == 0 || counter % 4 == 1 ){ //checks validity of positions values
               if ( Double.parseDouble(args[counter]) > fieldWidth/2 || Double.parseDouble(args[counter]) < -fieldHeight/2 ){
                  result = 1;
                  throw new NumberFormatException();
               }
            }
            if ( counter % 4 == 2 || counter % 4 == 3 ){ //checks validity of velocity values
               if ( Integer.parseInt(args[counter]) > 50 || Integer.parseInt(args[counter]) < -50 ){
                  result = 2;
                  throw new NumberFormatException();
               }
            }
            counter += 1;
         }
      } catch( NumberFormatException nfe ) {
         if ( result == 1 ){ //prints statements if given positions are invalid
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "     Please use ball coordinates inside the boundaries of the field: ");
            System.out.println( "     width of [" + fieldWidth/2 + ",-" + fieldWidth/2 + "] and height of [" +
                                      fieldHeight/2 + ",-" + fieldHeight/2 + "]" );
            System.out.println( "   Please try again.......\n" );
            System.exit( 1 );
         } else if ( result == 2 ) { //prints statements if given valocities are invalid
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "     Please use ballX and ballY velocities between 50 & -50");
            System.out.println( "       [ball velocities out of range]" );
            System.out.println( "   Please try again.......\n" );
            System.exit( 1 );
         } else { //returns if other issues
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "   Please try again.......\n" );
            System.exit( 1 );
         }
      }
   }

   public static String poleRand( double a ){ //creates random x,y positions for pole
      double rand = new Random().nextDouble(); //makes random number
      return Double.toString( ((a/2) * rand) * (new Random().nextBoolean() ? -1 : 1) ); //randomly makes the number positive or negative
   }

   public static double distanceCalculator( double x1, double y1, double x2, double y2 ){ //method to calculate distance between balls
      return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) ); //distance formula
   }

   public static boolean checkCollision( int i, int j ){ //method to check for collision between balls
      if ( balls[i].getBallRadius() * 2 >= distanceCalculator( balls[i].getX(), balls[i].getY(), balls[j].getX(), balls[j].getY() ) ){
         return true;
      } else {
         return false;
      }
   }

   public static void collisionHandler(){ //method to handle collisions of each ball
      for (int j = 0; j < balls.length - 1; j++){ //loops through all balls
         for (int i = j + 1; i < balls.length - 1; i++){ //loops through all unchecked balls
            if ( validBalls[i] && validBalls[j] ){ //iff both balls are valid
               if ( checkCollision( j, i ) ){ //check for collisions
                  endSim( j, i ); //calls end simulation function
               }
            }
         }
      }
   }

   public static void boundaryChecker(){ //ensures balls are all within field boundaries
      for (int i = 0; i < validBalls.length; i++){ //checks all balls
         if ( validBalls[i] ){ //if theyre valid, check against field boundaries
            if ( balls[i].getX() > fieldWidth/2 || balls[i].getX() < (fieldWidth/2 * -1) || balls[i].getY() > fieldHeight/2 || balls[i].getY() < (fieldHeight/2 * -1) ){
               validBalls[i] = false;
               stillMoving[i] = false;
               invalidBalls += 1;
            }
         }
      }
      if ( invalidBalls == validBalls.length - 1 ){ //if invalidBalls == the number of balls
         endSim( -2, -2 ); //call the end simulation method
      }
   }

   public static void velocityChecker(){ //ensures balls velocities are valid
      for (int i = 0; i < balls.length - 1; i++){ //for each ball
         if ( validBalls[i] && stillMoving[i] ){ //if the balls are valid and moving
            if ( Math.abs(balls[i].getXSpeed()) < 0.0833 || Math.abs(balls[i].getYSpeed()) < 0.0833 ){ //ensure the ball is moving
               stillMoving[i] = false;
               invalidBalls += 1;
            }
         }
      }
      if ( invalidBalls == validBalls.length - 1 ){ //if invalidBalls == the number of balls
         endSim( -1, -1 ); //call the end simulation method
      }
   }

   public static void reportFunction(){ //method to return all ball values at given times
      System.out.println( "Time Slice: " + timeValues[0] + ":" + timeValues[1] + ":" + timeValues[2] );
      for (int i = 0; i < balls.length; i++){ //for each ball
         if ( validBalls[i] ){ //if the ball is valid
            if ( i == validBalls.length - 1 ){ //if it is the last ball in the array its the pole
               System.out.println( "   Pole: " + balls[i].toString() ); //so print the pole values
            } else {
               if ( stillMoving[i] ){ //if the ball is still moving
                  System.out.println( "   Ball " + i + ": " + balls[i].toString() + " [In boundaries][Moving]" ); //print ball values
               } else {
                  System.out.println( "   Ball " + i + ": " + balls[i].toString() + " [In boundaries][Not moving]" ); //print ball values
               }
            }
         }
         if ( !validBalls[i] ){ //if the ball is valid
            if ( i == validBalls.length - 1 ){ //if it is the last ball in the array its the pole
               System.out.println( "   Pole: " + balls[i].toString() ); //so print the pole values
            } else {
               if ( stillMoving[i] ){ //if its still moving
                  System.out.println( "   Ball " + i + ": " + balls[i].toString() + " [Out of Bounds][Moving]" ); //print ball values
               } else {
                  System.out.println( "   Ball " + i + ": " + balls[i].toString() + " [Out of Bounds][Not moving]" ); //print ball values
               }
            }
         }
      }
      System.out.println( "\n" );
   }

   public static void endSim( int a, int b ){ //method to end the simulation and print final values
      if ( a == -1 && b == -1 ) { //called if all ball velocities have stopped
         System.out.println( "        -- NO COLLISION IS POSSIBLE --");
         System.out.println( "   -- ALL REMAINING VALID BALLS HAVE STOPPED --" );
         System.exit( 0 );
      } else if ( a == -2 && b == -2 ) { //called if all balls have passed field boundaries
         System.out.println( "             -- NO COLLISION IS POSSIBLE --" );
         System.out.println( "   -- ALL REMAINING VALID BALLS OUT OF FIELD BOUNDARIES --" );
         System.exit( 0 );
      } else { //called if at least one ball is still valid
         System.out.println( "   CONTACT ball at time " + timeValues[0] + " hours, " + timeValues[1] + " minutes, " +
                             timeValues[2] + " seconds. \nCOLLISION BETWEEN:" );
         if ( a == numBalls ){ //if a call value == the last ball it is the pole
            System.out.println( "   Pole at position<" + balls[a].getX() + "," + balls[a].getY() + ">" );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + b + " at " + balls[b].toString());
         } else if ( b == numBalls ) { //if b call value == the last ball it is the pole
            System.out.println( "   Pole at position<" + balls[b].getX() + "," + balls[b].getY() + ">" );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + a + " at " + balls[a].toString());
         } else { //if neither call value == the last ball, print both balls
            System.out.println( "   Ball " + a + " at " + balls[a].toString() );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + b + " at " + balls[b].toString());
         }
         System.out.println("\n");
         System.exit( 0 );
      }
   }

   public static void formatTimeValues(){ //method to format the time values
      timeValues[0] = formatter.format( Math.round( timer.getTotalSeconds() / 3600 - .5) ); //value set to hours
      timeValues[1] = formatter.format( Math.round( (timer.getTotalSeconds() % 3600) / 60 - .5 ) ); //value set to minutes
      timeValues[2] = formatter.format( timer.getTotalSeconds() % 60 ); //value set to seconds
   }

   public static void main ( String args[] ){
      SoccerSim ss = new SoccerSim(); //creates instance of SoccerSim class
      handleInitialArgs( args ); //calls method to handle initial args
      System.out.println( "\n    --STARTING SIMULATION--\n");
      formatTimeValues(); //calls method to format time values
      reportFunction(); //calls method to print all ball and time data
      collisionHandler(); //calls method to check for all ball collisions
      while (true){ //loop until collision, all balls have stopped, or all balls have left boundaries
         for ( int i = 0; i < balls.length; i++ ){ //for each ball...
            if ( validBalls[i] ){ //if it is valid...
               balls[i].ballRun( timer.getTimeSlice() ); //update its variables
            }
         }
         boundaryChecker(); //call method to check balls against their boundaries
         velocityChecker(); //call method to check for valid velocity of balls
         collisionHandler(); //call method to check for collision of all balls
         formatTimeValues(); //call method to format time values
         reportFunction(); //call method to print all ball and time data
         timer.tick(); //iterate to the next time slice
      }
   }

 }
