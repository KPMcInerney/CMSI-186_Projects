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

public class DiceSet {

  /**
   * private instance data
   */
   private static int count;
   private static int sides;
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
   /*public boolean isIdentical( DiceSet ds ) {
      if (this.ds == ds) {
         return true;
      } else {
         return false;
      }
   }*/

   public boolean isIdentical( DiceSet ds2 ) {
      System.out.println(count);
      System.out.println(ds2.getCount());
      System.out.println(sides);
      System.out.println(ds2.getSides());
      if (count != ds2.count || sides != ds2.sides) {
         return false;
      } else {
         return true;
      }
   }

   /*
   else {
      for (Die d : ds) {

      }
      for (int i = 0; i < ds.length; i++) {
         for (int j = 0; j < ds2.length; j++) {
            if (ds.getIndividual(i) != ds2.getIndividual(j)){
               return false;
            }
         }
      }
   }
   */

   /*public static String isNot = "[](){} qwertyuiopasdfghjklzxcvbnm";
   public boolean isIdentical( DiceSet ds2 ) {
      StringBuilder Builder = new StringBuilder();
      String dieValue = ds2.toString();
      System.out.println(dieValue);
      System.out.println(ds.toString());
      for (int k = 0; k < dieValue.length(); k++) {
         for (int j = 0; j < isNot.length(); j++){
            if ( dieValue.charAt(k) != isNot.charAt(j) ) {
               Builder.append(dieValue.charAt(k));
            }
         }
      }
      //System.out.println(Builder.toString());
      dieValue = Builder.toString();
      for (Die d : this.ds) {
         for (int i = 0; i < dieValue.length(); i++) {
            if ( d.getValue() != dieValue.charAt(i) ) {
               return false;
            }
         }
      }
      return true;
   }*/

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
      try { ds2 = new DiceSet( 3, 5 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      System.out.println( "DiceSet 1 has " + ds1.getCount() + " Die, each with " + ds1.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds1.toString() );
      System.out.println( "DiceSet 2 has " + ds2.getCount() + " Die, each with " + ds2.getSides() + " sides ");
      System.out.println( "The Die in this set are: " + ds2.toString() );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      //ds1.roll();
      //ds2.roll();
      //System.out.println( "Rerolling both Sets" );
      //System.out.println( " " );

      /*System.out.println( "DiceSet 1 has " + count + " Die, each with " + sides + " sides ");
      System.out.println( "The Die in this set are: " + ds1.toString() );
      System.out.println( "DiceSet 2 has " + count + " Die, each with " + sides + " sides ");
      System.out.println( "The Die in this set are: " + ds2.toString() );
      System.out.println( "The Set 1 is equal to Set 2: " + ds1.isIdentical( ds2 ) );
      ds1.roll();
      ds2.roll();
      System.out.println( "Rerolling both Sets" );
      System.out.println( " " );*/

      /*System.out.println( "The D
      /*System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      System.out.println( "The value of the Die at " + count/2 + " is: " + ds.getIndividual(count/2) );
      System.out.println( "Rerolling the Die at index " + count/2 + "\t It is now: " + ds.rollIndividual(count/2) );
      System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      ds.roll();
      System.out.println( " " );
      System.out.println( "Rerolling ");
      System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      System.out.println( " " );*/

      /*try { ds = new DiceSet( 13, 37 ); }
      catch( IllegalArgumentException iae ) { System.out.println("Too few die or die sides requested to constructor..."); }
      System.out.println( "This DiceSet has " + count + " Die each with " + sides + " sides ");
      System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      System.out.println( "The value of the Die at " + count/2 + " is: " + ds.getIndividual(count/2) );
      System.out.println( "Rerolling the Die at index " + count/2 + "\t It is now: " + ds.rollIndividual(count/2) );
      System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      ds.roll();
      System.out.println( " " );
      System.out.println( "Rerolling ");
      System.out.println( "The Die in this set are: " + ds.toString() );
      System.out.println( "The sum of the Die in this set is: " + ds.sum() );
      System.out.println( " " );*/

      // You do this part!
   }

}
