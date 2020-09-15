/**
 * Movement Interface implemented by both Enemy and player
 * Sasha and George
 * 2019-12-23
 */
import java.awt.Graphics;
public interface Movement {
  
  abstract void moveUp();
  abstract void moveDown();
  abstract void moveLeft();
  abstract void moveRight(); 
  abstract void update();
  abstract void spawn();
  abstract void draw(Graphics g);
}
