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

 public class SoccerSim {
    private static int numBalls;
    private static int timeArg;
    private static Ball[] balls;

    public SoccerSim(){
      super();
   }

   public static void handleInitialArgs( String args[] ){
      if ( (args.length % 4) == 0 || ((args.length % 4) + 1) == 0 ) {
         try {
            Timer timer = new Timer( args );
            numBalls = args.length % 4;
            balls = new Ball[numBalls];
            for ( int i = 0; i < numBalls; i++){
               balls[i] = new Ball( args[0+(i*4)], args[1+(i*4)], args[2+(i*4)], args[3+(i*4)] );
            }
            if ( ((args.length % 4) + 1) == 0 ){
               timeArg = args.length - 1;
            }
         } catch( NumberFormatException nfe ){
            System.out.println( "   Sorry, your arguments are invalid." );
            System.out.println( "     Please use positive real numbers [e.g., '10' for 10 seconds]");
            System.out.println( "       [time slice out of range]" );
            System.out.println( "     Please try again.......\n" );
            System.exit( 1 );
         }
      } else {
         System.out.println( "   Sorry you must enter 4 arguments per ball and/or an optional time slice\n" +
                             "   Usage: java Ball (<ballx> <bally> <ballxSpeed> <ballySpeed>) x n [optional time slice]\n" +
                             "   Please try again...........\n" );
         System.exit( 1 );
      }
   }

   public int getTimeArg(){
      return timeArg;
   }

   public double distanceCalculator( double x1, double y1, double x2, double y2 ){
      return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) );
   }

   public boolean checkCollision(){

   }

   public static void main ( String args[] ){
      SoccerSim ss = new SoccerSim();
      handleInitialArgs( args );
      checkCollision();
      for ( int i = 0; i < balls.length; i++ ){
         ball[i].ballRun();
      }


      timer.tick();
   }

 }
