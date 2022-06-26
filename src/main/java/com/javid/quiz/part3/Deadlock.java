package com.javid.quiz.part3;

public class Deadlock {

    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();


    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (LOCK_1) {
                System.out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }

                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (LOCK_2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }

    }

    private static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (LOCK_2) {
                System.out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    interrupt();
                }

                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (LOCK_1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }

    }
}
