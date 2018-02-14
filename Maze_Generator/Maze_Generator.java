import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Insets;

public class Maze_Generator {

private static int windowWidth = 800;
private static int windowHeight = 600;
private static int cols, rows;
private static int width = 20;
private static Cell[] grid = null;
private Cell current;
private Cell next;
private boolean allDone = true;

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
      //mg.draw();
      //mg.runWhile(allDone);
      DrawingComponent DC = new DrawingComponent();
      window.add(DC);
      window.setVisible(true);
      window.setResizable(false);
   }

   public void setup() {
      System.out.println("inside setup");
      cols = windowWidth / width;
      rows = windowHeight / width;
      grid = new Cell[cols*rows];
      for (int j = 0; j < rows; j++) {
         for (int i = 0; i < cols; i++) {
            grid[i + j * cols] = new Cell(i, j);
         }
      }
      System.out.println(grid.length);
      current = grid[0];
   }

   public void draw(){
      current.setVisited(true);
      //STEP 1
      next = current.checkNeighbors();
      if (next != null){
         next.setVisited(true);
         //STEP 2
         //removeWalls(current, next);
         //STEP 3

         //STEP 4
         current = next;
      }
   }

   public void runWhile(){
      for (Cell c : grid){
         if ( !c.getVisited() ){
            draw();
         }
      }
   }

   /*public void removeWalls(Cell a, Cell b){
      int x = a.getI() - b.getI();
      if ( x == 1 ){
         a.
      }
   }*/

   /*public void runWhile(boolean allDone){
      while (allDone) {
         for (Cell c : grid){
            if ( ){

            }
         }
      }
   }*/

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
