package TankWars;

import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alnguye
 * 3/21/18
 */
public class PowerUp extends GameObject {
    //constructor
    public PowerUp(int x, int y, BufferedImage image){
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
    public int getTarget(){
        return 0;
    }
    
    public void setTarget(){
        
    }
}
