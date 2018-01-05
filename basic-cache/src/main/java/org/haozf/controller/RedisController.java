package org.haozf.controller;

import org.haozf.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * redis restfull 对外服务接口
 */
@Controller
public class RedisController {

    @Autowired
    RedisService redisService;
    
    @RequestMapping("exists")
    @ResponseBody
    public String exists(String key){
        return Boolean.toString(redisService.exists(key));
    }
    
    @RequestMapping("get")
    @ResponseBody
    public String get(String key){
        if(redisService.exists(key))
            return redisService.get(key).toString();
        return "";
    }
    
    @RequestMapping("set")
    @ResponseBody
    public String set(String key,String value,long expire){
        return Boolean.toString(redisService.set(key, value, expire));
    }
    
    @RequestMapping("remove")
    @ResponseBody
    public String remove(String key){
        redisService.remove(key);
        return "true";
    }
    
}
