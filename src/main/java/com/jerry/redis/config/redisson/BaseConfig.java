package com.jerry.redis.config.redisson;

import lombok.Data;
import org.redisson.config.SslProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author 22454
 */
@Data
@Configuration
@ConfigurationProperties(value = "redisson")
public class BaseConfig<T extends org.redisson.config.BaseConfig<T>> {
    private int idleConnectionTimeout = 10000;
    private int connectTimeout = 10000;
    private int timeout = 3000;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private String password;
    private String username;
    private int subscriptionsPerConnection = 5;
    private String clientName = "";
    private boolean sslEnableEndpointIdentification = true;
    private SslProvider sslProvider = SslProvider.JDK;
    private URL sslTruststore;
    private String sslTruststorePassword;
    private URL sslKeystore;
    private String sslKeystorePassword;
    private int pingConnectionInterval;
    private boolean keepAlive;
    private boolean tcpNoDelay;

}
