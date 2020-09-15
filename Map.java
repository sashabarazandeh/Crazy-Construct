/**
 * Map.java
 * @author Sasha Barazandeh
 * 2020-01-11
 */
import java.awt.Graphics;
import java.awt.Color;
import java.util.Scanner;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;

public class Map implements GameConstants{
    private int width;
    private int height;
    private int totalCoinsLeft;
    private MapTile tile;
    private OuterWall border;
    private File textFileMap;
    private Scanner readFile;
    private MapTile [][] map;
    
    /* Map
     *  @param int xRes
     *  @param int yRes
     *  Constructor for the map class
     */
    public Map(int xRes, int yRes) {  //set map width and height according to game resolution
        this.width = xRes; 
        this.height = yRes;
    }  
    /* readMap
     * @Param String fileName
     *  Method that reads the map from the textfile
     */
    public void readMap(String fileName) {
        int mapWidth = 0;
        int mapLength = 0; //this is just to initialize them to avoid the 'may not haveb een initialized error'
        //Create and instantiate textfile
        try {
            textFileMap = new File(fileName+".txt");
            readFile = new Scanner (textFileMap);  
        }//end try
        catch (FileNotFoundException e) {
            System.out.println("Caught it!");
        }
        //Iterate the textfile
        while (readFile.hasNext()) {
            //read each line and process each line 
            mapLength++;
            mapWidth = getWidth(readFile.nextLine());
        }
        readFile.close();
        map = new MapTile[mapLength][mapWidth];
        //open file again
        try{
            readFile = new Scanner(textFileMap);
        }catch (Exception e){
            System.out.println("Caught it!");
        }
        int row = 0; //temporary integer value for the row to process each line
        while (readFile.hasNext()) { 
            processMapLine(row, readFile.nextLine());
            row++;
        }
    }
    
    /* findWidth
     *  @returns int line.length()
     *  returns width
     */
    private int getWidth(String line) {
        return line.length();
    }
    
    /* CountLeft method
     * @Author Sasha Barazandeh
     * @param MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player
     * This method counts the amount of tiles in the left direction of the player, stopping at barriers and enemy
     */
    public int countLeft(MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player) {
        int count = 0;
        boolean endCount = false; // Value is used to end the counting loop
        while (endCount == false) {
            if ((enemy.checkCollidedWith(t.getRect())) || (player.checkPlayerWallCollision(t.getRect()))) {
                endCount = true;
                count-= 2; // THIS MUST BE HERE in order to compensate for enemy speed. Since the enemy speed moves at a rate of less than 20, the collision
                // will not detect that the enemy is there, since at the time of counting tiles, the enemy's hitbox does not yet intersect with anything and
                // as a result cannot be found. This means that we must subtract 2 tiles just incase so as to not spawn a block over the enemy.
            }
            if(!(t instanceof OuterWall))  {
                count++;
                xPosInArray--;
                t = map[xPosInArray][yPosInArray];
            }else {
                endCount = true;
                count--; // accounts for the players current position. as you cannot build ON the player
            }
        }
        return count;
    }
    
       /* CountRight method
     * @Author Sasha Barazandeh
     * @param MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player
     * This method counts the amount of tiles in the rght direction of the player, stopping at barriers and enemy
     */
    public int countRight(MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player) {
        int count = 0;
        boolean endCount = false;
        while (endCount == false) {
            if ((enemy.checkCollidedWith(t.getRect())) || (player.checkPlayerWallCollision(t.getRect()))) {
                endCount = true;
                count-= 2;
            }
            if (!(t instanceof OuterWall)) {
                count++;
                xPosInArray++;
                t = map[xPosInArray][yPosInArray];
            }else {
                endCount = true;
                count--;
            }
        }
        return count;
    }
       /* CountUp method
     * @Author Sasha Barazandeh
     * @param MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player
     * This method counts the amount of tiles in the up direction of the player, stopping at barriers and enemy
     */
    public int countUp(MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player) {
        int count = 0;
        boolean endCount = false;
        while(endCount == false) {
            if ((enemy.checkCollidedWith(t.getRect())) || (player.checkPlayerWallCollision(t.getRect()))) {
                endCount = true;
                count-= 2;
            }
            if (!(t instanceof OuterWall)) {
                count++;
                yPosInArray--;
                t = map[xPosInArray][yPosInArray];
            }else {
                endCount = true;
                count--;
            }
        }
        return count;
    }
       /* CountDown method
     * @Author Sasha Barazandeh
     * @param MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player
     * This method counts the amount of tiles in the down direction of the player, stopping at barriers and enemy
     */
    public int countDown(MapTile t, int xPosInArray, int yPosInArray, Enemy enemy, Player player) {
        int count = 0;
        boolean endCount = false;
        while (endCount == false) {
            if ((enemy.checkCollidedWith(t.getRect())) || (player.checkPlayerWallCollision(t.getRect()))) {
                endCount = true;
                count-= 2;
            }
            if (!(t instanceof OuterWall))  {
                count++;
                yPosInArray++;
                t = map[xPosInArray][yPosInArray];
            }else {
                endCount = true;
                count--;
            }
        }
        return count;
    }
    
