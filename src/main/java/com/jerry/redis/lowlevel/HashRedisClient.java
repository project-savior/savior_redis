package com.jerry.redis.lowlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 哈希表相关操作底层封装
 * Hash table related low-level encapsulation
 */
@Component
public class HashRedisClient {
    private final JedisPool pool;

    @Autowired
    public HashRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Redis命令: HSET hash field value
     * Redis Command: HSET hash field value
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @param value Value 值
     * @return Number of value 设置的键值数量
     */
    public Long hSet(String hash, String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hset(hash, key, value);
        }
    }

    /**
     * Redis命令: HSETNX hash field value
     * Redis Command: HSETNX hash field value
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @param value Value 值
     * @return Number of value 设置的键值数量
     */
    public Long hSetNx(String hash, String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hsetnx(hash, key, value);
        }
    }

    /**
     * Redis命令: HGET hash field
     * Redis Command: HGET hash field
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @return Value 值
     */
    public String hGet(String hash, String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hget(hash, key);
        }
    }

    /**
     * Redis命令: HEXISTS hash field
     * Redis Command: HEXISTS hash field
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @return Hash and key exists or not 哈希表和对应的
     */
    public Boolean hExists(String hash, String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hexists(hash, key);
        }
    }

    /**
     * Redis命令: HDEL hash field...
     * Redis Command: HDEL hash field...
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @return Number of deleted key 被删除的键数量
     */
    public Long hDel(String hash, String ...key) {
        if (key.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.hdel(hash, key);
            }
        }
        return 0L;
    }

    /**
     * Redis命令: HLEN hash
     * Redis Command: HLEN hash
     *
     * @param hash Hash table name 哈希表名称
     * @return Hash table size 哈希表元素数量
     */
    public Long hLen(String hash) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hlen(hash);
        }
    }

    /**
     * Redis命令: HMGET hash field... value...
     * Redis Command: HMGET hash field... value...
     *
     * @param hash Hash table name 哈希表名称
     * @param kv Key and value 键值对
     * @return Result of operation 执行结果
     */
    public String hmSet(String hash, Map<String, String> kv) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hmset(hash, kv);
        }
    }

    /**
     * Redis命令: HMGET hash field...
     * Redis Command: HMGET hash field...
     *
     * @param hash Hash table name 哈希表名称
     * @param key Key 键
     * @return Value 值
     */
    public List<String> hmGet(String hash, String ...key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hmget(hash, key);
        }
    }

    /**
     * Redis命令: HKEYS hash
     * Redis Command: HKEYS hash
     *
     * @param hash Hash table name 哈希表名称
     * @return All keys in the hash table 哈希表内所有键名称
     */
    public Set<String> hKeys(String hash) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hkeys(hash);
        }
    }

    /**
     * Redis命令: HVALS hash
     * Redis Command: HVALS hash
     *
     * @param hash Hash table name 哈希表名称
     * @return All values in the hash table 哈希表内所有值
     */
    public List<String> hVals(String hash) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hvals(hash);
        }
    }

    /**
     * Redis命令: HGETALL hash
     * Redis Command: HGETALL hash
     *
     * @param hash Hash table name 哈希表名称
     * @return All key-value pairs in the hash table 哈希表内所有键值对
     */
    public Map<String, String> hGetAll(String hash) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hgetAll(hash);
        }
    }
}
