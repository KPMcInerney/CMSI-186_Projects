/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.lang.StringBuilder;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;

public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      if (count < 1 || sides < 4) {
         throw new IllegalArgumentException();
      } else {
         this.count = count;
         this.sides = sides;
         ds = new Die[count];
         for (int i = 0; i < count; i ++) {
            ds[i] = new Die(sides);
         }
      }
   }

   public int getCount() {
      return count;
   }

   public int getSides() {
      return sides;
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int num = 0;
      for (Die d : ds) {
         num += d.getValue();
      }
      return num;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
      for (Die d : ds) {
         d.roll();
      }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      ds[dieIndex - 1].roll();
      return ds[dieIndex - 1].getValue();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die value to return
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ds[dieIndex - 1].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      StringBuilder finalString = new StringBuilder();
      for (Die d : ds) {
         finalString.append("[" + d.getValue() + "]");
      }
      return finalString.toString();
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */

   public boolean isIdentical( DiceSet ds2 ) {
      if (count != ds2.count || sides != ds2.sides) {
         return false;
      }
      for (Die d : ds2.ds) {
         d.setVisited(false);
      }
      for (int i = 0; i < ds.length; i++) {
         for (int j = 0; j < ds2.ds.length; j++) {
            if (!ds2.ds[j].visited()) {
               if (ds[i].getValue() == ds2.ds[j].getValue()) {
                  ds2.ds[j].setVisited(true);
                  break;
               }
            }
         }
      }
      for (Die d : ds2.ds) {
         if ( !d.visited() ){
            return false;
         }
      }
      return true;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      DiceSet ds1 = null;
      DiceSet ds2 = null;
      /*try { ds1 = new DiceSet( 0, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      try { ds1 = new DiceSet( 1, 2 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      try { ds1 = new DiceSet( 1, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      System.out.println( " " );*/

      try { ds1 = new DiceSet( 2, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      try { ds2 = new DiceSet( 2, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      System.out.println( "DiceSet 1 has " + ds1.getCount() + " Die, each with " + ds1.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds1.toString() );
      System.out.println( "DiceSet 2 has " + ds2.getCount() + " Die, each with " + ds2.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds2.toString() );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      System.out.println( "The sum of DiceSet 1 is: " + ds1.sum() );
      ds1.roll();
      ds2.roll();
      System.out.println( "Rerolling both Sets" );
      System.out.println( " " );

      System.out.println( "DiceSet 1 has " + ds1.getCount() + " Die, each with " + ds1.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + toString(ds1) );
      System.out.println( "DiceSet 2 has " + ds2.getCount() + " Die, each with " + ds2.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + toString(ds2) );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      System.out.println( "Rerolling die at index 2 in DiceSet 1 to: " + ds1.rollIndividual(2) );
      System.out.println( "The Die in this set are: " + toString(ds1) );
      System.out.println( "The Die at index 1 of DiceSet 1 is: " + ds1.getIndividual(1) );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );

      System.out.println( "\f" );
      try { ds1 = new DiceSet( 2, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      try { ds2 = new DiceSet( 2, 4 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      System.out.println( "DiceSet 1 has " + ds1.getCount() + " Die, each with " + ds1.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds1.toString() );
      System.out.println( "DiceSet 2 has " + ds2.getCount() + " Die, each with " + ds2.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds2.toString() );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      ds1.roll();
      ds2.roll();
      System.out.println( "Rerolling both Sets" );
      System.out.println( " " );

      System.out.println( "DiceSet 1 has " + ds1.getCount() + " Die, each with " + ds1.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + toString(ds1) );
      System.out.println( "DiceSet 2 has " + ds2.getCount() + " Die, each with " + ds2.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + toString(ds2) );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      System.out.println( "Rerolling die at index 2 in DiceSet 1 to: " + ds1.rollIndividual(2) );
      System.out.println( "The Die in this set are: " + toString(ds1) );
      System.out.println( "The Die at index 1 of DiceSet 1 is: " + ds1.getIndividual(1) );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );

      // You do this part!
   }

}
