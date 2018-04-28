package TankWars;

import static TankWars.Main.WINDOW_HEIGHT;
import static TankWars.Main.WINDOW_WIDTH;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Color;
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
import java.util.Iterator;
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
 * This class handles all in-game objects (game objects)
 *
 */
public class GameWorld extends JPanel {

    //used for update method
    private Tank tank1, tank2;
    private BufferedImage background, miniMap, tank1View, tank2View, leftScreen, rightScreen;
    private static BufferedImage gameMap, tankImage1, tankImage2, payload, nWalls, bWalls, water;
    private static BufferedImage bullet1, bullet2, smallExplosion, largeExplosion, powerUp1, shield, lives;
    private int width, height;

    private Controller tankControls;
    private Timer timer;
    private Timer tank1BulletDelay, tank2BulletDelay;
    private KeyMapping player1, player2;

    //Arrays lists to store all game objects
    ArrayList<Explosions> explosionList;
    ArrayList<Tank> tankList;
    ArrayList<NormalWall> nWallList;
    ArrayList<BreakableWall> bWallList;
    private ArrayList<Bullet> bulletList = new ArrayList<>();
    private ArrayList<PowerUp> PowerUpList = new ArrayList<>();

    private Graphics2D worldMapGraphics, graphics2D;
    Graphics dbg;
    Image dbImage;
    GameWorld game;

    Music gameMusic;

    //tank 1
    int tankX1bounds, tankY1bounds;
    //tank 2
    int tankX2bounds, tankY2bounds;

