package lintcode;

public class digitCounts {

    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public static int digitCounts(int k, int n) {
        // write your code here
        int result = 0;
        for (int i = 0; i <= n  ; n++ ){
            String str = i+"";
            for (char c:str.toCharArray() ) {
                if(String.valueOf(c).equals(k+"")) result ++;
            }
        }

        return result;

    }

    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public static int digitCounts1(int k, int n) {
        // write your code here
        int cnt = 0;
        for (int i = 0; i <= n  ; n++ ){
            cnt += singleCount(i,k);
        }

        return cnt;

    }

    private static int singleCount(int i, int k) {
        if (i == 0 && k==0) {
            return 1;
        }

        int cnt = 0;

        while (i>=0){

            if (i%10 == k ){
                cnt ++;
            }
            i /=10;

        }

        return  cnt;
    }

}
