package com.jerry.redis.interfaces;


/**
 * @author 22454
 */
public interface Callable<T> {
    /**
     * 回调
     *
     * @return 返回值
     */
    T call();
}
