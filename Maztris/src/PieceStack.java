import java.util.ArrayList;
import java.util.Collections;

//stack for checking the pieces
public class PieceStack {
    int[] typeArray= {1,1,1,1,1,1,1};
    ArrayList<Integer> stack = new ArrayList<Integer>();

    public PieceStack(){
        fillstack();
    }

    public void fillstack(){
        for(int i=0; i< typeArray.length;i++){
            stack.add(typeArray[i]);
        }
        Collections.shuffle(stack);
    }
}