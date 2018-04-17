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
    public PowerUp(int x, int y, BufferedImage image, int z, int w){
        super(x,y,image, w , z);
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
    public int getTarget(){
        return 0;
    }
    
    public void setTarget(){
        
    }

    @Override
    public void collide(NormalWall normalWall) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
