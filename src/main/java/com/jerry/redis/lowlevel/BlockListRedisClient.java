package com.jerry.redis.lowlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * 阻塞列表相关操作底层封装
 * Block list related low-level encapsulation
 */
@Component
public class BlockListRedisClient {
    private final JedisPool pool;

    @Autowired
    public BlockListRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Redis命令: BLPOP value... timeout
     * Redis Command: BLPOP value... timeout
     *
     * @param lists Lists 列表名称
     * @param timeout Timeout 超时时间
     * @return Result of pop 被弹出的元素
     */
    public List<String> blPop(Duration timeout, String ...lists) {
        if (lists.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.blpop((int) timeout.getSeconds(), lists);
            }
        }
        return new LinkedList<>();
    }

    /**
     * Redis命令: BRPOP value... timeout
     * Redis Command: BRPOP value... timeout
     *
     * @param lists Lists 列表名称
     * @param timeout Timeout 超时时间
     * @return Result of pop 被弹出的元素
     */
    public List<String> brPop(Duration timeout, String ...lists) {
        if (lists.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.brpop((int) timeout.getSeconds(), lists);
            }
        }
        return new LinkedList<>();
    }

    /**
     * Redis命令: BRPOPLPUSH source destination timeout
     * Redis Command: BRPOPLPUSH source destination timeout
     *
     * @param source Source list 源列表
     * @param dest Destination list 目的列表
     * @param timeout Timeout 超时时间
     * @return Pop value 被弹出的元素
     */
    public String brPopLPush(String source, String dest, Duration timeout) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.brpoplpush(source, dest, (int) timeout.getSeconds());
        }
    }
}
