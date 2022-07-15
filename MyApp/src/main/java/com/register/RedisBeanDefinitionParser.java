package com.register;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.w3c.dom.Element;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-15 19:05
 */
public class RedisBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return RedisProperties.Jedis.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addConstructorArgValue(element.getAttribute("id"));
        builder.addConstructorArgValue(element.getAttribute("name"));
    }
}
