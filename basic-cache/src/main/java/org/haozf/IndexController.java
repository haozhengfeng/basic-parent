package org.haozf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.haozf.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController extends BaseController{
    
    @RequestMapping(value={"","index"})
    @ResponseBody
    public Map<String, String> index(HttpServletRequest request){
        
        Map<String, String> rt = new HashMap<String, String>();
//        RedisClient redisClient = RedisClient.create("redis://@localhost:6379/0");
//        StatefulRedisConnection<String, String> connection = redisClient.connect();
//        RedisCommands<String, String> syncCommands = connection.sync();
//        
//        System.out.println("PING: " + syncCommands.ping());
//
////        syncCommands.set("key", "Hello, Redis!");
////        System.out.println(syncCommands.get("key"));
//        connection.close();
//        redisClient.shutdown();
        
        return rt;
    }
    
    

}
