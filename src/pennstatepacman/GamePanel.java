package pennstatepacman;

import java.awt.Color;
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
    Timer t = new Timer(10, this);
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
        player = new Player(280, 320);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        setBackground(new Color(255, 250, 244));
        t.start();
        createLevel();
        createEnemies();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       g.setColor(new Color(24, 33, 133));
       
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
        int mx = 0;
        int my = 0;
        
        Rectangle leftSide = new Rectangle(0, 0, 10, 600);
        Rectangle rightSide = new Rectangle(590, 0, 10, 600);
        Rectangle topSide = new Rectangle(0, 0, 600, 10);
        Rectangle bottomSide = new Rectangle(0, 600, 600, 10);
        
        Rectangle r1 = new Rectangle(40, 40, 60 - mx, 120 - my);
        Rectangle r2 = new Rectangle(120, 40, 60 - mx, 120 - my);
        Rectangle r3 = new Rectangle(200, 40, 80 - mx, 120 - my);
        Rectangle r4 = new Rectangle(300, 40, 80 - mx, 120 - my);
        Rectangle r5 = new Rectangle(400, 40, 60 - mx, 120 - my);
        Rectangle r6 = new Rectangle(480, 40, 60 - mx, 120 - my);
        
        Rectangle r7 = new Rectangle(40, 180, 60 - mx, 120 - my);
        Rectangle r8 = new Rectangle(120, 180, 60 - mx, 120 - my);
        Rectangle r9 = new Rectangle(200, 180, 80 - mx, 120 - my);
        Rectangle r10 = new Rectangle(300, 180, 80 - mx, 120 - my);
        Rectangle r11 = new Rectangle(400, 180, 60 - mx, 120 - my);
        Rectangle r12 = new Rectangle(480, 180, 60 - mx, 120 - my);
        
        Rectangle r13 = new Rectangle(40, 320, 60 - mx, 100 - my);
        Rectangle r14 = new Rectangle(120, 320, 60 - mx, 100 - my);
        Rectangle r15 = new Rectangle(200, 300, 60 - mx, 120 - my);
        Rectangle r16 = new Rectangle(260, 360, 80 - mx, 60 - my);
        Rectangle r17 = new Rectangle(320, 300, 60 - mx, 120 - my);
        Rectangle r18 = new Rectangle(400, 320, 60 - mx, 100 - my);
        Rectangle r19 = new Rectangle(480, 320, 60 - mx, 100 - my);
        
        Rectangle r20 = new Rectangle(40, 440, 60 - mx, 120 - my);
        Rectangle r21 = new Rectangle(120, 440, 60 - mx, 120 - my);
        Rectangle r22 = new Rectangle(200, 440, 80 - mx, 120 - my);
        Rectangle r23 = new Rectangle(300, 440, 80 - mx, 120 - my);
        Rectangle r24 = new Rectangle(400, 440, 60 - mx, 120 - my);
        Rectangle r25 = new Rectangle(480, 440, 60 - mx, 120 - my);
        
        level.add(leftSide);
        level.add(rightSide);
        level.add(topSide);
        level.add(bottomSide);
        
        level.add(r1);
        level.add(r2);
        level.add(r3);
        level.add(r4);
        level.add(r5);
        level.add(r6);
        
        level.add(r7);
        level.add(r8);
        level.add(r9);
        level.add(r10);
        level.add(r11);
        level.add(r12);
        
        level.add(r13);
        level.add(r14);
        level.add(r15);
        level.add(r16);
        level.add(r17);
        level.add(r18);
        level.add(r19);
        
        level.add(r20);
        level.add(r21);
        level.add(r22);
        level.add(r23);
        level.add(r24);
        level.add(r25);
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
           if(player.getBounds().intersects(ghouls.get(i).getBounds()))//player check
           {
               player.setLives(player.getLives() - 1);
           }
           for(int j = 0; j < ghouls.size(); j++)
           {
               if(i != j && ghouls.get(i).getBounds().intersects(ghouls.get(j).getBounds()))
               {
                   ghouls.get(i).undoMove();//ghoul check
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
