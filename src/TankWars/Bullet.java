package TankWars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alnguye
 * 3/21/18
 */
public class Bullet extends Movable {
    int deltaX,deltaY;
    final int r = 15;
    private short angle;
    private int player;
    //constructor
    public Bullet(int x, int y, short angle, int player, BufferedImage image,int width,int height){
        super(x,y,image,width,height);
        this.angle = angle;
        this.player = player;
    }
    
     @Override
    public void collide(GameObject gameObject){
        
    }
    
    @Override
    public void collide(Tank tank){
        
    }
    
    public void collide(Bullet bullet){
        
    }
    
    @Override
    public void collide(BreakableWall breakableWall){
        
    }
    
    public void collide(PowerUp powerUp){
        
    }
    
    public void destroy(){
        
    }

    @Override
    public void collide(NormalWall normalWall) {
    }

    public void draw(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
	rotation.rotate(Math.toRadians(angle), this.getImage().getWidth() / 2, this.getImage().getHeight() / 2);
	Graphics2D graphic2D = (Graphics2D) g;
	graphic2D.drawImage(this.getImage(), rotation, null);
        graphic2D.draw(this.getHitBox());
    }
    public void move(){
        deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
	deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
	x += deltaX;
	y += deltaY;
    }
    public int getPlayer(){
        return this.player;
    }

    @Override
    public void update(Observable o, Object arg) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}