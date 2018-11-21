package cn.com.yusys.yusp.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.web.fillter.UserSessionRequestFilter;

@Configuration
public class WorkFlowConfig extends WebMvcConfigurerAdapter{
	
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
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
