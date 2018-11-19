package cn.com.yusys.yusp.workflow.web.fillter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.com.yusys.yusp.workflow.web.fillter.session.CurrentUser;
import cn.com.yusys.yusp.workflow.web.fillter.session.UserInfo;
public class UserSessionRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
    	
    	UserInfo userInfo = new UserInfo();
    	userInfo.setUserId("admin");
    	userInfo.setOrgId("0000");
    	userInfo.setSystemId("cmis");
    	userInfo.setUserName("王亚飞");
    	CurrentUser.info.set(userInfo);
        
    	filterChain.doFilter(servletRequest, servletResponse);
       
    	CurrentUser.info.remove();
    }

    @Override
    public void destroy() {

    }
}