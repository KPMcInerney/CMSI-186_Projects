public class CountTheDays {
   public static void main(String [] args) {
      try {
         long[] givenDates = new long[6]; //creates an array of date parts
         for (int i = 0; i < 6; i++) {
            givenDates[i] = Long.parseLong(args[i]); //loops through args and assigns to array of date parts
         }
         System.out.println(CalendarStuff.daysBetween(givenDates[0], givenDates[1], givenDates[2], givenDates[3], givenDates[4], givenDates[5])); //calls daysBetween function and prints the results for the givenDates
      } catch (Exception e){
         System.out.println("Exception occurred"); //return error statement for any given exception
         e.printStackTrace();
      }
   }
}
