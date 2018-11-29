package cn.com.yusys.yusp.workflow.service.ext.impl;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.core.engine.node.type.NoticeType;
import cn.com.yusys.yusp.workflow.core.org.WFUser;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowMessageInterface;
/**
 * 消息发送实现
 * @author figue
 *
 */
@Service
public class WorkflowMessageImpl implements WorkflowMessageInterface {

	@Override
	public void sendMessage(String noticeType ,WFUser user,ResultInstanceDto instanceInfo) {
		if(noticeType.equals(NoticeType.EMAIL)){// 发送邮件消息
			
		}else if(noticeType.equals(NoticeType.MESSAGE)){// 发送短信消息
				
			
		}else if(noticeType.equals(NoticeType.SYSTEM)){// 发送系统消息
			
			
		}else if(noticeType.equals(NoticeType.ALL)){// 发送所有消息				
			
		}
		
	}

}
