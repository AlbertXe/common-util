package com.test;

public class Base {
    public static final Long FIELD_0 = 99999L;
    public static final String FIELD_A = "field_a";
    public static String FIELD_B = "field_b";
    public String FIELD_C = "field_c";

    static {
        System.out.println("static{}");
    }

    public Base() {
        System.out.println("base constructor");
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
        System.out.println("extended static{}");
    }

    public Extended() {
        System.out.println("extended constructor");
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
        System.out.println(Base.FIELD_0);
//        System.out.println(Base.FIELD_A);
//        System.out.println(Base.FIELD_B);
//        Base.methodA();
//        new Base().methodB();

//        System.out.println(Extended.FIELD_A);
//        System.out.println(Extended.FIELD_B);
//        Extended.methodA();
//        new Extended().methodB();
//
//        Base base = new Extended();
//        System.out.println(base.FIELD_A);
//        System.out.println(base.FIELD_B);
//        System.out.println(base.FIELD_C);
//        base.methodA();
//        base.methodB();
    }
}