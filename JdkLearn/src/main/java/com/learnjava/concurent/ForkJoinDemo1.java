package com.learnjava.concurent;

import java.util.concurrent.*;

public class ForkJoinDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] array1 = {100, 400, 200, 90, 80, 300, 600, 10, 20, -10, 30, 2000, 1000};
        int[] array2 = {100, 400, 200, 90, 80, 300, 600, 10, 20, -10, 30, 2000, 3000};
        MaxNumber task = new MaxNumber(array1, 0, array1.length - 1);
        ForkJoinTask<Integer> fork = forkJoinPool.submit(task);
        System.out.println("Result: " + fork.get(1, TimeUnit.MINUTES));
        maxNumber(array1, array2);
    }

    private static void maxNumber(int[] array1, int[] array2) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int j : array1) {
            if (max1 < j) {
                max1 = j;
            }
        }

        for (int i : array2) {
            if (max2 < i) {
                max2 = i;
            }
        }
        System.out.println(Math.max(max1, max2));
    }

    private static class MaxNumber extends RecursiveTask<Integer> {
        private final int[] array;
        private final int index0;
        private final int index1;

        public MaxNumber(int[] array, int index0, int index1) {
            this.array = array;
            this.index0 = index0;
            this.index1 = index1;
        }

        @Override
        protected Integer compute() {
            int max = Integer.MIN_VALUE;
            int value = 2;
            if ((index1 - index0) <= value) {
                for (int j : array) {
                    max = Math.max(max, j);
                }
            } else {
                int mid = index0 + (index1 - index0) / 2;
                MaxNumber lMax = new MaxNumber(array, index0, mid);
                MaxNumber rMax = new MaxNumber(array, mid + 1, index1);

                lMax.fork();
                rMax.fork();

                Integer l = lMax.join();
                Integer r = rMax.join();
                max = Math.max(l, r);
            }
            return max;
        }
    }
}
