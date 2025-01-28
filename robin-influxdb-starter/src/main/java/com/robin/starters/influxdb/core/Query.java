package com.robin.starters.influxdb.core;

import com.robin.starters.influxdb.core.model.QueryModel;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 查询工具类
 *
 * @author zhao peng
 * @date 2025/1/28 22:34
 **/
public class Query extends Op {

    public static String build(QueryModel model) {
        Objects.requireNonNull(model.getMeasurement(), "QueryModel.Measurement");
        StringBuilder query = new StringBuilder();
        query.append("SELECT ").append(model.getSelect());
        query.append(" FROM ").append(model.getMeasurement());
        if (!ObjectUtils.isEmpty(model.getWhere())) {
            query.append(" WHERE ").append(model.getWhere());
        }
        if (!ObjectUtils.isEmpty(model.getGroup())) {
            query.append(" GROUP BY ").append(model.getGroup());
        }
        if (!ObjectUtils.isEmpty(model.getOrder())) {
            query.append(" ORDER BY time ").append(model.getOrder());
        }
        if (!ObjectUtils.isEmpty(model.getCurrent()) && !ObjectUtils.isEmpty(model.getSize())) {
            query.append(" ").append(model.getPageQuery());
        }
        if (model.getUseTimeZone()) {
            query.append(" ").append(model.getTimeZone());
        }
        String sql = query.toString();
        if (log.isDebugEnabled()) {
            log.debug("sql:{}", sql);
        }
        return sql;
    }

    public static String count(String field) {
        return "count(" + "\"" + field + "\"" + ")";
    }

    public static StringBuilder aggregate(String tag, String field) {
        StringBuilder sb = new StringBuilder();
        sb.append(tag).append("(").append("\"").append(field).append("\"").append(")");
        sb.append(" AS ").append("\"").append(field).append("\"");
        return sb;
    }

}
