import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Random;

public class Cell { //cell class

   public int i, j, x, y, top, right, bottom, left, neighborLength, resultIterable;
   private int w = Maze_Generator.getWidth();
   private Boolean[] walls = new Boolean[4];
   private boolean visited;
   private Cell[] neighbors = null;
   private Cell[] result = null;

   public Cell( int i, int j) { //cell constructor
      this.i = i * w; //sets i to the x value of cell * the cell width
      this.j = j * w; //sets j to the y value of the cell * the cell width
      this.x = i; //sets x to the x value of the cell
      this.y = j; //sets y to the y value of the cell
      visited = false; //sets the cells visited boolean to false
      walls[0] = true; //sets the cells top wall boolean to true
      walls[1] = true; //sets the cells right wall boolean to true
      walls[2] = true; //sets the cells bottom wall boolean to true
      walls[3] = true; //sets the cells left wall boolean to true
   }

   public Cell checkNeighbors(){ // method to return a random, valid, non-visited cell around this cell
      neighbors = new Cell[4]; //set length of neighbor array to 4
      top = index(x, y - 1); //sets top int to the grid index of the cell above this cell
      right = index(x + 1, y); //sets right int to the grid index of the cell to the right of this cell
      bottom = index(x, y + 1); //sets bottom int to the grid index of the cell below this cell
      left = index(x - 1, y); //sets left int to the grid index of the cell to the left of this cell
      neighborLength = 0; //sets neighborLength to 0
      resultIterable = 0; //sets resultIterable to 0
      if ( top > -1 && !Maze_Generator.getSpecificCell(top).getVisited() ) { //if top int is valid and the cell above this cell isnt visited...
         neighbors[0] = Maze_Generator.getSpecificCell(top); //add the cell above this cell to the neighbor array
         neighborLength += 1; //add one to the neighborLength int
      }
      if ( right > -1 && !Maze_Generator.getSpecificCell(right).getVisited() ) { //if right int is valid and the cell to the right of this cell isnt visited...
         neighbors[1] = Maze_Generator.getSpecificCell(right); //add the cell to the right of this cell to the neighbor array
         neighborLength += 1; //add one to the neighborLength int
      }
      if ( bottom > -1 && !Maze_Generator.getSpecificCell(bottom).getVisited() ) { //if bottom int is valid and the cell below this cell isnt visited...
         neighbors[2] = Maze_Generator.getSpecificCell(bottom); //add the cell below this cell to the neighbor array
         neighborLength += 1; //add one to the neighborLength int
      }
      if ( left > -1 && !Maze_Generator.getSpecificCell(left).getVisited() ) { //if left int is valid and the cell to the left of this cell isnt visited...
         neighbors[3] = Maze_Generator.getSpecificCell(left); //add the cell to the left of this cell to the neighbor array
         neighborLength += 1; //add one to the neighborLength int
      }
      result = new Cell[neighborLength]; //makes result array equal to the number of valid, non-visited neighbor cells around this cell
      for ( int i = 0; i < neighbors.length; i++ ){ //for 0 to the length of the neighbors array
         if ( neighbors[i] != null ){ //if the cell at the index exists...
            result[resultIterable] = neighbors[i]; //add the cell at the index of neighbors array to the result array
            resultIterable += 1; //increase the index counting variable for the result array
         }
      }
      if ( result.length > 0){ //if the result array has cells in it...
         int rand = new Random().nextInt(result.length); //set rand to a random value within the range of the valid, non-visited neighbors to this cell
         return result[rand]; //return the valid, non-visited neighbor of this cell at the given random value
      } else { //otherwise if the result array of valid, non-visited neighbors to this cell is empty...
         return null; //return null which is an invalid value
      }
   }

   public int index(int x, int y){ //method to return the grid index of the cell with the given coordinates
      if (x < 0 || y < 0 || x > Maze_Generator.getCols() - 1 || y > Maze_Generator.getRows() - 1 ){ //if the given coodinates arent valid...
         return -1; //return an invalid value
      } //otherwise if the given coordinates are valid...
      return ( x + y * Maze_Generator.getCols() ); //return the grid index of the cell with the given coordinates
   }

   public Rectangle rect() { //method to return a rect with the given coordinates and width
      Rectangle rect1 = new Rectangle(i, j, w, w); //creation of the rect with the given coordinates and width
      return rect1; //returning that rect
   }

   public Line2D top() { //method to return the top wall (line) of this cell
      Line2D.Double top = new Line2D.Double(i, j, i + w, j); //creates the top wall (line)
      return top; //returns this line
   }

   public Line2D right() { //method to return the right wall (line) of this cell
      Line2D.Double right = new Line2D.Double(i + w, j, i + w, j + w); //creates the right wall (line)
      return right; //returns this line
   }

   public Line2D bottom() { //method to return the bottom wall (line) of this cell
      Line2D.Double bottom = new Line2D.Double(i + w, j + w, i, j + w); //creates the bottom wall (line)
      return bottom; //returns this line
   }

   public Line2D left() { //method to return the left wall (line of this cell
      Line2D.Double left = new Line2D.Double(i, j + w, i, j); //creates the left wall (line)
      return left; //returns this line
   }

   public boolean getWall( int index ){ //method to get the wall of this cell at the given index
      return this.walls[index];
   }

   public void setWall( int index, boolean value){ //method to set the boolean value of a wall for this cell
      walls[index] = value;
   }

   public void setVisited( boolean value ){ //method to set the visited value of this cell
      visited = value;
   }

   public boolean getVisited(){ //method to get the visited value of this cell
      return visited;
   }

   public int getX(){ //method to get the x value of this cell
      return x;
   }

   public int getY(){ //method to get the y value of this cell
      return y;
   }

}
