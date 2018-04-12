package TankWars;


import javax.swing.JButton;
import javax.swing.JFrame;

 /*
 * @author Alnguye
 * 3/21/18
 */
public class GamePlay extends JFrame  {
    
    private JButton startGame;

    public void gameStart(){
        setTitle("Tank Game");
        setSize(1200,1000);
        //add(new mainMenu());
        add(new GameWorld());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        //this.setFocusable(true);
    }
    
    
    public static void main(String args[]){
        GamePlay newGame = new GamePlay();
        newGame.gameStart();
        
    }
}
