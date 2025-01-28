package com.robin.starters.influxdb.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 *
 * @author zhao peng
 * @date 2024/12/22 16:46
 **/
public final class DateTimeUtils {

    /**
     * Instant 字符串时间转换为 LocalDateTime
     * @param instant Instant 字符串时间
     * @return LocalDateTime
     */
    public static LocalDateTime instantToLocalDateTime(String instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(instant, formatter);
    }

    /**
     * LocalDateTime 转换为 Instant
     * @param localDateTime LocalDateTime
     * @return Instant
     */
    public static Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

}
