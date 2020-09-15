/**
 * DeathPanel.java
 * George Li
 * 2020-01-16
 * Death panel when player loses the game
 */

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeathPanel extends JPanel implements GameConstants{
    private JButton quitButton;//jbutton to exit the game
    private BufferedImage gameOver;//game over picture
    
    /* DeathPanel constructor
   * @param JFrame frame
   * @param JPanel containerPanel
   *  Contructor for the DeathPanel
   */
    DeathPanel(JFrame frame, JPanel containerPanel){
        this.setLayout(null);
        this.quitButton = new JButton("QUIT");
        this.add(quitButton);
        quitButton.setBounds(350, 400,100,40);
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.red);
        
        quitButton.addActionListener(new QuitButtonListener(frame));//add action listener to quit button
        loadDeathImage();//load the "you died" image
        
        this.setFocusable(true);      //make this window the active window
        this.requestFocusInWindow(); 
    }

//--------------------------------------------------------------------------------    
    /* loadDeathImage
   *  Method that loads deathScreen image
   */
    private void loadDeathImage(){
        try{
            this.gameOver = ImageIO.read(new File("images/ending/deathScreen.jpg"));
        }
        catch(Exception e){
            System.out.println("Failure to load death image");
        }
    }
//--------------------------------------------------------------------------------    
    /* paintComponent
   * @param Graphics g
   *  Draws gameOver picture to screen
   */
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);  //required
        setDoubleBuffered(true); 
        g.drawImage(this.gameOver,0,0,GAME_W,GAME_H,this);
    }
}//end DeathPanel