/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 */
public class NormalWall extends GameObject {

    public NormalWall(int x, int y, BufferedImage image, int width, int height) {
	super(x, y, image, width, height);

    }

    @Override
    public void collide(GameObject gameObject) {
	
    }

    @Override
    public void collide(Tank tank) {
	
    }

    @Override
    public void collide(BreakableWall breakableWall) {
	
    }

    @Override
    public void collide(NormalWall normalWall) {
	
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void collide(Bullet bullet) {
	
    }

    @Override
    public void collide(PowerUp powerUp) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}