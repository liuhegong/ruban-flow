package cn.com.yusys.yusp.workflow.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultCommentDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultWFMessageDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBenchInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowCoreServiceInterface;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
import cn.com.yusys.yusp.workflow.web.fillter.session.CurrentUser;
/**
 * 我的工作台
 * @author figue
 *
 */
@RestController
@RequestMapping("/api/bench")
public class WFBenchResource {
	@Autowired
	private WorkflowBenchInterface workflowBenchService;
	
	@Autowired
	private WorkflowCoreServiceInterface workflowCoreService;
	/**
	 * 用户待办查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/todo")
	protected ResultDto<List<ResultInstanceTodoDto>> todo(QueryModel queryModel) {
		queryModel.getCondition().put("userId", CurrentUser.info.get().getUserId());
		queryModel.setSort("u.start_time desc");
		return new ResultDto<List<ResultInstanceTodoDto>>(workflowBenchService.getInstanceInfoUserTodo(queryModel));
	}
	
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
	
}