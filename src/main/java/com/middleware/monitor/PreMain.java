package com.middleware.monitor;

import java.lang.instrument.Instrumentation;

public class PreMain {

    public static void premain(String[] agentArgs, Instrumentation inst) {
        inst.addTransformer(new ProfileTransformer());
    }

    public static void premain(String[] agentArgs) {

    }
}
