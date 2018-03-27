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
    private double x, y, xSpeed, ySpeed;
    private double ballRadius = 4.45;
    private double time = 1;
    private static DecimalFormat formatter = new DecimalFormat("#000.0000"); //sets format for the strings

    public Ball( String x, String y, String xSpeed, String ySpeed) {
      this.x = Double.parseDouble(x);
      this.y = Double.parseDouble(y);
      this.xSpeed = Double.parseDouble(xSpeed);
      this.ySpeed = Double.parseDouble(ySpeed);
   }

   public void ballRun( double seconds ){
      time = seconds;
      xSpeed = applyFriction(xSpeed);
      ySpeed = applyFriction(ySpeed);
      update();
   }

   public double getBallRadius(){
      return ballRadius;
   }

   public double getX(){
      return x;
   }

   public double getY(){
      return y;
   }

   public double getXSpeed(){
      return xSpeed;
   }

   public double getYSpeed(){
      return ySpeed;
   }

   public String toString(){
      String resultX = formatter.format(x);
      String resultY = formatter.format(y);
      String resultXSpeed = formatter.format(xSpeed);
      String resultYSpeed = formatter.format(ySpeed);
      if ( xSpeed == 0 && ySpeed == 0 ){
         return ( "position <" + resultX + "," + resultY + ">" );
      } else {
         return ( "position <" + resultX + "," + resultY + "> with velocity <" + resultXSpeed + "," + resultYSpeed + ">" );
      }
   }

   public double applyFriction( double varSpeed ){
      return (varSpeed - (varSpeed * 0.01 * time));
   }

   public void update(){
      x += xSpeed * time;
      y += ySpeed * time;
   }

}
