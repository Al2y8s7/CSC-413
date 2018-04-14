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
public class BreakableWall extends GameObject {
    
    //constructor
    public BreakableWall(int x, int y, BufferedImage image){
        super(x,y,image);
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
    public int getSpawn(){
        return 0;
    }
    
    public void setSpawn(){
        
    }
    
    public int getIntegrity(){
        return 0;
    }
    
    public void setIntegrity(){
        
    }
}
