package cn.com.yusys.yusp.workflow.web.fillter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class UserSessionRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        Map<String, List<String>> map = new HashMap<>();
        for (Entry<String, String[]> entry : entrySet) {
            map.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        System.out.println(">>> from 【"+request.getRemoteAddr()+":"+request.getRemotePort()+"】 to >> " + request.getRequestURL() + " "+request.getCharacterEncoding() +" -> " + map);
        
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("<<< 结束-----------------------");
    }

    @Override
    public void destroy() {

    }
}