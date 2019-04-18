package ua.goit.online69.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class MultipleSyncMethodsDemo {
//    https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/java-intrinsic-locks.html
//    Intrinsic lock is on object, not on a method
//    As mentioned before, if a thread has acquired the lock, other threads will be blocked even if they are calling
//    other 'synchronized' methods of the same object. Non-synchronized methods won't be blocked.
    public static void main (String[] args) throws InterruptedException {
        MultipleSyncMethodsDemo demo = new MultipleSyncMethodsDemo();
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 before call "+ LocalDateTime.now());
            demo.syncMethod1("from thread1");
            System.out.println("thread1 after call "+LocalDateTime.now());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 before call "+LocalDateTime.now());
            demo.syncMethod2("from thread2");
            System.out.println("thread2 after call "+LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
//        thread1 before call 2019-04-18T10:50:03.799
//        thread2 before call 2019-04-18T10:50:03.799
//        in the syncMethod1 from thread1 2019-04-18T10:50:03.808
//        thread1 after call 2019-04-18T10:50:08.810
//        in the syncMethod2 from thread2 2019-04-18T10:50:08.810
//        thread2 after call 2019-04-18T10:50:13.811
    }

    private synchronized void syncMethod1 (String msg) {
        System.out.println("in the syncMethod1 "+msg+" "+LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syncMethod2 (String msg) {
        System.out.println("in the syncMethod2 "+msg+" "+LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
