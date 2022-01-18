import java.util.zip.CheckedInputStream;

public class Piece extends PieceStack{
    private Board board;
    private int xLocation = 3;
    private int yLocation =  0;
    private int pieceType;
    private int dropDelay;
    private final int dropDelayLim=3;
    private boolean placed;
    private PieceStack pieceStack;

    private GamePanel panel;

    private int pieceSize;
    private int[][] pieceMatrix;
    private int[][] t_piece = 
    {{0,1,0},
    {1,1,1},
    {0,0,0}};

    public Piece(Board board, PieceStack pieceStack, GamePanel panel){
        this.board = board;
        this.pieceStack = pieceStack;
        this.panel = panel;

        dropDelay=0;
        placed =false;
        pieceAssign();
        placePiece();
    }

    public boolean checkEmptyLine(int i){
        boolean check = true;

        for(int j = 0; j<pieceSize;j++){
            if(pieceMatrix[i][j]!=0){
                check = false;
                break;
            }
        }

        return check;
    }

    public int checkEmptyLineHeight(){
        boolean check = true;
        int checkInt=0;

        for(int i=0 ;i<pieceSize;i++){
            check = true;
            for(int j = 0; j<pieceSize;j++){
                if(pieceMatrix[i][j]!=0){
                    check = false;
                    break;
                }
            }
            if(check==true){
                checkInt=i;
            }
        }
    
        return checkInt;
    }

    public void placePiece(){
        for(int i=0;i<pieceSize;i++){
            if(yLocation+i>board.getHeight()-1){
                break;
            }

            if(checkEmptyLine(i)== false){
                for(int j=0;j<pieceSize;j++){
                    if(pieceMatrix[i][j]!=0){
                        board.setTile(xLocation+j, yLocation+i, pieceMatrix[i][j]);
                    }
                }
            }
        }
    }

    public void clearPiece(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(pieceMatrix[i][j]!=0){
                    board.setTile(xLocation+j, yLocation+i, 0);
                }
            }
        }
    }

    public void pieceAssign(){
        if(pieceStack.stack.isEmpty()){
            pieceStack.fillstack();
            pieceType = pieceStack.stack.remove(0);
        }else if(!pieceStack.stack.isEmpty()){
            pieceType = pieceStack.stack.remove(0);
        }

        switch(this.pieceType){
            case 1:
                pieceSize= 3;
                pieceMatrix=t_piece.clone();
            break;
        }

    }

    public boolean checkOverlap(int checkx,int checky){
        boolean check = false;

        for(int i=0;i<pieceSize;i++){
            if(checkEmptyLine(i)==false){
                for(int j=0;j<pieceSize;j++){
                    if(board.getTileValue(checkx+j, checky+i) !=0 && pieceMatrix[i][j]!=0){
                        check = true;
                        break;
                    }else{
                        check=false;
                    }
                }
                if(check == true){
                    break;
                }
            }
        }
        return check;
    }

    public void moveY(){
        if(panel.gethardDrop()==true){
            clearPiece();
            while(!(checkOverlap(xLocation, yLocation+1)==true || yLocation+checkEmptyLineHeight()==board.getHeight())){
                yLocation+=1;    
            }
            dropDelay = dropDelayLim;
            panel.resethardDrop();
        }else if(panel.gethardDrop()==false){
            if(checkOverlap(xLocation, yLocation+1)==false && yLocation+1<board.getHeight()-1){
                clearPiece();
                yLocation+=1;
            }else if(checkOverlap(xLocation, yLocation+1)==true || yLocation+checkEmptyLineHeight()==board.getHeight()){
                dropDelay+=1;
            }
        }
        placePiece();
        dropDelayCheck();
    }

    public void moveX(){
        clearPiece();
        if((panel.getmoveX()<0 && this.xLocation!=0) || (panel.getmoveX()>0 && this.xLocation!=board.getWidth()-1)){  
            xLocation+=panel.getmoveX();
            panel.resetmoveX();
            placePiece();
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
