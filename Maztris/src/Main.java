import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        //game initialization 
        Board board = new Board(10,20);
        FrameStuff frame = new FrameStuff();
        PieceStack pieceStack = new PieceStack();
        Piece piece = new Piece(board,pieceStack);

        //game timer
        while(true){
            if(piece.getPlaced()==true){
                piece = new Piece(board,pieceStack);
            }
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            for(int i =0; i<board.getHeight();i++){
                for(int j =0; j<board.getWidth();j++){
                    System.out.print(board.getTileValue(j, i));
                }
                System.out.println();
            }
            piece.MoveY();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
