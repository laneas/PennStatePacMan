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
    
    public GamePanel()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        player = new Player(100, 100);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g); 
       
       g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
               
       Toolkit.getDefaultToolkit().sync();
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent ke)
        {
            player.keyReleased(ke);
            player.move();
            repaint();
        }
        
        @Override
        public void keyPressed(KeyEvent ke)
        {
            player.keyPressed(ke);
            player.move();
            repaint();
        }
    }
}
