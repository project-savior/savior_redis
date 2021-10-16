package com.jerry.redis.config.redisson.single;

import com.jerry.redis.config.redisson.BaseConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22454
 */
@Data
@Configuration
@ConfigurationProperties("redisson.single")
@EqualsAndHashCode(callSuper = true)
public class SingleServerConfig extends BaseConfig {
    private String address = "redis://localhost:6379";
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int connectionMinimumIdleSize = 24;
    private int connectionPoolSize = 64;
    private int database = 0;
    private long dnsMonitoringInterval = 5000;
}
