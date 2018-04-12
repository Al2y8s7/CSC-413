package TankWars;

import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alnguye
 * 3/21/18
 */
public class Bullet extends GameObject {
    
    //constructor
    public Bullet(int x, int y, BufferedImage image){
        super(x,y,image);
    }
    
     @Override
    public void collide(GameObject gameObject){
        
    }
    
    @Override
    public void collide(Tank tank){
        
    }
    
    @Override
    public void collide(Bullet bullet){
        
    }
    
    @Override
    public void collide(BreakableWall breakableWall){
        
    }
    
    @Override
    public void collide(PowerUp powerUp){
        
    }
    
    public void destroy(){
        
    }
    
}
