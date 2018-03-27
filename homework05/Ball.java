/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
import java.awt.geom.Line2D;
import java.text.DecimalFormat;

 public class Ball {
    /**
     *  Class field definintions go here
     */
    private double x, y, xSpeed, ySpeed;
    private double ballRadius = 4.45;
    private double time = 1;
    private static DecimalFormat formatter = new DecimalFormat("#000.0000"); //sets format for the strings
    /**
     *  Constructor goes here
     */
    public Ball( String x, String y, String xSpeed, String ySpeed) {
      this.x = Double.parseDouble(x);
      this.y = Double.parseDouble(y);
      this.xSpeed = Double.parseDouble(xSpeed);
      this.ySpeed = Double.parseDouble(ySpeed);
   }

   public void ballRun( double seconds ){ //method to change ball values every tick
      time = seconds; //sets time variable to given timeSlice
      xSpeed = applyFriction(xSpeed); //calculates new xSpeed
      ySpeed = applyFriction(ySpeed); //calculates new YSpeed
      update();
   }

   public double getBallRadius(){ //method to return balls radius
      return ballRadius;
   }

   public double getX(){ //method to return ballX
      return x;
   }

   public double getY(){ //method to return ballY
      return y;
   }

   public double getXSpeed(){ //method to return ball XSpeed
      return xSpeed;
   }

   public double getYSpeed(){ //method to return ball YSpeed
      return ySpeed;
   }

   public String toString(){ //method to return ball String form
      String resultX = formatter.format(x);
      String resultY = formatter.format(y);
      String resultXSpeed = formatter.format(xSpeed);
      String resultYSpeed = formatter.format(ySpeed);
      return ( "position <" + resultX + "," + resultY + "> velocity <" + resultXSpeed + "," + resultYSpeed + ">" );
   }

   public double applyFriction( double varSpeed ){ //method to apply friction to ball speed values
      return (varSpeed - (varSpeed * 0.01 * time));
   }

   public void update(){ //method to update ballX and ballY positions
      x += xSpeed * time;
      y += ySpeed * time;
   }

}
