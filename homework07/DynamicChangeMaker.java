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

/**
 * This class represents a program to find optimal change for a given number.
 */
public class DynamicChangeMaker {

private static int[] privateDenominations = null;
private static Tuple denomTuple = null;
private static Tuple[][] data = null;

   /**
   * Constructs DynamicChangeMaker() with super().
   */
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
               if ( denominations[i] == denominations[k] || denominations[i] <= 0 || denominations[k] <= 0 || denominations.length < 1){
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
         }
      }
      //System.out.println("data[" + (denominations.length - 1) + "][" + target + "] = " + data[denominations.length - 1][target] );
      return data[denominations.length - 1][target];
   }

   /**
    *  main method just calls the change making program from the command line
    * @param args from the command line. Last arg is the value you are finding
    *             the optimal coin set for. The other args are the coins you want
    *             to find the optimal solution with.
    */
   public static void main ( String[] args ){
      if ( args.length >= 2 ){
         privateDenominations = new int[args.length - 1];
         for ( int i = 0; i < args.length - 1; i++ ){
            privateDenominations[i] = Integer.parseInt(args[i]);
         }
         System.out.println( makeChangeWithDynamicProgramming(privateDenominations, Integer.parseInt(args[args.length - 1]) ));
      } else {
         System.out.println( "\n   Sorry, the arguments you entered are invalid." );
         System.out.println( "   For the first argument enter an int array of arguments with a value >= 0 \n" );
         System.out.println( "   For the second argument enter the target value [non-negative int]");
      }
   }

}
