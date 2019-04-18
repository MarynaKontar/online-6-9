package ua.goit.online69.multithreading;

import java.util.concurrent.TimeUnit;

public class MultithreadingExamples {
    public static void main(String[] args) throws InterruptedException {
//        while (true) {
//            //Do nothing
//        }


//        Каждый поток входит в какую-то группу (ThreadGroup).
//        А группы могут входит друг в друга, образовывая некоторую иерархию или структуру.
//        Группы позволяют упорядочить управление потоками и вести их учёт.
        Thread currentThread = Thread.currentThread();
        ThreadGroup threadGroup = currentThread.getThreadGroup();
        System.out.println("Thread: " + currentThread.getName());
        System.out.println("Thread Group: " + threadGroup.getName());
        System.out.println("Parent Group: " + threadGroup.getParent().getName());


//        Помимо групп потоки имеют свой обработчик исключений.
//        Деление на ноль вызовет ошибку, которая будет перехвачена обработчиком. Если обработчик не указывать самому,
//        то будет работать реализация обработчика по умолчанию, которая будет в StdError выводить стэк ошибки.
        Thread th = Thread.currentThread();
        th.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                StackTraceElement[] stackTraces = e.getStackTrace();
                String stackTrace = "";
                for (StackTraceElement trace : stackTraces) {
                    stackTrace = stackTrace + trace;
                }
                System.out.println("Возникла ошибка: " + stackTrace + ": " + e.getMessage());
            }
        });
//        System.out.println(2/0);




        //СОЗДАНИЕ ПОТОКА
//        Runnable task = new Runnable() {
//            public void run() {
//                System.out.println("Hello, World!");
//            }
//        };
//        Runnable is @FunctionalInterface (have only one method run()), so
        Runnable task1 = () -> {
//            while (true) {}
            System.out.println("Hello, World task1!");
        };
        Thread thread1 = new Thread(task1);
        thread1.start();// in visualvm we can see Thread-0 and Thread-2 that is STARTED threads thread1 and thread3

        Runnable task2 = () -> {
//            while (true) {}
            System.out.println("Hello, World task2!");

        };
        Thread thread2 = new Thread(task2); // in visualvm we can`t see Thread-1 and Thread-3 because they aren't STARTED, but for them reserved names Thread-1 and Thread-3
        Thread thread3 = new Thread(task2);
        Thread thread4 = new Thread(task2);// in visualvm we can`t see Thread-1 and Thread-3 because they aren't STARTED
//        thread1 = new Thread(task2);
        thread3.start();// in visualvm we can see Thread-0 and Thread-2 that is STARTED threads thread1 and thread3

//    Run two threads (thread1 and thread3) => in visualVm program can see for process MultithreadingExamples 13 threads:
//    11 demon threads and 2 non demon threads: Thread-0 (thread1) and Thread-2(thread3)



//        yield
//        Метод Thread.yield() загадочный и редко используемый. Существует много вариаций описания в интернете.
//        Вплоть до того, что некоторые пишут про какую-то очередь потоков, в которой поток переместится вниз с учётом
//        приоритетов потоков. Кто-то пишет, что поток изменит статус с running на runnable (хотя разделения на эти
//        статусы нет и Java их не различает). Но на самом деле всё куда неизвестнее и в каком-то смысле проще.
//
//        На тему документации метода yield есть баг "JDK-6416721 : (spec thread) Fix Thread.yield() javadoc".
//        Если прочитать его, то понятно, что на самом деле метод yield лишь передаёт некоторую рекомендацию
//        планировщику потоков Java, что данному потоку можно дать меньше времени исполнения. Но что будет на самом
//        деле, услышит ли планировщик рекомендацию и что вообще он будет делать - зависит от реализации JVM и
//        операционной системы. А может и ещё от каких-то других факторов. Вся путаница сложилась, скорее всего,
//        из-за переосмысления многопоточности в процессе развития языка Java.
//        Подробнее можно прочитать в обзоре "Brief Introduction to Java Thread.yield()".


//        Sleep - Засыпание потока
//        Поток в процессе своего выполнения может засыпать. Это самое простой тип взаимодействия с другими потоками.
//        В операционной системе, на которой установлена виртуальная Java машина, в которой выполняется Java код, есть
//        свой планировщик потоков, называемый Thread Scheduler. Именно он решает, какой поток когда запускать.
//        Программист не может из java кода напрямую взаимодействовать с этим планировщиком, но он может через
//        JVM попросить планировщик на какое-то время поставить поток на паузу, "усыпить" поток.
          Runnable task = () -> {
            try {
                int secToWait = 1000 * 5;
//                Thread.currentThread().sleep(secToWait);
//                Thread.sleep(secToWait);
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Waked up");//in visualvm Thread-4 sleep 5 seconds (purple color) and than running (green)
//                while (true) {}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          };
          Thread thread = new Thread(task);
          thread.start();




//        Прерывание потока или Thread.interrupt
//        Всё дело в том, что пока поток ожидает во сне, кто-то может захотеть прервать это ожидание. На этот случай
//        мы обрабатываем такое исключение. Сделано это было после того, как метод Thread.stop объявили Deprecated,
//        т.е. устаревшим и нежелательным к использованию. Причиной тому было то, что при вызове метода stop
//        поток просто "убивался", что было очень непредсказуемо. Мы не могли знать, когда поток будет остановлен,
//        не могли гарантировать консистентность данных. Представте, что вы пишете данные в файл и тут поток уничтожают.
//        Поэтому, решили, что логичнее будет поток не убивать, а информировать его о том, что ему следует прерваться.
//        Как на это реагировать - дело самого потока.
        Runnable task3 = () -> {
//            try {
//                TimeUnit.SECONDS.sleep(25);
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
//            }
            while(!Thread.currentThread().isInterrupted()) {
                //Do some work
            }
            System.out.println("Finished");
        };
        Thread thread5 = new Thread(task3);
        thread5.start();
        thread5.interrupt();// сразу выдаст "Interrupted" без sleep 25



//        Join - Ожидание завершения другого потока
//        Самым простым типом ожидания является ожидание завершения другого потока.
        Runnable task4 = () -> {
            try {
                TimeUnit.SECONDS.sleep(25);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread6 = new Thread(task4);
        thread6.start();
        thread6.join();
        System.out.println("Finished");
//        while(true) {}
//        В данном примере новый поток будет спать 25 секунд. В то же время, главный поток main будет ждать, пока спящий
//        поток не проснётся и не завершит свою работу.


    }
}
