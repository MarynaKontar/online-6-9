package ua.goit.online69.multithreading;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        https://javarush.ru/groups/posts/2078-threadom-java-ne-isportishjh--chastjh-v---executor-threadpool-fork-join-pool
//        Thread'ом Java не испортишь: Часть V - Executor
        Runnable task = () -> System.out.println("Task executed");
        Executor executor = (runnable) -> {
            new Thread(runnable).start();
        };
        executor.execute(task);


//        ExecutorService
//        Ещё раз вспомним. У нас есть Executor для execute (т.е. выполнения) некой задачи в потоке, когда реализация
//        создания потока скрыта от нас. У нас есть ExecutorService — особый Executor, который имеет набор возможностей
//        по управлению ходом выполнения. И у нас есть фабрика Executors, которая позволяет создавать ExecutorService.
        Callable<String> task1 = () -> Thread.currentThread().getName();
        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorService service1 = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Future result = service.submit(task1);
            Future result1 = service1.submit(task1);
            System.out.println(result.get());
            System.out.println(result1.get());
        }
        service.shutdown();
        service1.shutdown();
//
//        pool-1-thread-1
//        pool-2-thread-1
//        pool-1-thread-2
//        pool-2-thread-2
//        pool-1-thread-1
//        pool-2-thread-1
//        pool-1-thread-2
//        pool-2-thread-2
//        pool-1-thread-1
//        pool-2-thread-1

//        Как мы видим, мы указали фиксированный пул потоков (Fixed Thread Pool) размером 2. После чего мы поочередно
//        отправляем в пул задачи. Каждая задача возвращает строку (String), содержащую имя потока
//        (currentThread().getName()). Важно в самом конце выполнить shutdown для ExecutorService,
//        потому что в противном случае наша программа не завершится.
//
//        В фабрике Executors есть и другие фабричные методы. Например, мы можем создать пул всего из одного потока —
//        newSingleThreadExecutor или пул с кэшированием newCachedThreadPool, когда потоки будут убираться из пула,
//        если они простаивают 1 минуту.
//
//        На самом деле, за этими ExecutorService прячется блокирующая очередь, в которую помещаются задачи и из
//        которой эти задачи выполняются.

//        Супер упрощённо - BlockingQueue (блокирующая очередь) блокирует поток, в двух случаях:
//        поток пытается получить элементы из пустой очереди
//        поток пытается положить элементы в полную очередь

//        Если посмотреть на реализацию фабричных методов, то мы увидим, как они устроены. Например:
//
//        public static ExecutorService newFixedThreadPool(int nThreads) {
//            return new ThreadPoolExecutor(nThreads, nThreads,
//                    0L, TimeUnit.MILLISECONDS,
//                    new LinkedBlockingQueue<Runnable>());
//        }
//
//        или
//
//        public static ExecutorService newCachedThreadPool() {
//            return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                    60L, TimeUnit.SECONDS,
//                    new SynchronousQueue<Runnable>());
//        }

    }
}
