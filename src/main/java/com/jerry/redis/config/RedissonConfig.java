package com.jerry.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.redis.config.redisson.single.SingleServerProperties;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 22454
 */
@Component
public class RedissonConfig {
    private final ObjectMapper mapper;

    public RedissonConfig(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Bean(destroyMethod = "shutdown")
    @SneakyThrows
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(SingleServerProperties properties) {
        String jsonString = mapper.writeValueAsString(properties);
        Config config = Config.fromYAML(jsonString);
        if (StringUtils.isBlank(properties.getSingleServerConfig().getPassword())) {
            config.useSingleServer().setPassword(null);
        }
        return Redisson.create(config);
    }
}
