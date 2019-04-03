
package Reef;
/*source code from Plane Game*/
 
import Graphics.LoadGraphics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.event.ActionListener;


public class KeyControlP1 extends JPanel implements KeyListener, ActionListener{
    int w, h, x = 440, y = 450; 
    public boolean running = false;
    public int paddleX = 440;
    public int starX = 100;
    public int starY = 400;
    public int starXDirection = -2;
    public int starYDirection = -2;
    
    public KeyControlP1() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateStar();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(LoadGraphics.shell, paddleX, y, null);
        g2.drawImage(LoadGraphics.starfish, starX, starY, null);
    }
    
    public void left(){
        running = true;
        paddleX -= 20;
    }
    
    public void right(){
        running = true;
        paddleX += 20;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key Pressed");
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_LEFT){
             left();
             if(paddleX <= 8)
               paddleX = 8;
          
        }
        if(code == KeyEvent.VK_RIGHT){
            right();
            if(paddleX >= 560)
                paddleX = 560;
            
        }
        if(code == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
    
    public void updateBlockCollision() {
        starYDirection = -starYDirection;
    }
    
    private void updateStar() {
        if(running){
            if(new Rectangle(starX, starY, 35, 35).intersects(new Rectangle(paddleX, y, 80, 30))){
                starYDirection = -starYDirection;
            }
            starY += starYDirection;
            starX += starXDirection;
            if(starY < 0)
                starYDirection = -starYDirection;
            if(starY > 480)
                starYDirection = -starYDirection;
            if(starX < 0)
                starXDirection = -starXDirection;
            if(starX > 605)
                starXDirection = -starXDirection;
        }
    }
    
    public boolean starOutOfScope() {
        if(starY > 480) {
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}