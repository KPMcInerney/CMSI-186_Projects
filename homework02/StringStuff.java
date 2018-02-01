/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;
import java.lang.StringBuilder;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static String vowels = "aeiouy"; //list of vowels for comparison
   public static boolean containsVowel( String s ) {
      for (int i = 0; i < vowels.length(); i++) {  //iterates through vowels string chars
         for (int j = 0; j < s.length(); j++) {  //iterates through s string chars
            if (vowels.charAt(i) == s.toLowerCase().charAt(j)) {  //compares the char at vowels to the char at s
               return true;   //returns true if it finds a vowel
            }
         }
      }
      return false;  //returns false if no vowel is found
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
    public static boolean isPalindrome( String s ) {
      boolean result = true;  //creates true boolean
      for (int i = 0; i < s.length(); i++) { //loops through length of string
         if (s.charAt(i) != s.charAt((s.length() - 1) - i)) {  //checks if completmentary chars on each sides of string are equal
            result = false;   //sets boolean to false
         }
      }
      return result; //returns true/false boolean result
   }

   public static boolean palindromeResult;
   public static boolean isPalindromeRecursive( String s ) {
      palindromeResult = true;   //sets palindromeResult to true for each additional time palindrome is called
      isPalindromeCalculator(palindromeResult, s, 0, s.length() - 1);   //calls recursive function to change value of palindromeResult
      return palindromeResult;   //returns new value of palindromeResult
   }

   public static void isPalindromeCalculator(boolean result, String s, int x, int y) {
      boolean current = result;  //sets current to true
      if (x > y) {   //end case for recursive function
         palindromeResult = current;   //sets palindromeResult to final value of current
      } else {
         if (s.charAt(x) != s.charAt(y)) {   //checks if complementary chars on each side of the string are equal
            current = false;  //sets current to false if the chars are not equal
         }
         isPalindromeCalculator(current, s, x + 1, y - 1);  //function calls itself iterating through x and y by 1
      }
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String alphabet = "abcdefghijklmnopqrstuvwxyz";  //listed alphabet for comparison
   public static String evensOnly( String s ) {
      StringBuilder finalString = new StringBuilder();   //creating a StringBuilder
      for (int i = 0; i < s.length(); i++) {   //iterates through the length of s
         for (int j = 1; j < alphabet.length(); j += 2) {   //iterates through the even indexes of the alphabet
            if (s.toLowerCase().charAt(i) == alphabet.charAt(j)) {   //compares the chars at s to the even chars in alphabet
               finalString.append(s.charAt(i)); //if the char in s is an even index of the alphabet, add it to the StringBuilder
            }
         }
      }
      return finalString.toString();  //changes StringBuilder to an String and returns it
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      StringBuilder finalString = new StringBuilder();   //creating a StringBuilder
      for (int i = 0; i < s.length(); i++) { //iterates through the length of s
         for (int j = 0; j < alphabet.length(); j += 2) {   //iterates through the odd indexes of the alphabet
            if (s.toLowerCase().charAt(i) == alphabet.charAt(j)) {   //compares the chars at s to the odd chars in alphabet
               finalString.append(s.charAt(i)); //if the char in s is an odd index of the alphabet, add it to the StringBuilder
            }
         }
      }
      return finalString.toString();  //changes StringBuilder to an String and returns it
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      return removeDupes(evensOnly(s));
   }
   
  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      return removeDupes(oddsOnly(s));
   }

   public static String removeDupes( String s ) {
      StringBuilder builder = new StringBuilder();
      char[] chars = s.toCharArray();
      Set<Character> charSet = new LinkedHashSet<Character>();
      for (char c : chars) {
         charSet.add(c);
      }
      for (Character character : charSet) {
         builder.append(character);
      }
      return builder.toString();
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      StringBuilder builder = new StringBuilder();
      for (int i = 1; i < s.length() + 1; i++) {
         builder.append(s.charAt(s.length() - i));
      }
      return builder.toString();
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }
}
