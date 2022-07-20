package com.pojo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-17 17:01
 */
@Service
public class InitializingBeanService implements InitializingBean, BeanNameAware {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanService  afterPropertiesSet");
    }

    public void initMethod() {
        System.out.println("InitializingBeanService  initMethod");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("InitializingBeanService  postConstruct");
    }


    @Override
    public void setBeanName(String name) {

    }
}

