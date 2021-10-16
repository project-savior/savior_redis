package com.jerry.redis.config.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 22454
 */
@Data
@Component
@ConfigurationProperties("redisson.codec")
public class Codec {
    String className = "org.redisson.codec.JsonJacksonCodec";
}
