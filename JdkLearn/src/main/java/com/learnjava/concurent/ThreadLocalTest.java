package com.learnjava.concurent;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);

        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }

                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();

    }

    private static class InnerClass {
        public void add(String newStr) {
            //利用Counter类来实例化StringBuilder
            StringBuilder stringBuilder = Counter.COUNTER.get();

            //将newStr存进ThreadLocal里
            Counter.COUNTER.set(stringBuilder.append(newStr));
        }

        /**
         * 打印ThreadLocal的信息
         */
        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.COUNTER.hashCode(),
                    Counter.COUNTER.get().hashCode(),
                    Counter.COUNTER.get().toString());
        }

        public void set(String word) {
            Counter.COUNTER.set(new StringBuilder(word));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.COUNTER.hashCode(),
                    Counter.COUNTER.get().hashCode(),
                    Counter.COUNTER.get().toString());
        }
    }

    private static class Counter {
        private static final ThreadLocal<StringBuilder> COUNTER = ThreadLocal.withInitial(StringBuilder::new);
    }
}
