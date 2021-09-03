package com.middleware.monitor;

import javassist.ClassPool;
import javassist.CtClass;
import lombok.SneakyThrows;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

public class ProfileTransformer implements ClassFileTransformer {

    private static final Set<String> classNames = new HashSet<>();

    static {
        classNames.add("com.middleware.monitor.UserController");
    }
    @SneakyThrows
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String clzName = className.replaceAll("/", ".");
        if (!classNames.contains(clzName)) {
            return classfileBuffer;
        }

        CtClass ctClass = ClassPool.getDefault().get(clzName);
        String name = ctClass.getName();



        return new byte[0];
    }
}
