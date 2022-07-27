package com.util.springfactory;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SPI {
    String defaultId() default "";
}
