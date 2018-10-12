package shuffle;

import java.util.Random;

public class Shuffle {

    public static void shuffle(int[] array) {
        Random random = new Random();
        int length = array.length;
        for (int i = length - 1; i > 1; i--) {
            // 把随机位置交换到当前位置
            swap(array, i, random.nextInt(i - 1));
        }
    }

    private static void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
