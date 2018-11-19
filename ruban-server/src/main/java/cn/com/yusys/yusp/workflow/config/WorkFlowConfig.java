package cn.com.yusys.yusp.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;

@Configuration
public class WorkFlowConfig {
	
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
}
