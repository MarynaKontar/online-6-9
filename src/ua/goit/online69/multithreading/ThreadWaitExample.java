package ua.goit.online69.multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitExample {
    public static void main(String []args) throws InterruptedException {
//        LockSupport и парковка потоков
//        Начиная с Java 1.6 появился интересный механизм, называемый LockSupport.
//        Данный класс ассоциирует с каждым потоком, который его использует, "permit" или разрешение.
//        Вызов метода park возвращается немедленно, если permit доступен, занимая этот самый permit в процессе вызова.
//        Иначе он блокируется.
//        Вызов метода unpark делает permit доступным, если он ещё недоступен.
//        Permit есть всего 1.
        Object lock = new Object();
//        task будет ждать, пока его не оповестят через lock
        Runnable task = () -> {
            synchronized(lock) {
                try {
                    Thread.currentThread().sleep(5000);
                    System.out.println("enter task");
                    lock.wait();
//                    while (true) {}
                } catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            // После оповещения нас мы будем ждать, пока сможем взять лок
            System.out.println("thread");
        };
        Thread taskThread = new Thread(task);
        taskThread.start();
        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
        Thread.currentThread().sleep(10000);
        System.out.println("main");
        synchronized(lock) { // забираем лок (synchronized)
            lock.notify(); // оповещаем через лок
        }

//        Статус потока будет WAITING, но JVisualVM различает wait от synchronized и park от LockSupport. Почему так
//        важен этот LockSupport? Обратимся снова к Java API и посмотрим про Thread State WAITING. Как видим, в него
//        можно попасть только тремя способами. 2 способа - это wait и join. А третий - это LockSupport.


//
////        Локи в Java построены так же на LockSupport и представляют более высокоуровневые инструменты.
////        Давайте попробуем воспользоваться таковым.
//        Lock lock = new ReentrantLock();
//        Runnable task = () -> {
//            lock.lock();
//            System.out.println("Thread");
//            lock.unlock();
//        };
//        lock.lock();
//
//        Thread th = new Thread(task);
//        th.start();
//        System.out.println("main");
//        Thread.currentThread().sleep(2000);
//        lock.unlock();
//
////        lock ожидает, пока кто-то освободит ресурс. Если посмотреть в JVisualVM, то мы увидим, что новый поток будет
////        запаркован, пока main поток не отдаст ему лок

    }
}
