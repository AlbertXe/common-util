package com.test;

public class Base {
    public static final Integer FIELD_0 = 0;
    public static final String FIELD_A = "base_field_a";
    public static String FIELD_B = "base_field_b";
    public String FIELD_C = "base_field_c";


    static {
        System.out.println("base_cinit");
    }

    public Base() {
        System.out.println("base_init");
    }

    public static void methodA() {
        System.out.println("base_method_a");
    }

    public void methodB() {
        System.out.println("base_method_b");
    }
}

class Extended extends Base {
    public static final String FIELD_A = "extended_field_a";
    public static String FIELD_B = "extended_field_b";
    public String FIELD_C = "extended_field_c";

    static {
        System.out.println("extended_cinit");
    }

    public Extended() {
        System.out.println("extended_init");
    }

    public static void methodA() {
        System.out.println("extended_method_a");
    }

    public void methodB() {
        System.out.println("extended_method_b");
    }
}

class Test {

    public static void main(String[] args) {
//        System.out.println(Base.FIELD_0);                                                                                           //base_cinit 0
//        System.out.println(Base.FIELD_A);                                                                                         //base_field_a
//        System.out.println(Base.FIELD_B);                                                                                         //base_cinit base_field_b
//        Base.methodA();                                                                                                           //base_cinit base_method_a
//        new Base().methodB();                                                                                                     //base_cinit base_init base_method_b

//        System.out.println(Extended.FIELD_A);                                                                                     //extended_field_a
//        System.out.println(Extended.FIELD_B);                                                                                     //base_cinit extended_cinit extended_field_b
        Extended.methodA();                                                                                                       //base_cinit extended_cinit extended_method_a
        //new Extended().methodB();                                                                                                 //base_cinit extended_cinit base_init extended_init extended_method_b

        //Base base = new Extended();
        //System.out.println(base.FIELD_A);                                                                                         //base_cinit extended_cinit base_init extended_init base_field_a
        //System.out.println(base.FIELD_B);                                                                                         //base_cinit extended_cinit base_init extended_init base_field_b
        //System.out.println(base.FIELD_C);                                                                                         //base_cinit extended_cinit base_init extended_init base_field_c
        //base.methodA();                                                                                                           //base_field_c extended_cinit base_init extended_init base_method_a
        //base.methodB();                                                                                                           //base_cinit extended_cinit base_init extended_init extended_method_b
    }
}