/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Find the "nth" Fibonacci number given an argument, using BrobInt class
 * @author    :  B.J. Johnson
 * Date       :  2017-04-17
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-17  B.J. Johnson  Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci {

   private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
   private static long    maxCount    = 0;
   private static int    working     = 15000;
   private static String end1        = "st";
   private static String end2        = "nd";
   private static String end3        = "rd";
   private static String endRest     = "th";
   private static String cardinality = "";
   private static BrobInt n1 = new BrobInt("0");
   private static BrobInt n2 = new BrobInt("1");
   private static BrobInt n3 = new BrobInt("1");
   private static long startTime, endTime, duration, remainder, seconds, minutes;

   private static final  int NO_CMD_LINE_ARGS = -1;
   private static final  int BAD_CMD_LINE_ARG = -2;

   public Fibonacci() {
      super();
   }

   public static void main( String[] args ) {
      System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( NO_CMD_LINE_ARGS );
      }
      try {
         maxCount = Long.parseLong( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( BAD_CMD_LINE_ARG );
      }
      if( 2 == args.length ) {
         try {
            working = Integer.parseInt( args[1] );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
         }
      }

     // this is just for making the output pretty... no real "fibonacci" functionality
      int lastIndex = args[0].length() - 1;
      switch( args[0].charAt( lastIndex ) ) {
         case '1': cardinality = end1;
                   break;
         case '2': cardinality = end2;
                   break;
         case '3': cardinality = end3;
                   break;
         default : cardinality = endRest;
                   break;
      }

     // NOTE: you may want to handle the first and second Fibonacc numbers as 'special cases'...

     // NOTE: you WILL need to initialize your BrobInts to keep track of things....

     // NOTE: this section is just a happy notification that lets the user know to be patient.......
      if( maxCount > working ) {
         System.out.println( "\n                This may take me a while; please be patient!!\n\n" );
      }

      startTime = System.nanoTime();
      if ( maxCount == 0 ){
         n3 = n1;
      } else if ( maxCount == 1 ){
         n3 = n2;
      } else {
         for ( int i = 2; i < maxCount + 1; i++ ){
            //System.out.println("current index is = " + i);
            n3 = n1.addByte(n2);
            n1 = n2;
            n2 = n3;
         }
      }
      endTime = System.nanoTime(); //sets endTime to the time after the program runs
      duration = (endTime - startTime) / 1000000; //sets duration to total program run time in miliseconds
      remainder = duration % 1000; //set remainder to miliseconds left after turning converting duration to seconds
      seconds = (duration - remainder) / 1000; //set seconds to the total run seconds without remainder
      minutes = (seconds - (seconds % 60)) / 60; //sets duration to the total run minutes without remainder

      System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " + n3 );

      if (seconds < 1) { //checks if duration is less than a second
         System.out.println("\fFound the " + maxCount + cardinality + " fibonacci number in ." + duration + " seconds" ); //prints time the  program took to run
      } else if ( seconds >= 1 && minutes < 1){ //if duration is greater than a second
         System.out.println("\fFound the " + maxCount + cardinality + " fibonacci number in " + seconds + "." + remainder + " seconds" ); //prints time the program took to run
      } else {
         System.out.println("\fFound the " + minutes + cardinality + " fibonacci number in " + minutes + " minutes and " + (seconds - (minutes * 60)) + "." + remainder + " seconds" ); //prints time the program took to run
      }

      //System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " + n3 );

      if ( n3.getInternalValue().length() > 4 ){
         System.out.println("\n     This number is " + (n3.getInternalValue().length() - 1) + " digits long\f" );
      }
      //System.out.println( "\n\n\n  ...HA!! Like I'm going to do the ENTIRE thing for you.....  *grins*" );


      System.exit( 0 );
   }
}
