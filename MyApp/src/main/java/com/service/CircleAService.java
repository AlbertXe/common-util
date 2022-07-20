package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-17 19:43
 */
@Service
public class CircleAService {
    @Autowired
    private CircleBService circleBService;
}
