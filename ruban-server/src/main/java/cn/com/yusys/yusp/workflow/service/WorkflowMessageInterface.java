package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 消息发送接口
 * @author figue
 *
 */
public interface WorkflowMessageInterface {
	
	void sendMessage(ResultInstanceDto instanceInfo);
}
