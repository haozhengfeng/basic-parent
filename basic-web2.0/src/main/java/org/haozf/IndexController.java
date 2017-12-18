package org.haozf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    
    private Logger log = LoggerFactory.getLogger(IndexController.class); 
    
    @RequestMapping(value={"","index"})
    public String index(){
        log.trace("======trace");  
        log.debug("======debug");  
        log.info("======info");  
        log.warn("======warn");  
        log.error("======error");  
        return "index";
    }
    
    @RequestMapping("ajax")
    @ResponseBody
    public Map ajax(HttpServletRequest request){
        Map<String, String> rt = new HashMap<String, String>();
        rt.put("redis_session_id", request.getSession().getId());
        return rt;
    }

}
