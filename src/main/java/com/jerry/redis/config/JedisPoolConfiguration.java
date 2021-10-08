package com.jerry.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties("redis")
@Getter
@Setter
public class JedisPoolConfiguration {
    private String host = "127.0.0.1";

    private Integer port = 6379;

    private Integer timeout;

    private String username;

    private String password;

    private Integer minIdle;

    private Integer maxIdle;

    private Integer maxTotal;

    @Bean
    public JedisPool getPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        return new JedisPool(config, host, port, timeout, username, password);
    }
}
