package TankWars;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.lang.Object;


/**
 *
 * @author Alnguye
 * This class handles all game play functionality:
 * Create game window
 * 
 *
 * 
 */
public class Engine extends JFrame implements Runnable {
    Graphics dbg;
    Image dbImage;
    GameWorld game;
    private Thread thread;
    BufferedImage background, tank, payload,nWalls, bWalls;
    
    //checks to see if game objects have been updated
    public void isUpdated(GameWorld gameUpdate){
	
    }
    
    
    @Override
    public void run() {	
	
    }
   
    public Engine(){
	init();
	gameStart();
	
    }
    
    //load images from resources directory then add to array that holds them
    public void init(){
	try {
	    background = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Background.bmp"));
	    tank = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Tank1.png"));
	    payload = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Payload.png"));
	    //Normal walls
	    nWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall1.gif"));
	    //Breakable walls
	    bWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall2.gif"));
	    
	} catch (IOException ex) {
	    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }
    
    public void gameStart(){
	//set game window
	setTitle("Tank Wars");
	setSize(800, 800);
	setLocation(new Point(200, 200));
	setResizable(false);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null); 


    }
    //put graphics for JFrame
    public void paint(Graphics g){
	dbImage = createImage(getWidth(), getHeight());
	dbg = dbImage.getGraphics();
	paintComponent(dbg);
	g.drawImage(dbImage, 0, 0, this);
    }
    //draw objects to game window
    public void paintComponent(Graphics g){
	//draw background
	g.drawImage(background, 0, 0, null);
	//draw tank
	g.drawImage(tank, 20, 700, null);
	//draw payload
	g.drawImage(payload, 700, 20, null);
	repaint();
    }

    
    
}