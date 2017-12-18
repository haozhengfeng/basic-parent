package org.haozf.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.haozf.common.BaseLogger;
import org.springframework.stereotype.Component;

/**
 * 权限控制器
 */
@Component
public class AuthorityManager extends BaseLogger{
    
    private Set<String> authoritys=new HashSet<String>();
    
    public Set<String> authority(){
        log.info("获取当前用户权限");
        return authoritys;
    }
    
    public void addAuthority(Set<String> authority){
        if(authority==null) return;
        log.info("为当前用户设置权限");
        authoritys.addAll(authority);
    }
    
    public void removeAuthority(String authority){
        log.info("当前用户删除权限");
        authoritys.remove(authority);
    }
    
    public void clean(){
        log.info("清空权限");
        authoritys.clear();
    }
}
