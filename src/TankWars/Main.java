package TankWars;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author Alnguye This class runs the game Utilizes Engine class
 */
public class Main extends JFrame {

    public static void main(String[] args) {
	Main newGame = new Main();
	newGame.gameStart();

    }

    public void gameStart() {
	//set game window
	setTitle("Tank Wars");
	setSize(1318, 832);
	setLocation(new Point(200, 200));
	add(new GameWorld());
	setResizable(false);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

    }
}
