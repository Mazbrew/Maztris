public class Board {
    private  int width;
    private  int height;
    private final int[][] boardarray;

    public Board(int width,int height){
        this.width = width;
        this.height= height;
        boardarray = new int[width][height+1];
        clearBoard();
    }

    public void clearBoard(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                boardarray[j][i] = 0;
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
                    boardarray[j][i]=0;
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

    public void setTile(int x , int y, int type){
        boardarray[x][y] =type;
    }
}
