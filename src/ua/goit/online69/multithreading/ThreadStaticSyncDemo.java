package ua.goit.online69.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ThreadStaticSyncDemo {
    public static void main (String[] args) throws InterruptedException {
//        https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/java-intrinsic-locks.html

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 before call "+ LocalDateTime.now());
            syncMethod("from thread1");
            System.out.println("thread1 after call "+LocalDateTime.now());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 before call "+LocalDateTime.now());
            syncMethod("from thread2");
            System.out.println("thread2 after call "+LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
    }

    private static synchronized void syncMethod (String msg) {
        System.out.println("in the sync method "+msg+" "+LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    thread1 before call 2019-04-17T11:40:37.476
//    in the sync method from thread1 2019-04-17T11:40:37.481
//    thread2 before call 2019-04-17T11:40:37.476
//    thread1 after call 2019-04-17T11:40:42.484
//    in the sync method from thread2 2019-04-17T11:40:42.484
//    thread2 after call 2019-04-17T11:40:47.485

//    thread2 before call 2019-04-18T11:48:07.496
//    thread1 before call 2019-04-18T11:48:07.496
//    in the syncMethod2 from thread2 2019-04-18T11:48:07.499
//    in the syncMethod1 from thread1 2019-04-18T11:48:12.500
//    thread2 after call 2019-04-18T11:48:12.500
//    thread1 after call 2019-04-18T11:48:17.501
//    любой из двух потоков может перым захватить монитор (synchronized) и выполнять sleep, потом отпускает монитор и его захватывает другой поток
}
