import javax.swing.JFrame;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Random;

public class Maze_Generator { //Maze_Generator class
   private static int windowWidth;
   private static int windowHeight;
   private static int width;
   private static int cols, rows;
   private static Cell[] grid = null;
   private Cell current, next;
   private Stack myStack = null;
   private boolean allNotVisited;
   private long startTime, endTime, duration, remainder, seconds, minutes;
   private int numberOfSteps = 0;

   public static void main (String[] args){ //function should be called with WindowWidth, WindowHeight, CellWidth
      try {
         windowWidth = Integer.parseInt(args[0]); //sets windowWidth from given args
         windowHeight = Integer.parseInt(args[1]); //sets windowHeight from given args
         width = Integer.parseInt(args[2]); //sets cell width from given args
         JFrame window = new JFrame(); //creates the JFrame
         window.pack(); //sets the window around the given maze size
         Insets insets = window.getInsets(); //grabs insets (extra borders) of the window
         window.setSize(new Dimension(windowWidth + insets.left + insets.right, windowHeight+ + insets.top + insets.bottom)); //creates window size taking inset sizes into account
         window.setTitle("Maze_Generator"); //titles the window
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cancels program when drawing window is closed

         Maze_Generator mg = new Maze_Generator(); //creates instance of Maze_Generator class
         mg.setup(); //runs Maze_Generator setup() method
         mg.runWhile(); //runs Maze_Generator reunWhile() method
         DrawingComponent DC = new DrawingComponent(); //creates instance of DrawingComponent class
         window.add(DC); //adds the DrawingComponent instance to the drawing window
         window.setVisible(true); //sets the window visibility to true
         window.setResizable(false); //makes the window not resizable
      } catch (Exception e) { //catches exception if flaw with given args
         System.out.println("Invalid Inputs. Try usings integers in the order: WindowWidth WindowHeight CellWidth"); //explains proper input args format
      }
   }

   public void setup() { //setup() method
      cols = windowWidth / width; //sets column value
      rows = windowHeight / width; //sets row value
      grid = new Cell[cols*rows]; //sets length of grid
      myStack = new Stack(cols*rows); //sets length of stack
      for (int j = 0; j < rows; j++) { //iterates through rows
         for (int i = 0; i < cols; i++) { //iterates through columns of each row
            grid[i + j * cols] = new Cell(i, j); //creates a cell in the grid for each columns of each row
         }
      }
      System.out.println("\f"); //prints space in terminal
      System.out.println( "Maze size: " + windowWidth + " pixels x " + windowHeight + " pixels. "); //prints initial width and height given for maze
      System.out.println( "This gives it " + rows + " rows and " + cols + " columns, each cell having a width of " + width + " pixels."); //prints number of rows and columns in maze and width of each cell
      System.out.println( "Total Cells in maze are: " + grid.length); //prints total number of cells in the maze
      current = grid[ (new Random().nextInt(cols) * new Random().nextInt(rows)) ]; //sets current cell to random cell in the grid
   }

   public void draw(){ //method iterating through unvisited cells of the grid
      current.setVisited(true); //sets current cells visited boolean to true
      next = current.checkNeighbors(); //sets next cell to a random, valid, non-visited cell around the current cell
      if (next != null){ //if there is a valid, non-visited cell around the current cell
         next.setVisited(true); //sets the next cells visited boolean to true
         myStack.push(current); //adds the current cell to the stack
         removeWalls(current, next); //removes the walls between the current cell and the next cell
         current = next; //sets the current cell equal to the next cell
      } else if ( !myStack.isEmpty() ){ //if there is no valid, non-visited next cell and there are cells in the stack
         current = myStack.pop(); //set current to the last valid, non-visited cell added into the stack
      }
   }

   public void runWhile(){ //method looping through draw() method until maze is finished
      startTime = System.nanoTime(); //sets startTime to the time before program runs
      while ( checkRun() ) { //runs draw() method while there are unvisited cells in the grid
         draw();
         numberOfSteps += 1;
      }
      endTime = System.nanoTime(); //sets endTime to the time after the program runs
      duration = (endTime - startTime) / 1000000; //sets duration to total program run time in miliseconds
      remainder = duration % 1000; //set remainder to miliseconds left after turning converting duration to seconds
      seconds = (duration - remainder) / 1000; //set seconds to the total run seconds without remainder
      //remainder2 = seconds % 60;
      minutes = (seconds - (seconds % 60)) / 60; //sets duration to the total run minutes without remainder
      if (seconds < 1) { //checks if duration is less than a second
         System.out.println("Finished maze in ." + duration + " seconds" ); //prints time the  program took to run
         System.out.println("Total Steps were: " + numberOfSteps); //returns total steps the program runs
         System.out.println("\f"); //prints space into terminal
      } else if ( seconds >= 1 && minutes < 1){ //if duration is greater than a second
         System.out.println("Finished maze in " + seconds + "." + remainder + " seconds" ); //prints time the program took to run
         System.out.println("Total Steps were: " + numberOfSteps); //returns total steps the program runs
         System.out.println("\f"); //prints space into the terminal
      } else {
         System.out.println("Finished maze in " + minutes + " minutes and " + (seconds - (minutes * 60)) + "." + remainder + " seconds" ); //prints time the program took to run
         System.out.println("Total Steps were: " + numberOfSteps); //returns total steps the program runs
         System.out.println("\f"); //prints space into the terminal
      }
   }

   public boolean checkRun(){ //method checking for non-visited cells
      allNotVisited = false; //false by default
      for ( Cell c : grid ){ //loops through every cell in grid
         if ( !c.getVisited() ){ //checks if there are non-visited cells in grid
            allNotVisited = true; //set to true if there are non-visited cells in grid
         }
      }
      return allNotVisited; //returns true if there are non-visited cells in grid, returns false when all cells in grid are visited
   }

   public void removeWalls(Cell a, Cell b){ //method to remove necessary walls between two cells
      int x = a.getX() - b.getX(); //sets x to difference between x values of two cells
      if ( x == 1 ){ //if first cell is to the right of the second
         a.setWall(3, false); //remove the left wall of first cell
         b.setWall(1, false); //remove the right wall of the second cell
      } else if ( x == -1 ){ //if the first cell is to the left of the second
            a.setWall(1, false); //remove the right wall of first cell
            b.setWall(3, false); //remove the left wall of the second cell
      }
      int y = a.getY() - b.getY(); //sets y to difference between y values of two cells
      if ( y == 1 ){ //if first cell is below the second
         a.setWall(0, false); //remove the top wall of the first cell
         b.setWall(2, false); //remove the bottom wall of the second cell
      } else if ( y == -1 ){ //if the first cell is above the second
         a.setWall(2, false); //remove the bottom wall of the first cell
         b.setWall(0, false); //remove the top wall of the second cell
      }
   }

   public static int getWidth(){ //method to return the width of the cells
      return width;
   }

   public static int getRows(){ //method to return the number of rows in the grid
      return rows;
   }

   public static int getCols(){ //method to return the number of columns in the grid
      return cols;
   }

   public static Cell[] getGrid(){ //method to return the entire grid of cells
      return grid;
   }

   public static Cell getSpecificCell(int index){ //method to return a specific cell in the grid
      return grid[index];
   }

}
