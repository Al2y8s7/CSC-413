package TankWars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Alvin Nguyen & Moses Martinez
 */
public class Main extends JFrame {
    
    protected final static int WINDOW_WIDTH = 1312;
    protected final static int WINDOW_HEIGHT = 832;

    public static void main(String[] args) {
	Main newGame = new Main();
	newGame.gameStart();

    }

    public void gameStart() {
	//set game window
	setTitle("Tank Wars");
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	setLocation(new Point(200, 200));
	add(new GameWorld());
	setResizable(false);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

    }
}