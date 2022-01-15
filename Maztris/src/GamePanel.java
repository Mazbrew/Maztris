import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{
    private int moveX = 0;
    private int moveXSpeed = 1;


    public GamePanel(FrameStuff frame){
        super();
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        frame.add(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP){
            
        }else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            
        }else if(e.getKeyCode()== KeyEvent.VK_LEFT){
            moveX=-moveXSpeed;
        }else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            moveX=moveXSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getmoveX(){
        return moveX;
    }
    public void resetmoveX(){
        moveX=0;
    }
}
