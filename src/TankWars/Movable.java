package TankWars;

import java.awt.image.BufferedImage;

/**
 *
 * @author Alnguye
 */
public abstract class Movable extends GameObject {
    //data fields
    private int velocity;
    double d;
    
    //constructor
    public Movable(int x, int y, int velocity, BufferedImage image, int width, int length){
	super(x, y, velocity, image, width, length);
	this.x = x;
	this.y = y;
	this.image = image;
	this.velocity = velocity;
	this.width = width;
	this.length = length;
	
    }
    
    
    public void move(int direction) {
	int newXcoord = (int) (getVelocity() * Math.sin(getDirection()));
	int newYcoord = (int) (getVelocity() * Math.cos(getDirection()));
	this.setCoord(newXcoord + this.getX(), newYcoord + this.getY());
    } 
    
    public int getX(){
	return x;
    }
    
    public int getY(){
	return y;
    }
    
    public void newX(int changeX){
	this.x += changeX;
    }
    
    public void newY(int changeY){
	this.y += changeY;
    }
    
    public void setX(int x){
	this.x = x;
    }
    public void setY(int y){
	this.y = y;
    }
    
    public int getVelocity(){
	return velocity;
    }
    
   public void setVelocity(int v){
       this.velocity = v;
   }
   
   public void changeVelocity(int newV){
       this.velocity += newV;
   }
   
   public double getDirection(){
       return d;
   }
   
   public void setDirection(double d){
       this.d = d;
   }
   
 
   
   
   
    
}
