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

   public static void main (String[] args){
      JFrame window = new JFrame();
      window.pack();
      Insets insets = window.getInsets();
      window.setSize(new Dimension(windowWidth + insets.left + insets.right, windowHeight+ + insets.top + insets.bottom));
      window.setTitle("Maze_Generator_2");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Maze_Generator mg = new Maze_Generator();
      mg.setup();
      DrawingComponent DC = new DrawingComponent();
      window.add(DC);
      window.setVisible(true);
      window.setResizable(false);
      //System.out.println( window.getSize() );
      //System.out.println( window.getInsets() );
   }

   public void setup() {
      cols = windowWidth / width;
      rows = windowHeight / width;
      grid = new Cell[cols*rows];
      for (int j = 0; j < rows; j++) {
         for (int i = 0; i < cols; i++) {
            grid[i + j * cols] = new Cell(i * width ,j * width);
         }
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
