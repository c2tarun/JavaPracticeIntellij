package com.java.practice.wait.notify.demo;

import java.util.Scanner;

/**
 * Created by tarun on 10/11/15.
 */
public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running");
            wait();
            System.out.println("Producer thread resumed");
        }

    }

    public void consume() throws InterruptedException {
            Thread.sleep(2000);

        Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
        }
    }
}
