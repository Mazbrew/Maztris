public class Piece extends PieceStack{
    private Board board;
    private int xLocation = 4;
    private int yLocation =  0;
    private int pieceType;
    private int dropDelay;
    private final int dropDelayLim=3;
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
            pieceType = pieceStack.stack.remove(0);
        }else if(!pieceStack.stack.isEmpty()){
            pieceType = pieceStack.stack.remove(0);
        }
    }

    public void moveY(){
        if(panel.gethardDrop()==true){
            board.setPiece(xLocation, yLocation, 0);
            while(yLocation<board.getHeight()-1 && board.getTileValue(xLocation, yLocation+1)==0){
                yLocation+=1;    
            }
            board.setPiece(xLocation, yLocation, pieceType);
            dropDelay = dropDelayLim;
            panel.resethardDrop();
        }else if(panel.gethardDrop()==false){
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
            case dropDelayLim:
                placed = true;
                board.checkLineClear();
                break;
        }
    }

    public boolean getPlaced(){
        return placed;
    }
}
