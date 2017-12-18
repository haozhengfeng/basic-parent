package org.haozf.session;

import java.util.Set;

import org.haozf.common.BaseLogger;
import org.haozf.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager extends BaseLogger{
    
    @Autowired
    SessionManager sessionManager;
    
    @Autowired
    RoleManager roleManager;
    
    @Autowired
    AuthorityManager authorityManager;
    
    public Member getSubject(){
        return sessionManager.member();
    }
    
    public void login(Member member){
        sessionManager.session(member);
        roleManager.addRole(null);
        authorityManager.addAuthority(null);
    }
    
    public void logout(){
        sessionManager.clean();
        roleManager.clean();
        authorityManager.clean();
    }
    
}
