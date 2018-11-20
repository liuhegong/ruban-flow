package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBizInterface;
@Service
public class FeginWorkflowBizImpl implements WorkflowBizInterface {

	@Override
	public Map<String, Object> afterInit(ResultInstanceDto instanceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Map<String, Object> afterSubmit(ResultInstanceDto instanceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> afterEnd(ResultInstanceDto instanceInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
