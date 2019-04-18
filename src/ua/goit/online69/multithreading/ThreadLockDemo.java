package ua.goit.online69.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ThreadLockDemo {
//    https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/java-intrinsic-locks.html

//    How threads are blocked on 'intrinsic lock'?
//    The 'synchronized' method calls in a given thread will wait/block if there's already another thread is executing
//    the same method or some other 'synchronized' method of the same instance. From a programmer perspective, the block
//    is similar to other blocking calls e.g. Thread.join(). The method call blocks to get the instrict lock at the point
//    of the synchronized method call as shown in the following example.
    public static void main (String[] args) throws InterruptedException {
        ThreadLockDemo demo = new ThreadLockDemo();
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 before call "+ LocalDateTime.now());
            demo.syncMethod("from thread1");
            System.out.println("thread1 after call "+LocalDateTime.now());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 before call "+LocalDateTime.now());
            demo.syncMethod("from thread2");
            System.out.println("thread2 after call "+LocalDateTime.now());
        });

        thread1.start();
        thread2.start();

//        thread2 before call 2019-04-17T11:09:04.702
//        thread1 before call 2019-04-17T11:09:04.702
//        in the sync method from thread2 2019-04-17T11:09:04.711
//        thread2 after call 2019-04-17T11:09:09.740
//        in the sync method from thread1 2019-04-17T11:09:09.740
//        thread1 after call 2019-04-17T11:09:14.741

//        Per above output thread1 call to syncMethod acquired the lock on ThreadLockDemo instance 'demo' first.
//        This call starts executing the method almost immediately.
//        During this execution thread2 call to syncMethod is blocked at line: demo.syncMethod("from thread2");
//        The output might be different on different machines. I'm running windows 10 on a 4 core/8 logical processors
//        machine. But either of the two threads will be blocked during the sleeping time of other.

    }

    private synchronized void syncMethod (String msg) {
        System.out.println("in the sync method "+msg+" "+LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//    Thread Starvation
//    Thread starvation occurs when one or more threads are constantly blocked to access a shared resource in favor of
//    other threads.
//    Thread starvation can occur as a result of assigning inappropriate priorities to different threads or it could
//    be the result of wrong synchronized block logic.