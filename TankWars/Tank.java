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
    private int nonCollideX,nonCollideY;
    final int r = 5;
    public short angle;
    private KeyMapping keyMap;
    //for collision detection
    protected int xCollide, yCollide;
    private boolean shotsFired, collided;

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
	

    }

    @Override
    public void collide(Tank tank) {
	collided = true;
	
    }

    @Override
    public void collide(BreakableWall breakableWall) {
	collided = true;
	
    }

    @Override
    public void collide(NormalWall normalWall) {
	collided = true;
	
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
    public void update(Observable o, Object obj) {
	Controller controller = (Controller) o;
	keys = controller.getKeys();
        NonCollisionCoord();
	MoveTanks();
    }

    //getters for coordinate

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
	this.shotsFired = true;
    }
    public void setShoot(boolean shoot){
        this.shotsFired  = shoot;
    }
    public boolean getShoot(){
        return this.shotsFired;
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
	this.angle -= 5;
    }

    public void moveRight() {
	this.angle += 5;
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
	    this.shoot();
	}
    }

    public void draw(Graphics g) {
	// super.paintComponent(g);    
	AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);
        graphic2D.draw(this.getHitBox());
    }
    public void NonCollisionCoord(){
	this.nonCollideX = x;
	this.nonCollideY = y;
    }
    public int getXnonCollision(){
        return this.nonCollideX;
    }
    public int getYnonCollision(){
        return this.nonCollideY;
    }
    public short getAngle(){
        return this.angle;
    }
    
}
