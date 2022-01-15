public class Board {
    private  int width;
    private  int height;
    private final int[][] boardarray;

    public Board(int width,int height){
        this.width = width;
        this.height= height;
        boardarray = new int[width][height];
        clearBoard();
    }

    public void clearBoard(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                boardarray[j][i] = 0;
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

    public void setPiece(int x , int y, int type){
        boardarray[x][y] =type;
    }
}
