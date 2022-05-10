package com.util.sequence;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-10 18:06
 */
public interface SequenceCallback {
    void init();

    Long nextVal(String type,String name);
}
