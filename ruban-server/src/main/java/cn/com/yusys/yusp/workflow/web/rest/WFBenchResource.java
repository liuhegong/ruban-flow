package cn.com.yusys.yusp.workflow.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.NWfInstanceHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;
import cn.com.yusys.yusp.workflow.service.NWfInstanceHisService;
import cn.com.yusys.yusp.workflow.service.NWfInstanceService;
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
	
	@Autowired
	private NWfInstanceHisService instanceHisService;
	
	@Autowired
	private NWfInstanceService instanceService;
	
	/**
	 * 用户待办查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/todo")
	protected ResultDto<List<ResultInstanceTodoDto>> todo(QueryModel queryModel) {
		String userId =  CurrentUser.info.get().getUserId();
		//queryModel.getCondition().put("userId",userId);
		queryModel.setSort("u.start_time desc");
		return new ResultDto<List<ResultInstanceTodoDto>>(workflowBenchService.getInstanceInfoUserTodo(queryModel));
	}
	
	/**
	 * 用户已办查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/done")
	protected ResultDto<List<ResultInstanceTodoDto>> done(QueryModel queryModel) {
		String userId =  CurrentUser.info.get().getUserId();
		//queryModel.getCondition().put("userId",userId);
		queryModel.setSort("u.start_time desc");
		return new ResultDto<List<ResultInstanceTodoDto>>(workflowBenchService.getInstanceInfoUserDone(queryModel));
	}
	
	/**
	 * 用户办结查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/his")
	protected ResultDto<List<ResultInstanceTodoDto>> his(QueryModel queryModel) {
		String userId =  CurrentUser.info.get().getUserId();
		//queryModel.getCondition().put("userId",userId);
		queryModel.setSort("u.start_time desc");
		return new ResultDto<List<ResultInstanceTodoDto>>(workflowBenchService.getInstanceInfoUserHis(queryModel));
	}	
	
	/**
	 * 用户发起任然在办理中的任务查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/start/doing")
	public ResultDto<List<NWfInstance>> myStartDoing(QueryModel queryModel) {
		String userId =  CurrentUser.info.get().getUserId();
		//queryModel.getCondition().put("flowStarter",userId);
		queryModel.setSort("start_time desc");
		List<NWfInstance> insatnceInfos = instanceService.selectByModel(queryModel);
		return new ResultDto<List<NWfInstance>>(insatnceInfos);
	}
	
	/**
	 * 用户发起的已经结束的任务查询
	 * @param queryModel
	 * @return
	 */
	@GetMapping("/start/his")
	public ResultDto<List<NWfInstanceHis>> myStartHis(QueryModel queryModel) {
		String userId =  CurrentUser.info.get().getUserId();
		//queryModel.getCondition().put("flowStarter",userId);
		queryModel.setSort("start_time desc");
		List<NWfInstanceHis> insatnceInfos = instanceHisService.selectByModel(queryModel);
		return new ResultDto<List<NWfInstanceHis>>(insatnceInfos);
	}	
	
	
}