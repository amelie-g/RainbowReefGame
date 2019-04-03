
package Graphics;
//author: codenmore
import java.awt.image.BufferedImage;
import Graphics.Sprite;
import static Graphics.LoadImage.loadImage;

public class LoadGraphics {
   
    public static BufferedImage starfish, shell, octopus, background1, background2, wall, block1, block2, block3, block4, block5;
    
    public static void init(){
       Sprite sheet = new Sprite(loadImage("/Resources/Sprite.png"));
       background1 = sheet.cut(0, 80, 640, 480);
       starfish = sheet.cut(0, 0, 35, 35);
       shell = sheet.cut(398, 0, 80, 30);
       octopus = sheet.cut(315, 0, 80, 80);
       block1 = sheet.cut(275, 0, 40, 20);
       block2 = sheet.cut(235, 0, 40, 20);
       block3 = sheet.cut(195, 0, 40, 20);
       block4 = sheet.cut(155, 0, 40, 20);
       block5 = sheet.cut(115, 0, 40, 20);
       
   }
}
