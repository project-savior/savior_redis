package com.jerry.redis.lowlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 列表相关操作底层封装
 * List related operation low-level encapsulation
 */
@Component
public class ListRedisClient {
    private final JedisPool pool;

    @Autowired
    public ListRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Redis命令: LPUSH key value...
     * Redis Command: LPUSH key value...
     *
     * @param list List name 列表名
     * @param value Values to insert 插入的值
     * @return Inserted number of value 插入的值数量
     */
    public Long lPush(String list, String ...value) {
        if (value.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.lpush(list, value);
            }
        }
        return 0L;
    }

    /**
     * Redis命令: LPUSHX key value...
     * Redis Command: LPUSHX key value...
     *
     * @param list List name 列表名
     * @param value Values to insert 插入的值
     * @return Inserted number of value 插入的值数量
     */
    public Long lPushX(String list, String ...value) {
        if (value.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.lpushx(list, value);
            }
        }
        return 0L;
    }

    /**
     * Redis命令: LPOP key
     * Redis Command: LPOP key
     *
     * @param list List name 列表名
     * @return List head ("nil" returned when list is empty) 表头元素(当表为空时返回"nil")
     */
    public String lPop(String list) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lpop(list);
        }
    }

    /**
     * Redis命令: RPUSH key value...
     * Redis Command: RPUSH key value...
     *
     * @param list List name 列表名
     * @param value Values to insert 插入的值
     * @return Inserted number of value 插入的值数量
     */
    public Long rPush(String list, String ...value) {
        if (value.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.rpush(list, value);
            }
        }
        return 0L;
    }

    /**
     * Redis命令: RPUSHX key value...
     * Redis Command: RPUSHX key value...
     *
     * @param list List name 列表名
     * @param value Values to insert 插入的值
     * @return Inserted number of value 插入的值数量
     */
    public Long rPushX(String list, String ...value) {
        if (value.length > 0) {
            try (Jedis jedis = pool.getResource()) {
                return jedis.rpushx(list, value);
            }
        }
        return 0L;
    }

    /**
     * Redis命令: RPOP key
     * Redis Command: RPOP key
     *
     * @param list List name 列表名
     * @return List tail ("nil" returned when list is empty) 表尾元素(当表为空时返回"nil")
     */
    public String rPop(String list) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.rpop(list);
        }
    }

    /**
     * Redis命令: RPOPLPUSH source destination
     * Redis Command: RPOPLPUSH source destination
     *
     * @param source Source list 源列表
     * @param dest Destination list 目的列表
     * @return Pop value 被弹出的值
     */
    public String rPopLPush(String source, String dest) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.rpoplpush(source, dest);
        }
    }

    /**
     * Redis命令: LREM key count value
     * Redis Command: LREM key count value
     *
     * @param list List name 列表名
     * @param count Element count to remove 要移除的元素数量
     * @param value Value 要移除的值
     * @return Removed number of elements 被移除的元素数量
     */
    public Long lRem(String list, long count, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lrem(list, count, value);
        }
    }

    /**
     * Redis命令: LLEN key
     * Redis Command: LLEN key
     *
     * @param list List name 列表名
     * @return Length of list 列表长度
     */
    public Long lLen(String list) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.llen(list);
        }
    }

    /**
     * Redis命令: LINDEX key index
     * Redis Command: LINDEX key index
     *
     * @param list List name 列表名
     * @param index Index value 下标值
     * @return Target value 目标值
     */
    public String lIndex(String list, long index) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lindex(list, index);
        }
    }

    /**
     * Redis命令: LSET key index value
     * Redis Command: LSET key index value
     *
     * @param list List name 列表名
     * @param index Index value 下标值
     * @param value Value to set 用于设置的元素值
     * @return Result of set 设置结果
     */
    public String lSet(String list, long index, String value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lset(list, index, value);
        }
    }

    /**
     * Redis命令: LRANGE key start stop
     * Redis Command: LRANGE key start stop
     *
     * @param list List name 列表名
     * @param start Start position 起点下标
     * @param stop End position 终点下标
     * @return Elements within range 范围内所有元素
     */
    public List<String> lRange(String list, long start, long stop) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lrange(list, start, stop);
        }
    }
}
