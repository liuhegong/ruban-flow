package cn.com.yusys.yusp.workflow.service.ext;

import cn.com.yusys.yusp.workflow.core.org.WFUser;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 消息发送接口
 * @author figue
 *
 */
public interface WorkflowMessageInterface {
	
	void sendMessage(String noticeType,WFUser user,ResultInstanceDto instanceInfo);
}
