/**
 * DeconstructWallTimer.java
 * Sasha Barazandeh 
 * 2020-01-19
 */
import java.util.Timer; 
import java.util.TimerTask; 

public class DeconstructWallTimer implements GameConstants {
    private Timer timer;;
    private int timeLeft;
    private Player player;
    
    TimerTask task = new TimerTask(){
        /* run method
         * decrements timeLeft by 1 each time a second has passed
         */
        public void run(){
            timeLeft--;
            if (timeLeft <= 0) { // once timer hits 0, begin player deconstruction
                player.deconstruct();
                timer.cancel();
            }
        }
    };
    /* DeconstructWallTimer
     * Constructor for timer that determines when to deconstruct player walls
     */
    public DeconstructWallTimer(int seconds, Player p) { 
        this.timeLeft = seconds;
        this.player = p;
        this.timer = new Timer();
    }
    /* start
     * Starts the timer after 2 seconds, at a fixed rate of 1 sec
     */
    public void start(){
        timer.scheduleAtFixedRate(task,2000,1000);
    }
    /* cancel
     * cancels the timer
     */
    public void cancel(){
        timer.cancel();
    }
    /* getTimeLeft
     * @return int timeLeft
     * method that returns how much time is left for the player in game
     */
    public int getTimeLeft(){
        return this.timeLeft;
    }
}
