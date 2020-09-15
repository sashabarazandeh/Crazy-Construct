/**
 * CrazyConstruct.java
 * @author Sasha Barazandeh and George Li
 * @2020/01/20
 * JPanel where we draw and update the characters to the screen
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.image.BufferedImage; 
import java.awt.CardLayout;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.Font;

class GamePanel extends JPanel implements GameConstants{
    private GameTimer timer;//timer keeps track of how much time the player has
    //creating character objects
    private Enemy enemy;
    private Player player;
    //keylistener
    private PlayerKeyListener keyListener;
    //for loading map and levels
    private Map map;
    private String mapName;
    private int level;
    private MapStorage storage;
    //used for cardlayout which switches screens when player wins or dies
    private CardLayout cl;
    private JPanel container;
    //condition for gameloop to run
    private boolean gameOver;
    //game music
    private Clip gameTheme;
    //font used for graphics
    private Font hudFont;
    
    /* GamePanel
     * @param JPanel container
     *  Constructor for the GamePanel that starts the game loop
     */
    GamePanel (JPanel container) {
        //game object initialization
        this.timer = new GameTimer();
        this.timer.start();
        this.level = 1;
        this.player = new Player (PLAYER_SPAWN_X,PLAYER_SPAWN_Y , "images/player/", "png");
        this.enemy = new Enemy(ENEMY_SPAWN_X, ENEMY_SPAWN_Y);
        this.storage = new MapStorage(level);//instantiate MapStorage
        this.mapName = storage.chooseMap(); //Choose txt file name
        this.map = new Map(GAME_W, GAME_H); //instantiate Map
        this.gameOver = false;
        this.cl = (CardLayout)(container.getLayout());
        this.container = container;
        //set font
        this.hudFont = new Font("Lucida Console", Font.BOLD, 26);//set font for the hud
        
        //read the level and load
        map.readMap(mapName);//load in map for that specific txtfile
        
        //attach key and mouse listeners to the game panel
        keyListener = new PlayerKeyListener(player, map, enemy);
        this.addKeyListener(keyListener);
        
        this.setFocusable(true);      //make this window the active window
        this.requestFocusInWindow(); 
        
        //load game music
        loadGameTheme();
        
        //start the game loop in a separate thread (allows simple frame rate control)
        Thread t = new Thread(new Runnable() { public void run() {gameLoop(); }}); //start the gameLoop 
        t.start();
    }
    //-----------------------------------------------  
    /* gameLoop
     * @author George and Sasha
     *  Game loop where game runs
     */
    private  void gameLoop() { 
        //map.readMap(mapName);
        long currentTime;
        long previousTime = System.nanoTime();
       
        while(!gameOver){
            currentTime = System.nanoTime();
            if((currentTime-previousTime)/1000000.0 > SPRITE_UPDATE_MS){
                player.update();//update player
                enemy.update();//update enemy
            }
            previousTime = currentTime;
            
            playMusic();//if music isn't playing will restart the song
            
            enemy.seekPlayer(player, map);//seek player and follow him
            
            //check if all coins are collected so player can advance levels
            if (map.totalCoinsLeft() == 0){
                level++;//increment level difficulty
                if (level <= LAST_LEVEL) {
                    nextMapSequence();//load next map
                }
            }
            //ending screens
            //If player is killed by enemy
            if (playerKilled(player, enemy) || timesUp() ) {
                timer.cancel();
                this.gameOver = true;
                this.removeKeyListener(keyListener);
                cl.show(container,"2");//switch to game over screen
            }else if (gameWon()) { //if player wins the game                
              this.gameOver = true;
              this.removeKeyListener(keyListener);
                cl.show(container,"3");//show victory screen card#3
            }            //repaint the window
            this.repaint();
            //wait for the frame to be seen by the user before refreshing
            try{ Thread.sleep(FRAME_TIME);
            }catch (Exception exc){System.out.println("Thread Error");}
        }
        this.gameTheme.stop();//stop playing game music once loop is exited
    }
    //-----------------------------------------------
    /* loadGameTheme
     * @author George
     * method that loads the game music
     */
    private void loadGameTheme(){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("sounds/gameTheme.wav"));
            this.gameTheme = AudioSystem.getClip();
            this.gameTheme.open(audioStream);
        }
        catch(Exception e) {System.out.println("error loading sound");};        
    }
    
    /* playMusic
     *  Method that checks if the music has stopped and replays the game theme
     */
    private void playMusic() {
        if (!this.gameTheme.isRunning()) {
            this.gameTheme.stop();              // stop the sound effect if still running
            this.gameTheme.flush();             // clear the buffer with audio data
            this.gameTheme.setFramePosition(0); // prepare to start from the beginning
        }
        this.gameTheme.start(); // start theme of game
    }
    //-----------------------------------------------
    /* nextMapSequence
     *  Loads the next level and respawns the player and enemy resetting their locations, also increases enemies speed as level increases
     */
    private void nextMapSequence() {
        player.spawn();
        enemy.spawn();
        enemy.setSpeed(enemy.getSpeedFromArray(this.level-1)); //change speed according to level
        storage.updateLevel(level); 
        mapName = storage.chooseMap();
        map.readMap(mapName);
    }
    //-----------------------------------------------
     /* gameWon
      * @returns boolean value if game is won or not
     *  Method returns true if your current level is greater than the last indicating that you beat the last level and false elsewise
     */
    private boolean gameWon(){
        return (this.level> LAST_LEVEL);//once you pass level 5 you win
    }
    /* playerKilled
      * @returns boolean value if player has been killed
     *  Method returns true if player's hitbox has intersected with the enemy meaning he died
     */
    private boolean playerKilled(Player player, Enemy enemy){
        return player.checkIntersection(enemy.getHitBox()) ;
    }
    /* timesUp
      * @returns boolean value if time is up or not
     *  Method returns true if you have no more time left and false if it hasn't
     */
    private boolean timesUp(){
        if(this.timer.getTimeLeft() <= 0){
            return true;
        }
        else {
            return false;
        }
    }
    //-----------------------------------------------  
    /* paintComponent
      * @param Graphics g
     *  Draws the map, player, enemy and hud to screen
     */
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);          //required
        setDoubleBuffered(true); 
        //screen is being refreshed - draw all objects
        g.setColor(Color.black);//make background black
        g.fillRect(0, 0, GAME_W, GAME_H);
        map.draw(g);
        player.draw(g); 
        enemy.draw(g);
        g.setFont(hudFont);//set font to new font
        g.setColor(Color.pink);
        g.drawString("Level: "+ level, 550, 50);
        g.drawString("Time left:"+timer.getTimeLeft()+"s", 550,100);
        g.drawString("Coins left:" + map.totalCoinsLeft() , 550, 150);
    }
}

