package com.robin.starters.influxdb.core;

import com.robin.starters.influxdb.util.DateTimeUtils;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Insert 工具类
 *
 * @author zhao peng
 * @date 2025/1/28 22:35
 **/
public class Insert extends Op {

    public static String build(Object object) {
        Objects.requireNonNull(object, "实体不能为空");
        StringBuilder insert = new StringBuilder();
        String time = "";
        Class<?> clazz = object.getClass();
        Measurement measurement = clazz.getAnnotation(Measurement.class);
        // insert.append("insert ");
        insert.append(measurement.name());
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            try {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                if (column.tag()) {
                    if (field.get(object) != null) {
                        insert.append(",").append(column.name()).append("=").append(field.get(object));
                    }
                } else {
                    if (field.get(object) != null) {
                        if ("time".equals(column.name())) {
                            time = DateTimeUtils.localDateTimeToInstant((LocalDateTime) field.get(object)).getEpochSecond() + "000000000";
                        } else {
                            if (i == 0) {
                                insert.append(" ");
                            } else {
                                insert.append(",");
                            }
                            insert.append(column.name()).append("=").append(field.get(object));
                            i++;
                        }

                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("Influxdb save error. error :{}", e.getMessage());
            }
        }
        insert.append(" ").append(time);
        String sql = insert.toString();
        if (log.isDebugEnabled()) {
            log.debug("sql:{}", sql);
        }
        return sql;
    }

}
