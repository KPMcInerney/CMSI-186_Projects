import javax.swing.JFrame;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Insets;

public class Maze_Generator {

private static int windowWidth;
private static int windowHeight;
private static int width;
private static int cols, rows;
private static Cell[] grid = null;
private Cell current, next;
private Stack myStack = null;
private boolean allVisited;
private long startTime, endTime, duration, remainder;

   public static void main (String[] args){ //function should be called
      try {
         windowWidth = Integer.parseInt(args[0]);
         windowHeight = Integer.parseInt(args[1]);
         width = Integer.parseInt(args[2]);
         JFrame window = new JFrame();
         window.pack();
         Insets insets = window.getInsets();
         window.setSize(new Dimension(windowWidth + insets.left + insets.right, windowHeight+ + insets.top + insets.bottom));
         window.setTitle("Maze_Generator_2");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         Maze_Generator mg = new Maze_Generator();
         mg.setup();
         mg.runWhile();
         DrawingComponent DC = new DrawingComponent();
         window.add(DC);
         window.setVisible(true);
         window.setResizable(false);
      } catch (Exception e) {
         System.out.println("Invalid Inputs. Try usings integers in the order: WindowWidth WindowHeight CellWidth");
      }
   }

   public void setup() {
      cols = windowWidth / width;
      rows = windowHeight / width;
      grid = new Cell[cols*rows];
      myStack = new Stack(cols*rows);
      for (int j = 0; j < rows; j++) {
         for (int i = 0; i < cols; i++) {
            grid[i + j * cols] = new Cell(i, j);
         }
      }
      System.out.println("\f");
      System.out.println( "Maze size: " + windowWidth + " pixels x " + windowHeight + " pixels. ");
      System.out.println( "This gives it " + rows + " rows and " + cols + " columns, each cell having a width of " + width + " pixels.");
      System.out.println( "Total Cells in grid are: " + grid.length);
      current = grid[0];
   }

   public void draw(){
      current.setVisited(true);
      next = current.checkNeighbors();
      if (next != null){
         next.setVisited(true);
         myStack.push(current);
         removeWalls(current, next);
         current = next;
      } else if ( !myStack.isEmpty() ){
         current = myStack.pop();
      }
   }

   public void runWhile(){
      startTime = System.nanoTime();
      while ( checkRun() ) {
         draw();
      }
      endTime = System.nanoTime();
      duration = (endTime - startTime) / 1000000;
      if (duration < 1000) {
         System.out.println("Finished maze in ." + duration + " seconds" );
      } else {
         remainder = duration % 1000;
         duration = (duration - remainder) / 1000;
         System.out.println("finished maze in " + duration + "." + remainder + " seconds" );
         System.out.println("\f");
      }
   }

   public boolean checkRun(){
      allVisited = false;
      for ( Cell c : grid ){
         if ( !c.getVisited() ){
            allVisited = true;
         }
      }
      return allVisited;
   }

   public void removeWalls(Cell a, Cell b){
      int x = a.getX() - b.getX();
      if ( x == 1 ){
         a.setWall(3, false);
         b.setWall(1, false);
      } else if ( x == -1 ){
            a.setWall(1, false);
            b.setWall(3, false);
      }
      int y = a.getY() - b.getY();
      if ( y == 1 ){
         a.setWall(0, false);
         b.setWall(2, false);
      } else if ( y == -1 ){
         a.setWall(2, false);
         b.setWall(0, false);
      }

   }

   public static int getWidth(){
      return width;
   }

   public static int getRows(){
      return rows;
   }

   public static int getCols(){
      return cols;
   }

   public static Cell[] getGrid(){
      return grid;
   }

   public static Cell getSpecificCell(int index){
      return grid[index];
   }

}
