import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Line2D;

public class DrawingComponent extends JComponent{

   public void paintComponent (Graphics g){
      Graphics2D g2 = (Graphics2D) g;
      Color myColour;
      for (int i = 0; i < Maze_Generator.getGrid().length; i++) {
         if ( Maze_Generator.getSpecificCell(i).getVisited() ){
            myColour = new Color(255, 0, 255, 100);
            g.setColor(myColour);
            g2.fill( Maze_Generator.getSpecificCell(i).rect() );
         }
         myColour = new Color(0, 0, 0);
         g.setColor(myColour);
         if ( Maze_Generator.getSpecificCell(i).getWall(0) ) {
            g2.draw( Maze_Generator.getSpecificCell(i).top() );
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(1) ) {
            g2.draw( Maze_Generator.getSpecificCell(i).right() );
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(2) ) {
            g2.draw( Maze_Generator.getSpecificCell(i).bottom() );
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(3) ) {
            g2.draw( Maze_Generator.getSpecificCell(i).left() );
         }
      }
   }
}
