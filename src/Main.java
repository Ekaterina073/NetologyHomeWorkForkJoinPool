import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        long m = System.currentTimeMillis();
        int SIZE = 17000;
        int[] arr = new int[SIZE];
        randomArr(arr);
        int result = sumArr(arr);
        System.out.println("Результат:" + result);
        System.out.println("Продолжительность (мс)" + (double) (System.currentTimeMillis() - m));
    }

    private static int sumArr(int[] arr) {
        if (arr.length == 0) return 0;
        return arr[0] + sumArr(Arrays.copyOfRange(arr, 1, arr.length));
    }

    private static void randomArr(int[] arr) {
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(100);
            System.out.println(arr[i]);
        }
    }
}
