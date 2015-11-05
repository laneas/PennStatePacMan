package pennstatepacman;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{   
    public GameFrame()
    {
        //GamePanel gp = new GamePanel();
        //add(gp);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
