package com.robin.starters.influxdb.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 基础模型
 *
 * @author zhao peng
 * @date 2024/12/22 16:55
 **/
@Getter
@Setter
public abstract class BaseModel {
    /**
     * 表
     */
    private String measurement;

    /**
     * 条件
     */
    private String where;

    /**
     * 开始时间
     */
    private LocalDateTime start;

    /**
     * 结束时间
     */
    private LocalDateTime end;

    /**
     * where 条件额外参数
     */
    private Map<String, Object> params;

    public BaseModel() {

    }

    public BaseModel(String measurement) {
        this.measurement = measurement;
    }

}
