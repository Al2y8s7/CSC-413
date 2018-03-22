package TankWars;
import java.util.Observable;
import java.util.Observer;
import java.util.*;

/**
 *
 * @author Alnguye
 * 3/21/18
 * 
 * A game object is any object represented in-game
 * The following behaviors are to be inherited:
 * isVisible, Collidable, Update, getCoordinates, setCoordinates
 */

public abstract class GameObject extends Observable implements Observer {

    
    //constructor
    public GameObject(){
        
    }
    
    public boolean isVisible(){
        return true;
    }
    
    public abstract void collide(GameObject gameObject);
    
    public abstract void collide(Tank tank);
    
    public abstract void collide(Bullet bullet);
    
    public abstract void collide(BreakableWall breakableWall);
    
    public abstract void collide(PowerUp powerUp);
}
