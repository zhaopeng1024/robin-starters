package com.robin.starters.influxdb.core;

import com.robin.starters.influxdb.core.model.DeleteModel;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 删除工具类
 *
 * @author zhao peng
 * @date 2025/1/28 22:35
 **/
public class Delete extends Op {

    /**
     * 构建一个删除语句的SQL字符串
     * @param model 删除模型
     * @return 删除语句
     */
    public static String build(DeleteModel model) {
        Objects.requireNonNull(model.getMeasurement(), "DeleteModel.Measurement");
        StringBuilder delete = new StringBuilder();
        delete.append("DELETE FROM ").append(model.getMeasurement());
        if (!ObjectUtils.isEmpty(model.getWhere())) {
            delete.append(" WHERE ").append(model.getWhere());
        } else {
            throw new RuntimeException("where condition is missing");
        }
        String sql = delete.toString();
        if (log.isDebugEnabled()) {
            log.debug("sql:{}", sql);
        }
        return sql;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new TreeMap<>();
        map.put("device_id", "666");
        DeleteModel model = new DeleteModel("ojbk");
        model.setParams(map);
        model.setStart(LocalDateTime.now().plusHours(-10L));
        model.setEnd(LocalDateTime.now());
        model.setWhere(Op.where(model));
        System.out.println(Delete.build(model));
    }

}
