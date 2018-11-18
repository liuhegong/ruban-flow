package cn.com.yusys.yusp.workflow.service;

import java.util.Map;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 节点提交后处理
 * @author figue
 *
 */
public interface WorkflowNodeInterface {
	Map<String,Object> afterSubmit(ResultInstanceDto instanceInfo);
}
