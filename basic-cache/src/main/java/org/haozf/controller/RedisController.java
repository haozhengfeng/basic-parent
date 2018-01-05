package org.haozf.controller;

import org.haozf.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * redis restfull 对外服务接口
 */
@Controller
public class RedisController {

    @Autowired
    RedisService redisService;
    
    @RequestMapping("exists")
    public String exists(String key){
        return Boolean.toString(redisService.exists(key));
    }
    
}
