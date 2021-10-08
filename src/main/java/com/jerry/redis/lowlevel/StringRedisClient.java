package com.jerry.redis.lowlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.time.Duration;
import java.util.Date;

/**
 * 字符串相关操作底层封装
 * String related operation low-level encapsulation.
 *
 * @author masteryyh
 */
@Component
public class StringRedisClient {
    private final JedisPool pool;

    @Autowired
    public StringRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Redis命令: SET key value
     * Redis Command: SET key value
     *
     * @param key Key 键
     * @param value Value 值
     * @return Result of SET command SET命令返回结果
     */
    public String set(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.set(key, value);
        }
    }

    /**
     * Redis命令: SET key value PX milliseconds
     * Redis Command: SET key value PX milliseconds
     *
     *
     * @param key Key 键
     * @param value Value 值
     * @param expire Expire milliseconds in java.time.Duration form 键值过期毫秒数(使用java.time.Duration表示)
     * @return Result of SET command SET命令返回结果
     */
    public String set(String key, String value, Duration expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.set(key, value, SetParams.setParams().px(expire.toMillis()));
        }
    }

    /**
     * Redis命令: SETNX key value
     * Redis Command: SETNX key value
     *
     * @param key Key 键
     * @param value Value 值
     * @return Key number 保存的键值数量
     */
    public Long setNx(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.setnx(key, value);
        }
    }

    /**
     * Redis命令: SET key value PX milliseconds NX
     * Redis Command: SET key value PX milliseconds NX
     *
     * @param key Key 键
     * @param value Value 值
     * @param expire Expire milliseconds in java.time.Duration form 键值过期毫秒数(使用java.time.Duration表示)
     * @return Result of SET command SET命令返回结果
     */
    public String setNx(String key, String value, Duration expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.set(key, value, SetParams.setParams().px(expire.toMillis()).nx());
        }
    }

    /**
     * Redis命令: GET key
     * Redis Command: GET key
     *
     * @param key Key 键
     * @return Value ("nil" returned if no value present) 值 (若不存在则返回"nil")
     */
    public String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * Redis命令: DEL key
     * Redis Command: DEL key
     *
     * @param key Key (or list, set, sorted set, hash table's name) 键 (或列表、集合、有序集合、哈希表的名称)
     * @return Number of deleted key(s) 被删除的键数量
     */
    public Long del(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.del(key);
        }
    }

    /**
     * Redis命令: TTL key
     * Redis Command: TTL key
     *
     * @param key Key 键
     * @return A key's time-to-live seconds (TTL) 对应键的生存时间秒数(TTL)
     */
    public Long ttl(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.ttl(key);
        }
    }

    /**
     * Redis命令: PTTL key
     * Redis Command: PTTL key
     *
     * @param key Key 键
     * @return A key's time-to-live milliseconds (TTL) 对应键的生存时间毫秒数(TTL)
     */
    public Long pTtl(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.pttl(key);
        }
    }

    /**
     * Redis命令: PEXIPRE key milliseconds
     * Redis Command: PEXPIRE key milliseconds
     *
     * @param key Key 键
     * @param expire Expire time in java.time.Duration form 过期时间(java.time.Duration形式)
     * @return Number of successfully set TTL value 设置TTL成功的键数量
     */
    public Long pExpire(String key, Duration expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.pexpire(key, expire.toMillis());
        }
    }

    /**
     * Redis命令: PEXPIREAT key milliseconds
     * Redis Command: PEXPIREAT key milliseconds
     *
     * @param key Key 键
     * @param expire Unix-timestamp to expire key in milliseconds 键过期时间(Unix时间戳, 毫秒)
     * @return Number of successfully set TTL value 设置TTL成功的键数量
     */
    public Long pExpireAt(String key, Date expire) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.pexpireAt(key, expire.toInstant().toEpochMilli());
        }
    }

    /**
     * Redis命令: PERSIST key
     * Redis Command: PERSIST key
     *
     * @param key Key 键
     * @return Number of successfully removed TTL value 移除TTL成功的键数量
     */
    public Long persist(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.persist(key);
        }
    }
}
