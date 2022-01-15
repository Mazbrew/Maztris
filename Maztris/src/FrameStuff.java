import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class FrameStuff extends JFrame{
    private final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = 500;
    private final int height= 500;

    public FrameStuff(){
        super();
        this.setResizable(false);
        this.setSize(width,height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation((int)screenDimension.getWidth()/2 - width/2, (int)screenDimension.getHeight()/2 - height /2);
        this.setVisible(true);
    }
}
