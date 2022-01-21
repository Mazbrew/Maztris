public class Piece extends PieceStack{
    private Board board;
    private int xLocation = 5;
    private int yLocation =  0;
    private int pieceType;
    private int dropDelay;
    private final int dropDelayLim=3;
    private boolean placed;
    private PieceStack pieceStack;

    private GamePanel panel;

    public int pieceSize;
    public int[][] pieceMatrix;
    private int[][] t_piece = 
    {{0,1,0},
    {1,1,1},
    {0,0,0}};

    private int[][] i_piece = 
    {{0,0,0,0},
    {2,2,2,2},
    {0,0,0,0},
    {0,0,0,0}};

    private int[][] o_piece = 
    {{3,3},
    {3,3}};

    private int[][] l_piece = 
    {{0,0,4},
    {4,4,4},
    {0,0,0}};

    private int[][] j_piece = 
    {{5,0,0},
    {5,5,5},
    {0,0,0}};

    private int[][] s_piece = 
    {{0,6,6},
    {6,6,0},
    {0,0,0}};

    private int[][] z_piece = 
    {{7,7,0},
    {0,7,7},
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

    public boolean checkEmptyLineY(int i){
        boolean check = true;

        for(int j = 0; j<pieceSize;j++){
            if(pieceMatrix[i][j]!=0){
                check = false;
                break;
            }
        }

        return check;
    }

    public int getEmptyLineHeight(){
        boolean check = true;
        int checkInt=0;

        for(int i=pieceSize-1 ;i>=0;i--){
            check = true;
            for(int j = 0; j<pieceSize;j++){
                if(pieceMatrix[i][j]!=0){
                    check = false;
                    break;
                }
            }
            if(check==false){
                checkInt=i+1;
                break;
            }
        }
    
        return checkInt;
    }

    public int getEmptyLineWidth(){
        boolean check = true;
        int checkInt=0;
        if(panel.getmoveX()<0){
            for(int j=0 ;j<pieceSize;j++){
                check = true;
                for(int i = 0; i<pieceSize;i++){
                    if(pieceMatrix[i][j]!=0){
                        check = false;
                        break;
                    }
                }
                if(check==false){
                    checkInt=j;
                    break;
                }
            }
        }else if(panel.getmoveX()>0){
            for(int j=pieceSize-1 ;j>=0;j--){
                check = true;
                for(int i = 0; i<pieceSize;i++){
                    if(pieceMatrix[i][j]!=0){
                        check = false;
                        break;
                    }
                }
                if(check==false){
                    checkInt=j+1;
                    break;
                }
            }
        }
        return checkInt;
    }

    public void placePiece(){
        for(int i=0;i<pieceSize;i++){
            if(yLocation+i>board.getHeight()-1){
                break;
            }

            if(checkEmptyLineY(i)== false){
                for(int j=0;j<pieceSize;j++){
                    if(pieceMatrix[i][j]!=0){
                        board.setTile(xLocation+j, yLocation+i, pieceMatrix[i][j]);
                    }
                }
            }
        }
    }

    public void clearPiece(){
        for(int i=0;i<pieceSize;i++){
            for(int j=0;j<pieceSize;j++){
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
            case 2:
                pieceSize= 4;
                pieceMatrix=i_piece.clone();
            break;
            case 3:
                pieceSize= 2;
                pieceMatrix=o_piece.clone();
            break;
            case 4:
                pieceSize= 3;
                pieceMatrix=l_piece.clone();
            break;
            case 5:
                pieceSize= 3;
                pieceMatrix=j_piece.clone();
            break;
            case 6:
                pieceSize= 3;
                pieceMatrix=s_piece.clone();
            break;
            case 7:
                pieceSize= 3;
                pieceMatrix=z_piece.clone();
            break;
        }

    }

    public boolean checkOverlap(int checkx,int checky){
        boolean check = false;

        for(int i=0;i<pieceSize;i++){
            if(checkEmptyLineY(i)==false){
                for(int j=0;j<pieceSize;j++){
                    if(checky+i<board.getHeight()){
                        if(board.getTileValue(checkx+j, checky+i) !=0 && pieceMatrix[i][j]!=0){
                            check = true;
                            break;
                        }else{
                            check=false;
                        }
                    }
                }
                if(check == true){
                    break;
                }
            }
        }
        return check;
    }

    private boolean checkOverlap(int checkx,int checky, int[][] tempMatrix) {
        boolean check = false;

        for(int i=0;i<pieceSize;i++){
            if(checkEmptyLineY(i)==false){
                for(int j=0;j<pieceSize;j++){
                    if(checky+i<board.getHeight()){
                        if(board.getTileValue(checkx+j, checky+i) !=0 && tempMatrix[i][j]!=0){
                            check = true;
                            break;
                        }else{
                            check=false;
                        }
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
            while(!(checkOverlap(xLocation, yLocation+1)==true || yLocation+getEmptyLineHeight()==board.getHeight())){
                yLocation+=1;    
            }
            dropDelay = dropDelayLim;
            panel.resethardDrop();
        }else if(panel.gethardDrop()==false){
            clearPiece();
            if(checkOverlap(xLocation, yLocation+1)==false && yLocation+getEmptyLineHeight()<board.getHeight()){
                yLocation+=1;
            }else if(checkOverlap(xLocation, yLocation+1)==true || yLocation+getEmptyLineHeight()==board.getHeight()){
                dropDelay+=1;
            }
        }
        placePiece();
        dropDelayCheck();
    }

    public void moveX(){
        if((panel.getmoveX()>0 &&  xLocation+getEmptyLineWidth() != board.getWidth()) || panel.getmoveX()<0 && xLocation!=0){  
            clearPiece();
            if(checkOverlap(xLocation+panel.getmoveX(), yLocation)==false){
                dropDelay=0;
                xLocation+=panel.getmoveX();
                panel.resetmoveX();
                placePiece();
            }
        }
        
    }

    public void moveZ(){
        int tempMatrix[][]= new int[pieceSize][pieceSize];
        int ctrlc;
        int ctrlv;
        
        
        if(panel.getmoveZ()==1){
            dropDelay=0;
            clearPiece();
            for(int i=0;i<pieceSize;i++){
                ctrlv=i;
                for(int j=pieceSize-1;j>=0;j--){
                    ctrlc=pieceSize-1-i;
                    tempMatrix[j][ctrlv]= pieceMatrix[ctrlc][j];
                }
            }
            if(checkOverlap(xLocation, yLocation, tempMatrix)==false){
                pieceMatrix=tempMatrix.clone();
                panel.resetmoveZ();
                placePiece();
            }
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

    public int something(){
        return getEmptyLineHeight();
    }
}
