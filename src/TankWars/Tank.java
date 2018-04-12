package TankWars;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class Tank extends Movable implements Observer {
    //data fields
    private int health, ammo,velocity, x, y;
    
    
    //default constructor
    public Tank(int x, int y, int velocity, BufferedImage image, int health, int ammo, int width, int length){
	super(x, y, velocity, image, width, length);
	this.health = health;
	this.ammo = ammo;
	this.isSolid = true;
	this.isVisible = true;
	this.velocity = velocity;
	this.width = width;
	this.length = length;
    }
    
     
    //getters
    public int getHealth(int startHealth){
	return this.health = startHealth;
    }
    
    public int getAmmo(int startAmmo){
	return this.ammo = startAmmo;
    }
    
    public int getXcoord(int startX){
	return this.x = startX;
    }
    
    
    //methods for movement
    public void move(){
	
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
	
    }
    
    private void moveForward(){
	
    }

   

}
