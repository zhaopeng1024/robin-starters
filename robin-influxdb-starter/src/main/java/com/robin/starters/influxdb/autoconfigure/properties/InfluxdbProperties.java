package com.robin.starters.influxdb.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Influxdb 属性配置
 *
 * @author zhao peng
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.influxdb")
public class InfluxdbProperties {

    private String username;

    private String password;

    private String url;

    private String database;

}
