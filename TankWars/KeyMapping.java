/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

/**
 *
 * @author mmmos
 */
public class KeyMapping {
    
    int upKey, rightKey, downKey, leftKey, shootKey;
    
    public KeyMapping(int up , int right, int down, int left, int shoot){
       this.upKey = up;
       this.rightKey = right;
       this.downKey = down;
       this.leftKey = left;
       this.shootKey = shoot;    
    }
    public int getUpKey(){
        return this.upKey;
    }
    public int getRightKey(){
        return this.rightKey;
    }
    public int getDownKey(){
        return this.downKey;
    }
    public int getLeftKey(){
        return this.leftKey;
    }
    public int getShootKey(){
        return this.shootKey;
    }
}
