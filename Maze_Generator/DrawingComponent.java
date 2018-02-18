import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Line2D;

public class DrawingComponent extends JComponent{ //DrawingComponent class

   public void paintComponent (Graphics g){ //method to handle all of the drawing
      Graphics2D g2 = (Graphics2D) g; //initiates a 2D graphics variable from a regular graphics variable
      Color myColour; //initiates a color variable
      for (int i = 0; i < Maze_Generator.getGrid().length; i++) { //iterates from 0 to the length of grid
         if ( Maze_Generator.getSpecificCell(i).getVisited() ){ //if loop to color the visited cells
            myColour = new Color(255, 0, 255, 100); //creates the color (for a rect)(to slightly transparent pinkish)
            g.setColor(myColour); //sets the color for the graphics variable
            g2.fill( Maze_Generator.getSpecificCell(i).rect() ); //draws a rect filled with the created color
         }
         myColour = new Color(0, 0, 0); //creates the color (for lines) to black
         g.setColor(myColour); //sets the color for the graphics variable
         if ( Maze_Generator.getSpecificCell(i).getWall(0) ) { //if the top wall boolean is true...
            g2.draw( Maze_Generator.getSpecificCell(i).top() ); //draw the top wall (line)(in black)
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(1) ) { //if the right wall boolean is true...
            g2.draw( Maze_Generator.getSpecificCell(i).right() ); //draw the right wall (line)(in black)
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(2) ) { //if the bottom wall boolean is true...
            g2.draw( Maze_Generator.getSpecificCell(i).bottom() ); //draw the bottom wall (line)(in black)
         }
         if ( Maze_Generator.getSpecificCell(i).getWall(3) ) { //if the left wall boolean is true...
            g2.draw( Maze_Generator.getSpecificCell(i).left() ); //draw the left wall (line)(in black)
         }
      }
   }
}
