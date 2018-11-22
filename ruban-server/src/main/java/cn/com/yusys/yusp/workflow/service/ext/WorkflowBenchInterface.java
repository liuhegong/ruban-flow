package cn.com.yusys.yusp.workflow.service.ext;

import java.util.List;

import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;

/**
 * 我的控制台
 * @author figue
 *
 */
public interface WorkflowBenchInterface {
	/**
	 * 用户待办信息查询
	 * @param queryModel
	 * @return
	 */
	List<ResultInstanceTodoDto> getInstanceInfoUserTodo(QueryModel queryModel);
}
