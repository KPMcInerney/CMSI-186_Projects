import java.util.List;
import java.util.ArrayList;

public class Stack { //Stack class
   private int length;
   private List<Cell> stack;

   public Stack( int SIZE ) { //stack constructor
      length = SIZE; //sets length equal to given size
      stack = new ArrayList<Cell>(SIZE); //creates stack arraylist
   }

   public int getLength(){ //method to return length of stack
      return length;
   }

   public Cell getCell( int i ){ //method to return the cell at a specific index
      return stack.get(i);
   }

   public void push( Cell i ) { //method to add a cell to the end of the stack
      stack.add(0,i);
   }

   public Cell pop(){ //method to return the last entered cell and remove it from the stack
      if ( !stack.isEmpty() ) { //checks if the stack is not empty
         Cell i = stack.get(0); //sets i equal to the last entered cell
         stack.remove(0); //removes the last entered cell from the stack
         return i; //returns the cell i
      } else { //otherwise if the stack is empty...
         return null; //return null which is an invalid value
      }
   }

   public Cell peek() { //method to check the last entered cell in the stack
      if ( !stack.isEmpty() ){ //checks if the stack is not empty
         return stack.get(0); //returns the last entered cell
      } else { //otherwise if the stack is empty...
         return null; //return null which is an invalid value
      }
   }

   public boolean isEmpty() { //method to return if the stack is empty
      return stack.isEmpty();
   }

}
