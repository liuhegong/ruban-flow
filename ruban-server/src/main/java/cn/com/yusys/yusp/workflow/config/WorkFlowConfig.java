package cn.com.yusys.yusp.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.service.impl.WorkflowUserServiceImpl;

@Configuration
public class WorkFlowConfig {
	
	@Value("${flow.path}")
	private String flowPath = null;
	
	@Value("${org.path}")
	private String orgPath = null;
	
	@Bean
	public EngineCache initWorkFlowEngine(){
		EngineCache engineCache = EngineCache.getInstance();
		engineCache.init(flowPath);
		return engineCache;
	}
	
	@Bean
	public OrgCache initWorkFlowOrgCache(){
		OrgCache engineCache = OrgCache.getInstance();
		engineCache.init(orgPath);
		
		WorkflowUserServiceImpl hh = new WorkflowUserServiceImpl();
		hh.getUsersByOrgId("cmis", "2-1");
		
		hh.getLowOrgUsers("cmis", "2-1");
		
		hh.getUpOrgUsers("cmis", "2-2");
		return engineCache;
	}
}
