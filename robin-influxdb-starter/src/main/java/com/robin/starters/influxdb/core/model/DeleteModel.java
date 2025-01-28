package com.robin.starters.influxdb.core.model;

/**
 * 删除模型
 *
 * @author zhao peng
 * @date 2025/1/28 21:36
 **/
public class DeleteModel extends BaseModel {

    public DeleteModel() {
        super();
    }

    public DeleteModel(String measurement) {
        super(measurement);
    }

}
