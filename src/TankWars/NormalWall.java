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
 * @author Alnguye
 */
public class NormalWall extends GameObject {

    public NormalWall(int x, int y, int velocity, BufferedImage image, int width, int length) {
	super(x, y, velocity, image, width, length);

	
    }

    @Override
    public void update(Observable o, Object arg) {
	
    }
    
}
