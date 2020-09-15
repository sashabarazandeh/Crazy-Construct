/**
 * StartButtonListener.java
 * George Li
 * Action listener that starts the game
 * 2020-01-16
 */
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class StartButtonListener implements ActionListener {  
    private JFrame targetFrame;
    /* StartButtonListener
     *  @param JFrame target
     * Constructor for the StartButtonListener
     */
    StartButtonListener(JFrame target) { 
        targetFrame = target;
    }
    /* actionPerformed
     *  @param ActionEvent event
     * Once action is performed dispose of target frame and create the game frame which starts the actual game
     */
    public void actionPerformed(ActionEvent event)  {  
        targetFrame.dispose();            //dispose of the start screen
        new GameFrame();               //create a new frame - the game frame
    }
}