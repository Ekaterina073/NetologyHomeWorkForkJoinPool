import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MainFork {
    public static void main(String[] args) {
        long m = System.currentTimeMillis();
        int nThreads = Runtime.getRuntime().availableProcessors();
        //размер массива
        int SIZE = 100000;
        int[] arr = new int[SIZE];
        randomArr(arr);
        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
        Integer result = forkJoinPool.invoke(new Sum(arr, 0, arr.length));
        System.out.println("Результат:" + result);
        System.out.println("Продолжительность (мс)" + (double) (System.currentTimeMillis() - m));
    }

    static class Sum extends RecursiveTask<Integer> {
        int low;
        int high;
        int[] array;

        Sum(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            if (high - low <= 10) {
                int sum = 0;
                for (int i = low; i < high; ++i)
                    sum += array[i];
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                Sum left = new Sum(array, low, mid);
                Sum right = new Sum(array, mid, high);
                left.fork();
                int rightResult = right.compute();
                int leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

    private static void randomArr(int[] arr) {
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(100);
            System.out.println(arr[i]);
        }
    }
}
