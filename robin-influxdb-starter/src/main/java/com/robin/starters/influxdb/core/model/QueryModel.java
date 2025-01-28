package com.robin.starters.influxdb.core.model;

import com.robin.starters.influxdb.core.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

/**
 * 查询模型
 **/
@Getter
@Setter
public class QueryModel extends BaseModel {

    /**
     * 查询的字段
     */
    private String select;

    /**
     * influxdb仅支持time排序
     * Order.DESC Order.ASC
     */
    private Order order;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页的大小
     */
    private Long size;

    /**
     * 默认不使用时区
     */
    private Boolean useTimeZone = false;

    /**
     * 默认时区 Asia/Shanghai
     */
    private String timeZone = "tz('Asia/Shanghai')";

    /**
     * 分组
     */
    private String group;

    public QueryModel() {}

    public QueryModel(String measurement) {
        super(measurement);
    }

    public String getPageQuery() {
        return "limit " + size + " offset " + (current - 1) * size;
    }

    public String getSelect() {
        if (ObjectUtils.isEmpty(select)) {
            select = "*";
        }
        return select;
    }

}
