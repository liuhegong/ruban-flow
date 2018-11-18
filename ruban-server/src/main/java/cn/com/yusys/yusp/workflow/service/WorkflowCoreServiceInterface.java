package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.WFStratDto;
import cn.com.yusys.yusp.workflow.dto.WFSubmitDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultCommentDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultNodeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultWFMessageDto;

public interface WorkflowCoreServiceInterface {
	
	/**
	 * 发起流程
	 * @param stratDto
	 * @return
	 */
	ResultInstanceDto start(WFStratDto stratDto);
	
	/**
	 * 获取实例基本信息
	 * @param instanceId
	 * @return
	 */
	ResultInstanceDto getInstanceInfo(String instanceId,String nodeId,String currentUserId);
	
	/**
	 * 保存或更新评论
	 * @param comment
	 * @return
	 */
	int saveComment(WFCommentDto comment);
	
	/**
	 * 获取用户评论
	 * @param comment
	 * @return
	 */
	ResultCommentDto getComment(String instanceId,String nodeId,String currentUserId);
	
	/**
	 * 获取路由后面节点信息
	 * @param nodeId
	 * @return
	 */
	List<ResultNodeDto> getNextNodeInfos(String instanceId,String nodeId);
	
	/**
	 * 流程提交
	 * @param stratDto
	 * @return
	 */
	List<ResultWFMessageDto> submit(WFSubmitDto submitDto);
}
