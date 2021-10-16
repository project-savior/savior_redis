package com.jerry.redis.utils;

import com.jerry.redis.interfaces.Callable;
import com.jerry.redis.lowlevel.*;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 22454
 */
@Slf4j
@Component
public class RedisHelper {
    private final BlockListRedisClient blockListRedisClient;
    private final HashRedisClient hashRedisClient;
    private final ListRedisClient listRedisClient;
    private final SetRedisClient setRedisClient;
    private final StringRedisClient stringRedisClient;
    private final RedissonClient redissonClient;

    public RedisHelper(BlockListRedisClient blockListRedisClient,
                       HashRedisClient hashRedisClient,
                       ListRedisClient listRedisClient,
                       SetRedisClient setRedisClient,
                       StringRedisClient stringRedisClient,
                       RedissonClient redissonClient) {
        this.blockListRedisClient = blockListRedisClient;
        this.hashRedisClient = hashRedisClient;
        this.listRedisClient = listRedisClient;
        this.setRedisClient = setRedisClient;
        this.stringRedisClient = stringRedisClient;
        this.redissonClient = redissonClient;
    }

    public BlockListRedisClient opsForBlockList() {
        return blockListRedisClient;
    }

    public HashRedisClient opsForHash() {
        return hashRedisClient;
    }

    public ListRedisClient opsForList() {
        return listRedisClient;
    }

    public SetRedisClient opsForSet() {
        return setRedisClient;
    }

    public StringRedisClient opsForString() {
        return stringRedisClient;
    }

    public <T> T lockTask(String key, Long waitTime, Long leaseTime, TimeUnit timeUnit, Callable<T> callable) {
        RLock lock = redissonClient.getLock(key);
        try {
            boolean hasLock;
            if (Objects.isNull(leaseTime) || leaseTime <= 0) {
                hasLock = lock.tryLock(waitTime, timeUnit);
            } else {
                hasLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            }
            if (hasLock) {
                return callable.call();
            }

        } catch (Exception e) {
            throw new RuntimeException(String.format("failed to acquire distributed lock,Cause: %s", e.getMessage()));
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        throw new RuntimeException("failed to acquire distributed lock");
    }

}
