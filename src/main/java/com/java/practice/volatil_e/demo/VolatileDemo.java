package com.java.practice.volatil_e.demo;

/**
 * Created by tarun on 10/11/15.
 */
public class VolatileDemo {
    static volatile int MY_VALUE = 0;
    //Comment above line and uncomment below line.
//    static int MY_VALUE = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int tValue = MY_VALUE;
                while (tValue < 5) {
                    if (tValue != MY_VALUE) {
                        tValue = MY_VALUE;
                        System.out.println("received updated value: " + MY_VALUE);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(MY_VALUE < 5) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    MY_VALUE++;
                    System.out.println("Update MYVALUE: " + MY_VALUE);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
