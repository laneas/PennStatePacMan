package pennstatepacman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
    final int SCREEN_WIDTH = 850;
    final int SCREEN_HEIGHT = 650;
    Player player;
    Ghoul ghoul;
    Ghoul ghoul2;
    Timer t = new Timer(30, this);
    ArrayList<Rectangle> level;
    
    public GamePanel()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        level = new ArrayList<Rectangle>();
        player = new Player(100, 100);
        ghoul = new Ghoul(500, 500);
        ghoul2 = new Ghoul(500, 100);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        t.start();
        createLevel();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g); 
       
       g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
       g.drawImage(ghoul.getImage(), ghoul.getX(), ghoul.getY(), ghoul.getWidth(), ghoul.getHeight(), this);
       g.drawImage(ghoul2.getImage(), ghoul2.getX(), ghoul2.getY(), ghoul2.getWidth(), ghoul2.getHeight(), this);
       
       for(int i = 0; i < level.size(); i++)
       {
           Rectangle r = level.get(i);
           g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
       }
               
       Toolkit.getDefaultToolkit().sync();
    }
    
    
    public void createLevel()
    {   
        Rectangle leftSide = new Rectangle(0, 0, 10, 600);
        Rectangle rightSide = new Rectangle(590, 0, 10, 600);
        Rectangle topSide = new Rectangle(0, 0, 600, 10);
        Rectangle bottomSide = new Rectangle(0, 600, 600, 10);
        level.add(leftSide);
        level.add(rightSide);
        level.add(topSide);
        level.add(bottomSide);
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
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object o = ae.getSource();
        if(o == t)
        {
            ghoul.decideMove(player.getX(), player.getY());
            ghoul.move();
            ghoul2.decideMove(player.getX(), player.getY());
            ghoul2.move();
            repaint();
        }
    }
}
