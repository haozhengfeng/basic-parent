package org.haozf.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.haozf.common.BaseLogger;
import org.haozf.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager extends BaseLogger{
    
    public final static String SESSION_MEMBER="_SESSION_MEMBER_";
    public final static String SESSION_MEMBER_ID="_SESSION_MEMBER_ID_";
    
    @Autowired  
    private HttpSession session;  
      
    @Autowired  
    private HttpServletRequest request;  

    public Member member(){
        log.info("获取当前用户");
        Member member = (Member)session.getAttribute(SESSION_MEMBER);
        return member;
    }
    
    public void session(Member member){
        log.info("设置session信息");
        session.setAttribute(SESSION_MEMBER_ID, member.getId());
        session.setAttribute(SESSION_MEMBER, member);
    }
    
    public void clean(){
        log.info("清空session");
        session.invalidate();
    }
    
}
