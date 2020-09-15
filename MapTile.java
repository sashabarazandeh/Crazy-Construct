/**
 * MapTile.java
 * Sasha Barazandeh
 * 2020-01-11
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
public class MapTile implements GameConstants {
    public int x;
    public int y;
    public Rectangle hitBox;
    public int height;
    public int width;
    private boolean hasCoin;
    public BufferedImage img;
    public MapTile() {
        //Only here because outerwall cant call its own constructor without a void constructor method in the parent class
    }
    /* MapTile constructor
     * @param int xPos, int yPos, boolean isCoinTile
     * This constructor takes in parameters to determine its position on the map, and whether or not it is a coin block 
     */
    public MapTile(int xPos, int yPos, boolean isCoinTile) { 
        this.x = xPos;
        this.y = yPos;
        this.hasCoin = isCoinTile;
        loadImage();
        this.width = BOX_W; 
        this.height = BOX_H; 
        this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
    }
    //Getters
    /* getRect
     * @return Rectangle hitBox
     * This method gets the hitBox of the maptile
     */
    public Rectangle getRect() {
        return this.hitBox;
    }
    /* getX
     * @return int x value
     */
    public int getX() {
        return this.x;
    }
    /* getX
     * @return int y value
     */
    public int getY() {
        return this.y;
    }
    /* getIfHasCoin
     * @return boolean value of hasCoin
     */
        public boolean getIfHasCoin() {
        return this.hasCoin; 
    }
    //-----------------------------------
        /* loadImage
         * This method determines which image the maptile should show, if its a coin block or a regular walkable block
         */
    public void loadImage() {
        if (this.hasCoin == true) {
            try {
                this.img = ImageIO.read(new File("images/coin.png"));
            }catch(Exception e) {System.out.println("Error loading image in MAPTILE");}
        }else {
            try {
                this.img = ImageIO.read(new File("images/walkable.png"));
            }catch(Exception e) {System.out.println("Error loading image in MAPTILE");}
        }
    }

    /* removeCoin
     * This method sets the state of the coin to false when the player has interacted with it, and loads the new regular walking image
     */
    public void removeCoin() {
        this.hasCoin = false;
        loadImage();
    }
    
    /* draw
     * @param Graphics g
     * This method draws the image of the mapTile
     */
    public void draw(Graphics g) {
        g.drawImage(this.img, this.x, this.y, null);
    }
}
