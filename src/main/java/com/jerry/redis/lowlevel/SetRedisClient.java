package com.jerry.redis.lowlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * 集合相关底层操作封装
 * Set related low-level encapsulation
 */
@Component
public class SetRedisClient {
    private final JedisPool pool;

    @Autowired
    public SetRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Redis命令: SADD key member...
     * Redis Command: SADD key member...
     *
     * @param set Set name 集合名称
     * @param member Members 元素
     * @return Number of added elements 添加数量
     */
    public Long sAdd(String set, String ...member) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sadd(set, member);
        }
    }

    /**
     * Redis命令: SISMEMBER key member
     * Redis Command: SISMEMBER key member
     *
     * @param set Set name 集合名称
     * @param member Member 元素
     * @return Member belongs to set or not 元素是否属于集合
     */
    public Boolean sIsMember(String set, String member) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sismember(set, member);
        }
    }

    /**
     * Redis命令: SPOP key
     * Redis Command: SPOP key
     *
     * @param set Set name 集合名称
     * @return Randomly removed value 被随机移除的元素
     */
    public String sPop(String set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.spop(set);
        }
    }

    /**
     * Redis命令: SRANDMEMBER key
     * Redis Command: SRANDMEMBER key
     *
     * @param set Set name 集合名称
     * @return Randomly selected value 被随机选中的元素
     */
    public String sRandMember(String set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.srandmember(set);
        }
    }

    /**
     * Redis命令: SREM key member...
     * Redis Command: SREM key member...
     *
     * @param set Set name 集合名称
     * @param value Value to remove 需要移除的元素
     * @return Number of removed value 移除的元素数量
     */
    public Long sRem(String set, String ...value) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.srem(set, value);
        }
    }

    /**
     * Redis命令: SMOVE source destination member
     * Redis Command: SMOVE source destination member
     *
     * @param source Source set 源集合名称
     * @param dest Destination set 目的集合名称
     * @param member Member 元素名称
     * @return Number of moved value 移动的元素数量
     */
    public Long sMove(String source, String dest, String member) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.smove(source, dest, member);
        }
    }

    /**
     * Redis命令: SCARD key
     * Redis Command: SCARD key
     *
     * @param set Set name 集合名称
     * @return Set size 集合元素数量
     */
    public Long sCard(String set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.scard(set);
        }
    }

    /**
     * Redis命令: SMEMBERS key
     * Redis Command: SMEMBERS key
     *
     * @param set Set name 集合名称
     * @return Set members 集合元素
     */
    public Set<String> sMembers(String set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.smembers(set);
        }
    }

    /**
     * Redis命令: SINTER key...
     * Redis Command: SINTER key...
     *
     * @param set Sets 求交集的集合
     * @return Intersection 交集
     */
    public Set<String> sInter(String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sinter(set);
        }
    }

    /**
     * Redis命令: SINTERSTORE destination key...
     * Redis Command: SINTERSTORE destination key...
     *
     * @param dest Destination set 目标集合
     * @param set Sets 求交集的集合
     * @return Number of destination set size 目标集合的元素数量
     */
    public Long sInterStore(String dest, String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sinterstore(dest, set);
        }
    }

    /**
     * Redis命令: SUNION key...
     * Redis Command: SUNION key...
     *
     * @param set Sets 求并集的集合
     * @return Union 并集
     */
    public Set<String> sUnion(String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sunion(set);
        }
    }

    /**
     * Redis命令: SUNIONSTORE destination key...
     * Redis Command: SUNIONSTORE destination key...
     *
     * @param dest Destination set 目标集合
     * @param set Sets 求并集的集合
     * @return Number of destination set size 目标集合的元素数量
     */
    public Long sUnionStore(String dest, String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sunionstore(dest, set);
        }
    }

    /**
     * Redis命令: SDIFF key...
     * Redis Command: SDIFF key...
     *
     * @param set Sets 求差集的集合
     * @return Difference 差集
     */
    public Set<String> sDiff(String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sdiff(set);
        }
    }

    /**
     * Redis命令: SDIFFSTORE destination key...
     * Redis Command: SDIFFSTORE destination key...
     *
     * @param dest Destination set 目标集合
     * @param set Sets 求差集的集合
     * @return Number of destination set size 目标集合的元素数量
     */
    public Long sDiffStore(String dest, String ...set) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.sdiffstore(dest, set);
        }
    }
}
