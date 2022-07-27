package com.javassist;

import javassist.*;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class ApiTest {
    @SneakyThrows
    public static void main(String[] args) {
        // 全局的
        ClassPool pool  = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.MathUtil");

        CtField ctField = new CtField(CtClass.doubleType, "pai", ctClass);
        ctField.setModifiers(Modifier.PRIVATE + Modifier.STATIC + Modifier.FINAL);
        ctClass.addField(ctField, "3.14");

        CtMethod circularArea = new CtMethod(CtClass.doubleType, "circularArea", new CtClass[]{CtClass.doubleType}, ctClass);
        circularArea.setModifiers(Modifier.PUBLIC);
        circularArea.setBody("{return  pai*$1*$1;}");
        ctClass.addMethod(circularArea);

        CtMethod sum = new CtMethod(CtClass.doubleType, "sum", new CtClass[]{CtClass.doubleType, CtClass.doubleType}, ctClass);
        sum.setModifiers(Modifier.PUBLIC);
        sum.setBody("{return $1+$2;}");
        ctClass.addMethod(sum);

        ctClass.writeFile();

        Class mathUtil = ctClass.toClass();
        Object obj = mathUtil.newInstance();
        Method areaMethod = mathUtil.getDeclaredMethod("circularArea",double.class);
        Object m1 = areaMethod.invoke(obj, 2);
        System.out.println("圆的面积："+m1);

        Method sumMethod = mathUtil.getDeclaredMethod("sum", double.class, double.class);
        Object m2 = sumMethod.invoke(obj, 1, 2);
        System.out.println("两数之和：" + m2);
    }
}
