/**
 * GameFrame.java
 * @author George Li
 * @Date: 2020/01/20
 * JFrame used to play the game
 */
import java.awt.image.BufferedImage; 
import java.awt.CardLayout;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

class GameFrame extends JFrame implements GameConstants{ 
    
    private JPanel containerPanel;//jpanel that uses cardlayout
    private DeathPanel deathScreen;
    private WinPanel winScreen;
    private GamePanel gamePanel;
 
    /* GameFrame
     *  Constructor for the JFrame used to run the game
     */
    GameFrame() { 
        super("Crazy Construct");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GAME_W, GAME_H);
        this.setLocationRelativeTo(null); //start the frame in the center of the screen
        this.setResizable(false); 
        this.setUndecorated(false);     //set to true to remove title bar CHANGE LATER TO TRUE
        
        //create the game panel (where all graphics are drawn)
        containerPanel = new JPanel(new CardLayout());
        
        gamePanel = new GamePanel(containerPanel);
        deathScreen = new DeathPanel(this,containerPanel);
        winScreen = new WinPanel(this);
        
        containerPanel.add(gamePanel,"1");
        containerPanel.add(deathScreen,"2");
        containerPanel.add(winScreen,"3");

        
        //add the panel to the frame
        this.add(containerPanel);
        this.setFocusable(false);         //focus on the game panel
        this.setVisible(true);    

    }
    
}




