/**
 * QuitButtonListener.java
 * George Li
 * Action listener for quit button
 * 2020-01-16
 */
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuitButtonListener implements ActionListener{
    private JFrame targetFrame;
    
    /* QuitButtonListener
     * @param JFrame frame
     *  Constructor for QuitButtonListener that sets parameter JFrame as the targetFrame
     */
    QuitButtonListener(JFrame frame){
        this.targetFrame = frame;//set target frame
    }
    
    /* actionPerformed
     *  @param Action event
     *   once action is performed, will dispose of target frame
     */
    public void actionPerformed(ActionEvent event)  {  
        targetFrame.dispose();            //dispose of the start screen when quit button is clicked
    }
}