    protected int tankXoffset, tankYoffset;

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
	gameMusic.music();

    }

    //draw objects to game window
    public void paintComponent(Graphics g) {
	super.printComponents(g);
	drawEverything();
	splitScreen(g);
	setMiniMap(g);
	graphics2D.dispose();
	g.dispose();
    }

    private void drawEverything() {
	worldMapGraphics = gameMap.createGraphics();
	worldMapGraphics.drawImage(background, 0, 0, null);
	drawExplosions(worldMapGraphics);
	drawWalls(worldMapGraphics);
	tank1.draw(worldMapGraphics);
	tank2.draw(worldMapGraphics);
	drawBullets(worldMapGraphics);
	drawHealthBars(worldMapGraphics);
	drawLives(worldMapGraphics);
	drawPowerUps(worldMapGraphics);
    }

    public void setMap() {
	//instantiate World
	WorldMap currMap = new WorldMap();
	NormalWall normalWalls;
	BreakableWall breakableWalls;
	PowerUp power;
	//create reference for 2D array
	int[][] GameMap = currMap.getGameMap();
	//tile map with walls
	for (int y = 0; y < 25; y++) {
	    for (int x = 0; x <= 43; x++) {
		if (GameMap[y][x] == 1) {
		    normalWalls = new NormalWall(x * 32, y * 32, nWalls, 32, 32);
		    nWallList.add(normalWalls);
		} else if (GameMap[y][x] == 2) {
		    breakableWalls = new BreakableWall(x * 32, y * 32, bWalls, 32, 32);
		    bWallList.add(breakableWalls);
		} else if (GameMap[y][x] == 3) {
		    power = new PowerUp(x * 32, y * 32, 1, powerUp1, 32, 32);
		    PowerUpList.add(power);
		} else if (GameMap[y][x] == 4) {
		    power = new PowerUp(x * 32, y * 32, 2, shield, 32, 32);
		    PowerUpList.add(power);
		}
	    }
	}
    }

    public void drawWalls(Graphics g) {
	for (NormalWall nWall : nWallList) {
	    //draws coordinates for single normal wall
	    g.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
	    //use graphics to draw normal wall
	    worldMapGraphics.drawImage(nWall.getImage(), nWall.getX(), nWall.getY(), null);
	}
	for (int i = 0; i < bWallList.size(); i++) {
	    BreakableWall bWall = bWallList.get(i);
	    if (!bWall.getVisibility()) {
		bWallList.remove(bWall);
	    } else {
		g.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
		worldMapGraphics.drawImage(bWall.getImage(), bWall.getX(), bWall.getY(), null);
	    }
	}
    }

    public void setMiniMap(Graphics g) {
	AffineTransform mMap = new AffineTransform();
	mMap.scale(.25, .25);
	AffineTransformOp op = new AffineTransformOp(mMap, AffineTransformOp.TYPE_BILINEAR);
	graphics2D = (Graphics2D) g;
	miniMap = op.filter(gameMap, miniMap);
	worldMapGraphics.drawImage(background, 0, 0, null);
	g.drawImage(miniMap, 520, 568, null);
	//draws tanks with rotation
	worldMapGraphics.drawImage(tank1.getImage(), tank1.getX(), tank1.getY(), null);
	worldMapGraphics.drawImage(tank2.getImage(), tank2.getX(), tank2.getY(), null);

    }

    public void splitScreen(Graphics g) {
	//left Screen
	leftScreen = gameMap.getSubimage(0, 0, WINDOW_WIDTH / 2 - 21, 800);
	g.drawImage(leftScreen, 0, 0, null);
	//right Screen
	rightScreen = gameMap.getSubimage(WINDOW_WIDTH / 2 - 22, 0, WINDOW_WIDTH / 2 + 15, 800);
	g.drawImage(rightScreen, (WINDOW_WIDTH / 2 - 20), 0, null);

	//tank1View = gameMap.getSubimage(tankX1bounds, tankY1bounds, WINDOW_WIDTH / 2 - 21, WINDOW_HEIGHT /2);
	//tank2View = gameMap.getSubimage(tankX2bounds, tankY2bounds, WINDOW_WIDTH / 2 - 22, WINDOW_HEIGHT /2);
	//tankViewBounds();
	
	//g.drawImage(tank1View, 0, 0, game);
	//g.drawImage(tank2View, WINDOW_WIDTH / 2 - 22, 0, game);
	//right Screen

	//tank1View = gameMap.getSubimage(tankXmid, tankYmid, tank1.getImageWidth(), tank1.getImageHeight());
    }

    public void tankViewBounds() {

	if ((tankX1bounds = tank1.getMiddleX() - WINDOW_WIDTH / 4) < 0) {
	    tankX1bounds = 0;
	} else if ((tankX1bounds >= WINDOW_WIDTH - WINDOW_WIDTH / 2)) {
	    tankX1bounds = WINDOW_WIDTH - WINDOW_WIDTH / 2;
	}
	if ((tankY1bounds = tank1.getMiddleY() - WINDOW_HEIGHT / 2) < 0) {
	    tankY1bounds = 0;
	} else if ((tankY1bounds >= WINDOW_HEIGHT)) {
	    tankY1bounds = WINDOW_HEIGHT;
	    System.out.println(tankY1bounds);
	}
	if ((tankX2bounds = tank2.getMiddleX() - WINDOW_WIDTH / 4) < 0) {
	    tankX2bounds = 0;
	} else if ((tankX2bounds >= WINDOW_WIDTH - WINDOW_WIDTH / 2)) {
	    tankX2bounds = WINDOW_WIDTH - WINDOW_WIDTH / 2;
	}
	if ((tankY2bounds = tank2.getMiddleY() - WINDOW_HEIGHT / 2) < 0) {
	    tankY2bounds = 0;
	} else if ((tankY2bounds >= WINDOW_HEIGHT)) {
	    tankY2bounds = WINDOW_HEIGHT;
	}
    }

    //get Xoffset for tank
    public int tankXoffset(Tank tank) {
	tankXoffset = ((tank.getX() + tank.getImageWidth() / 2) - (WINDOW_WIDTH / 2));
	if (tank.getX() + tankXoffset < 0) {
	    return WINDOW_WIDTH / 2;
	} else if (tank.getX() + tankXoffset > 0 && tank.getX() + tankXoffset < gameMap.getWidth() + WINDOW_WIDTH / 4) {
	    return (tank.getX() + tankXoffset) / 2;
	} else if (tank.getX() + tankXoffset >= gameMap.getWidth() + WINDOW_WIDTH / 4) {
	    return (this.gameMap.getWidth() - Main.WINDOW_WIDTH / 2);
	} else {
	    return 0;
	}
    }

    ///get Yoffset for tank
    public int tankYoffset(Tank tank) {
	tankYoffset = ((tank.getY() + tank.getImageHeight() / 2) - (WINDOW_HEIGHT / 2));
	if (tank.getY() + tankYoffset <= 0) {
	    return WINDOW_HEIGHT / 2;
	} else if (tank.getY() + tankYoffset > 0 && tank.getY() + tankYoffset < gameMap.getHeight() + WINDOW_HEIGHT) {
	    return (tank.getY() + tankYoffset);
	} else if (tank.getY() + tankYoffset >= this.gameMap.getHeight() + WINDOW_HEIGHT) {
	    return (this.gameMap.getHeight() - WINDOW_HEIGHT);
	} else {
	    return 0;
	}
    }

    //load background, tanks, walls and other game objects
    public void initResources() {
	try {
	    background = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Background2.png"));
	    tankImage1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Standard_Tank.png"));
	    tankImage2 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Standard_Tank.png"));
	    nWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall1.gif"));
	    bWalls = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Wall2.gif"));
	    bullet1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/BulletBill.png"));
	    bullet2 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/BulletBill2.png"));
	    smallExplosion = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Explosion_small.gif"));
	    largeExplosion = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Explosion_large.gif"));
	    powerUp1 = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Herramienta.png"));
	    shield = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Shield1.gif"));
	    lives = ImageIO.read(GameWorld.class.getResource("/TankWars/resources/Heart.png"));

	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    public void setBackground() {
	width = background.getWidth(this);
	height = background.getHeight(this);
	setPreferredSize(new Dimension(width, height ));
	gameMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void setGameLists() {
	explosionList = new ArrayList<>();
	tankList = new ArrayList<>();
	nWallList = new ArrayList<>();
	bWallList = new ArrayList<>();
    }

    private void initTanks() {
	tank1 = new Tank(70, 600, (short) 0, 1, tankImage1, player1, 32, 32);
	tank2 = new Tank(1080, 50, (short) 180, 2, tankImage2, player2, 32, 32);
	tankControls = new Controller();
	addKeyListener(tankControls.getKeyAdapter());
	this.tankControls.addObserver(tank1);
	this.tankControls.addObserver(tank2);
    }

    private void initTimer() {
	timer = new Timer(1000 / 144, (ActionEvent e) -> {
	    if (tank1.getLives() == 0) {
		System.out.println("Player 2 WINS!!");
	    }
	    if (tank2.getLives() == 0) {
		System.out.println("Player 1 WINS!!");
	    }
	    GameWorld.this.checkShooting();
	    GameWorld.this.detectCollision();
	    GameWorld.this.repaint();
	});
    }

    private void initKeyMapping() {
	player1 = new KeyMapping(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
	player2 = new KeyMapping(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE);
    }

    //collision detection
    public void detectCollision() {
	//set hitboxes for tanks
	Rectangle tank1HitBox = tank1.getHitBox();
	Rectangle tank2HitBox = tank2.getHitBox();
	if (tank1HitBox.intersects(tank2HitBox)) {
	    tank1.collide(tank2);
	}
	if (tank2HitBox.intersects(tank1HitBox)) {
	    tank2.collide(tank1);
	}
	//iterate through GO ArrayList and draw hitboxes for all GO's
	for (BreakableWall bWall : bWallList) {
	    Rectangle bHitBox = bWall.getHitBox();
	    if (tank1HitBox.intersects(bHitBox)) {
		bWall.setVisibility(false);
	    }
	    if (tank2HitBox.intersects(bHitBox)) {
		bWall.setVisibility(false);
	    }
	    for (Bullet bullet : bulletList) {
		Rectangle hitBoxBullet = bullet.getHitBox();
		if (hitBoxBullet.intersects(bHitBox)) {
		    bWall.setVisibility(false);
		    bullet.setVisibility(false);
		    Explosions explosion = new Explosions(bWall.getX(), bWall.getY(), smallExplosion, 0, 0);
		    explosionList.add(explosion);
		}
	    }
	}
	for (NormalWall nWall : nWallList) {
	    Rectangle nHitBox = nWall.getHitBox();
	    if (tank1HitBox.intersects(nHitBox)) {

		tank1.collide(nWall);
	    }
	    if (tank2HitBox.intersects(nHitBox)) {
		tank2.collide(nWall);
	    }
	    for (Bullet bullet : bulletList) {
		Rectangle hitBoxBullet = bullet.getHitBox();
		if (hitBoxBullet.intersects(nHitBox)) {
		    bullet.setVisibility(false);
		}
	    }
	}
	for (Bullet bullet : bulletList) {
	    Rectangle hitBoxBullet = bullet.getHitBox();
	    if (bullet.getPlayer() != tank2.getPlayer() && tank2HitBox.intersects(hitBoxBullet)) {
		if (tank2.getHealth() <= 10) {
		    Explosions explosion = new Explosions(tank2.getX(), tank2.getY(), largeExplosion, 0, 0);
		    explosionList.add(explosion);
		}
		tank2.collide(bullet);
		Explosions explosion = new Explosions(bullet.getX(), bullet.getY(), smallExplosion, 0, 0);
		explosionList.add(explosion);
	    }
	    if (bullet.getPlayer() != tank1.getPlayer() && tank1HitBox.intersects(hitBoxBullet)) {
		if (tank1.getHealth() <= 10) {
		    Explosions explosion = new Explosions(tank1.getX(), tank1.getY(), largeExplosion, 0, 0);
		    explosionList.add(explosion);
		}
		tank1.collide(bullet);
		Explosions explosion = new Explosions(bullet.getX(), bullet.getY(), smallExplosion, 0, 0);
		explosionList.add(explosion);
	    }
	}
	for (PowerUp power : PowerUpList) {
	    Rectangle powerHitBox = power.getHitBox();
	    if (tank2HitBox.intersects(powerHitBox)) {
		power.collide(tank2);
	    }
	    if (tank1HitBox.intersects(powerHitBox)) {
		power.collide(tank1);
	    }
	}
    }

    public void drawBullets(Graphics g) {
	Iterator<Bullet> iterator = bulletList.iterator();
	while (iterator.hasNext()) {
	    Bullet bullet = iterator.next();
	    if (!bullet.getVisibility()) {
		iterator.remove();
	    } else {
		bullet.draw(g);
		bullet.move();
	    }
	}
    }

    public void checkShooting() {
	if (tank1.getShoot() == true) {
	    Bullet bullet = new Bullet(tank1.getX() + (tank1.getImageWidth() / 2), tank1.getY() + (tank1.getImageHeight() / 2), tank1.getAngle(), 1, bullet1, 1, 1);
	    bulletList.add(bullet);
	    tank1.setShoot(false);
	}

	if (tank2.getShoot() == true) {
	    Bullet bullet = new Bullet(tank2.getX(), tank2.getY(), tank2.getAngle(), 2, bullet2, 1, 1);
	    bulletList.add(bullet);
	    tank2.setShoot(false);
	}
    }

    public void drawExplosions(Graphics g) {
	Iterator<Explosions> iterator = explosionList.iterator();
	while (iterator.hasNext()) {
	    Explosions explosion = iterator.next();
	    if (!explosion.getVisibility()) {
		iterator.remove();
	    } else {
		g.drawImage(explosion.getImage(), explosion.getX(), explosion.getY(), null);
		explosion.setVisibility(false);
	    }
	}
    }

      public void drawHealthBars(Graphics g){
        g.setColor(Color.red);
        g.fillRect(tank1.getX()-10, tank1.getY()+tank1.getImageHeight(), 100, 20);
        
        g.setColor(Color.green);
        g.fillRect(tank1.getX()-10, tank1.getY()+tank1.getImageHeight(), tank1.getHealth(), 20);
        
        g.setColor(Color.white);
        g.drawRect(tank1.getX()-10, tank1.getY()+tank1.getImageHeight(), 100, 20);
        
        g.setColor(Color.red);
        g.fillRect(tank2.getX()-10, tank2.getY()+tank2.getImageHeight(), 100, 20);
        
        g.setColor(Color.green);
        g.fillRect(tank2.getX()-10, tank2.getY()+tank2.getImageHeight(), tank2.getHealth(), 20);
        
        g.setColor(Color.white);
        g.drawRect(tank2.getX()-10, tank2.getY()+tank2.getImageHeight(), 100, 20);
        
    }

    public void drawPowerUps(Graphics g) {
	Iterator<PowerUp> iterator = PowerUpList.iterator();
	while (iterator.hasNext()) {
	    PowerUp power = iterator.next();
	    if (!power.getVisibility()) {
		iterator.remove();
	    } else {
		g.drawImage(power.getImage(), power.getX(), power.getY(), null);
	    }
	}
    }

    public void drawLives(Graphics g) {
	for (int i = 0; i < tank1.getLives(); i++) {
	    g.drawImage(lives, tank1.getX() + (i * 20), tank1.getY() + tank1.getImageHeight() - 5, null);
	}
	for (int i = 0; i < tank2.getLives(); i++) {
	    g.drawImage(lives, tank2.getX() + (i * 20), tank2.getY() + tank2.getImageHeight() - 5, null);
	}
    }
}
