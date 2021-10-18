package com.jerry.redis.utils;

import com.jerry.redis.interfaces.Callable;
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
public class RedisLockHelper {
    private final RedissonClient redissonClient;

    public RedisLockHelper(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public <T> TaskResult<T> lockTask(String key, Long waitTime, Long leaseTime, TimeUnit timeUnit, Callable<T> callable) {
        RLock lock = redissonClient.getLock(key);
        // 假设一定失败
        TaskResult<T> taskResult = new TaskResult<>();
        taskResult.setCompleted(false);
        taskResult.setResult(null);
        try {
            boolean hasLock;
            if (Objects.isNull(leaseTime) || leaseTime <= 0) {
                hasLock = lock.tryLock(waitTime, timeUnit);
            } else {
                hasLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            }
            if (hasLock) {
                // 如果成功了，重新设置返回值
                T callResult = callable.call();
                taskResult.setCompleted(true);
                taskResult.setResult(callResult);
            }
        } catch (Exception e) {
            log.warn("failed to acquire distributed lock,Cause: {}", e.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return taskResult;
    }

}
