/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Timer;

/**
 *
 * @author Alnguye
 */
public class GameClock extends Observable implements ActionListener {
    Timer gameTimer;
    Timer timer;
    
    //constructor
    public GameClock(){
	
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
	setChanged();
	notifyObservers();
    }
    
}
