package Reef;
import Graphics.LoadGraphics;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Reef.KeyControlP1;

public class Block {
    static int w = 40, h = 20;
    protected int x, y;
    protected BufferedImage i;
    
public Block(BufferedImage i, int x, int y){
    this.i = i; 
    this.x = x; 
    this.y = y;
}
    
public Rectangle getBounds() {
    return new Rectangle(x, y, w, h);
}

public boolean collides(Rectangle rec) {
    return getBounds().intersects(rec);
}    

public int getX(){
    return this.x;
}
    
public int getY(){ 
    return this.y;
}

public void setX(int newX){
    this.x = newX;
}

public void setY(int newY){
    this.y = newY;
}
}
