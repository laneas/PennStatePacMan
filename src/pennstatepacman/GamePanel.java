package pennstatepacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
    ArrayList<Grass> grass;
    JLabel playerLives;
    JLabel playerScore;
    
    public GamePanel()
    {
        setup();
        setFocusable(true);
    }
    
    public void setup()
    {
        level = new ArrayList<Rectangle>();
        ghouls = new ArrayList<Ghoul>();
        grass = new ArrayList<Grass>();
        player = new Player(280, 320);
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setVisible(true);
        setLayout(null);
        createScoreBoard();
        setBackground(new Color(255, 250, 244));
        t.start();
        createLevel();
        createEnemies();
        createGrass();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       g.setColor(new Color(24, 33, 133));
       
       //if(player.checkCondition())
       //{
           g.drawImage(player.getImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
       //}
       g.drawImage(new ImageIcon("src//pennstatepacman//images//logo.jpg").getImage(), 600, 400, 134, 250, this);
       
       for(int i = 0; i < level.size(); i++)
       {
           Rectangle r = level.get(i);
           g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
       }
       
       for(int i = 0; i < ghouls.size(); i++)
       {
           g.drawImage(ghouls.get(i).getImage(), ghouls.get(i).getX(), ghouls.get(i).getY(), ghouls.get(i).getWidth(), ghouls.get(i).getHeight(), this);
       }
       
       for(int i= 0; i< grass.size(); i++)
       {
           if(grass.get(i).getVis())
           {
             g.drawImage(grass.get(i).getImage(), grass.get(i).getX(), grass.get(i).getY(), grass.get(i).getWidth(), grass.get(i).getHeight(), this);
           }
       }
       
       
       Toolkit.getDefaultToolkit().sync();
    }
    
    public void createScoreBoard()
    {
        Font f = new Font("Impact", Font.BOLD, 20);
        
        JLabel lives = new JLabel("LIVES: ");
          lives.setBounds(600, 50, 100, 100);
          lives.setFont(f);
          add(lives);
        playerLives = new JLabel(Integer.toString(player.getLives()));
          playerLives.setBounds(700, 50, 100, 100);
          playerLives.setFont(f);
          add(playerLives);
        JLabel high = new JLabel("HIGH: ");
          high.setBounds(600, 150, 100, 100);
          high.setFont(f);
          add(high);
        JLabel highscore = new JLabel("0000"); //<--------- will read value from text file
          highscore.setBounds(700, 150, 100, 100);
          highscore.setFont(f);
          add(highscore);
        JLabel score = new JLabel("SCORE: ");
          score.setBounds(600, 250, 100, 100);
          score.setFont(f);
          add(score);
        playerScore = new JLabel(Integer.toString(player.getScore()));
          playerScore.setBounds(700, 250, 100, 100);
          playerScore.setFont(f);
          add(playerScore);
        
    }
    
    public void createLevel()
    {   
        int mx = 3;
        int my = 3;
        
        Rectangle leftSide = new Rectangle(0, 0, 10, 600);
        Rectangle rightSide = new Rectangle(570, 0, 30, 600);
        Rectangle topSide = new Rectangle(0, 0, 600, 10);
        Rectangle bottomSide = new Rectangle(0, 580, 600, 20);
        
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
              ghoul1.setImage("src//pennstatepacman//images//Ill.png");
            Ghoul ghoul2 = new Ghoul(540, 10);
              ghoul2.setImage("src//pennstatepacman//images//msu.png");
            Ghoul ghoul3 = new Ghoul(10, 558);
              ghoul3.setImage("src//pennstatepacman//images//msu.png");
            Ghoul ghoul4 = new Ghoul(540, 558);
              ghoul4.setImage("src//pennstatepacman//images//Ill.png");
            ghouls.add(ghoul1);
            ghouls.add(ghoul2);
            ghouls.add(ghoul3);
            ghouls.add(ghoul4);
    }
    
    public void createGrass()
    {
        for(int i = 0; i < 600; i++)
        {
            for(int j = 0; j < 600; j++)
            {
                if(i % 20 == 0 && j % 20 == 0)
                {
                    Grass g = new Grass(i, j);
                    grass.add(g);
                }
            }
        }
        
        for(int i = 0; i < level.size(); i++)
        {
            for(int j = 0; j < grass.size(); j++)
            {
                if(level.get(i).intersects(grass.get(j).getBounds()))
                {
                    grass.get(j).setVis(false);
                }
            }
        }

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
               player.setX(280);
               player.setY(320);
               ghouls.get(0).setX(10);
               ghouls.get(0).setY(10);
               ghouls.get(1).setX(540);
               ghouls.get(1).setY(10);
               ghouls.get(2).setX(10);
               ghouls.get(2).setY(558);
               ghouls.get(3).setX(540);
               ghouls.get(3).setY(558);
               player.setLives(player.getLives() - 1);
               playerLives.setText(Integer.toString(player.getLives()));
           }
           for(int j = 0; j < ghouls.size(); j++)
           {
               if(i != j && ghouls.get(i).getBounds().intersects(ghouls.get(j).getBounds()))
               {
                   ghouls.get(i).undoMove();//ghoul check
               }
           }
       }
       for(int i = 0; i < grass.size(); i++)// grass check
       {
           if(grass.get(i).getVis() && player.getBounds().intersects(grass.get(i).getBounds()))
           {
               grass.get(i).setVis(false);
               player.setScore(player.getScore() + 3);
               playerScore.setText(Integer.toString(player.getScore()));
           }
       }
       
       for(int i = 0; i < grass.size(); i++)
       {
           int counter = 0;
           if(!grass.get(i).getVis())
           {
               counter++;
           }
           if(counter == grass.size())
           {
               System.out.println("you win");
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
