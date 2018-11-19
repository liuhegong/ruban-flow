package cn.com.yusys.yusp.workflow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.service.impl.WorkflowUserServiceImpl;

@Configuration
public class WorkFlowConfig {
	
	@Value("${flow.path}")
	private String flowPath = null;
	
	@Bean
	public EngineCache initWorkFlowEngine(){
		EngineCache engineCache = EngineCache.getInstance();
		try {
			engineCache.init(flowPath);
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engineCache;
	}
	
	@Bean
	public OrgCache initWorkFlowOrgCache(){
		OrgCache engineCache = OrgCache.getInstance();
		engineCache.init(flowPath);
		
		WorkflowUserServiceImpl hh = new WorkflowUserServiceImpl();
		hh.getUsersByOrgId("cmis", "2-1");
		
		hh.getLowOrgUsers("cmis", "2-1");
		
		hh.getUpOrgUsers("cmis", "2-2");
		return engineCache;
	}
}
