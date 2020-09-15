/**
 * OuterWall.java
 * Sasha Barazandeh
 * 2020-01-11
 */
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;
    public class OuterWall extends MapTile {
      
      /* OuterWall constructor
       * @param int x, int y
       * This method instantiates the outerwall through its given x and y coordinates on the map
       */
      public OuterWall(int x, int y) { 
        this.x = x;
        this.y = y;
        loadImg();
        this.width = this.img.getWidth();
        this.height = this.img.getHeight();
        this.hitBox = new Rectangle(this.x, this.y, this.width, this.height);
      }
      
    /* LoadImg
     * This method loads the image for the OuterWall blocks in the map
     */
    public void loadImg() {
        try {
            this.img = ImageIO.read(new File("images/playerwall.png"));
        }catch(Exception e){System.out.println("Error loading image in OUTERWALL");}
    }
    
    /* GetImg
     * @returns BufferedImage img
     * This method gets the image of the OuterWall class
     */
    public BufferedImage getImg() {
        return this.img;
    }
}
