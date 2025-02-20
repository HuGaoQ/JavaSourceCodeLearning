package com.learnjava.concurent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
        completableFutureDemo.testCompletedFuture();
        completableFutureDemo.testCompletedFutureAsync();
    }

    public void testCompletedFuture() throws Exception {
        String expectedValue = "the expected value";
        CompletableFuture<String> alreadyCompleted = CompletableFuture.completedFuture(expectedValue);
        System.out.println(alreadyCompleted.get());
    }

    public void testCompletedFutureAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟一个耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Async result";
        });
        future.thenAccept(result -> System.out.println("result: " + result));
    }

}
