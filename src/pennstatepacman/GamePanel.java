package pennstatepacman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    final int SCREEN_WIDTH = 850;
    final int SCREEN_HEIGHT = 650;
    Player player;
    Ghoul ghoul;
    
    public GamePanel()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        player = new Player(100, 100);
        ghoul = new Ghoul(500, 500);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g); 
       
       g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
       g.drawImage(ghoul.getImage(), ghoul.getX(), ghoul.getY(), ghoul.getWidth(), ghoul.getHeight(), this);
               
       Toolkit.getDefaultToolkit().sync();
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent ke)
        {
            ghoul.decideMove();//for testing
            player.keyReleased(ke);
            player.move();
            ghoul.move();//for testing
            repaint();
        }
        
        @Override
        public void keyPressed(KeyEvent ke)
        {
            ghoul.decideMove();//for testing
            player.keyPressed(ke);
            player.move();
            ghoul.move();//for testing
            repaint();
        }
    }
}
