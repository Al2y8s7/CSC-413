package TankWars;

import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 * 3/21/18
 */
public class PowerUp extends GameObject {
    int powerUpType;
    //constructor
    public PowerUp(int x, int y, int type, BufferedImage image, int z, int w){
        super(x,y,image, w , z);
        this.powerUpType = type;
    }
    @Override
    public void collide(GameObject gameObject){
        
    }
    
    @Override
    public void collide(Tank tank){
        if(powerUpType == 1){
        tank.setHealth(100);
        this.setVisibility(false);
        }
        else if(powerUpType == 2){
            
        }
    }
    
    @Override
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
    }
    
    public void update(Observable obj,Object o){
	
    }

}