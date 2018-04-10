package TankWars;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alnguye This class handles all in-game objects (game objects) Utilizes ActionsEvents and checks for updates from Observers
 *
 */
public class GameWorld extends JPanel implements Observer {

    //used for update method
    private Tank t1, t2;
    private Controller controller1, controller2;
    private GameClock gameClock;
    private BufferedImage background; 
    private static BufferedImage gameMap, tank1, payload, nWalls, bWalls;
    private int width, height;
    
    //Arrays lists to store all game objects
    ArrayList<GameObject> GOList;
    ArrayList<Tank> tankList;
    ArrayList<NormalWall> nWallList;
    ArrayList<BreakableWall> bWallList;
    //private ArrayList<Bullet> bulletList = new ArrayList<>();
    //private ArrayList<Explosion> explosionList = new ArrayList<>();
    //private ArrayList<Explosion> wallList = new AraryList<>();
    
    private Graphics2D worldMapGraphics;
    Graphics dbg;
    Image dbImage;
    GameWorld game;
   
    //constructor
    public GameWorld() {
	setGameLists();
	setFocusable(true);
	setBackground();
	initResources();
	setMap();
	//setGameClock();
	

    }
    
     //put graphics for JFrame
//    @Override
//    public void paint(Graphics g){
//	dbImage = createImage(getWidth(), getHeight());
//	dbg = dbImage.getGraphics();
//	paintComponent(dbg);
//	g.drawImage(dbImage, 0, 0, this);
//	for(int i = 0; i < nWallList.size(); i++){
//	    nWallList.get(i).draw(g);
//      }
//    }
    //draw objects to game window
    public void paintComponent(Graphics g){
	super.printComponents(g);
	worldMapGraphics = gameMap.createGraphics();
	//draw background
	g.drawImage(background, 0, 0, null);
	worldMapGraphics.drawImage(background, 0, 0, null);
	for(int i = 0; i < nWallList.size(); i++){
	    nWallList.get(i).draw(g);
	}
//	
//	for(NormalWall nWall: nWallList){
//	    //draws coordinates for single normal wall
//	    g.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
//	    //use graphics to draw normal wall
//	    worldMapGraphics.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
//	}
//	for(int i = 0; i < bWallList.size(); i++){
//	    BreakableWall bWall = bWallList.get(i);
//	    if(!bWall.checkVisibility()){
//		bWallList.remove(bWall);
//		GOList.remove(bWall);
//	    }else{
//		g.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
//		worldMapGraphics.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
//	    }
//	}
	
    }
    
        public void setMap() {
	//instantiate World
	WorldMap currMap = new WorldMap();
	NormalWall normalWalls;
	BreakableWall breakableWalls;
	//create reference for 2D array
	int[][] GameMap = currMap.getGameMap();
	//tile map with walls
	for (int y = 0; y < 20; y++) {
	    for (int x = 0; x < 20; x++) {
 		if (GameMap[y][x] == 1) {
		    normalWalls = new NormalWall(x * 32, y * 32, 0, nWalls, 32, 32);
		    nWallList.add(normalWalls);
		    GOList.add(normalWalls);
		} else if(GameMap[y][x] == 2){
		   breakableWalls = new BreakableWall(x * 32, y * 32, 0, bWalls, 32, 32);
		   bWallList.add(breakableWalls);
		   GOList.add(breakableWalls);
		}
	    }
	}
    }
	
    //load background  
    public void initBackground() {
	try {
	    background = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Background.bmp"));

	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }
    
    //load tanks, walls and other game objects
    public void initResources() {
	try{
	    tank1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Tank1.png"));
	    nWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall1.gif"));
	    bWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall2.gif"));
	    
	}catch(IOException ex){
	    ex.printStackTrace();
	}
    }
    
    public void setBackground(){
	initBackground();
	width = background.getWidth(this);
	height = background.getHeight(this);
	setPreferredSize(new Dimension(width, height));
	gameMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
    }
    
    public void setGameLists() {
	GOList = new ArrayList<>();
	tankList = new ArrayList<>();
	nWallList = new ArrayList<>();
	bWallList = new ArrayList<>();
    }

    protected void setGameClock() {
	gameClock = new GameClock();
	gameClock.addObserver(this);
	gameClock.addObserver(t1);
	gameClock.addObserver(t2);
    }

    @Override
    public void update(Observable o, Object arg) {
	updateTanks(t1, controller1);
	updateTanks(t2, controller2);
	repaint();
    }

    //methods for game objects to be updated
    public void updateTanks(Tank tank, Controller controller) {

    }

   
    
    
    
    //setup controls for tanks
    public void setControls() {
	//controls for controller1
	controller1 = new Controller(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER);
	controller2 = new Controller(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_SPACE);

    }
}
