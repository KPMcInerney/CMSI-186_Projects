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

 public class Ball {
    private double x, y, xSpeed, ySpeed;
    private double oldX, oldY;
    private double ballRadius = 4.45;
    private Line2D.Double line1;


    public Ball( String x, String y, String xSpeed, String ySpeed ) {
      this.x = Double.parseDouble(x);
      this.y = Double.parseDouble(y);
      this.xSpeed = Double.parseDouble(xSpeed);
      this.ySpeed = Double.parseDouble(ySpeed);
   }

   public void ballRun(){
      update();
      handleLine();
      xSpeed = ballFriction(xSpeed);
      ySpeed = ballFriction(ySpeed);
   }

   public double getX(){
      return x;
   }

   public double getY(){
      return y;
   }

   public double ballFriction( double varSpeed){
      return varSpeed - (varSpeed * 0.01);
   }

   public void update(){
      oldX = x;
      oldY = y;
      x += xSpeed;
      y += ySpeed;
   }

   public void handleLine(){
      Line2D.Double line1 = new Line2D.Double( x, y, oldX, oldY );
   }

   public Line2D getLine(){
      return line1;
   }

}
