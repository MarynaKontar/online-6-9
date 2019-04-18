package ua.goit.online69.multithreading;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int threadBound = 2;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, threadBound,
                0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return Thread.currentThread().getName();
        };
        for (int i = 0; i < threadBound + 1; i++) {
            threadPoolExecutor.submit(task);
            System.out.println("i" + i);
        }
        threadPoolExecutor.shutdown();
    }

}
