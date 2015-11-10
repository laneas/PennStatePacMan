
package pennstatepacman;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * @author Ardjen
 */
public class Ghoul 
{
    protected int x;
    protected int y;
    protected int speed;
    private Image image;
    protected int moveX;
    protected int moveY;
    private int width;
    private int height;
    private int lives;
    private int score;
    
    public Ghoul(int xLocation, int yLocation)
    {
        x = xLocation;
        y = yLocation;
        speed = 2; //determines how far the player moves with each cycle
        moveX = 0;
        moveY = 0;
        lives = 3;
        score = 0;
        setImage("src//pennstatepacman//images//testpac.png");//<--------Change this value once we have an image for the player
    }
    
    public void setImage(String imageLocation)
    {
        ImageIcon icon = new ImageIcon(imageLocation);
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        image = icon.getImage();
    }
    
    public Image getImage()
    {
        return image;
    }
    
    /*
    Note about move():
    As of now, move will only move the player for however long the button is held.
    We must ask the group if they want continous movement, or the ability to stop.
    */
    public void move()
    {
        x = x + moveX;
        y = y + moveY;
        
        if(x < 1){x = 1;}
        if(y < 1){y = 1;}
    }
    
    /*
    Will randomly move the Ghoul Character
    For testing purposes only
    Not to be implemented
    */
    public void decideMove(int playerX, int playerY)
    {   
        if(y > playerY + 10)
        {
            moveY = -speed;
            moveX = 0;
        }
        else if(y < playerY - 10)
        {
            moveY = speed;
            moveX = 0;
        }
        else if(x > playerX - 10)
        {
            moveX = -speed;
            moveY = 0;
        }
        else if(x < playerX + 10)
        {
            moveX = speed;
            moveY = 0;
        }
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }
    
    public boolean checkCondition()
    {
        if(lives <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
