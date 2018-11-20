package cn.com.yusys.yusp.workflow.service.ext.impl;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowRouteInterface;
@Service
public class FeginWorkflowRouteImpl implements WorkflowRouteInterface {

	@Override
	public boolean run(ResultInstanceDto instanceInfo,String nextNodeId) {
		return true;
	}

}
