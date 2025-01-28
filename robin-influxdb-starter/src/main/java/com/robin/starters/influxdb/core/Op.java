package com.robin.starters.influxdb.core;

import com.robin.starters.influxdb.core.model.BaseModel;
import com.robin.starters.influxdb.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作类
 *
 * @author zhao peng
 * @date 2025/1/28 21:56
 **/
public class Op {

    protected static Logger log = LoggerFactory.getLogger(Op.class);

    /**
     * 生成时间范围的查询字符串
     * @param start 开始时间
     * @param end 结束时间
     * @return 时间范围字符串
     */
    public static StringBuilder time(LocalDateTime start, LocalDateTime end) {
        StringBuilder sb = new StringBuilder();
        sb.append("time");
        if (start != null) {
            sb.append(" >='").append(DateTimeUtils.localDateTimeToInstant(start));
        }
        if (end != null) {
            sb.append("' AND ").append("time <= '").append(DateTimeUtils.localDateTimeToInstant(end)).append("'");
        }
        return sb;
    }

    /**
     * 生成基于 BaseModel 对象的时间范围和参数的查询条件字符串
     * @param model BaseModel 对象
     * @return 查询条件字符串
     */
    public static String where(BaseModel model) {
        StringBuilder sb = new StringBuilder();
        if (!ObjectUtils.isEmpty(model.getStart()) && !ObjectUtils.isEmpty(model.getEnd())) {
            sb = time(model.getStart(), model.getEnd());
            if (!ObjectUtils.isEmpty(model.getParams())) {
                for (Map.Entry<String, Object> entry : model.getParams().entrySet()) {
                    sb.append(" AND ").append("\"").append(entry.getKey()).append("\"").append("=");
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        sb.append("'").append(value).append("'");
                    } else {
                        sb.append(entry.getValue());
                    }
                }
            }
        } else {
            if (!ObjectUtils.isEmpty(model.getParams())) {
                int i = 0;
                for (Map.Entry<String, Object> entry : model.getParams().entrySet()) {
                    if (i != 0) {
                        sb.append(" AND ");
                    }
                    sb.append("\"").append(entry.getKey()).append("\"").append("=");
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        sb.append("'").append(value).append("'");
                    } else {
                        sb.append(entry.getValue());
                    }
                    i++;
                }
            }
        }
        return sb.toString();
    }

}
