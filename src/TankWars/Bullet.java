package TankWars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alvin Nguyen & Moses Martinez 3/21/18
 */
public class Bullet extends GameObject {

    Tank tank;
    boolean moving;
    private int deltaX, deltaY;
    //speed of bullet
    final int r = 10;
    private short angle;

    //constructor
    public Bullet(Tank tank, int x, int y, short angle, BufferedImage image, int z, int w) {
	super(x, y, image, z, w);
	this.angle = angle;
	moving = true;
	
    }

    public void moveUp() {
	deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }

    public void draw(Graphics g) {
	// super.paintComponent(g);    
	AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);

    }

    @Override
    public void collide(GameObject gameObject) {

    }

    @Override
    public void collide(Tank tank) {

    }
//    @Override
//    public void collide(Bullet bullet) {
//
//    }

    @Override
    public void collide(BreakableWall breakableWall) {

    }

//    public void collide(PowerUp powerUp){
//        
//    }
    public void destroy() {
	
    }

    @Override
    public void collide(NormalWall normalWall) {
    }

    @Override
    public void update(Observable o, Object arg) {
	if(moving){
	    moveUp();
	}
    }

}
