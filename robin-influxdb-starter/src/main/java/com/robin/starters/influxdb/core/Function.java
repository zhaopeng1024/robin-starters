package com.robin.starters.influxdb.core;

import lombok.Getter;

/**
 * 函数枚举
 *
 * @author zhao peng
 * @date 2024/12/22 16:48
 **/
@Getter
public enum Function {
    SUM("sum", "累加"),
    LAST("last", "最后一条数据"),
    MEAN("mean", "平均数")
    ;
    private final String tag;
    private final String content;

    Function(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }
}
