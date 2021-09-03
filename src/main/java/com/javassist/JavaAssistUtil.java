package com.javassist;

import com.middleware.monitor.UserController;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class JavaAssistUtil {

    @SneakyThrows
    public static CtClass addMethod(String clzName, String methodName,String body,int rtnType, Object[] argTypes) {
        CtClass ctClass = ClassPool.getDefault().get(clzName);
        CtClass returnType = chooseType(rtnType);
        CtMethod ctMethod = new CtMethod(returnType, methodName, null, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody(body);
        ctClass.addMethod(ctMethod);
        return ctClass;
    }

    @SneakyThrows
    public static void main(String[] args) {
        UserController userController = new UserController();
        System.out.println(userController.getUser());

        CtClass ct = addMethod("com.middleware.monitor.UserController", "say", "{return 5;}", 0, null);
        Class aClass = ct.toClass();
        Object o = aClass.newInstance();
        Method say = aClass.getDeclaredMethod("say", null);
        Object invoke = say.invoke(o);
        System.out.println(invoke);

    }

    private static CtClass chooseType(int rtnType) {
        if (rtnType == 0) {
            return CtClass.intType;
        }
        return CtClass.intType;
    }
}
