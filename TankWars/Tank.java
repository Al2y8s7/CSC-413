package TankWars;
import java.util.Observable;

/**
 *
 * @author Alnguye
 * 3/21/18
 */
public class Tank extends Movable {
    //data fields
    private int health;
    private int ammo;
    private int lives;
    private int x;
    private int y;
    
    //constructor
    public Tank(){
        
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
    
    @Override
    public void update(Observable o, Object obj){
        
    }
    
    //getters for coordinates
    public int getX(int x){
        return this.x = x;
    }
    
    public int getY(int y){
        return this.y = y;
    }
    
    //setters for coordinates
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setLives(int lives){
        this.lives = lives;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public int getAmmo(int ammo){
        return this.ammo = ammo;
    }
    
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
    
    public void shoot(){
        
    }
}
