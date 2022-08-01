package com;

import com.service.TransactionA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-08-01 22:25
 */
@SpringBootTest
public class MyAppTest {
    @Autowired
    private TransactionA transactionA;

    @Test
    public void testA() {
        transactionA.testA();
    }

}
