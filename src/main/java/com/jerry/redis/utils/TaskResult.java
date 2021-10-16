package com.jerry.redis.utils;

import lombok.Data;

/**
 * @author 22454
 */
@Data
public class TaskResult<T> {
    private Boolean completed;
    private T result;
}
