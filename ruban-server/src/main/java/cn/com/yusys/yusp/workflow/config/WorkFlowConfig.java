package cn.com.yusys.yusp.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.web.fillter.UserSessionRequestFilter;

@Configuration
public class WorkFlowConfig{
	
	@Value("${flow.path}")
	private String flowPath = null;
	
	@Bean
	public EngineCache initWorkFlowEngine(){
		EngineCache engineCache = EngineCache.getInstance(flowPath);		
		engineCache.init();		
		return engineCache;
	}
	
	@Bean
	public OrgCache initWorkFlowOrgCache(){
		OrgCache engineCache = OrgCache.getInstance(flowPath);
		engineCache.init();	
		return engineCache;
	}
	
	@Bean
	public UserSessionRequestFilter userSessionRequestFilter(){
		return new UserSessionRequestFilter();
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Integer.MIN_VALUE);
		return bean;
	}

}
