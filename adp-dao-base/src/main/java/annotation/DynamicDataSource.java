package annotation;

import java.lang.annotation.*;

/**
 * @author xiehongwei
 * @date 2022/12/29 1:45 下午
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {
    String value() default "";
}
