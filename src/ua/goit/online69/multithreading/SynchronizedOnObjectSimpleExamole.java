package ua.goit.online69.multithreading;

public class SynchronizedOnObjectSimpleExamole {
    public static void main(String[] args) throws InterruptedException {
//        Здесь главный поток сначала отправляет задачу task в новый поток, а потом сразу же "захватывает" лок и выполняет
//        с ним долгую операцию (8 секунд). Всё это время task не может для своего выполнения зайти в блок synchronized,
//        т.к. лок уже занят.
//        Если поток не может получить лок - он будет ждать у монитора до тех пор, пока не сможет получить лок.
//        Как только поток получает лок, он продолжает выполнение. Когда поток выходит из под монитора, он освобождает
//        лок.
        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                System.out.println("thread");
//                while (true) {}
            }
        };
//
//        Thread th1 = new Thread(task);
//        th1.start();
//        synchronized (lock) {
//            for (int i = 0; i < 8; i++) {
//                Thread.sleep(3000);
//                System.out.print("  " + i + " th1 is " + th1.getState());
//            }
//            System.out.println(" ...");
//        }


        // главный поток передаст task в два новых потока, th1 и th2, th1 даже успеет выполниться,
        // потом main поток захватывает лок ("main thread; th1 is...)
        Thread th1 = new Thread(task);
        Thread th2 = new Thread(task);
        th1.start();// успеет выполниться (sout("thread"))? но только если она быстрая (sout, например).
        // Если же в task поставить for или while, то не успеет зайти в него, как главный поток захватит лок и перейдет к след. строке
        th2.start();// не успеет выполниться и перейдет к след. строке
        Thread.sleep(2000);// если ввести єту строчку, то пока главный поток будет спать, th1 и th2 успеют захватить лок
        synchronized (lock) {
            while (true) {
                System.out.print("main thread; th1 is " + th1.getState());
            }
        }


    }
}
