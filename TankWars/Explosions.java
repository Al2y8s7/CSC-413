/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.image.BufferedImage;

/**
 *
 * @author mmmos
 */
public class Explosions extends GameObject{

    public Explosions(int x, int y, BufferedImage Image, int width, int height) {
        super(x, y, Image, width, height);
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
    public void collide(Bullet bullet) {
    }


    
}