       /* CountTiles method
     * @Author Sasha Barazandeh
     * @param Enemy enemy, Player player
     * @returns amount of tiles in that direction
     * This method determines the position of the player so counting tiles can commence
     */  
    public int countTiles(Enemy enemy, Player player) {
        int tilesNum = 0;
        for (int i = 0; i < this.map.length; i++) {
            for (int k = 0; k < this.map[0].length; k++) {
                if ((map[i][k].getRect()).intersects(player.getHitBox())) {
                    if (player.getDir().equals("left")){
                        tilesNum = countLeft(map[i][k], i, k, enemy, player);
                    }else if(player.getDir().equals("right")) {
                        tilesNum = countRight(map[i][k], i, k, enemy, player);
                    }else if(player.getDir().equals("up")) {
                        tilesNum = countUp(map[i][k], i, k, enemy, player);
                    }else {
                        tilesNum = countDown(map[i][k], i, k, enemy, player);
                    }
                }
            }
        }
        return tilesNum;  
    }
    /*
     * processMapLine
     * @author Sasha Barazandeh
     * @param int row, String line
     * This method takes in a rowline from the textfile, and iterates through it to determine what type of tile should be placed in the 2D map array
     */
    public void processMapLine(int row, String line) {
        for (int i = 0; i < line.length(); i++) {  
            if (line.charAt(i) == WALL) {
                map[row][i] =  new OuterWall(row*this.BOX_W, i*this.BOX_H);
            }else if (line.charAt(i) == WALKABLE) {
                map[row][i] = new MapTile(row*this.BOX_W, i*this.BOX_H, false);
            }else if (line.charAt(i) == COIN) {
                map[row][i] = new MapTile(row*this.BOX_W, i*this.BOX_H, true);
                this.totalCoinsLeft++; //Determines how many coin blocks are in the map
            }
        }
    }
    
 //-----------------------------------------------     
    //getters
    /* totalCoinsLeft
     * @author Sasha Barazandeh
     * @returns int totalCoinsLeft
     */
    public int totalCoinsLeft() {
        return this.totalCoinsLeft;
    }
//-----------------------------------------------   
    /* checkCollidedWithWall
     * @author Sasha Barazandeh
     * @param Rectangle otherHitBox
     * @returns true or false if intersection occured
     * This method determines if a given hitbox has collided with a wall barrier on the map
     */
    public boolean checkCollidedWithWall(Rectangle otherHitBox) {
      for (int i = 0; i < map.length; i++) {
            for (int z = 0; z < map[i].length; z++) {
                if((map[i][z] instanceof OuterWall)){
                    if ((map[i][z].getRect().intersects(otherHitBox))) {       
                        return true;
                    }
                }
            }
        } 
        return false;
    }
    /* checkCoinCollected
     * @author Sasha Barazandeh
     * @param Rectangle playerHitBox
     * This method determines if the player has stepped on a coin block in the map, then deletes it from the screen to show the player has collected it
     */
    public void checkCoinCollected(Rectangle playerHitBox) {
        for (int i = 0; i < this.map.length; i++) {
            for (int k = 0; k < this.map[0].length; k++) {
                if(map[i][k] != null){
                    if (((map[i][k].getRect()).intersects(playerHitBox)) && (map[i][k].getIfHasCoin())) {//player hitbox is gone for a second edit later
                        map[i][k].removeCoin();
                        this.totalCoinsLeft--;
                    }
                }
            }
        }
    }
    
//-----------------------------------------------  
    /* draw
     * @param Graphics g
     * This method goes through a nested for loop to draw each maptile on the screen
     */
    public void draw(Graphics g) {
        for(int i = 0; i < map.length; i++) {
            for(int z = 0; z < map[0].length; z++) {
                map[i][z].draw(g);
            }
        }
    }//end draw();
}
