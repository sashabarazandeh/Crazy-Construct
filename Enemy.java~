/**
 * Enemy.java
 * @author George and Sasha
 * 2020-1-20
 */
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;

public class Enemy implements GameConstants,Movement {
    private int x, y; 
    private Rectangle hitBox;
    private BufferedImage sprite;
    private int width, height;
    private boolean animated;
    private int currentSprite;   
    private int speed;
    private String direction;//direction enemy is facing
    private BufferedImage [][] spritesArray;//2d array each row represents the direction, each column being a seperate image for that direction
    private  int [] speedArray = {ENEMY_SPEED_1,ENEMY_SPEED_2,ENEMY_SPEED_3,ENEMY_SPEED_4,ENEMY_SPEED_5};
    
    /* Enemy
     * @param int x, starting x cooridinate for enemy
     * @param int y, starting y cooridinate for enemy
     *  Constructor for enemy object
     */
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.spritesArray = new BufferedImage[4][3];
        loadSprites();
        this.animated = false;
        this.currentSprite = 0;
        this.direction = "down"; 
        this.sprite = this.spritesArray[DOWN][0];
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
        this.speed = ENEMY_SPEED_1;
        this.hitBox = new Rectangle(x, y, width, height);
    }
    
//-----------------------------------------------      
    /* loadSprites
     * @author George
     *  Method that loads sprite images for the game
     */
    private void loadSprites(){
        String [] directions = {"left","right","up","down"};
        try {
            for(int r = 0; r < spritesArray.length; r++){
                for(int c = 0; c < spritesArray[r].length; c++){
                    this.spritesArray[r][c] = ImageIO.read(new File("images/enemy/"+directions[r]+c+".png"));
                }
            }
        }catch(Exception e) {System.out.println("error loading sprite");};
    }
//-----------------------------------------------  
    /* seekPlayer
     * @author George and Sasha, George responsible for movement and setting direction, Sasha coded checking wall collision
     * @param Player player
     * @param Map map
     *  method that makes enemy move accordingly due to the players position
     */
    public void seekPlayer(Player player, Map map) {
        int diffXDistance = this.x - player.getX();
        int diffYDistance = this.y - player.getY();
        if (diffXDistance  > 0) {// enemy x > playerx meaning enemy is to the right of player and needs to move left
            moveLeft();
            if ((player.checkPlayerWallCollision(this.hitBox)) || (map.checkCollidedWithWall(this.hitBox))) {
                moveRight();
            }
            setDirection("left");
        }
        else if(diffXDistance < 0){// enemy x < player x meaning enemy is to the left of player and needs to move right
            moveRight();
            if ((player.checkPlayerWallCollision(this.hitBox)) || (map.checkCollidedWithWall(this.hitBox))) {
                moveLeft();
            }
            setDirection("right");
        }
        if (diffYDistance  > 0) {// enemy Y > playery meaning enemy is below player
            moveUp();
            if ((player.checkPlayerWallCollision(this.hitBox)) || (map.checkCollidedWithWall(this.hitBox))) {
                moveDown();
            }
            setDirection("up");
        }
        else if(diffYDistance < 0){// vice versa meaning player is below enemy
            moveDown();
            if ((player.checkPlayerWallCollision(this.hitBox)) || (map.checkCollidedWithWall(this.hitBox))) {
                moveUp();
            }
            setDirection("down");
        }
        else{
            setAnimated(true);
        }
    }
    /* checkCollidedWith
     * @author Sasha
     * @param Reactangle t
     * @return boolean
     *  Method that checks if enemy hitbox collides with rectangle t
     */
    public boolean checkCollidedWith(Rectangle t) {
        return (t.intersects(this.hitBox));
    }
    
    /* spawn
     * @author George
     *  Method that spawns the enemy in his spawn location and updates his hitbox along with it
     */
    public void spawn(){
        this.setX(ENEMY_SPAWN_X);
        this.setY(ENEMY_SPAWN_Y);
        this.updateHitBox();
    }
    
    /* moveLeft
     * @author George
     *  Method that makes the enemy move left by subtracting his speed from his x coordinate
     */
    public void moveLeft(){
        this.x = this.x - speed;
        updateHitBox();
        this.setAnimated(true);  
    }
    /* moveRight
     * @author George
     *  Method that makes the enemy move right by adding his speed to his x coordinate
     */
    public void moveRight(){
        this.x = this.x + speed;
        updateHitBox();
        this.setAnimated(true);
    }
    /* moveUp
     * @author George
     *  Method that makes the enemy move up by subtracting his speed from his y coordinate
     */
    public void moveUp(){
        this.y = this.y - speed;
        updateHitBox();
        this.setAnimated(true);
    }
    /* moveDown
     * @author George
     *  Method that makes the enemy move down by adding his speed to his y coordinate
     */
    public void moveDown(){
        this.y = this.y + speed;
        updateHitBox();
        this.setAnimated(true);
    }
//-----------------------------------------------      
    /* update
     * @author George
     *  Method that changes the enemies sprite to match his direction of movement from the 2d array
     */
    public void update(){  
        if(this.animated){
            this.currentSprite = (this.currentSprite + 1)%this.spritesArray[0].length;//length of column
            if(this.direction.equals("left")){
                this.sprite = this.spritesArray[LEFT][this.currentSprite];
            }
            else if(this.direction.equals("right")){
                this.sprite = this.spritesArray[RIGHT][this.currentSprite];
            }
            else if(this.direction.equals("up")){
                this.sprite = this.spritesArray[UP][this.currentSprite];
            }
            else if(this.direction.equals("down")){
                this.sprite = this.spritesArray[DOWN][this.currentSprite];
            }
        }
    }
    //-----------------------------------------------  
    //SETTERS
    /* setAnimated
     * @param Boolean animated
     *  Sets enemy's animated boolean value
     */
    public void setAnimated(boolean animated){
        this.animated = animated;
    }
    /* setDirection
     * @param String direction
     *  Sets enemy's direction 
     */
    private void setDirection(String direction){
        this.direction = direction;
    }
    /* setX
     * @param int x
     *  Sets enemy's x position
     */
    public void setX(int x) {
        this.x = x; 
    }
    /* setY
     * @param int y
     *  Sets enemy's y position
     */
    public void setY(int y) {
        this.y = y;
    }
    /* setSpeed
     * @param int speed
     *  Sets enemy's speed
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }
    /* updateHitBox
     *  Updates enemies hitbox
     */
    public void updateHitBox() {
        this.hitBox.setLocation(this.x, this.y);
    }
//-----------------------------------------------          
    //GETTERS
    /* getHitBox
     *  returns the enemies hitbox
     */
    public Rectangle getHitBox() {
        return this.hitBox;
    }
    /* getSpeedFromArray
     * @return int speedArray[index]
     *  returns the enemies speed from the specific index from the speedArray[]
     */
    public int getSpeedFromArray(int index){
        return this.speedArray[index];
    }
//-----------------------------------------------      
    /* draw
     * @params Graphics g
     * Draws the enemy to the screen
     */
    public void draw(Graphics g) { 
        g.drawImage(this.sprite, this.x, this.y, null);
        g.setColor(Color.red);
    }
}
