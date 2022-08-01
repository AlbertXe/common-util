package com.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-08-01 22:32
 */
@Service
@Transactional
public class TransactionA {
    public void testA() {
        System.out.println("testA");
    }
}
