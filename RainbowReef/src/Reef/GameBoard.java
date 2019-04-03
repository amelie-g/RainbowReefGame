
package Reef;

import Graphics.LoadGraphics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class GameBoard {
    public int x, y, w = 640, h = 480;
    int rows = 5;
    int columns = 16;
    
    public ArrayList<Block> gameBlocks = new ArrayList<Block>();
        
    BufferedImage bi[] = {LoadGraphics.block1, LoadGraphics.block2, LoadGraphics.block3, LoadGraphics.block4, LoadGraphics.block5};
    

    private void createGameBoard() {
        for(x = 40; x < 640; x+=160){
            for(y = 0; y < 80; y+=80){
                addBlock(LoadGraphics.octopus, x, y);
            }
        }
        // block configuration
        for(int x = 0; x < 640; x += 40) {
            for(int y = 80; y < 200; y += 20) {
                Random rand = new Random();
                int biIndex = rand.nextInt(5);
                addBlock(bi[biIndex], x, y);
            }
        }
    }
    
    public GameBoard(){
        createGameBoard();
    }

    public boolean blockCollision(int x, int y) {
        // pass in star to see if block collision
        for (Block b: gameBlocks) {
            if((b.getBounds()).intersects(new Rectangle(x, y, 35, 35))){
                return true;
            }
        }
        return false;
    }
    
    public void evictBlocks(int x, int y) {
        ArrayList<Block> toRemove = new ArrayList<Block>();
        for (Block b: gameBlocks) {
            if((b.getBounds()).intersects(new Rectangle(x, y, 35, 35))){
                toRemove.add(b);
            }
        }
        for (Block b : toRemove) {
            removeBlock(b); 
        }
    }

    private void paintBlock(Graphics g, Block b) {
        g.drawImage(b.i, b.getX(), b.getY(), null);
    }
    
    public void paintBlocks(Graphics g) {
        for (Block b : gameBlocks) {
            paintBlock(g, b);
        }
    }

    public void addBlock(BufferedImage i, int x, int y){
        gameBlocks.add(new Block(i, x, y));
    }

    public void removeBlock(Block b){
        gameBlocks.remove(b);
        if(gameBlocks.isEmpty()){
            JOptionPane.showMessageDialog(null, "You Won!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

}
    
    
