package org.haozf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisComponet {
    
    @Autowired
    StringRedisTemplate redisTemplate;
    
    public boolean add() {
        redisTemplate.opsForValue().set("name","tom");
        return true;
    }
}
