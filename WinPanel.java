/**
 * WinPanel.java
 * George Li
 * 2020-01-16
 * Win panel for when user wins the game
 */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;

class WinPanel extends JPanel implements GameConstants{
    private BufferedImage victoryImage;//victory image
    private JButton quitButton;
    
    WinPanel(JFrame frame){
        this.setLayout(null);
        this.quitButton = new JButton("QUIT");
        this.add(quitButton);
        quitButton.setBounds(350, 400,100,40);
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.yellow);
        
        quitButton.addActionListener(new QuitButtonListener(frame));
        loadVictoryImage();
        
        this.setFocusable(true);      //make this window the active window
        this.requestFocusInWindow(); 
    }
    
//----------------------------------------       
    private void loadVictoryImage(){
        try{
            this.victoryImage = ImageIO.read(new File("images/ending/winScreen.png"));
        }
        catch(Exception e){
            System.out.println("Failure to load death image");
        }
    }
//----------------------------------------       
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);  //required
        setDoubleBuffered(true); 
        g.drawImage(this.victoryImage,0,0,GAME_W,GAME_H,this);
   }

}