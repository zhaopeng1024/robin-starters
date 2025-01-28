package com.robin.starters.influxdb.autoconfigure;

import com.robin.starters.influxdb.autoconfigure.properties.InfluxdbProperties;
import com.robin.starters.influxdb.core.InfluxdbTemplate;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * influxdb 自动配置类
 *
 * @author zhao peng
 **/
@AutoConfiguration
@EnableConfigurationProperties(InfluxdbProperties.class)
@ConditionalOnProperty(prefix = "influxdb", name = "enable", matchIfMissing = true)
public class InfluxdbAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public InfluxDB influxdb(InfluxdbProperties influxdbProperties) {
        InfluxDB influxDB = InfluxDBFactory.connect(influxdbProperties.getUrl(), influxdbProperties.getUsername(), influxdbProperties.getPassword());
        influxDB.setDatabase(influxdbProperties.getDatabase());
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }


    @Bean
    public InfluxdbTemplate influxdbTemplate(InfluxdbProperties influxdbProperties) {
        return new InfluxdbTemplate(influxdbProperties);
    }

}
