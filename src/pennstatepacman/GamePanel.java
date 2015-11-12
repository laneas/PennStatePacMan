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
    Timer t = new Timer(15, this);
    ArrayList<Rectangle> level;
    ArrayList<Ghoul> ghouls;
    
    public GamePanel()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        level = new ArrayList<Rectangle>();
        ghouls = new ArrayList<Ghoul>();
        player = new Player(300, 300);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        t.start();
        createLevel();
        createEnemies();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g); 
       
       g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
       
       for(int i = 0; i < level.size(); i++)
       {
           Rectangle r = level.get(i);
           g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
       }
       
       for(int i = 0; i < ghouls.size(); i++)
       {
           g.drawImage(ghouls.get(i).getImage(), ghouls.get(i).getX(), ghouls.get(i).getY(), ghouls.get(i).getWidth(), ghouls.get(i).getHeight(), this);
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
    
    public void createEnemies()
    {
            Ghoul ghoul1 = new Ghoul(10 ,10);
            Ghoul ghoul2 = new Ghoul(558, 10);
            Ghoul ghoul3 = new Ghoul(10, 558);
            Ghoul ghoul4 = new Ghoul(558, 558);
            ghouls.add(ghoul1);
            ghouls.add(ghoul2);
            ghouls.add(ghoul3);
            ghouls.add(ghoul4);
    }
    
    public void checkCollisions()
    {
       for(int i = 0; i < level.size(); i++)//Wall Check
       {
           if(player.getBounds().intersects(level.get(i))) //Checks to see if the player intersects the wall
           {
               player.undoMove();
           }
           for(int j = 0; j < ghouls.size(); j++)//Checks each ghoul to see if they intersect the wall
           {
               if(ghouls.get(j).getBounds().intersects(level.get(i)))
               {
                   ghouls.get(j).undoMove();
               }
           }
       }
       
       for(int i = 0; i < ghouls.size(); i++)//check to see if the player intersects the ghoul or ghoul intersects ghoul
       {
           if(player.getBounds().intersects(ghouls.get(i).getBounds()))
           {
               player.setLives(player.getLives() - 1);
           }
           for(int j = 0; j < ghouls.size(); j++)
           {
               if(i != j && ghouls.get(i).getBounds().intersects(ghouls.get(j).getBounds()))
               {
                   ghouls.get(i).undoMove();
               }
           }
       }
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
            for(int i = 0; i < ghouls.size(); i++)
            {
                ghouls.get(i).decideMove(player.getX(), player.getY());
                ghouls.get(i).move();
            }
            checkCollisions();
            repaint();
        }
    }
}
