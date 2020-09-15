/**
 * ControlPanel.java
 * George Li
 * 2020-01-16
 * Panel that displays game controls for the player
 */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;

class ControlsPanel extends JPanel implements GameConstants{
    private JButton backButton;
    private BufferedImage gameControls;
    
    /* ControlsPanel constructor
   * @param JFrame frame, menuFrame
   * @param JPanel containerPanel
   *  Constructor for ControlsPanel
   */
    ControlsPanel(JFrame frame, JPanel containerPanel){
        this.setLayout(null);
        addBackButton(frame,containerPanel);
        loadControlsPicture();//load game controls
        
        this.setFocusable(true);      //make this window the active window
        this.requestFocusInWindow(); 
    }
    
//--------------------------------------------------------------------------------
    /* ControlsPanel constructor
   * @param JFrame frame, menuFrame
   * @param JPanel containerPanel
   *  Method that adds and customizes a back button to the controls screen and also adds an ActionListener
   */
    private void addBackButton(JFrame frame, JPanel containerPanel){
        this.backButton = new JButton("Back");
        this.add(backButton);
        backButton.setBounds(30,30,80,50);
        backButton.setFont(new Font("Lucida Console", Font.BOLD, 16));
        backButton.setBackground(Color.white);
        backButton.addActionListener(new ControlButtonListener(frame,containerPanel));
    }
    
    /* loadControlsPicture method
     *  Loads the controls picture to be drawn later for the controls page
     */
    private void loadControlsPicture(){
        try{
            this.gameControls = ImageIO.read(new File("images/menu/gameControls.png"));
        }
        catch(Exception e){
            System.out.println("Failure to load instructions");
        }
    }
//-------------------------------------------------------------------------------- 
    /* paintComponent
   * @param Graphics g
   *  Method that draws the controls picture to the screen
   */
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);  //required
        setDoubleBuffered(true); 
        g.drawImage(this.gameControls,0,0,START_W,START_H,this);
    }
}