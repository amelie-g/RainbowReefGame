//author: codenmore
package Graphics;

import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage sheet;
    
    public Sprite(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage cut(int x, int y, int w, int h){
        return sheet.getSubimage(x, y, w, h);
    }
}
