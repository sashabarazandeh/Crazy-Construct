/**
 * PlayerKeyListener.java
 * Sasha Barazandeh and George Li
 * 2019-01-18
 * Class for the keyboard listener - detects key actions and runs the corresponding code
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
class PlayerKeyListener implements KeyListener,GameConstants {
    //reference to items effected by keyboard actions
    private Player player;
    private Map map;
    private DeconstructWallTimer timer;
    private Enemy enemy;
    
    /* PlayerKeyListener
     * @param Player player
     * @param Map map
     * @Enemy enemy
     *  contructor for the PlayerKeyListener
     */
    PlayerKeyListener(Player player, Map map, Enemy enemy) {
        this.player = player;
        this.map = map;
        this.enemy = enemy;
    }
    //not used has to be here due to implementing keyListener
    public void keyTyped(KeyEvent e) {  
    }
    
    /* keyPressed
     * @authors Sasha and George
     * @param KeyEvent e
     *  method that checks which key is pressed and according to that button will move the player
     *  Sasha: checks collisions
     *  George: updated player sprite and responsible for moving player
     * AUTHORS NOTE: the movement in the game does not immediately become drawn, when the player moves, the hitbox
     * changes and then the game detects if the player is allowed to move in that position, and sets the hitbox accordingly which is then drawn 
     * to the screen for the user/
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) { // if space has been pressed
            player.construct(map.countTiles(enemy, player)); //construct the wall given the available tiles to build onto
            timer = new DeconstructWallTimer(PLAYER_WALL_DECONSTRUCT_TIME, player); //begin new timer for deconstruction of wall
            timer.start();
            //set up a timer for 3 seconds, then after it hits 3 seconds, call on deconstruct
        }
        if(keyCode == KeyEvent.VK_LEFT){
            player.moveLeft();    
            if (player.checkPlayerWallCollision(player.getHitBox())) { // First check collision with a PlayerWall, if true, move right
                player.moveRight();
            }
            if (map.checkCollidedWithWall(player.getHitBox())) { //check if player has collided/tried to move into a OuterWall
                player.moveRight();
            }
            player.setDir("left"); //set direction to proper position after all checks/movements
            map.checkCoinCollected(player.getHitBox()); //check to see if after this movement, a coin has been collected by the player
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            player.moveRight();
            if (player.checkPlayerWallCollision(player.getHitBox())) {
                player.moveLeft();
            }
            if (map.checkCollidedWithWall(player.getHitBox())) {
                player.moveLeft();
            }
            player.setDir("right");
            map.checkCoinCollected(player.getHitBox());
        }
        else if(keyCode == KeyEvent.VK_UP){
            player.moveUp();
            if (player.checkPlayerWallCollision(player.getHitBox())) {
                player.moveDown();
            }
            if (map.checkCollidedWithWall(player.getHitBox())) {
                player.moveDown();
            }
            player.setDir("up");
            map.checkCoinCollected(player.getHitBox());
        }
        else if(keyCode == KeyEvent.VK_DOWN){
            player.moveDown();   
            if (player.checkPlayerWallCollision(player.getHitBox())) {
                player.moveUp();
            }
            if (map.checkCollidedWithWall(player.getHitBox())) {
                player.moveUp();
            }
            player.setDir("down");
            map.checkCoinCollected(player.getHitBox());
        } 
    }   
//----------------------------------------       
    /* keyReleased
     * @author George
     * @param KeyEvent e
     *  method that checks which key is released
     */
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            player.setAnimated(false);
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            player.setAnimated(false);
        }
        else if(keyCode == KeyEvent.VK_UP){
            player.setAnimated(false);
        }
        else if(keyCode == KeyEvent.VK_DOWN){
            player.setAnimated(false);
        }
    }
}

