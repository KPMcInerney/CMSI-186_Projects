import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Random;

public class Cell {

   public int i, j, x, y, top, right, bottom, left, neighborLength, resultIterable;
   private int w = Maze_Generator.getWidth();
   private Boolean[] walls = new Boolean[4];
   private boolean visited;
   private Cell[] neighbors = null;
   private Cell[] result = null;

   public Cell( int i, int j) {
      this.i = i * w;
      this.j = j * w;
      this.x = i;
      this.y = j;
      visited = false;
      walls[0] = true;
      walls[1] = true;
      walls[2] = true;
      walls[3] = true;
   }

   public Cell checkNeighbors(){
      neighbors = new Cell[4];
      top = index(x, y - 1);
      right = index(x + 1, y);
      bottom = index(x, y + 1);
      left = index(x - 1, y);
      neighborLength = 0;
      resultIterable = 0;
      if ( top > -1 && !Maze_Generator.getSpecificCell(top).getVisited() ) {
         neighbors[0] = Maze_Generator.getSpecificCell(top);
         neighborLength += 1;
      }
      if ( right > -1 && !Maze_Generator.getSpecificCell(right).getVisited() ) {
         neighbors[1] = Maze_Generator.getSpecificCell(right);
         neighborLength += 1;
      }
      if ( bottom > -1 && !Maze_Generator.getSpecificCell(bottom).getVisited() ) {
         neighbors[2] = Maze_Generator.getSpecificCell(bottom);
         neighborLength += 1;
      }
      if ( left > -1 && !Maze_Generator.getSpecificCell(left).getVisited() ) {
         neighbors[3] = Maze_Generator.getSpecificCell(left);
         neighborLength += 1;
      }
      result = new Cell[neighborLength];
      for ( int i = 0; i < neighbors.length; i++ ){
         if ( neighbors[i] != null ){
            result[resultIterable] = neighbors[i];
            resultIterable += 1;
         }
      }
      if ( result.length > 0){
         int rand = new Random().nextInt(result.length);
         return result[rand];
      } else {
         return null;
      }
   }

   public int index(int x, int y){
      if (x < 0 || y < 0 || x > Maze_Generator.getCols() - 1 || y > Maze_Generator.getRows() - 1 ){
         return -1;
      }
      return ( x + y * Maze_Generator.getCols() );
   }

   public Rectangle rect() {
      Rectangle rect1 = new Rectangle(i, j, w, w);
      return rect1;
   }

   public Line2D top() {
      Line2D.Double top = new Line2D.Double(i, j, i + w, j);
      return top;
   }

   public Line2D right() {
      Line2D.Double right = new Line2D.Double(i + w, j, i + w, j + w);
      return right;
   }

   public Line2D bottom() {
      Line2D.Double bottom = new Line2D.Double(i + w, j + w, i, j + w);
      return bottom;
   }

   public Line2D left() {
      Line2D.Double left = new Line2D.Double(i, j + w, i, j);
      return left;
   }

   public boolean getWall( int index ){
      return this.walls[index];
   }

   public void setWall( int index, boolean value){
      walls[index] = value;
   }

   public void setVisited( boolean value ){
      visited = value;
   }

   public boolean getVisited(){
      return visited;
   }

   public int getX(){
      return x;
   }

   public int getY(){
      return y;
   }

   public int getWidth(){
      return w;
   }

}
