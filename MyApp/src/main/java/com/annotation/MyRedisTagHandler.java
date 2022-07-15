package com.annotation;

import com.register.RedisBeanDefinitionParser;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-07-15 01:47
 */
public class MyRedisTagHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("redis", new RedisBeanDefinitionParser());
        // 具体标签的解析
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return super.parse(element, parserContext);
    }
}
