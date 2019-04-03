package Reef;
 
import Graphics.LoadGraphics;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game implements Runnable{
    private Thread thread;
    private Display display;
    Graphics2D g2;
    private BufferStrategy b;
    private Graphics g;
    private KeyControlP1 keyControlP1;
    private GameBoard gameBoard;
    int speed = 1, move = 0;
    int w = 640, h = 480;
    String t;
    boolean isDead = false;
    public boolean running = false;
    int score = 0;
    JLabel printScore;
   
    
public Game(String t, int w, int h){
    this.w = w;
    this.h = h;
    this.t = t;
    initialize();
}

private void initialize() {
    LoadGraphics.init();
    keyControlP1 = new KeyControlP1();
    gameBoard = new GameBoard(); 
    printScore = new JLabel("Score: 0");
    printScore.setVisible(true);
}

public synchronized void start(){
    running = true;
    keyControlP1.running = true;
    thread = new Thread(this);
    thread.start();
}

public synchronized void stop(){
if(!running)
    return;
running = false;
try{
     thread.join();
 }   catch(InterruptedException e){
 }
}

public void init(){
    display = new Display(t, w, h);
    display.getFrame().addKeyListener(keyControlP1);
    initSound();
}
int x = 0;

private void update(){
    x += 1;
    updateStarBlockCollision();
    this.display.getJFrame().getContentPane().add(printScore, BorderLayout.NORTH);
    this.display.getJFrame().pack();
    this.display.getJFrame().setVisible(true);
    gameBoard.evictBlocks(keyControlP1.starX, keyControlP1.starY);

}

private void updateStarBlockCollision() {
    if (gameBoard.blockCollision(keyControlP1.starX, keyControlP1.starY)) {
        keyControlP1.updateBlockCollision();
        score++;
        printScore.setText("Score: " + score);
    }
}

private void render(){
    b = display.getCanvas().getBufferStrategy();
    if(b == null){
        display.getCanvas().createBufferStrategy(3);
        return;
    }
    g = b.getDrawGraphics();
    //clear
    g.clearRect(0, 0, w, h);
    //draw
    
   g.drawImage(LoadGraphics.background1, 0, 0, null);
   gameBoard.paintBlocks(g);
   
    if (keyControlP1.starOutOfScope()) {
        //https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
        Object[] possibleValues = { "Yes", "No" };
        Object selectedValue = JOptionPane.showInputDialog(null, "Continue?", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
        //System.out.println(selectedValue);
        if (selectedValue == "Yes") {
             keyControlP1.starX = 100;
             keyControlP1.starY = 400;
        } else {
            System.exit(0);
        }
    }
    keyControlP1.paintComponent(g);
    b.show();
    g.dispose();
}
    //author: codenmore
    //https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src
    @Override
    public void run(){
    init();
    
    int fps = 60;
    double timePerTick = 1000000000 / fps;
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();
    long timer = 0;
    int ticks = 0;
    
    while(running){
        now = System.nanoTime();
        delta += (now - lastTime) / timePerTick;
        timer += now - lastTime;
        lastTime = now;
        
        if(delta >= 1){
            update();
            render();
            ticks++;
            delta--;
        }   
        if(timer >= 1000000000){
            //System.out.println("Ticks and Frames: " + ticks);
            ticks = 0;
            timer = 0;
        }
    }
    stop();
}
    
     public void initSound(){
        /* 
        Source of audio input stream taken from:
        https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
        */
        
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;
        try {
            System.out.println(System.getProperty("user.dir"));
            stream = AudioSystem.getAudioInputStream(new File("Resources/GameMusic.wav"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            clip.loop(100);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public KeyControlP1 getKeyControlP1(){
        return keyControlP1;
    }
    
    public static void main(String[] args) {
        Game myGame = new Game("Amelie's Rainbow Reef", 640, 480);
        JOptionPane.showMessageDialog(null, "Welcome to Amelie's Rainbow Reef Game!", "Welcome!", JOptionPane.PLAIN_MESSAGE);
        myGame.start();
       
    }
}

  