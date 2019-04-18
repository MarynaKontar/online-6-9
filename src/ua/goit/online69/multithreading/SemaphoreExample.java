package ua.goit.online69.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
//        Семафоры
//        Самое простое средство контроля за тем, сколько потоков могут одновременно работать — семафор.
//        Как на железной дороге. Горит зелёный — можно. Горит красный — ждём. Что мы ждём от семафора? Разрешения.
//        Разрешение на английском — permit. Чтобы получить разрешение — его нужно получить, что на английском будет
//        acquire. А когда разрешение больше не нужно мы его должны отдать, то есть освободить его или избавится от него,
//        что на английском будет release. Посмотрим, как это работает.
        Semaphore semaphore = new Semaphore(0);
        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println("Finished");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(5000);
        semaphore.release(1);
    }

}
