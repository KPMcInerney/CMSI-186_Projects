/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private double epsilon = .01;
   private double totalSeconds, timeSlice, angleGoal;
   private double hourHandAngle, minuteHandAngle, handAngleDifference;
  /**
   *  Constructor goes here
   */
   public Clock( String args[] ) {
      angleGoal = validateAngleArg( args[0] );
      totalSeconds = 0;
      if ( args.length == 1 ){
         timeSlice = validateTimeSliceArg( "" );
      } else if ( args.length == 2 ){
         timeSlice = validateTimeSliceArg( args[1] );
      } else if ( args.length == 3 ){
         timeSlice = validateTimeSliceArg( args[1] );
         epsilon = validateEpsilon( args[2] );
      } else {
         System.exit( 1 );
      }
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;
      return totalSeconds;
   }

   public double validateEpsilon( String argValue ) throws NumberFormatException {
      double given = Double.parseDouble(argValue);
      if ( given < 1 && given > 0 ){
         return Double.parseDouble(argValue);
      } else {
         return 0.01;
      }
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double given = Double.parseDouble(argValue);
      if ( given < MAXIMUM_DEGREE_VALUE && given >= 0 ){
         return Double.parseDouble(argValue);
      } else {
         throw new NumberFormatException();
      }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
      if ( argValue.equals(null) || argValue.equals("") ){
         timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
         return timeSlice;
      }
      double given = Double.parseDouble(argValue);
      if ( given > 0 ){
         return given;
      } else {
         throw new NumberFormatException();
      }
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      hourHandAngle = ( totalSeconds * HOUR_HAND_DEGREES_PER_SECOND ) % 360;
      return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      minuteHandAngle = ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) % 360;
      return minuteHandAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      getHourHandAngle();
      getMinuteHandAngle();
      handAngleDifference = Math.abs( minuteHandAngle - hourHandAngle );
      return handAngleDifference;
   }

   public double compareHandAngle() {
      getHourHandAngle();
      getMinuteHandAngle();
      double tempValue = Math.abs( minuteHandAngle - hourHandAngle );
      double tempValue2 = Math.abs( (360 - minuteHandAngle) + hourHandAngle );
      double tempValue3 = Math.abs( (360 - hourHandAngle) + minuteHandAngle );
      if ( angleGoal > (tempValue - (tempValue * epsilon)) && angleGoal < (tempValue + (tempValue * epsilon)) ) {
         return tempValue;
      } else if ( angleGoal > (tempValue2 - (tempValue2 * epsilon)) && angleGoal < (tempValue2 + (tempValue2 * epsilon)) ) {
         return tempValue2;
      } else if ( angleGoal > (tempValue3 - (tempValue3 * epsilon)) && angleGoal < (tempValue3 + (tempValue3 * epsilon)) ) {
         return tempValue3;
      } else {
         return 0.0;
      }
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      return "Angle arg: " + angleGoal + " TimeSlice arg: " + timeSlice;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {
      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock( args );
      System.out.println( "    New clock created: " + clock.toString() );
      clock.tick();
      System.out.println( "Total seconds are: " + clock.getTotalSeconds() );
      System.out.println( "Hour hand angle is: " + clock.getHourHandAngle() );
      System.out.println( "Minute hand angle is: " + clock.getMinuteHandAngle() );
      System.out.println( "Difference angle between hands is: " + clock.getHandAngle() );

      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.println( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.println( "      sending '  0 seconds', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.println( "      sending '  359 degrees', expecting double value   359.0" );
      try { System.out.println( (359.0 == clock.validateAngleArg( "359.0" )) ? " - got 359.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.println( "      sending '  1799 seconds', expecting double value   1799.0" );
      try { System.out.println( (1799.0 == clock.validateTimeSliceArg( "1799.0" )) ? " - got 1799.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.println( "      sending '  360 degrees', expecting double value   360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360.0" )) ? " - got 360.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.println( "      sending '  no seconds', expecting double value   60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "" )) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
