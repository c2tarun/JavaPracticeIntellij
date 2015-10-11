package com.java.practice.reentrant.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tarun on 10/11/15.
 */
public class ReentrantRunner {

    private int count = 0;
    private Lock lock = new ReentrantLock();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void secondThread() throws InterruptedException {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
