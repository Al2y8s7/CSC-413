package TankWars;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 *
 */
public class Tank extends Movable implements Observer {

    //data fields
    private int health;
    private Set<Integer> keys;
    private int ammo;
    private int lives;
    private int deltaX, deltaY;
    final int r = 10;
    private short angle;
    private KeyMapping keyMap;
    //for collision detection
    protected int xCollide, yCollide;
    boolean shotsFired, collided;

    //constructor
    public Tank(int x, int y, short angle, BufferedImage image, KeyMapping kmap, int width, int length) {
	super(x, y, image, width, length);
	keys = new HashSet();
	this.keyMap = kmap;
	this.angle = angle;
	this.shotsFired = false;
	collided = false;
    }

    @Override
    public void collide(GameObject gameObject) {
	collided = true;
	setXcoord(xCollide);
	setYcoord(yCollide);

    }

    @Override
    public void collide(Tank tank) {
	collided = true;
	setXcoord(xCollide);
	setYcoord(yCollide);
    }

    @Override
    public void collide(BreakableWall breakableWall) {
	collided = true;
	setXcoord(xCollide);
	setYcoord(yCollide);
    }

    @Override
    public void collide(NormalWall normalWall) {
	collided = true;
	setXcoord(xCollide);
	setYcoord(yCollide);
    }
//    
//    @Override
//    public void collide(Bullet bullet){
//        
//    }
//    
//    @Override
//    public void collide(BreakableWall breakableWall){
//        
//    }
//    
//    @Override
//    public void collide(PowerUp powerUp){
//        
//    }
//    
    @Override
    public Rectangle getHitBox(){
	return new Rectangle(this.x, this.y, 64, 64);
    }
    
    public void NonCollisionCoord(){
	xCollide = this.x;
	yCollide = this.y;
    }
    
    @Override
    public void update(Observable o, Object obj) {
	Controller controller = (Controller) o;
	keys = controller.getKeys();
	//MoveTanks();
	if (keys.contains(keyMap.getRightKey())) {
	    System.out.println("Right");
	    this.moveRight();
	    System.out.println("Right");
	}
	if (keys.contains(keyMap.getDownKey())) {
	    System.out.println("Down");
	    this.moveDown();
	    System.out.println("Down");
	}
	if (keys.contains(keyMap.getLeftKey())) {
	    System.out.println("Left");
	    this.moveLeft();
	    System.out.println("Left");

	}
	if (keys.contains(keyMap.getUpKey())) {
	    System.out.println("UP");
	    this.moveUp();
	    System.out.println("UP");
	}
	if (keys.contains(keyMap.getShootKey())) {

	}
	//collision detection
	if(!collided){
	    NonCollisionCoord();
	}else{
	    collided = false;
	    
	}
    }

    //getters for coordinates
    public int getXcoord() {
	return this.x;
    }

    public int getYCoord() {
	return this.y;
    }

    //setters for coordinates
    public void setXcoord(int x) {
	this.x = x;
    }

    public void setYcoord(int y) {
	this.y = y;
    }

    public void setLives(int lives) {
	this.lives = lives;
    }

    public void setHealth(int health) {
	this.health = health;
    }

    public int getAmmo(int ammo) {
	return this.ammo = ammo;
    }

    public void setAmmo(int ammo) {
	this.ammo = ammo;
    }

    public void shoot() {
	shotsFired = true;
    }

    public void moveUp() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }

    public void moveDown() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x -= deltaX;
	y -= deltaY;
    }

    public void moveLeft() {
	this.angle -= 10;
    }

    public void moveRight() {
	this.angle += 10;
    }

    private void MoveTanks() {
	if (keys.contains(keyMap.getUpKey())) {
	    this.moveUp();
	}
	if (keys.contains(keyMap.getRightKey())) {
	    this.moveRight();
	}
	if (keys.contains(keyMap.getDownKey())) {
	    this.moveDown();
	}
	if (keys.contains(keyMap.getLeftKey())) {
	    this.moveLeft();
	}
	if (keys.contains(keyMap.getShootKey())) {
	    this.moveLeft();
	}
    }

    public void draw(Graphics g) {
	// super.paintComponent(g);    
	AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);

    }

}
