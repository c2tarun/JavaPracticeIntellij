package com.java.practice;

import com.google.common.collect.ImmutableList;

/**
 * Created by tarun on 10/11/15.
 */
public class FinallyTest {

    public static void main(String[] args) {

        ImmutableList<Integer> immutableIntegers = ImmutableList.of(1, 2, 3, 4, 5);
        immutableIntegers.forEach(integer -> System.out.print(integer + " "));
        try {
            System.out.println("Try block executed");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Catch Block executed");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
