package cn.com.yusys.yusp.workflow.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.WorkflowNodeInterface;
@Service
public class FeginWorkflowNodeImpl implements WorkflowNodeInterface {

	@Override
	public Map<String, Object> afterSubmit(ResultInstanceDto instanceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
