package com.robin.starters.influxdb.core.anno;

import com.robin.starters.influxdb.core.Function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 聚合注解
 *
 * @author zhao peng
 * @date 2025/1/28 23:13
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Aggregate {

    /**
     * 字段名
     * @return 字段名
     */
    String value();

    /**
     * 字段使用的聚合函数
     * @return 聚合函数
     */
    Function tag();

}
