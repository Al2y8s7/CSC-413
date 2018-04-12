package TankWars;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alnguye 3/21/18
 */
public abstract class Movable extends GameObject{
    
        
    public Movable(int x, int y, BufferedImage image) {
        super(x,y,image);
    }

    @Override
    public void collide(GameObject gameObject) {
    }

    @Override
    public void collide(Tank tank) {
    }

    @Override
    public void collide(Bullet bullet) {
    }

    @Override
    public void collide(BreakableWall breakableWall) {
    }

    @Override
    public void collide(PowerUp powerUp) {
    }

}
