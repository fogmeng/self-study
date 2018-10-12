package FindSuanFa;

public class FindSuanFa {

    public static int binarySearch(int[] array,int value){

        int left = 0;
        int right = array.length;

        while (left <= right){
            int middle = left + (right - left)>>1;
            if (array[middle] == value){
                return  middle;
            }else if (array[middle] > value) {
                right = middle -1;
            } else if (array[middle] < value){
                left = middle + 1;
            }
        }

        return  -1;

    }
}
