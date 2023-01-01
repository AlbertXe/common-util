package com.thread;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2023-01-01 20:53
 */
public class ThreadA extends Thread {
    private String name;

    public ThreadA(String name) {
        super(name);
        this.name = name;
    }

//    @Override
//    public void run() {
//        while (!isInterrupted()) {
//            System.out.println("name="+name+";中断标识="+isInterrupted());
//        }
//        System.out.println("END name="+name+";中断标识="+isInterrupted());
//    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("name="+name+";中断标识="+isInterrupted());
//                e.printStackTrace();
            }

        }
        System.out.println("END name="+name+";中断标识="+isInterrupted());
    }
}
