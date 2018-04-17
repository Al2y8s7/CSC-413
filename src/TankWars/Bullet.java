package TankWars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alnguye
 */
public class Bullet extends Movable {
    protected int damage;
    boolean targetHit;
    Tank tank;
    

    public Bullet(int x, int y, BufferedImage image, int velocity, int width, int length, Tank tank) {
	super(x, y, image, width, length);
	targetHit = false;
	this.tank = tank;
	damage = 10;
    }
    
   
    
    
    public void destroy(){
	
    }
    
    @Override
    public void collide(GameObject gameObject) {
	
    }

    @Override
    public void collide(Tank tank) {
	
    }

    @Override
    public void collide(BreakableWall breakableWall) {
	
    }

    @Override
    public void collide(NormalWall normalWall) {
	
    }

    @Override
    public void update(Observable o, Object arg) {
	
    }

  public void draw(Graphics g){
      Graphics2D graphic2D = (Graphics2D) g;
  }
    
    
    
}
