package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBizInterface;
/**
 * 节点流转业务处理
 * @author figue
 *
 */
@Service
public class WorkflowBizImpl implements WorkflowBizInterface {

	@Override
	public Map<String, Object> afterInit(ResultInstanceDto instanceInfo) {
		System.out.println("流程发起后处理:"+instanceInfo);
		return null;
	}

	
	@Override
	public Map<String, Object> afterSubmit(ResultInstanceDto instanceInfo) {
		System.out.println("流程提交后处理:"+instanceInfo);
		return null;
	}

	@Override
	public Map<String, Object> afterEnd(ResultInstanceDto instanceInfo) {
		System.out.println("流程结束后处理:"+instanceInfo);
		return null;
	}
	
}
