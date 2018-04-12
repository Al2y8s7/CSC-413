package TankWars;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 * @author Alnguye
 * 3/21/18
 */
public class Tank extends Movable implements Observer{
    //data fields
    private int health;
    private Set<Integer> keys;
    private int ammo;
    private int lives;
    private int deltaX,deltaY;
    final int r = 4;
    private short angle;
    private KeyMapping keyMap;
    
    //constructor
    public Tank(int x, int y,short angle, BufferedImage image, KeyMapping kmap){
        super(x,y,image);
        keys = new HashSet();
        this.keyMap = kmap;
        this.angle = angle;
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
        Controller controller = (Controller) o;
        keys = controller.getKeys();
        MoveTanks();
    }
    
    //getters for coordinates
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
    public void moveUp() {
        deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x += deltaX;
        y += deltaY;
    }

    public void moveDown() {
        deltaX = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        deltaY = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x -= deltaX;
        y -= deltaY;
    }

    public void moveLeft() {
        this.angle -= 3;
    }
    public void moveRight() {
        this.angle += 3;
    }
    private void MoveTanks(){
        if(keys.contains(keyMap.getUpKey())){
            this.moveUp(); 
        }
        if(keys.contains(keyMap.getRightKey())){
            this.moveRight(); 
        }
        if(keys.contains(keyMap.getDownKey())){
            this.moveDown();
        }
        if(keys.contains(keyMap.getLeftKey())){
           this.moveLeft(); 
        }
        if(keys.contains(keyMap.getShootKey())){
           this.moveLeft();
        }
    }
}
