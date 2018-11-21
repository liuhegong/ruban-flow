package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;
import cn.com.yusys.yusp.workflow.repository.mapper.WorkflowBenchMapper;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBenchInterface;

/**
 * 我的控制台
 * @author figue
 *
 */
@Service
public class WorkflowBenchServiceImpl implements WorkflowBenchInterface{

	@Autowired
	private WorkflowBenchMapper workflowBenchMapper;
	
	@Override
	public List<ResultInstanceTodoDto> getInstanceInfoTodo(QueryModel queryModel) {
		return workflowBenchMapper.getInstanceInfoTodo(queryModel);
	}
	
}
