package TankWars;

import static TankWars.Main.WINDOW_WIDTH;
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
    private int spawnX,spawnY;
    final int r = 15;
    public short angle;
    private KeyMapping keyMap;
    //for collision detection
    protected int xCollide, yCollide;
    private boolean shotsFired, collided;
    private int player;
    long lastShoot = System.currentTimeMillis();
    final long threshold = 1000;

    //constructor
    public Tank(int x, int y, short angle,int player, BufferedImage image, KeyMapping kmap, int width, int length) {
	super(x, y, image, width, length);
	keys = new HashSet();
	this.keyMap = kmap;
	this.angle = angle;
	this.shotsFired = false;
	collided = false;
        this.health = 100;
        this.spawnX = x;
        this.spawnY = y;
        this.lives = 3;
        this.player = player;
    }
    
    @Override
    public void collide(GameObject gameObject) {
    }

    @Override
    public void collide(Tank tank) {
        this.x = nonCollideX;
        this.y = nonCollideY;
    }

    @Override
    public void collide(BreakableWall breakableWall) {
	collided = true;
	
    }

    @Override
    public void collide(NormalWall normalWall) {
	this.x = nonCollideX;
        this.y = nonCollideY;
    }
    
    @Override
    public void collide(Bullet bullet){    
        this.health -= 10;
            if(health <= 0){
                --lives;
                respawn();
            }
            bullet.setVisibility(false);
            System.out.println("Tank's Health: "+ health);
    }
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

    public synchronized void moveUp() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }

    public synchronized void moveDown() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x -= deltaX;
	y -= deltaY;
    }

    public synchronized void moveLeft() {
	this.angle -= 10;
    }

    public synchronized void moveRight() {
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
            long current = System.currentTimeMillis();
            if((current - threshold) > lastShoot){
	    this.shoot();
            lastShoot = current;
            }
	}
    }

    public void draw(Graphics g) {
	// super.paintComponent(g);    
	AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);
        //graphic2D.draw(this.getHitBox());
    }
    public void NonCollisionCoord(){
	this.nonCollideX = x;
	this.nonCollideY = y;
    }
    public short getAngle(){
        return this.angle;
    }
    public void respawn(){
        this.health = 100;
        this.x = spawnX;
        this.y = spawnY;
    }
    public int getLives(){
        return this.lives;
    }
    public int getHealth(){
        return this.health;
    }
    public int getPlayer(){
        return this.player;
    }

    @Override
    public void collide(PowerUp powerUp) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getMiddleX(){
	return this.getX() + this.getImageWidth() / 2;
    }
    
    public int getMiddleY(){
	return this.getY() + this.getImageHeight() / 2;
    }
}