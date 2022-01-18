import java.util.ArrayList;
import java.util.Collections;

//stack for checking the pieces
public class PieceStack {
    //int[] typeArray= {1,1,1,1,1,1,1};
    int[] typeArray= {2,2,2,2,2,2,2};
    //int[] typeArray= {3,3,3,3,3,3,3};
    //int[] typeArray= {4,4,4,4,4,4,4};
    //int[] typeArray= {5,5,5,5,5,5,5};
    //int[] typeArray= {6,6,6,6,6,6,6};
    //int[] typeArray= {7,7,7,7,7,7,7};
    //int[] typeArray= {1,2,3,4,5,6,7};
    
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