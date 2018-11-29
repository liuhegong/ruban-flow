package cn.com.yusys.yusp.workflow.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.WFStratDto;
import cn.com.yusys.yusp.workflow.dto.WFSubmitDto;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultCommentDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultNodeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultWFMessageDto;
import cn.com.yusys.yusp.workflow.service.NWfInstanceService;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowCoreInterface;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;

@RestController
@RequestMapping("/api/core")
public class WorkflowCoreResource {
	@Autowired
	private WorkflowCoreInterface workflowCoreService;
	
	@Autowired
	private NWfInstanceService instanceService;
	
	/**
	 * 用户待办实例信息获取
	 * @param instanceId
	 * @param nodeId
	 * @param userId
	 * @return
	 */
	@GetMapping("/instanceInfo")
	protected ResultDto<ResultInstanceDto> instanceInfo(String instanceId, String nodeId, String userId) {		
		ResultInstanceDto instanceInfo = workflowCoreService.getInstanceInfo(instanceId, nodeId, userId);
		return new ResultDto<ResultInstanceDto>(instanceInfo);
	}
	
	/**
	 * 获取流程实例下所有评论
	 * @param instanceId
	 * @return
	 */
	@GetMapping("/getComments")
	protected ResultDto<List<ResultCommentDto>> getComments(String instanceId) {		
		List<ResultCommentDto> comments = workflowCoreService.getComments(instanceId);
		return new ResultDto<List<ResultCommentDto>>(comments);
	}
	
	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	@PostMapping("/saveComment")
	protected ResultDto<ResultCommentDto> saveComment(@Valid @RequestBody WFCommentDto comment) {				
		ResultCommentDto instanceInfo = workflowCoreService.saveComment(comment);
		return new ResultDto<ResultCommentDto>(instanceInfo);
	}
	
	/**
	 * 签收动作
	 * @param instanceId
	 * @param nodeId
	 * @param userId
	 * @return
	 */
	@GetMapping("/signIn")
	protected ResultDto<ResultWFMessageDto> signIn(String instanceId,String nodeId,String userId) {
		return new ResultDto<ResultWFMessageDto>(workflowCoreService.signIn(instanceId, nodeId, userId));
	}
	
	/**
	 * 撤销签收动作
	 * @param instanceId
	 * @param nodeId
	 * @param userId
	 * @return
	 */
	@GetMapping("/unSignIn")
	protected ResultDto<ResultWFMessageDto> unSignIn(String instanceId,String nodeId,String userId) {
		return new ResultDto<ResultWFMessageDto>(workflowCoreService.unSignIn(instanceId, nodeId, userId));
	}
	
	/**
	 * 流程发起
	 * @param stratDto
	 * @return
	 * @throws WorkflowException
	 */
	@PostMapping("/start")
	public ResultDto<ResultInstanceDto> start(@Valid @RequestBody WFStratDto stratDto) throws WorkflowException{
		return new ResultDto<ResultInstanceDto>(workflowCoreService.start(stratDto));
	}
	
	/**
	 * 流程提交
	 * @param stratDto
	 * @return
	 * @throws WorkflowException
	 */
	@PostMapping("/submit")
	public ResultDto<List<ResultWFMessageDto>> submit(@Valid @RequestBody WFSubmitDto submitDto) throws WorkflowException{
		return new ResultDto<List<ResultWFMessageDto>>(workflowCoreService.submit(submitDto));
	}
	
	/**
	 * 获取后续节点基本信息
	 * @param instanceId
	 * @param nodeId
	 * @return
	 * @throws WorkflowException
	 */
	@GetMapping("/getNextNodeInfos")
	public ResultDto<List<ResultNodeDto>> getNextNodeInfos(String instanceId,String nodeId) throws WorkflowException{
		return new ResultDto<List<ResultNodeDto>>(workflowCoreService.getNextNodeInfos(instanceId,nodeId));
	}
	
	/**
	 * 获取节点用户列表
	 * @param queryModel
	 * @return
	 * @throws WorkflowException
	 */
	@GetMapping("/getNodeUsers")
	public ResultDto<List<WFUserDto>> getNodeUsers(QueryModel queryModel) throws WorkflowException{		
		String instanceId = queryModel.getCondition().get("instanceId").toString();
		String nodeId = queryModel.getCondition().get("nodeId").toString();;
		ResultDto<List<WFUserDto>> data = new ResultDto<List<WFUserDto>>();	
		NWfInstance instanceInfo = instanceService.selectByPrimaryKey(instanceId);
		data.setData(workflowCoreService.getNodeUsers(instanceInfo.getInstanceId(),nodeId,instanceInfo.getOrgId(),instanceInfo.getSystemId()));
		return data;
	}
}