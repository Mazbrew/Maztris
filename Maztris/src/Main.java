public class Main {
    public static void main(String[] args) throws Exception {
        Board board = new Board(10,20);
        FrameStuff frame = new FrameStuff();

        for(int i =0; i<board.getHeight();i++){
            for(int j =0; j<board.getWidth();j++){
                System.out.print(board.getTileValue(j, i));
            }
            System.out.println();
        }
    }
}
