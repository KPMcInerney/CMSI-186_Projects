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
import static java.lang.Math.sqrt;
import java.awt.geom.Line2D;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Random;

 public class SoccerSim {
    private static int numBalls;
    private static Ball[] balls;
    private static Timer timer;
    private static double fieldWidth = 1000;
    private static double fieldHeight = 1000;
    private static boolean[] validBalls;
    private static int invalidBalls = 0;
    private static String[] timeValues = new String[3]; //creates timeValue string to handle hours, mins, and seconds
    private static DecimalFormat formatter = new DecimalFormat("#00.0"); //sets format for the strings

    //check given args for validity
    //ensure stopped balls are still checked for collision if still inside boundaries

    public SoccerSim(){
      super();
   }

   public static void handleInitialArgs( String args[] ){
      if ( ((args.length % 4) == 0 && args.length > 7) || ((args.length % 4) == 1 && args.length > 7) ) {
         try {
            timer = new Timer( args );
            numBalls = args.length/4 + 1;
            balls = new Ball[numBalls];
            validBalls = new boolean[numBalls];
            for ( int j = 0; j < validBalls.length; j++ ){
               validBalls[j] = true;
            }
            for ( int i = 0; i < balls.length - 1; i++ ){
               balls[i] = new Ball( args[0+(i*4)], args[1+(i*4)], args[2+(i*4)], args[3+(i*4)] );
            }
            balls[numBalls-1] = new Ball( poleRand(fieldWidth), poleRand(fieldHeight), "0", "0" );
         } catch( NumberFormatException nfe ){
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "     Please use positive real numbers for time slice[e.g., '10' for 10 seconds]");
            System.out.println( "       [time slice out of range]" );
            System.out.println( "     Please try again.......\n" );
            System.exit( 1 );
         }
      } else {
         System.out.println( "   Sorry you must enter 4 arguments per ball for at least 2 balls and/or an optional time slice\n" +
                             "   Usage: java Ball (<ballx> <bally> <ballxSpeed> <ballySpeed>) x n [optional time slice]\n" +
                             "   Please try again...........\n" );
         System.exit( 1 );
      }
   }

   public static String poleRand( double a ){
      double rand = new Random().nextDouble();
      return Double.toString( ((a/2) * rand) * (new Random().nextBoolean() ? -1 : 1) );
   }

   public static double distanceCalculator( double x1, double y1, double x2, double y2 ){
      return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) );
   }

   public static boolean checkCollision( int i, int j ){
      if ( balls[i].getBallRadius() * 2 >= distanceCalculator( balls[i].getX(), balls[i].getY(), balls[j].getX(), balls[j].getY() ) ){
         return true;
      } else {
         return false;
      }
   }

   public static void collisionHandler(){
      for (int j = 0; j < balls.length - 1; j++){
         for (int i = j + 1; i < balls.length - 1; i++){
            if ( validBalls[i] && validBalls[j] ){
               if ( checkCollision( j, i ) ){
                  endSim( j, i );
               }
            }
         }
      }
   }

   public static void boundaryChecker(){
      for (int i = 0; i < validBalls.length; i++){
         if ( validBalls[i] ){
            if ( balls[i].getX() > fieldWidth/2 || balls[i].getX() < (fieldWidth/2 * -1) || balls[i].getY() > fieldHeight/2 || balls[i].getY() < (fieldHeight/2 * -1) ){
               validBalls[i] = false;
               invalidBalls += 1;
               System.out.println("boundary checker");
            }
         }
      }
      if ( invalidBalls == validBalls.length - 1 ){
         endSim( -2, -2 );
      }
   }

   public static void velocityChecker(){
      for (int i = 0; i < balls.length - 1; i++){
         if ( validBalls[i] ){
            if ( Math.abs(balls[i].getXSpeed()) < 0.0833 || Math.abs(balls[i].getYSpeed()) < 0.0833 ){
               validBalls[i] = false;
               invalidBalls += 1;
               System.out.println("velocity checker");
            }
         }
      }
      if ( invalidBalls == validBalls.length - 1){
         endSim( -1, -1 );
      }
   }

   public static void reportFunction(){
      System.out.println( "Time Slice: " + timeValues[0] + ":" + timeValues[1] + ":" + timeValues[2] );
      for (int i = 0; i < balls.length; i++){
         if ( validBalls[i] || !validBalls[i] ){
            if ( i == validBalls.length - 1 ){
               System.out.println( "   Ball " + i + ": " + balls[i].toString() + " " + validBalls[i] );
            } else {
               System.out.println( "   Ball " + i + ": " + balls[i].toString() + " " + validBalls[i] );
            }
         }
      }
      System.out.println( "\n" );
   }

   public static void endSim( int a, int b ){
      //System.out.println( validBalls[0] + " " + validBalls[1] + " " + validBalls[2] );
      if ( a == -1 && b == -1 ) {
         System.out.println( "        -- NO COLLISION IS POSSIBLE --");
         System.out.println( "   -- ALL REMAINING VALID BALLS HAVE STOPPED --" );
         System.exit( 0 );
      } else if ( a == -2 && b == -2 ) {
         System.out.println( "             -- NO COLLISION IS POSSIBLE --" );
         System.out.println( "   -- ALL REMAINING VALID BALLS OUT OF FIELD BOUNDARIES --" );
         System.exit( 0 );
      } else {
         System.out.println( "   CONTACT ball at time " + timeValues[0] + " hours, " + timeValues[1] + " minutes, " +
                             timeValues[2] + " seconds. \nCOLLISION BETWEEN:" );
         if ( a == numBalls ){
            System.out.println( "   Pole at position<" + balls[a].getX() + "," + balls[a].getY() + ">" );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + b + " at " + balls[b].toString());
         } else if ( b == numBalls ) {
            System.out.println( "   Pole at position<" + balls[b].getX() + "," + balls[b].getY() + ">" );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + a + " at " + balls[a].toString());
         } else {
            System.out.println( "   Ball " + a + " at " + balls[a].toString() );
            System.out.println( "   ---- AND ----" );
            System.out.println( "   Ball " + b + " at " + balls[b].toString());
         }
         System.out.println("\n");
         System.exit( 0 );
      }
   }

   public static void main ( String args[] ){
      SoccerSim ss = new SoccerSim();
      handleInitialArgs( args );
      System.out.println( "\n    --STARTING SIMULATION--\n");
      collisionHandler();
      while (true){
         timeValues[0] = formatter.format( Math.round( timer.getTotalSeconds() / 3600 - .5) ); //value set to hours
         timeValues[1] = formatter.format( Math.round( (timer.getTotalSeconds() % 3600) / 60 - .5 ) ); //value set to minutes
         timeValues[2] = formatter.format( timer.getTotalSeconds() % 60 ); //value set to seconds
         reportFunction();
         for ( int i = 0; i < balls.length; i++ ){
            if ( validBalls[i] ){
               balls[i].ballRun( timer.getTimeSlice() );
            }
         }
         boundaryChecker();
         velocityChecker();
         collisionHandler();
         timer.tick();
      }
   }

 }
