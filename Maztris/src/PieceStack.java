import java.util.ArrayList;
import java.util.Collections;

//stack for checking the pieces
public class PieceStack {
    int[] typeArray= {1,2,3,4,5,6,7};
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