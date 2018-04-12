/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author mmmos
 */
public class mainMenu extends JPanel {
    
    private BufferedImage background;
    
    
    public mainMenu(){
        loadImages();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = background.getWidth();
        int height = background.getHeight();
        for(int y = 0; y<getHeight(); y+=height){
            for(int x = 0; x<getWidth();x+=width){
                g2d.drawImage(background, x, y, this);
            }    
        }
        g2d.dispose();
    }
    private void loadImages(){
        try{
            background = ImageIO.read(new File("src/Resources/Title.bmp"));
        }
        catch(IOException e){
            System.out.println("File Not found");
        }
    }
}
