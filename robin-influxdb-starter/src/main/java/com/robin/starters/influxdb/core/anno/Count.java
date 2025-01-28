package com.robin.starters.influxdb.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Influxdb 分页查询 count 字段注解
 *
 * @author zhao peng
 * @date 2025/1/28 23:13
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Count {

    String value() default "";

}
