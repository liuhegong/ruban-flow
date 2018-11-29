package cn.com.yusys.yusp.workflow.service.ext;

import java.util.Map;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 节点提交后处理
 * @author figue
 *
 */
public interface WorkflowBizInterface {
	
	/**
	 * 流程初始化
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterInit(String bizBeanId,ResultInstanceDto instanceInfo);
	
	/**
	 * 流程提交后处理
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterSubmit(String bizBeanId,ResultInstanceDto instanceInfo);
	
	/**
	 * 流程结束后处理
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterEnd(String bizBeanId,ResultInstanceDto instanceInfo);
}
