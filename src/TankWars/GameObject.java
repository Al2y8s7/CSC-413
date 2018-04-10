/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alnguye
 * A game object is any object represented in-game
 * The following behaviors are to be inherited:
 */
public abstract class GameObject extends Observable implements Observer {
    
    //data fields
    protected int x, y, velocity, width, length;
    boolean isSolid, isVisible;
    BufferedImage image;
    
    
   //default constructor
    public GameObject(){
	
    }
    
    
    // custom constructor
    public GameObject(int x, int y, int velocity, BufferedImage image, int width, int length){
	this.x = x;
	this.y = y;
	this.velocity = velocity;
	this.image = image;
	this.isSolid = true;
	this.isVisible = true;
	this.width = width;
	this.length = length;
    } 
    
    public void draw(Graphics g){
	g.drawImage(image, x, y, null);
    }
   
    public void setCoord(int x, int y){
       this.x = x;
       this.y = y;
	     
   }
    
    public int getX(){
	return this.x;
    }
    
    public int getY(){
	return this.y;
    }
    
    public BufferedImage getImage(){
	return this.image;
    }
    
    public boolean checkVisibility(){
	return this.isVisible;
    }
    
    public boolean checkSolidity(){
	return this.isSolid;
    }
    
    
    //public abstract void collide(GameObject gameObject);
    
    //public abstract void collide(Tank tank);
    
    //public abstract void collide(Bullet bullet);
    
    //public abstract void collide(BreakableWall breakableWall);
    
    //public abstract void collide(PowerUp powerUp);

}
