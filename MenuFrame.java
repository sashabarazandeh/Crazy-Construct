/**
 * StartingFrame.java
 * George Li
 * Starting frame that includes menuPanel and controlsPanel
 * 2020-01-16
 */
import java.awt.CardLayout;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;

class MenuFrame extends JFrame implements GameConstants{ 
    private Clip menuTheme;
    private JPanel containerPanel;//jpanel that uses cardlayout
    private MenuPanel menuPanel;
    private ControlsPanel controlsPanel;
    
   /* MenuFrame
     *  Constructor for the menu frame
     */
    MenuFrame() { 
        super("Menu Crazy Construct");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(START_W, START_H);//set size
        this.setResizable(false); 
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        
        //create containerPanel for cardlayout)
        containerPanel = new JPanel(new CardLayout());
        //menu panel
        menuPanel = new MenuPanel(this,containerPanel);
        //control screen panel
        controlsPanel = new ControlsPanel(this,containerPanel);
        
        containerPanel.add(menuPanel);//add menuPanel to container panel
        containerPanel.add(controlsPanel);//add controlsPanel to container panel
        
        //add the panel to the frame
        this.add(containerPanel);
        this.requestFocusInWindow();      //make this window the active window
        this.setVisible(true);   
        
        //play menu theme
        loadSound();
    }
    
//-----------------------------------------------  
    /* loadSound
     *  Loads the menu sound
     */
    private void loadSound(){
        try {
          AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("sounds/menuTheme.wav"));
          this.menuTheme = AudioSystem.getClip();
          this.menuTheme.open(audioStream);
          this.menuTheme.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e) {System.out.println("error loading sound");};        
    }
    
//-----------------------------------------------      
    /* dispose
     *  Disposes menuFrame and stop menuTheme from playing once this frame is disposed
     */
    @Override
    public void dispose(){
      this.menuTheme.stop();//stop playing menu theme when dispose menu frame
      super.dispose();//dispose this frame when game starts
    }
}