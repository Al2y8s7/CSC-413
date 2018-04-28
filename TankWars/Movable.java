package TankWars;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 */
public abstract class Movable extends GameObject{
    
        
    public Movable(int x, int y, BufferedImage image, int width, int length) {
        super(x,y,image, width, length);
    }


}