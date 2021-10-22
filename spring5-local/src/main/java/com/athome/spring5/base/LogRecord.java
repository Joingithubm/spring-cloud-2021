package com.athome.spring5.base;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/19 17:19
 * @Version 1.0
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogRecord {

    String desc() default  "";
}
