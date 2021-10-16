package com.jerry.redis.config.redisson.single;

import com.jerry.redis.config.redisson.Codec;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22454
 */
@Data
@Configuration
@ConfigurationProperties("redisson.single.properties")
public class SingleServerProperties {

    private final SingleServerConfig singleServerConfig;

    private final Codec codec;

    public SingleServerProperties(SingleServerConfig singleServerConfig,
                                  Codec codec) {
        this.singleServerConfig = singleServerConfig;
        this.codec = codec;
    }

    private int threads = 0;

    private int nettyThreads = 0;

    private String transportMode = "NIO";



}
