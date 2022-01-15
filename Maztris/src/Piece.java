public class Piece extends PieceStack{
    private Board board;
    private int xLocation = 4;
    private int yLocation =  0;
    private int pieceType;
    private int dropDelay;
    private boolean placed;
    private PieceStack pieceStack;

    private GamePanel panel;

    public Piece(Board board, PieceStack pieceStack, GamePanel panel){
        this.board = board;
        this.pieceStack = pieceStack;
        this.panel = panel;

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

    public void moveY(){
        //if at limit or if there is a piece below then don't move and increase delay
        if(yLocation==board.getHeight()-1 || board.getTileValue(xLocation, yLocation+1)!=0){
            dropDelay+=1;
        }
        //if not at limit and no piece obstructing then move
        else if(yLocation<board.getHeight()-1 && board.getTileValue(xLocation, yLocation+1)==0){
            board.setPiece(xLocation, yLocation, 0);
            yLocation+=1;
            board.setPiece(xLocation, yLocation, pieceType);
                       
        }
        dropDelayCheck();
    }

    public void moveX(){
        if((panel.getmoveX()<0 && this.xLocation!=0) || (panel.getmoveX()>0 && this.xLocation!=board.getWidth()-1)){
            board.setPiece(xLocation, yLocation, 0); 
            xLocation+=panel.getmoveX();
            board.setPiece(xLocation,yLocation,pieceType);
            panel.resetmoveX();
        }
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
