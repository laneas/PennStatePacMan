
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
    protected int lastX;
    protected int lastY;
    protected int speed;
    private Image image;
    protected int moveX;
    protected int moveY;
    private int width;
    private int height;
    int xcheck;
    int ycheck;
    int xuncheck;
    int yuncheck;
    
    public Ghoul(int xLocation, int yLocation)
    {
        x = xLocation;
        y = yLocation;
        speed = 1; //determines how far the player moves with each cycle
        moveX = 0;
        moveY = 0;
        xcheck = 0;
        ycheck = 0;
        //setImage("src//pennstatepacman//images//testpac.png");//<--------Change this value once we have an image for the player
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

    public void move()
    {
        lastX = x;
        lastY = y;
        xuncheck = xcheck;
        yuncheck = ycheck;
        x = x + moveX;
        y = y + moveY;
        
        if(x < 1){x = 1;}
        if(y < 1){y = 1;}
    }
    
    public void undoMove()
    {
        x = lastX;
        y = lastY;
        xcheck = xuncheck;
        ycheck = yuncheck;
    }
    
    public void decideMove(int playerX, int playerY)
    {   
        if(y > playerY && xcheck == 0)
        {
            moveY = -speed;
            moveX = 0;
            ycheck++;
            if(ycheck > 1){ycheck = 0;}
        }
        if(y < playerY && xcheck == 0)
        {
            moveY = speed;
            moveX = 0;
            ycheck++;
            if(ycheck > 1){ycheck = 0;}
        }
        if(x > playerX && ycheck == 0)
        {
            moveX = -speed;
            moveY = 0;
            xcheck++;
            if(xcheck > 1){xcheck = 0;}
        }
        if(x < playerX && ycheck == 0)
        {
            moveX = speed;
            moveY = 0;
            xcheck++;
            if(xcheck > 1){xcheck = 0;}
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
}
