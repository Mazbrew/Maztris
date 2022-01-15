import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{
    private FrameStuff frame;
    boolean flag;

    public GamePanel(FrameStuff frame){
        super();
        this.frame = frame;
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        frame.add(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode()== KeyEvent.VK_UP){
            this.setBackground(Color.green);
        }else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            this.setBackground(Color.red);
        }else if(e.getKeyCode()== KeyEvent.VK_LEFT){
            this.setBackground(Color.blue);
        }else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            this.setBackground(Color.MAGENTA);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
