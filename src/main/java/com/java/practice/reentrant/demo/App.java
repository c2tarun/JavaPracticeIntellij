package com.java.practice.reentrant.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tarun on 10/11/15.
 */
public class App {

    private static ThreadMXBean bean = ManagementFactory.getThreadMXBean();

    public static void main(String[] args) throws InterruptedException {
        DeadlockRunner deadlockRunner = new DeadlockRunner();

        ScheduledExecutorService deadLockDetector = Executors.newScheduledThreadPool(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadlockRunner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadlockRunner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        deadLockDetector.schedule(new Runnable() {
            @Override
            public void run() {
                long[] deadlockedThreads = bean.findDeadlockedThreads();
                if (deadLockDetector != null) {
                    System.out.println("Deadlock found");
                }

            }
        }, 100, TimeUnit.MILLISECONDS);

        t1.join();
        t2.join();

        deadlockRunner.finished();


    }
}
