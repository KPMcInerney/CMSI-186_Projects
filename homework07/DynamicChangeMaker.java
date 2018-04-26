/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* File name  :  DynamicChangeMaker.java
* Purpose    :  Program to make change from any set of coins dynamically
* @author    :  Kevin McInerney
* @author    :  B.J. Johnson totally ripped off from the original
* Date       :  2017-04-19
* Description:  This program takes in any set of coins in an integer array and
*               finds the number of each coin that find a given solution with
*               the optimal (lowest) number of coins possible.
* Notes      :  None
* Warnings   :  None
*
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class DynamicChangeMaker {

private static int[] denominations2 = null;
private static Tuple denomTuple = null;
private static Tuple[][] data = null;

   public DynamicChangeMaker(){
      super();
   }

   /**
   * Returns a Tuple of the optimal (lowest) number of each of the
   * given coins for a given solution.
   *
   * @param denominations the integer array of the coin denominations you want to check
   * @param target the integer target you want the optimal number of coins for
   * @return a Tuple to represent the optimal number of each coin
   */
   public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int target ){
      try {
         if ( target <= 0 ){
            throw new IllegalArgumentException();
         }
         for ( int i = 0; i < denominations.length; i++ ){
            for ( int k = i + 1; k < denominations.length; k++ ){
               if ( denominations[i] == denominations[k] || denominations[i] <= 0 || denominations[k] <= 0 ){
                  throw new IllegalArgumentException();
               }
            }
         }
      } catch( IllegalArgumentException iae ) {
         System.out.println( "\n   Sorry, the arguments you entered are invalid." );
         System.out.println( "   For the first argument enter an int array of arguments with a value >= 0 \n" );
         System.out.println( "   For the second argument enter the target value [non-negative int]");
         return new Tuple(new int[0]);
      }
      data = new Tuple[denominations.length][target + 1];
      int runNumber = 0;
      for ( int row = 0; row < denominations.length; row++ ){
         for ( int column = 0; column < target + 1; column++ ){
            if ( column == 0 ){
               data[row][column] = new Tuple(denominations.length);
            } else {
               if ( column < denominations[row] ){
                  data[row][column] = new Tuple(new int[0]);
                  if ( column - denominations[row] >= 0 ){
                     if ( !(data[row][column - denominations[row]].isImpossible()) ){
                        data[row][column].add(data[row][column - denominations[row]]);
                     }
                  }
                  if ( row != 0 ){
                     if ( !(data[row - 1][column].isImpossible()) ){
                        if ( data[row][column].isImpossible() || data[row - 1][column].total() < data[row][column].total() ){
                           data[row][column] = data[row - 1][column];
                        }
                     }
                  }
               } else {
                  data[row][column] = new Tuple(denominations.length);
                  data[row][column].setElement(row, 1);
                  if ( column - denominations[row] >= 0 ){
                     if ( data[row][column - denominations[row]].isImpossible() ){
                        data[row][column] = new Tuple(new int[0]);
                     } else {
                        data[row][column] = data[row][column].add(data[row][column - denominations[row]]);
                     }
                  }
                  if ( row != 0 ){
                     if ( !(data[row - 1][column].isImpossible()) ){
                        if ( data[row][column].isImpossible() || data[row - 1][column].total() < data[row][column].total() ){
                           data[row][column] = data[row - 1][column];
                        }
                     }
                  }
               }
            }
            // System.out.println( runNumber + ":  data[" + row + "][" + column + "] = " + data[row][column] );
            //runNumber += 1;
         }
      }
      //System.out.println("data[" + (denominations.length - 1) + "][" + target + "] = " + data[denominations.length - 1][target] );
      return data[denominations.length - 1][target];
   }

   public static void main ( String[] args ){
      System.out.println( "Testing constructor" );
      int[] usaDenominations  = new int[] { 1, 10, 25, 5 };
      System.out.println( makeChangeWithDynamicProgramming( usaDenominations, 26 ) );
      // ChangeMaker change = new ChangeMaker( args );
      // System.out.println( "denominations = " + Arrays.toString(denominations) );
      // System.out.println( "  denomTuple = " + denomTuple.toString() );
      // System.out.println( "  data = " + Arrays.toString(data) );
   }

}
