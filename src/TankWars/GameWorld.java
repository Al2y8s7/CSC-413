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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.Timer;
import java.util.Observable;
import java.util.Observer;
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
    private Tank tank1, tank2, tank;
    private Bullet bullet;
    private BufferedImage background, miniMap, gameMap; 
    private static BufferedImage tankImage1, tankImage2, bulletImage, payload, nWalls, bWalls, water;
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
    
    private Graphics2D worldMapGraphics, graphics2D;
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
	initKeyMapping();
	initTanks();
	initTimer();
	timer.start();	

    }
    
    
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
	worldMapGraphics.drawImage(background, 0, 0, null);
	drawWalls(g);
	tank1.draw(g);
	tank2.draw(g);
	//draws tanks with rotation
	worldMapGraphics.drawImage(tankImage1, tank1.getX() , tank1.getY(), null);
	worldMapGraphics.drawImage(tankImage2, tank2.getX() , tank2.getY(), null);
	drawBullets(g);
	setMiniMap(g);


	
    }
    
    public void drawWalls(Graphics g){
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
		    GOList.add(normalWalls);
		} else if(GameMap[y][x] == 2){
		   breakableWalls = new BreakableWall(x * 32, y * 32, bWalls, 32, 32);
		   bWallList.add(breakableWalls);
		   GOList.add(breakableWalls);
		}
	    }
	}
    }
	
	public void drawBullets(Graphics g){
	    for(int i = 0; i < bulletList.size(); i++){
		Bullet gameBullets = bulletList.get(i);
		if(gameBullets.visibility){
		    g.drawImage(gameBullets.getImage(), gameBullets.getX(), gameBullets.getY(), null);
		}else{
		    bulletList.remove(i);
		}
	    }
	}
	
	private void bulletShot(Tank tank){
	    if(tank.getShoot() == true){
		Bullet bullet = new Bullet(tank, tank.getX(), tank.getY(), (short) 0, bulletImage, 32, 32);
		bulletList.add(bullet);
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
	    tankImage1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Standard_Tank.png"));
	    tankImage2 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Standard_Tank2.png"));
	    nWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall1.gif"));
	    bWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall2.gif"));
	    bulletImage = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Shell.gif"));
	    water = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/water.png"));
	    
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
    
    public void setMiniMap(Graphics g){
	AffineTransform mMap = new AffineTransform();
	mMap.scale(.25, .25);
	AffineTransformOp op  = new AffineTransformOp(mMap, AffineTransformOp.TYPE_BILINEAR);
	//worldMapGraphics.drawImage(op.filter(tank.getImage(), null), tank.getX(), tank.getY(), null);
	graphics2D = (Graphics2D) g;
	miniMap = op.filter(gameMap, miniMap);
	g.drawImage(miniMap, 480, 568, null);
	
    }
    
    public void setGameLists() {
	GOList = new ArrayList<>();
	tankList = new ArrayList<>();
	nWallList = new ArrayList<>();
	bWallList = new ArrayList<>();
    }
    
   
   //Movement implement by Moses
   private void initTanks(){
        tank1 = new Tank(70, 600,(short) 0, tankImage1,player1, 32, 32);
        tank2 = new Tank(1080, 50,(short) 0,tankImage2,player2, 32, 32);
        tankControls = new Controller();
        addKeyListener(tankControls.getKeyAdapter());
        this.tankControls.addObserver(tank1);
        this.tankControls.addObserver(tank2);
    }

   private void initTimer(){     
        timer = new Timer(20, (ActionEvent e) -> {
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
                  // bWallList.remove(bWall);
	       }
	       if(tank2HitBox.intersects(bHitBox)){
		 
                     bWall.setVisibility(false);
                  //  bWallList.remove(bWall);
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
        
/*	for(GameObject GO: GOList){
	   Rectangle goHitBox = GO.getHitBox();
	   if(tank1HitBox.intersects(goHitBox)){
	       tank1.collide(GO);
	       System.out.println("Collided!");
	   }
	   if(tank2HitBox.intersects(goHitBox)){
	       tank2.collide(GO);
	       System.out.println("Collided!");
	   }
	   for(BreakableWall bWall: bWallList){
	       Rectangle bHitBox = bWall.getHitBox();
	       if(tank1HitBox.intersects(bHitBox) && GO instanceof BreakableWall){
		   tank1.collide(bWall);
		   System.out.println("Collided");
	       }
	       if(tank2HitBox.intersects(bHitBox)){
		   tank2.collide(bWall);
	       }
	  }
	   for(NormalWall nWall: nWallList){
	       Rectangle nHitBox = nWall.getHitBox();
	       if(tank1HitBox.intersects(nHitBox) && GO instanceof BreakableWall){
		   tank1.collide(nWall);
		   System.out.println("Collided");
	       }
	       if(tank2HitBox.intersects(nHitBox)){
		   tank2.collide(nWall);
		   System.out.println("Collided!");
	       }
	   }
	}
    */
    
    }

}