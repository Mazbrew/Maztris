public class Board {
    private  int width;
    private  int height;
    private  int inviswidth=2;
    private final int[][] boardarray;

    public Board(int width,int height){
        this.width = width+(2*inviswidth);
        this.height= height+1;
        boardarray = new int[this.width][this.height];
        clearBoard();
    }

    public void clearBoard(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(j<inviswidth){
                    boardarray[j][i] = 8;
                }else if(j>width-1-inviswidth){
                    boardarray[j][i]=8;
                }else{
                    boardarray[j][i]=0;
                }
            }
        }
    }

    public void checkLineClear(){
        int tileCount;
        for(int i=0;i<height;i++){
            tileCount = 0;
            for(int j=0;j<width;j++){
                switch(boardarray[j][i]){
                    case 0:
                        break;
                    default:
                        tileCount++;
                }
            }
            if(tileCount==width){
                for(int j=0;j<width;j++){
                    switch(boardarray[j][i]){
                        case 8:
                            break;
                        default:
                            boardarray[j][i]=0;
                    }
                }
                moveAllDown(i);
                i--;
            }
        }
    }

    public void moveAllDown(int i){
        int tempArray[] = new int[width];
        for(;i>1;i--){
            for(int j=0;j<width-1;j++){
                tempArray[j]=boardarray[j][i];
                boardarray[j][i]=boardarray[j][i-1];
                boardarray[j][i-1]=tempArray[j];
            }
        }
    }

    public int[][] getBoardarray(){
        return boardarray;
    }

    public int getTileValue(int x, int y){
        return boardarray[x][y];
    }

    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public int getinviswidth(){
        return inviswidth;
    }

    public void setTile(int x , int y, int type){
        boardarray[x][y] =type;
    }
}
