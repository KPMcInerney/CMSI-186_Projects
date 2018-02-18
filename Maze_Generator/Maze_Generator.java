import javax.swing.JFrame;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Insets;

public class Maze_Generator {

private static int windowWidth = 800;
private static int windowHeight = 600;
private static int width = 20;
private static int cols, rows;
private static Cell[] grid = null;
private Cell current, next;
private Stack myStack = null;
private boolean allVisited;

   public static void main (String[] args){
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
      System.out.println( "Maze size: " + windowWidth + " pixels x " + windowHeight + " pixels. " + "Cell width is: " + width);
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
      while ( checkRun() == false ) {
         draw();
      }
      System.out.println("finished maze");
   }

   public boolean checkRun(){
      allVisited = true;
      for ( Cell c : grid ){
         if ( !c.getVisited() ){
            allVisited = false;
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
