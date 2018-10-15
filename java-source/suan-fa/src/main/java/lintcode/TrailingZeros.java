package lintcode;

public class TrailingZeros {

    public static long trailingZeros(long n){
        int sum = 0;
        while(n != 0){
            sum += n/5;
            n /= 5;
        }
        return  sum;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeros(31   ));
    }

}
