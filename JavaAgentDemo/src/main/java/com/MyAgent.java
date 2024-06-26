package com;


import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2024-05-29 20:27
 */
public class MyAgent {
    //JVM 首先尝试在代理类上调用以下方法
//    public static void premain(String agentArgs, Instrumentation inst) {
//        System.out.println("this is my agent：" + agentArgs);
//        MyMonitorTransformer monitor = new MyMonitorTransformer();
//        inst.addTransformer(monitor);
//    }

//    public static void premain(String agentArgs, Instrumentation inst) {
//        System.out.println("this is my agent：" + agentArgs);
//
//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
//            return builder
//                    .method(ElementMatchers.any()) // 拦截任意方法
//                    .intercept(MethodDelegation.to(MethodCostTime.class)); // 委托
//        };
//
//        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
//            @Override
//            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//
//            @Override
//            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
//
//            }
//
//            @Override
//            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//
//            @Override
//            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//
//        };
//
//        new AgentBuilder
//                .Default()
////                .type(ElementMatchers.nameStartsWith("")) // 指定需要拦截的类
//                .type(ElementMatchers.any()) // 指定需要拦截的类
//                .transform(transformer)
//                .with(listener)
//                .installOn(inst);
//    }

    //JVM与内存信息

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is my agent：" + agentArgs);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                JvmStack.printMemoryInfo();
                JvmStack.printGCInfo();
                System.out.println("===================================================================================================");
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
