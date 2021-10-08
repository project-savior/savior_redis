package com.jerry.redis.utils;

import com.jerry.redis.lowlevel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 22454
 */
@Component
public class RedisHelper {
    private final BlockListRedisClient blockListRedisClient;
    private final HashRedisClient hashRedisClient;
    private final ListRedisClient listRedisClient;
    private final SetRedisClient setRedisClient;
    private final StringRedisClient stringRedisClient;

    public RedisHelper(BlockListRedisClient blockListRedisClient,
                       HashRedisClient hashRedisClient,
                       ListRedisClient listRedisClient,
                       SetRedisClient setRedisClient,
                       StringRedisClient stringRedisClient) {
        this.blockListRedisClient = blockListRedisClient;
        this.hashRedisClient = hashRedisClient;
        this.listRedisClient = listRedisClient;
        this.setRedisClient = setRedisClient;
        this.stringRedisClient = stringRedisClient;
    }

    public BlockListRedisClient opsForBlockList(){
        return blockListRedisClient;
    }
    public HashRedisClient opsForHash(){
        return hashRedisClient;
    }
    public ListRedisClient opsForList(){
        return listRedisClient;
    }

    public SetRedisClient opsForSet(){
        return setRedisClient;
    }
    public StringRedisClient opsForString(){
        return stringRedisClient;
    }
}
