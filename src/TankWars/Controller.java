package TankWars;


import java.awt.event.KeyAdapter;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * 
 * @author Alnguye
 * This class maps the movement of game objects
 */
public class Controller extends Observable implements KeyListener {
    
    //directions for movements
    int left, right, up, down, shoot;
    boolean mvLeft, mvRight, mvUp, mvDown;
    private final Set<Integer> keyInput;
    
    //constructor
    public Controller(int left, int right, int up, int down, int shoot){
	keyInput = new HashSet<>();
	this.left = left;
	this.right = right;
	this.up = up;
	this.down = down;
	this.shoot = shoot;
    }
    
    
    @Override
    public void keyPressed(KeyEvent k){
	int key = k.getKeyCode();
	this.keyInput.add(key);
	setChanged();
	notifyObservers();
    }
    
    @Override 
    public void keyReleased(KeyEvent k){
	int key = k.getKeyCode();
	this.keyInput.remove(key);
	setChanged();
	notifyObservers();
    	
    }
    
     @Override
    public void keyTyped(KeyEvent e) {
	
    }
    
    
    
    
    //map direction keys for movement
    public void movement(KeyEvent key){
	//when keys are pressed
	if(key.getID() == KeyEvent.KEY_PRESSED){
	    if(key.getID() == getLeft() && !mvLeft){
		mvLeft = true;
	    }
	    if(key.getID() == getRight() && !mvRight){
		mvRight = true;
	    }
	    if(key.getID() == getUp() && !mvUp){
		mvUp = true;
	    }
	    if(key.getID() == getDown() && !mvDown){
		mvDown = true;
	    }
	}
	//when keys are released
	if(key.getID() == KeyEvent.KEY_RELEASED){
	    if(key.getID() == getLeft() && mvLeft){
		mvLeft = false;
	    }
	    if(key.getID() == getRight() && mvRight){
		mvRight = false;
	    }
	    if(key.getID() == getUp() && mvUp){
		mvUp = false;
	    }
	    if(key.getID() == getDown() && mvDown){
		mvDown = false;
	    }
	}
    }
    
    
    //getters   
    public boolean getMvLeft(){
	return mvLeft;
    }
    
    public boolean getMvRight(){
	return mvRight;
    }
    
    public boolean getMvUp(){
	return mvUp;
    }
    
    public boolean getMvDown(){
	return mvDown;
    }
    
    public int getLeft(){
	return left;
    }
    
    public int getRight(){
	return right;
    }
    
    public int getUp(){
	return up;
    }
    
    public int getDown(){
	return down;
    }

 
    
}
