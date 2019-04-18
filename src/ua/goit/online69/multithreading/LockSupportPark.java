package ua.goit.online69.multithreading;

import java.util.concurrent.locks.LockSupport;

public class LockSupportPark {
    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(0);
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            // Просим разрешение и ждём, пока не получим его
//            e.printStackTrace();
//        }
//        System.out.println("Hello, World!");

        Runnable task = () -> {
            //Запаркуем текущий поток
            System.err.println("Will be Parked");// 1
            LockSupport.park();
            // Как только нас распаркуют - начнём действовать
            System.err.println("Unparked"); // 3
        };
        Thread th = new Thread(task);
        th.start();
        Thread.currentThread().sleep(8000);
        System.err.println("Thread state: " + th.getState()); // 2

        LockSupport.unpark(th);
        Thread.currentThread().sleep(10000);

//        Will be Parked
//        Thread state: WAITING
//        Unparked

    }
}
