package cn.com.yusys.yusp.workflow.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBenchInterface;
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
	/**
	 * 用户待办查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/todo")
	protected ResultDto<List<ResultInstanceTodoDto>> todo(QueryModel queryModel) {
		queryModel.getCondition().put("userId", CurrentUser.info.get().getUserId());
		return new ResultDto<List<ResultInstanceTodoDto>>(workflowBenchService.getInstanceInfoTodo(queryModel));
	}
	
}