/**
 * Player.java
 * George and Sasha
 * 2020-1-20
 */

import java.util.ArrayList;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;

class Player implements GameConstants,Movement{ 
    private ArrayList<PlayerWall> buildingBlocks;
    private int x, y; 
    private Rectangle hitBox;
    private BufferedImage sprite;
    private int width, height;
    private boolean animated;
    private int currentSprite;   
    private String direction;
    private BufferedImage [][] spritesArray;
    private int speed;
    private PlayerKeyListener keyListener;
    
    /* Player
     * @param int x
     * @param int y
     * @param String fileName
     * @param String type
     *  Constructor for Player class that creates an instance of the player and sets the player stats
     */
    public Player(int x, int y, String fileName, String type) { 
        this.x=x;
        this.y=y;
        this.spritesArray = new BufferedImage[4][4];
        loadSprites(fileName,type);
        this.animated = false;
        this.currentSprite = 0;
        this.sprite = this.spritesArray[DOWN][this.currentSprite];
        this.direction = "down"; 
        this.width =  BOX_W; 
        this.height = BOX_H; 
        this.speed = PLAYER_SPEED;
        this.hitBox = new Rectangle(x, y, width, height);
        this.buildingBlocks = new ArrayList<PlayerWall>();//arraylist used for constructing walls
    }
//----------------------------------------   
    /* loadSprites
     * @author George
     * @param String fileName
     * @param String fileType
     *  Loads sprites images for the player and stores it in a 2d array
     */
    private void loadSprites(String fileName, String fileType) {
        String [] directions = {"left","right","up","down"};
        try {
            for(int r = 0; r < spritesArray.length; r++){
                for(int c = 0; c < spritesArray[r].length; c++){
                    this.spritesArray[r][c] = ImageIO.read(new File(fileName+directions[r]+c+"."+fileType));
                }
            }
        }
        catch(Exception e) {System.out.println("error loading sprite");};
    }
    //-----------------------------------------------
    /* checkPlayerWallCollision
     * @author Sasha
     * @param Rectangle otherHitBox
     *  Method that checks if player's hitbox collides with the parameter rectangle and returns a boolean
     */
    public boolean checkPlayerWallCollision(Rectangle otherHitBox) {
        for (int i = 0; i < this.buildingBlocks.size(); i++) {
            if (otherHitBox.intersects(buildingBlocks.get(i).getRect())) {
                return true;
            }
        }
        return false;
    }
    /* checkIntersection
     * @author Sasha
     * @param Rectangle otherHitBox
     *  Method that checks if player intersects with the other hitbox and returns a boolean
     */
    public boolean checkIntersection(Rectangle otherHitBox) {
        if ((otherHitBox == null) || (this.hitBox == null)) {
            return false;
        }else {
            if ((this.hitBox).intersects(otherHitBox)) {
                return true;
            }else {
                return false;
            }
        }
    }
    //----------------------------------------     
    //Sasha
    /* constructLeft
     * @param int tileNum
     * This method adds tileNum amount of new PlayerWalls to the buildingBlocks ArrayList in the left direction
     */
    private void constructLeft(int tileNum) {
        int tempX = this.x; //temporary values for the x and y for blocks going FROM players x and y position
        int tempY = this.y;
        for(int i = 0; i < tileNum; i++) {  
            tempX -= SPACE_BETWEEN_BLOCKS;
            buildingBlocks.add(new PlayerWall(tempX, tempY));
        }
    }
    /* constructRight
     * @param int tileNum
     * This method adds tileNum amount of new PlayerWalls to the buildingBlocks ArrayList in the right direction
     */
    private void constructRight(int tileNum) {
        int tempX = this.x;
        int tempY = this.y;
        for(int i = 0; i < tileNum; i++) {
            tempX += SPACE_BETWEEN_BLOCKS;
            this.buildingBlocks.add(new PlayerWall(tempX, tempY));
        }
    }
    /* constructUp
     * @param int tileNum
     * This method adds tileNum amount of new PlayerWalls to the buildingBlocks ArrayList in the upwards direction
     */
    private void constructUp(int tileNum) {
        int tempX = this.x;
        int tempY = this.y;
        for(int i = 0; i < tileNum; i++) {
            tempY -= this.speed;
            this.buildingBlocks.add(new PlayerWall(tempX, tempY));
        }
    }
    /* constructDown
     * @param int tileNum
     * This method adds tileNum amount of new PlayerWalls to the buildingBlocks ArrayList in the downwards direction
     */
    private void constructDown(int tileNum) {
        int tempX = this.x;
        int tempY = this.y;
        for(int i = 0; i < tileNum; i++) {
            tempY += this.speed;
            this.buildingBlocks.add(new PlayerWall(tempX, tempY));
        }
    }
//----------------------------------------        
    //Sasha
    /* construct 
     * @param int tileNum
     * This determines the direction in which to build the blocks, which is dependent on the direction the player is currently facing
     */
    public void construct(int tileNum) {
        if ((this.direction).equals("left")){
            constructLeft(tileNum);
        }else if((this.direction).equals("right")) {
            constructRight(tileNum);
        }else if((this.direction).equals("up")) {
            constructUp(tileNum);
        }else {
            constructDown(tileNum);
        }
    }
    
