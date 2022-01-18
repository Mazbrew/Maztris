import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        //game initialization 
        Board board = new Board(10,20);
        FrameStuff frame = new FrameStuff();
        GamePanel panel = new GamePanel(frame,board);
        PieceStack pieceStack = new PieceStack();
        Piece piece = new Piece(board,pieceStack,panel);

        //game timer
        while(true){
            if(piece.getPlaced()==true){
                piece = new Piece(board,pieceStack,panel);
            }
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            for(int i =1; i<board.getHeight();i++){
                for(int j =0; j<board.getWidth();j++){
                    System.out.print(board.getTileValue(j, i));
                }
                System.out.println();
            }
            piece.moveX();
            piece.moveY();
            
            TimeUnit.MILLISECONDS.sleep(100);
            panel.repaint();
        }
    }
}
