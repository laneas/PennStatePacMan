/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pennstatepacman;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
/**
 *
 * @author tot5148
 */
public class Grass 
{
    protected int x;
    protected int y;
    private int width;
    private int height;
    private Image image;
    private int score;
    boolean visibility = true;
    
    public Grass(int xLocation, int yLocation)
    {
        x = xLocation;
        y = yLocation;
        score = 5;
        setImage("src//pennstatepacman//images//grass.png");//<--------Change this value once we have an image for the player
        
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
  
    public void setVis(boolean b)
    {
        visibility = b;
    }
    public boolean getVis()
    {
        return visibility;
    }
}
