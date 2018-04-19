package TankWars;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.Timer;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 * This class handles all in-game objects (game objects) Utilizes ActionsEvents and checks for updates from Observers
 *
 */
public class GameWorld extends JPanel{// implements Observer {

    //used for update method
    private Tank tank1, tank2;
    private BufferedImage background; 
    private static BufferedImage gameMap, tankImage1, tankImage2, payload, nWalls, bWalls,water;
    private static BufferedImage bulletImage;
    private int width, height;
    
    private Controller tankControls;
    private Timer timer;
    private KeyMapping player1, player2;
    
    //Arrays lists to store all game objects
    ArrayList<GameObject> GOList;
    ArrayList<Tank> tankList;
    ArrayList<NormalWall> nWallList;
    ArrayList<BreakableWall> bWallList;
    private ArrayList<Bullet> bulletList = new ArrayList<>();
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
        initResources();
	setBackground();
	setMap();
	initKeyMapping();
	initTanks();
	initTimer();
	timer.start();	

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
    
    /*
    @Override
    public void update(Observable o, Object obj){
	//detectCollision();
	//repaint();
    }
    
    */
    
    //draw objects to game window
    public void paintComponent(Graphics g){
	super.printComponents(g);
	worldMapGraphics = gameMap.createGraphics();
	//draw background
	g.drawImage(background, 0, 0, null);
	//draws tanks with rotation
	tank1.draw(g);
	tank2.draw(g);
        
        if(tank1.getShoot() == true){
            Bullet bullet = new Bullet(tank1.getX(),tank1.getY(),tank1.getAngle(),bulletImage,1,1);
            bulletList.add(bullet);
            tank1.setShoot(false);
        }
        if(tank2.getShoot() == true){
            Bullet bullet = new Bullet(tank2.getX(),tank2.getY(),tank2.getAngle(),bulletImage,1,1);
            bulletList.add(bullet);
            tank2.setShoot(false);
        }
        drawBullets(g);
	for(NormalWall nWall: nWallList){
	    //draws coordinates for single normal wall
	    g.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
	    //use graphics to draw normal wall
	    worldMapGraphics.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
	}
	for(int i = 0; i < bWallList.size(); i++){
	    BreakableWall bWall = bWallList.get(i);
	    if(!bWall.getVisibility()){
		bWallList.remove(bWall);
		//GOList.remove(bWall);
	    }
            else{
		g.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
		worldMapGraphics.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
	    }
	}
            
	
    }
        public void setMap() {
	//instantiate World
	WorldMap currMap = new WorldMap();
	NormalWall normalWalls;
	BreakableWall breakableWalls;
	//create reference for 2D array
	int[][] GameMap = currMap.getGameMap();
	//tile map with walls
	for (int y = 0; y < 25; y++){ 
	    for (int x = 0; x <= 40; x++) {
 		if (GameMap[y][x] == 1) {
		    normalWalls = new NormalWall(x * 32, y * 32, nWalls , 32, 32);
		    nWallList.add(normalWalls);
		   // GOList.add(normalWalls);
		} else if(GameMap[y][x] == 2){
		   breakableWalls = new BreakableWall(x * 32, y * 32, bWalls, 32, 32);
		   bWallList.add(breakableWalls);
		 //  GOList.add(breakableWalls);
		}
	    }
	}
    }
    //load background  
    //load tanks, walls and other game objects
    public void initResources() {
	try{
            background = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Background.png"));
	    tankImage1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Tank1.png"));
	    tankImage2 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Tank2.png"));
	    nWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall1.gif"));
	    bWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall2.gif"));
	    water = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/water.png"));
            bulletImage = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Shell.gif"));
	    
	}catch(IOException ex){
	    ex.printStackTrace();
	}
    }
    
    public void setBackground(){
	width = background.getWidth(this);
	height = background.getHeight(this);
	setPreferredSize(new Dimension(width, height));
	gameMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    public void setGameLists() {
	//GOList = new ArrayList<>();
	tankList = new ArrayList<>();
	nWallList = new ArrayList<>();
	bWallList = new ArrayList<>();
    }
    
   
   private void initTanks(){
        tank1 = new Tank(70, 600,(short) 0, tankImage1,player1, 32, 32);
        tank2 = new Tank(1080, 50,(short) 0,tankImage2,player2, 32, 32);
        tankControls = new Controller();
        addKeyListener(tankControls.getKeyAdapter());
        this.tankControls.addObserver(tank1);
        this.tankControls.addObserver(tank2);
    }
   private void initTimer(){     
        timer = new Timer(1000/144, (ActionEvent e) -> {
            GameWorld.this.detectCollision();
            GameWorld.this.repaint();
        });
    }    
    private void initKeyMapping(){
        player1 = new KeyMapping(KeyEvent.VK_UP,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_ENTER);
        player2 = new KeyMapping(KeyEvent.VK_W,KeyEvent.VK_D,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_SPACE);
    }
    //collision detection
    public void detectCollision(){
	//set hitboxes for tanks
	Rectangle tank1HitBox = tank1.getHitBox();
	Rectangle tank2HitBox = tank2.getHitBox();
        if(tank1HitBox.intersects(tank2HitBox)){
            tank1.setX(tank1.getXnonCollision());
            tank1.setY(tank1.getYnonCollision());
            tank2.setX(tank2.getXnonCollision());
            tank2.setY(tank2.getYnonCollision());
        }
	//iterate through GO ArrayList and draw hitboxes for all GO's
        for(BreakableWall bWall: bWallList){
	       Rectangle bHitBox = bWall.getHitBox();
	       if(tank1HitBox.intersects(bHitBox)){
		
                   bWall.setVisibility(false);
	       }
	       if(tank2HitBox.intersects(bHitBox)){
		 
                     bWall.setVisibility(false);
	       }
        }
        for(NormalWall nWall: nWallList){
	       Rectangle nHitBox = nWall.getHitBox();
	       if(tank1HitBox.intersects(nHitBox)){
		   
                 tank1.setX(tank1.getXnonCollision());
                 tank1.setY(tank1.getYnonCollision());
	       }
	       if(tank2HitBox.intersects(nHitBox)){
		    tank2.setX(tank2.getXnonCollision());
                    tank2.setY(tank2.getYnonCollision());
	       }
	   }    
    }
    public void drawBullets(Graphics g){
        for(int i = 0;i<bulletList.size();i++){
            bulletList.get(i).draw(g);
            bulletList.get(i).move();
        }
    }
}