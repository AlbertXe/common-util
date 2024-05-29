package com;

import java.util.LinkedList;
import java.util.List;

public class ApiTest {

//    public static void main(String[] args) throws InterruptedException {
//        ApiTest apiTest = new ApiTest();
//        apiTest.echoHi();
//    }

    public static void main(String[] args) {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
        }
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}