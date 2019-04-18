package ua.goit.online69.multithreading;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureExample {
    public static void main(String []args) throws Exception {
//        https://javarush.ru/groups/posts/2065-threadom-java-ne-isportishjh--chastjh-iv---callable-future-i-druzjhja
//        CompletableFuture
//        Шло время и в Java 1.8 появился новый класс, который зовётся CompletableFuture. Он реализует интерфейс
//        Future, то есть наши task будут выполнены в будущем и мы сможем выполнить get и получить результат.
//        Но ещё он реализует некоторый CompletionStage. Судя из перевода уже понятно его назначение -
//        Это некий этап (Stage) каких-то вычислений.


//        Вспомним про функциональные интерфейсы, про которые мы писали ранее.
//        У нас есть функция (Function), которая принимает А и возвращает Б. Имеет единственный метод - apply (применить).
//        У нас есть потребитель (Consumer), которая принимает А и ничего не возвращает (Void). Имеет единственный метод -
//        accept (принять).
//        У нас есть запускаемый в потоке код Runnable, который не принимает и не возвращает. Имеет единственный метод -
//        run (запустить).
//        Второе, что надо помнить, что CompletalbeFuture в своей работе использует Runnable, потребителей и функции.
//        Помня это Вы всегда сможете вспомнить, что с CompletableFuture можно делать так:
        AtomicLong longValue = new AtomicLong(0);
        Runnable task = () -> longValue.set(new Date().getTime());
        Function<Long, Date> dateConverter = (longvalue) -> new Date(longvalue);
        Consumer<Date> printer = date -> {
            System.out.println(date);
            System.out.flush();
        };
        // CompletableFuture computation
        CompletableFuture.runAsync(task)
                .thenApply((v) -> longValue.get())
                .thenApply(dateConverter)
                .thenAccept(printer);
    }
}
