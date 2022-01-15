public class Piece extends PieceStack{
    private Board board;
    private int xLocation = 4;
    private int yLocation =  0;
    private int pieceMatrix;
    private int pieceType;
    private int dropDelay;
    private boolean placed;
    private PieceStack pieceStack;

    public Piece(Board board, PieceStack pieceStack){
        this.board = board;
        this.pieceStack = pieceStack;
        dropDelay=0;
        placed =false;
        pieceAssign();
        board.setPiece(xLocation, yLocation, pieceType);
    }

    public void pieceAssign(){
        if(pieceStack.stack.isEmpty()){
            pieceStack.fillstack();
        }else if(!pieceStack.stack.isEmpty()){
            pieceType = pieceStack.stack.remove(0);
        }
    }

    public void MoveY(){
        //if at limit or if there is a piece below then don't move and increase delay
        if(yLocation==board.getHeight()-1 || board.getTileValue(xLocation, yLocation+1)!=0){
            dropDelay+=1;
        }
        //if not at limit and no piece obstructing then move
        else if(yLocation<board.getHeight()-1 && board.getTileValue(xLocation, yLocation+1)==0){
            yLocation+=1;
            board.setPiece(xLocation, yLocation, pieceType);
            board.setPiece(xLocation, yLocation-1, 0);            
        }
        dropDelayCheck();
    }

    public void dropDelayCheck(){
        switch(dropDelay){
            case 3:
                placed = true;
                break;
        }
    }

    public boolean getPlaced(){
        return placed;
    }
}
