package com.util.sequence;

import lombok.Data;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-10 18:12
 */
@Data
public class SequenceConfig {
    public static final String DEFAULT_CLASS = SequenceCallbackImpl.class.getName();

    private String callBackClass;

    public SequenceConfig() {
        callBackClass = DEFAULT_CLASS;
    }
}
