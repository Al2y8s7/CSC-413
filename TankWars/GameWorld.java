package TankWars;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Alnguye 
 * 3/21/18
 */

/**
 * GameWorld keeps track of all game objects
 *
 */
public class GameWorld extends JPanel{
    private BufferedImage background, tankImage1, tankImage2, breakableWall, defaultWall;
    private Tank tank1, tank2;
    private BreakableWall breakWall;
    private NormalWall normWall;
    private Controller tankControls;
    private Timer timer;
    private KeyMapping player1, player2;

    public GameWorld(){
        loadImages();
        initKeyMapping();
        initTanks();
        this.setFocusable(true);
        initTimer();
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = background.getWidth();
        int height = background.getHeight();
        int wallwidth = defaultWall.getWidth();
        int wallheight = defaultWall.getHeight();
        for(int y = 0; y<getHeight(); y+=height){
            for(int x = 0; x<getWidth();x+=width){
                g2d.drawImage(background, x, y, this);
            }    
        }
        for(int y = 0; y<getHeight();y+=wallheight){
           for(int x = 0; x<getWidth();x+=wallwidth){
               if(y == 0){
                   g2d.drawImage(normWall.getImage(),x,y,this);
               }
           }
        }
        g2d.drawImage(tank1.getImage(),tank1.getX(),tank1.getY(),null);
        g2d.drawImage(tank2.getImage(),tank2.getX(),tank2.getY(),null);
        g2d.drawImage(breakWall.getImage(),breakWall.getX(),breakWall.getY(),null);
        g2d.drawImage(normWall.getImage(),normWall.getX(),normWall.getY(),null);
       // g2d.dispose();
    }
    private void loadImages(){
        try{
            background = ImageIO.read(new File("src/Resources/Background.bmp"));
            tankImage1 = ImageIO.read(new File("src/Resources/Tank1.gif"));
            tankImage2 = ImageIO.read(new File("src/Resources/Tank2.gif"));
            breakableWall = ImageIO.read(new File("src/Resources/Wall1.gif"));
            defaultWall = ImageIO.read(new File("src/Resources/Wall2.gif"));
        }
        catch(IOException e){
            System.out.println("File Not found");
        }
        breakWall = new BreakableWall(500,600,breakableWall);
        normWall = new NormalWall(600,500,defaultWall);
    }
    private void initTanks(){
        tank1 = new Tank(100,400,(short) 0, tankImage1,player1);
        tank2 = new Tank(700,400,(short) 0,tankImage2,player2);
        tankControls = new Controller();
        addKeyListener(tankControls.getKeyAdapter());
        this.tankControls.addObserver(tank1);
        this.tankControls.addObserver(tank2);
    }
    private void initTimer(){     
        timer = new Timer(1000/144, (ActionEvent e) -> {
            GameWorld.this.repaint();
        });
    }    
    private void initKeyMapping(){
        player1 = new KeyMapping(KeyEvent.VK_UP,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_ENTER);
        player2 = new KeyMapping(KeyEvent.VK_W,KeyEvent.VK_D,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_SPACE);
    }    
}