    //Sasha
    /* deconstruct
     * This method clears the arraylist of buildingBlocks, causing it to disappear when drawn on screen
     */
    public void deconstruct() {
        this.buildingBlocks.clear();
    }
//----------------------------------------        
    //George
    public void spawn(){
        this.deconstruct();
        this.setX(PLAYER_SPAWN_X);
        this.setY(PLAYER_SPAWN_Y);
        this.updateHitBox();
    }
//----------------------------------------    
    //GETTERS
    
    /* getHitBox
     * @return Rectangle hitBox
     * Returns the hitbox of the player
     */
    public Rectangle getHitBox() {
        return this.hitBox;
    }
    
    /* getX
     * @returns int x
     * Method returns the players x coordinate value
     */
    public int getX(){
        return this.x;
    }
    
    /* getY
     * @return int y value
     * Returns the current players y coordinate value
     */
    public int getY(){
        return this.y;
    }    
    
    /* getWidth
     * @return int width
     * Returns the width of the player
     */
    public int getWidth(){
        return this.width;
    }
    
    /* getHeight
     * @return int height
     * Returns the height of the player
     */
    public int getHeight(){
        return this.height;
    }
    
    /* getDir
     * @returns String direction
     * Gets the current direction the player is facing
     */
    public String getDir() {
        return this.direction;
    }
//-----------------------------------------------  
    //SETTERS
    
    /* setX
     * @param int x
     * sets x value to given parameter
     */
    public void setX(int x){
        this.x = x;
    }
    
    /* setY
     * @param int y
     * sets y value to given paramter
     */
    public void setY(int y){
        this.y = y;
    }
    
    /* set
     * @param int speed
     * sets speed of player to given int parameter
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    /* setDir
     * @param String direction
     * sets current direction player is facing to given direction paramter
     */
    public void setDir(String direction){
        this.direction = direction;
    }
    
    /* setAnimated
     * @param boolean animated
     * sets current state of boolean value to given parameter for animation
     */
    public void setAnimated(boolean animated){
        this.animated = animated;
    }
    //Sasha
    /* updateHitBox
     * This method updates the players hitbox to whatever the current x and y value are
    */
    public void updateHitBox() {
        this.hitBox.setLocation(this.x, this.y);
    }
//----------------------------------------        
    /* moveLeft
     * @author George
     *  Method that moves the player to the left and makes them animated, while also updating their hitbox
     */
    public void moveLeft(){
        this.x = this.x - speed;
        updateHitBox();
        this.setAnimated(true);
    }
    /* moveRight
     * @author George
     *  Method that moves the player to the right and makes them animated, while also updating their hitbox
     */
    public void moveRight(){
        this.x = this.x + speed;
        updateHitBox();
        this.setAnimated(true);
    }
    /* moveUp
     * @author George
     *  Method that moves the player up and makes them animated, while also updating their hitbox
     */
    public void moveUp(){
        this.y = this.y - speed;
        updateHitBox();
        this.setAnimated(true);
    }
    /* movedown
     * @author George
     *  Method that moves the player down and makes them animated, while also updating their hitbox
     */
    public void moveDown(){
        this.y = this.y + speed;
        updateHitBox();
        this.setAnimated(true);
    }
//----------------------------------------   
    /* update
     * @author George
     *  Method that updates the players sprite as long as animated is set to true
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
    /* draw
     *  Method that draws the player
     */
    public void draw(Graphics g) { 
        g.drawImage(this.sprite, this.x, this.y, null);
        for (int i = 0; i < buildingBlocks.size(); i++) {
            (buildingBlocks.get(i)).draw(g);
        }
    }
    //----------------------------------------       
    /* PlayerWall
     * @author Sasha
     *  Nested class that extends wall class
     */
    private class PlayerWall extends MapTile implements GameConstants{
      /* PlayerWall constructor
       * @param int x, int y
       * This constructor creates a new player wall with given x and y coordinates and uses the gameconstants for all other values
       */
      public PlayerWall(int x, int y) {
            this.x =x;
            this.y = y;
            this.width = BOX_W;
            this.height = BOX_H;
            loadImg();
            this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);   
        }
      
      /* loadImg
       * This method loads the image for a PlayerWall to be shown on the screen
       */
        public void loadImg() {
            try {
                this.img = ImageIO.read(new File("images/ice.png"));
            }catch(Exception e){System.out.println("Error loading image in PLAYERWALL");}
        }
//-----------------------------------------------
        /* draw
         * @param Graphics g
         * Draws the PlayerWall to scren
         */
        public void draw(Graphics g) {
            g.setColor(Color.blue);
            g.drawImage(this.img, this.x, this.y, null);
        }
    }//PlayerWall.java
}//Player.java

