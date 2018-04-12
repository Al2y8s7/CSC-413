/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author mmmos
 */
public class Controller extends Observable {// implements KeyListener{
    
    Set<Integer> keys = new HashSet();
    KeyAdapter ka;
    
    public Controller(){
        ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                keys.add(key); 
                Controller.this.setChanged();
                Controller.this.notifyObservers(Controller.this);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                keys.remove(key);
                Controller.this.setChanged();
                Controller.this.notifyObservers(Controller.this);
    }
        };
    }
    
    
    /*
    @Override
    public void keyTyped(KeyEvent e){ }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("Key Pressed: " + e.getKeyCode());
        keys.add(key); 
        notifyObservers();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("Key Released: " + e.getKeyCode());
        keys.remove(key);
        notifyObservers();
    }
    */
    public Set<Integer> getKeys(){
        return keys;
    }
    public KeyAdapter getKeyAdapter(){
        return ka;
    }
    
}
