/**
 * MenuPanel.java
 * George Li
 * Starting frame that includes menuPanel and controlsPanel
 * 2020-01-16
 */
import java.awt.image.BufferedImage; 
import java.awt.Color;
import java.io.File;
import java.awt.Font;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

class MenuPanel extends JPanel implements GameConstants{
    private BufferedImage menuBackground;
    private BufferedImage gameLogo;
    private JButton startButton;
    private JButton controlsButton;
    private JButton quitButton;
    
    /* MenuPanel
     * @param JFrame frame
     * @param JPanel containerPanel
     *  Contructor for the menu Panel
     */
    public MenuPanel(JFrame frame,JPanel containerPanel){
        this.setLayout(null);
        loadImages();//load images for the menu
        createButtons();
        addButtons();
        setButtonBounds();
        editButton(startButton);
        editButton(controlsButton);
        editButton(quitButton);
        
        this.startButton.addActionListener(new StartButtonListener(frame));//add action listener to buttons
        this.controlsButton.addActionListener(new ControlButtonListener(frame,containerPanel));
        this.quitButton.addActionListener(new QuitButtonListener(frame));
        this.setFocusable(true);      //make this window the active window
        this.requestFocusInWindow(); 
    }
//-----------------------------------------------    
     /* loadImages
     *  method that loads images for the menu panel
     */
    private void loadImages(){
        try{
            this.menuBackground = ImageIO.read(new File("images/menu/background.jpg"));
            this.gameLogo = ImageIO.read(new File("images/menu/crazyConstruct.png"));
        }
        catch(Exception e){
            System.out.println("Failure to load images");
        }
    }
//-----------------------------------------------  
    /* createButtons
     *  method that creates JButtons for the menu panel
     */
    private void createButtons(){
        this.startButton = new JButton("START");
        this.controlsButton = new JButton("CONTROLS");
        this.quitButton = new JButton("QUIT");
    }
    /* addButtons
     *  method that creates adds the JButtons to the menu panel
     */
    private void addButtons(){
        this.add(this.startButton);
        this.add(this.controlsButton);
        this.add(this.quitButton);
    }
    /* setButtonBounds
     *  method that sets the bounds for the menu buttons
     */
    private void setButtonBounds(){
        startButton.setBounds(200,300,200,30);
        controlsButton.setBounds(200, 360, 200, 30);
        quitButton.setBounds(200,420,200,30);
    }
    /* editButton
     * @param JButton button 
     *  Edits the button that is taken in as a parameter
     */
    private void editButton(JButton button){
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        button.setFont(new Font("Lucida Console", Font.BOLD, 24));
    }
//-----------------------------------------------  
    /* paintComponent
     *  @param Graphics g
     * Draws the menu background and logo to the panel
     */
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);  //required
        setDoubleBuffered(true); 
        g.drawImage(this.menuBackground,0,0,START_W,START_H,this);//menu background fills entire screen
        g.drawImage(this.gameLogo,70,50,460,170,this);
    }
}