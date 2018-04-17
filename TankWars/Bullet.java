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
    public Bullet(int x, int y, BufferedImage image,int z,int w){
        super(x,y,image,z,w);
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

    @Override
    public void update(Observable o, Object arg) {
    }
    
}
