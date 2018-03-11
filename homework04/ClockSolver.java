/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1; //small value for double-precision comparisons
   private String newArgs;
   private static double[] clockValues;
   private static int counter = 0;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     // your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"
      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if ( args.length < 1 || args.length > 3) {
         System.out.println( "   Sorry you must enter either one, two, or three arguments\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice] {epsilon (error boundaries)}\n" +
                             "   Please try again...........\n" );
         System.exit( 1 );
      } else {
         try {
            Clock clock = new Clock( args );
            if ( args.length < 2 ){
               clockValues = new double[2];
               clockValues[1] = 60.0;
            } else {
               clockValues = new double[args.length];
            }
            for ( int i = 0; i < args.length; i++ ){
               clockValues[i] = Double.parseDouble(args[i]);
            }
         } catch( NumberFormatException nfe ){
            if ( args.length > 1 && Integer.parseInt(args[1]) <= 0 ){
               System.out.println( "   Sorry, your arguments are invalid." );
               System.out.println( "     " + nfe );
               System.out.println( "       [degree value out of range]" );
               System.out.println( "     Please try again.......\n" );
               System.exit( 1 );
            } else {
               System.out.println( "   Sorry, your arguments are invalid." );
               System.out.println( "     Please use real numbers [e.g., '90.0' for 90 degrees]");
               System.out.println( "       [time slice out of range]" );
               System.out.println( "     Please try again.......\n" );
               System.exit( 1 );
            }
         }
      }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      Clock clock = new Clock( args );
      String[] timeValues = new String[3];
      DecimalFormat formatter = new DecimalFormat("#00.0");
      System.out.println( "  Your simulation is running,\n" + "    looking for angles of " + clockValues[0] + " degrees\n" + "    with a time slice of " + clockValues[1] + " seconds\n\n" );
      while( clock.getTotalSeconds() < 43200 ) {
         timeValues[0] = formatter.format( Math.round( clock.getTotalSeconds() / 3600 - .5) );
         timeValues[1] = formatter.format( Math.round( (clock.getTotalSeconds() % 3600) / 60 - .5 ) );
         timeValues[2] = formatter.format( clock.getTotalSeconds() % 60 );
         if ( clock.compareHandAngle() != 0) {
            System.out.println( "Found target angle of " + clockValues[0] + " at time:   " + timeValues[0] +
                                " hours, " + timeValues[1] + " minutes, " + timeValues[2] + " seconds" );
            counter += 1;
         }
         clock.tick();
      }
      System.out.println( "\nThere were " + counter + " times found for the target angle " + clockValues[0] + "\n" );
      System.exit( 0 );
   }

}
