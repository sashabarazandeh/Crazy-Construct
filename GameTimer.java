/**
 * CrazyConstruct.java
 * @George Li
 * @2020/01/20
 * Timer used as game condition in game panel
 */
import java.util.Timer; 
import java.util.TimerTask; 

class GameTimer implements GameConstants {
    private int secondsLeft = GAME_TIME;
    Timer timer = new Timer();
    TimerTask task = new TimerTask(){
        /* run
     *  decrements seconds left everytime
     */
        public void run(){
            secondsLeft--;
        }
    };
    /* start
     *  starts the timer decrementing it at a fixed rate of 1 second
     */
    public void start(){
        timer.scheduleAtFixedRate(task,2000,1000);
    }
    /* cancel
     *  cancels the timer
     */
    public void cancel(){
        timer.cancel();
    }
    /* getTimeLeft
     *  @return int secondsLeft
     * returns how many seconds are left
     */
    public int getTimeLeft(){
        return this.secondsLeft;
    }
}