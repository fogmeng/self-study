package leetcode.binarySearch;

/**
 * @Author ll
 * @Date 2018/10/24 9:45
 */
public class GuessGame {

    private static int myNumber = 9;

    public int guess(int n){

        if(n == myNumber){
            return 0;
        }else if (n < myNumber) {
            return  1;
        }
        return -1;
    }

}
