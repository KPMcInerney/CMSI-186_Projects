import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class Cell {

   private int i, j;
   private int w = Maze_Generator.getWidth();
   private Boolean[] walls = new Boolean[4];

   public Cell( int i, int j) {
      this.i = i;
      this.j = j;
      walls[0] = true;
      walls[1] = true;
      walls[2] = true;
      walls[3] = true;
   }

   public Rectangle draw() {
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

   public boolean getWall(int index){
      return this.walls[index];
   }

   public int getI(){
      return i;
   }

   public int getJ(){
      return j;
   }

   public int getWidth(){
      return w;
   }


}
