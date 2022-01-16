import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{
    private int moveX = 0;
    private int moveXSpeed = 1;
    private boolean hardDrop = false;
    private Board board;


    public GamePanel(FrameStuff frame, Board board){
        super();
        this.board = board;
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(new Color(240,234,214));
        g.fillRect(0, 0, 500, 500);
        paintBoard(g,board);
    }

    private void paintBoard(Graphics g,Board board){
        int color;

        /*
        1- T
        2- I
        3- O
        4- L
        5- J
        6- S
        7- Z
        */

        for(int i=0;i<board.getHeight();i++){
            for(int j=0; j<board.getWidth();j++){
                color = board.getTileValue(j, i);
                switch(color){
                    case 0:
                        g.setColor(new Color(211,211,211).darker());
                        break;
                    case 1:
                        g.setColor(Color.magenta);
                        break;
                    case 2:
                        g.setColor(Color.CYAN);
                            break;
                    case 3:
                        g.setColor(Color.yellow);
                        break;
                    case 4:
                        g.setColor(Color.orange);
                        break;
                    case 5:
                        g.setColor(Color.blue);
                        break;
                    case 6:
                        g.setColor(Color.green);
                        break;
                    case 7:
                        g.setColor(Color.red);
                        break;
                }
                
                g.fillRect((j*20)+20, (i*20)+20, 15, 15);
                g.setColor(g.getColor().darker());
                g.drawRect((j*20)+20, (i*20)+20, 15, 15);
            }
        }
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
        }else if(e.getKeyCode()== KeyEvent.VK_SPACE){
            hardDrop=true;
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
    public boolean gethardDrop(){
        return hardDrop;
    }
    public void resethardDrop(){
        hardDrop=false;
    }
}
