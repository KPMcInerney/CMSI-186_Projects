import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Line2D;

public class DrawingComponent extends JComponent{


   public void paintComponent (Graphics g){
      Graphics2D g2 = (Graphics2D) g;
      //Rectangle rect1 = new Rectangle(5, 5, 100, 200);
      //g2.draw(rect1);

      for (int i = 0; i < Maze_Generator.getGrid().length; i++) {
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

/*
for (int i = 0; i < Maze_Generator_2.getGrid().length; i++) {
   g2.draw( Maze_Generator_2.getSpecificCell(i).top() );
   g2.draw( Maze_Generator_2.getSpecificCell(i).right() );
   g2.draw( Maze_Generator_2.getSpecificCell(i).bottom() );
   g2.draw( Maze_Generator_2.getSpecificCell(i).left() );
}*/

/*for (int i = 0; i < Maze_Generator_2.getCells().length; i++) {
   int x = Maze_Generator_2.getSpecificCell(i).getI();
   int y = Maze_Generator_2.getSpecificCell(i).getJ();
   int w = Maze_Generator_2.getSpecificCell(i).getWidth();
   int h = Maze_Generator_2.getSpecificCell(i).getWidth();
   g2.drawRect( x, y, w, h );
}*/
