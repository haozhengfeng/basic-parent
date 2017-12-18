package org.haozf.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.haozf.common.BaseLogger;
import org.springframework.stereotype.Component;

/**
 * 角色控制器
 */
@Component
public class RoleManager extends BaseLogger{
    
    private Set<String> roles=new HashSet<String>();
    
    public Set<String> role(){
        log.info("获取当前用户角色");
        return roles;
    }
    
    public void addRole(Set<String> role){
        if(role==null) return;
        log.info("为当前用户添加角色");
        roles.addAll(role);
    }
    
    public void removeRole(String role){
        log.info("当前用户删除角色");
        roles.remove(role);
    }
    
    public void clean(){
        log.info("清空角色");
        roles.clear();
    }
    
}
