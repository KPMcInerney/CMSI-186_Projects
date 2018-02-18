import java.util.List;
import java.util.ArrayList;

public class Stack {
   private int length;

    private List<Cell> stack;

    public Stack( int SIZE ) {
      length = SIZE;
      stack = new ArrayList<Cell>(SIZE);
    }

    public int getLength(){
      return length;
    }

    public Cell getCell( int i ){
      return stack.get(i);
   }

    public void push( Cell i ) {
       stack.add(0,i);
    }

     public Cell pop(){
        if(!stack.isEmpty()) {
           Cell i= stack.get(0);
           stack.remove(0);
           return i;
        } else{
           return null;// Or any invalid value
        }
     }

     public Cell peek() {
        if(!stack.isEmpty()){
           return stack.get(0);
        } else{
           return null;// Or any invalid value
        }
     }

     public boolean isEmpty() {
       return stack.isEmpty();
     }

 }
